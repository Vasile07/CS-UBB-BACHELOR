package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static List<String> readFromFile(String filename){
        List<String> lines = new ArrayList<>();
        try{
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()){
                String line = myReader.nextLine();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    private static Map<String, Integer> readMapFromFile(String filename){
        Map<String, Integer> atomi = new HashMap<>();
        try{
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()){
                String line = myReader.nextLine();
                String[] splits = line.split("~");
                atomi.put(splits[0],Integer.parseInt(splits[1]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return atomi;
    }

    private final static String ATOMS = "\\b(main\\(\\)|read|write|if|else|while|int|bool|char|double|struct)\\b|\\b\\d+(\\.\\d+)?\\b|==|!=|&&|\\|\\||[{};=+.,\\-/%*<>()]|\\b[a-zA-Z]+\\b";
    private final static List<String> CuvinteRezervate = Arrays.asList("main","read","write","if","else","while","int","bool","char","double","struct");
    private final static List<String> Operatori = Arrays.asList("!=","==","&&","||","{","}",";","=","+",".",",","-","/","%","*","<",">","(",")");
    private final static String TOKENS = "\\S+";
    public static List<String> extractAtoms(List<String> data) throws EroareLexicala {
        List<String> atoms = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        AutomatFinit automatFinitID = new AutomatFinit("afID.txt");
        AutomatFinit automatFinitConstante = new AutomatFinit("afConstante.txt");

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            int lastMatchEnd = 0;

            for (int j = 0; j < line.length(); ) {
                char currentChar = line.charAt(j);
                StringBuilder token = new StringBuilder();

                if (Character.isWhitespace(currentChar)) {
                    j++;
                    continue;
                }
                boolean eNumar = Character.isDigit(currentChar);

                if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
                    while (j < line.length() && (Character.isLetterOrDigit(line.charAt(j)) || (line.charAt(j) == '.') && eNumar)) {
                        token.append(line.charAt(j));
                        j++;
                    }
                    String tokenStr = token.toString();
                    if (CuvinteRezervate.contains(tokenStr) || automatFinitID.eAcceptat(tokenStr) || automatFinitConstante.eAcceptat(tokenStr)) {
                        atoms.add(tokenStr);
                    } else {
                        errors.add("Line " + (i + 1) + ": Invalid token: " + tokenStr);
                    }
                    lastMatchEnd = j;
                    continue;
                }

                boolean matched = false;
                for (String op : Operatori) {
                    if (line.startsWith(op, j)) {
                        atoms.add(op);
                        j += op.length();
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    token.append(currentChar);
                    j++;
                    errors.add("Line " + (i + 1) + ": Invalid token: " + token.toString());
                }

                lastMatchEnd = j;
            }

            if (lastMatchEnd < line.length()) {
                String invalidSegment = line.substring(lastMatchEnd).trim();
                if (!invalidSegment.isEmpty()) {
                    errors.add("Line " + (i + 1) + ": Invalid token: " + invalidSegment);
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new EroareLexicala(errors);
        }

        return atoms;
    }


    private final static String IDS = "\\b[a-zA-Z]+\\b";
    private final static String CONST = "\\b\\d+(\\.\\d+)?\\b";
    private final static String REZERVED = "(main\\(\\)|write|read|if|else|while|int|bool|char|double|struct)|==|!=|&&|\\|\\||[{};=+.,\\-/%*<>()]";

    private static Map<String, Integer> makeFIPandTS(List<String>  atoms) throws EroareLexicala{
        List<FIPEntity> FIP = new ArrayList<>();
        ListaOrdonata<String> TS = new ListaOrdonata<>(atoms.size());
        List<String> ConstIds = new ArrayList<>();

        Map<String, Integer> codeTable = readMapFromFile("atomi.csv");
        codeTable.put("ID",0);
        codeTable.put("CONST",1);

        for(String atom : atoms){
            if(Pattern.matches(REZERVED,atom)){
                FIPEntity fipEntity = new FIPEntity();
                fipEntity.setCod(codeTable.get(atom));
                FIP.add(fipEntity);
            } else if(Pattern.matches(CONST,atom)) {
                TS.add(atom);

                FIPEntity fipEntity = new FIPEntity();
                fipEntity.setCod(1);
                FIP.add(fipEntity);
                ConstIds.add(atom);
            }else{
                TS.add(atom);

                FIPEntity fipEntity = new FIPEntity();
                fipEntity.setCod(0);
                FIP.add(fipEntity);

                ConstIds.add(atom);
            }

        }

        int i = 0 ;
        for(FIPEntity fipEntity : FIP)
            if(fipEntity.getCod() == 0 || fipEntity.getCod() == 1) {
                fipEntity.setPozInTS(TS.indexOf(ConstIds.get(i)) + 1);
                i++;
            }

        FileStore.writeTSToFile(TS);
        FileStore.writeFIPToFile(FIP);

        return codeTable;
    }

    private static Map<String, Integer> makeFIPandTSWithAF(List<String>  atoms) throws EroareLexicala{
        List<FIPEntity> FIP = new ArrayList<>();
        ListaOrdonata<String> TS = new ListaOrdonata<>(atoms.size());
        List<String> ConstIds = new ArrayList<>();

        Map<String, Integer> codeTable = readMapFromFile("atomi.csv");
        codeTable.put("ID",0);
        codeTable.put("CONST",1);

        AutomatFinit afID = new AutomatFinit("afID.txt");
        AutomatFinit afConstante = new AutomatFinit("afConstante.txt");

        for(String atom : atoms){
            if(Pattern.matches(REZERVED,atom)){
                FIPEntity fipEntity = new FIPEntity();
                fipEntity.setCod(codeTable.get(atom));
                FIP.add(fipEntity);
            } else if(afConstante.eAcceptat(atom)) {
                TS.add(atom);

                FIPEntity fipEntity = new FIPEntity();
                fipEntity.setCod(1);
                FIP.add(fipEntity);
                ConstIds.add(atom);
            }else if (afID.eAcceptat(atom)){
                TS.add(atom);

                FIPEntity fipEntity = new FIPEntity();
                fipEntity.setCod(0);
                FIP.add(fipEntity);

                ConstIds.add(atom);
            }

        }

        int i = 0 ;
        for(FIPEntity fipEntity : FIP)
            if(fipEntity.getCod() == 0 || fipEntity.getCod() == 1) {
                fipEntity.setPozInTS(TS.indexOf(ConstIds.get(i)) + 1);
                i++;
            }

        FileStore.writeTSToFile(TS);
        FileStore.writeFIPToFile(FIP);

        return codeTable;
    }

    public static void main(String[] args) {
        try{
            List<String> lines = readFromFile("code.txt");
            List<String> atoms = extractAtoms(lines);
//        atoms.forEach(System.out::println);
            var codeTable = makeFIPandTSWithAF(atoms);
            codeTable.forEach((s, integer) -> System.out.println(s + " : " + integer));
        }catch (EroareLexicala eroareLexicala){
            System.out.println(eroareLexicala.getMessage());
        }

    }
}