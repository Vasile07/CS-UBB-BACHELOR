import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
/*
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("gramatica.txt");
        boolean chomsky = gramatica.esteInFormaNormalaChomsky();
        if(chomsky)
            System.out.println("Gramatica se afla in forma Chomsky");
        else
            System.out.println("Gramatica nu se afla in forma Chomsky");

        gramatica.genereazaFIRST();
        gramatica.genereazaFOLLOW();
        gramatica.printSets();
        gramatica.formeazaTabelDeAnaliza();
        List<String> secventa = new ArrayList<>();
        secventa.add("numar");
        secventa.add("+");
        secventa.add("numar");
        secventa.add("+");

        List<Integer> sirProductii = gramatica.analizaSecventa(secventa);
        System.out.printf("Secventa %s e acceptata  de automat si sirul de productii este:%n", secventa.subList(0,secventa.size()-1));
        for(Integer indiceProductie : sirProductii)
            System.out.print(indiceProductie + " ");
    }
*/

    private final static String ATOMS = "\\b(main\\(\\)|main|read|write|if|else|while|int|bool|char|float|struct)\\b|\\b\\d\\b|==|!=|&&|\\|\\||[{};=+.,\\-/%*<>()]|[a-zA-Z]";

    private final static List<String> Operatori = Arrays.asList("!=", "==", "&&", "||", "{", "}", ";", "=", "+", ".", ",", "-", "/", "%", "*", "<", ">", "(", ")");

    private static List<String> readFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String line = myReader.nextLine();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static List<String> extractAtoms(List<String> data) {
        List<String> atoms = new ArrayList<>();
        List<String> errors = new ArrayList<>();


        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            Pattern regex = Pattern.compile(ATOMS);

            // Create a matcher
            Matcher matcher = regex.matcher(line);

            while (matcher.find()) {
                atoms.add(matcher.group());
            }

        }
        if (!errors.isEmpty()) {
            throw new RuntimeException(errors.get(0));
        }

        return atoms;
    }


    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("cod.txt");

        gramatica.genereazaFIRST();
        gramatica.genereazaFOLLOW();
        gramatica.printSets();

        gramatica.formeazaTabelDeAnaliza();

        List<String> atoms = extractAtoms(readFromFile("exempluCod.txt"));
//        System.out.println(atoms);
        System.out.println(gramatica.analizaSecventa(atoms));
    }
}