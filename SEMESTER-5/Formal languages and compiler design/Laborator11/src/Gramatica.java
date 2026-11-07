import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Pair<E1, E2> {
    private E1 first;
    private E2 second;

    public Pair(E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    public E1 getFirst() {
        return first;
    }

    public E2 getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}

public class Gramatica {
    private final Map<String, List<Pair<List<String>, Integer>>> reguliDeProductie = new HashMap<>();
    private final Map<String, Set<String>> FIRST = new HashMap<>();
    private final Map<String, Set<String>> FOLLOW = new HashMap<>();
    private final Set<String> terminali = new HashSet<>();
    private final Set<String> neterminali = new HashSet<>();
    private final Map<Pair<String, String>, Pair<String, Integer>> tabelDeAnaliza = new HashMap<>();
    private String simbolStart;

    public Gramatica(String fisier) {
        try {
            int nrLinie = 0;
            File myObj = new File(fisier);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String line = myReader.nextLine();
                String[] tokens = line.split("->");
                if (nrLinie == 0)
                    simbolStart = tokens[0];

                List<String> productie = new ArrayList<>();
                if (tokens.length == 1)
                    productie.add("ε");
                else
                    productie.addAll(obtainListOfAtoms(tokens[1]));

                if (reguliDeProductie.containsKey(tokens[0]))
                    reguliDeProductie.get(tokens[0]).add(new Pair<>(productie, nrLinie + 1));
                else {
                    List<Pair<List<String>, Integer>> newList = new ArrayList<>();
                    newList.add(new Pair<>(productie, nrLinie + 1));
                    reguliDeProductie.put(tokens[0], newList);
                }

                Pattern pattern = Pattern.compile("[^A-Zε]");

                for (String atom : productie)
                    if (atom.matches("[^A-Zε]+"))
                        terminali.add(atom);
                neterminali.add(tokens[0]);

                nrLinie++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> obtainListOfAtoms(String token) {

        List<String> atomi = new ArrayList<>();
            Pattern pattern = Pattern.compile("\"([^\"]*)\"|[a-zA-Z]|[()+*={};><!-]|[0-9]");
            Matcher matcher = pattern.matcher(token);
            System.out.println(token);

            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    // If it's a quoted string, add the content inside the quotes
                    atomi.add(matcher.group(1));
                } else {
                    // Otherwise, add the matched atom
                    atomi.add(matcher.group());
                }
            }
        return atomi;
    }


    public boolean esteInFormaNormalaChomsky() {
        boolean formaChomsky = true;
        for (String neterminal : reguliDeProductie.keySet()) {
            for (Pair<List<String>, Integer> productie : reguliDeProductie.get(neterminal))
                if (!((productie.getFirst().size() == 2 && productie.getFirst().get(0).matches("[A-Z]") && productie.getFirst().get(1).matches("[A-Z]")) || (productie.getFirst().size() == 1 && (productie.getFirst().get(0).matches("[a-z]+") || Objects.equals(productie.getFirst().get(0), "ε"))))) {
                    formaChomsky = false;
                }
        }

        return formaChomsky;
    }

    public void genereazaFIRST() {
        boolean existaSchimbariProduse;
        do {
            existaSchimbariProduse = false;
            for (String nonTerminal : reguliDeProductie.keySet()) {
                Set<String> currentFIRST = new HashSet<>();
                if (FIRST.containsKey(nonTerminal))
                    currentFIRST = FIRST.get(nonTerminal);

                for (Pair<List<String>, Integer> pereche : reguliDeProductie.get(nonTerminal)) {
                    List<String> productii = pereche.getFirst();

                    System.out.println(nonTerminal + " " + productii);

                    String firstLiteral = productii.get(0);
                    if (firstLiteral.matches("[A-Z]")) {
                        Set<String> nonTerminalFIRST = new HashSet<>();
                        if (FIRST.containsKey(firstLiteral))
                            nonTerminalFIRST = FIRST.get(firstLiteral);
                        for (String literal : nonTerminalFIRST)
                            if (!currentFIRST.contains(literal)) {
                                currentFIRST.add(literal);
                                existaSchimbariProduse = true;
                            }
                    } else {
                        if (!currentFIRST.contains(firstLiteral)) {
                            currentFIRST.add(firstLiteral);
                            existaSchimbariProduse = true;
                        }
                    }
                }
                FIRST.put(nonTerminal, currentFIRST);
            }
        } while (existaSchimbariProduse);
    }

    public void genereazaFOLLOW() {
        FOLLOW.put(simbolStart, new HashSet<>(Collections.singletonList("$")));
        boolean existaSchimbariProduse;

        do {
            existaSchimbariProduse = false;

            for (String nonTerminal : reguliDeProductie.keySet()) {
                Set<String> currentFOLLOW = new HashSet<>();
                if (FOLLOW.containsKey(nonTerminal))
                    currentFOLLOW = FOLLOW.get(nonTerminal);

                for (String nonTerminalProductie : reguliDeProductie.keySet()) {
                    for (Pair<List<String>, Integer> pereche : reguliDeProductie.get(nonTerminalProductie)) {
                        List<String> productii = new ArrayList<>(pereche.getFirst());
                        int index = productii.indexOf(nonTerminal);
                        while (index != -1) {
                            if (index != productii.size() - 1) {
                                String literal = productii.get(index + 1);
                                if (!reguliDeProductie.containsKey(literal)) {
                                    if (!currentFOLLOW.contains(literal)) {
                                        existaSchimbariProduse = true;
                                        currentFOLLOW.add(literal);
                                    }
                                } else {
                                    Set<String> firstSet = new HashSet<>();
                                    if (FIRST.containsKey(literal))
                                        firstSet = FIRST.get(literal);
                                    for (String first : firstSet) {
                                        if (!first.equals("ε") && !currentFOLLOW.contains(first)) {
                                            existaSchimbariProduse = true;
                                            currentFOLLOW.add(first);
                                        }
                                    }

                                    if (firstSet.contains("ε")) {
                                        boolean schimbari = copiazaFOLLOW(nonTerminalProductie, currentFOLLOW);
                                        if (schimbari) {
                                            existaSchimbariProduse = true;
                                        }
                                    }
                                }
                            } else {

                                boolean schimbari = copiazaFOLLOW(nonTerminalProductie, currentFOLLOW);
                                if (schimbari) {
                                    existaSchimbariProduse = true;
                                }
                            }

                            productii = productii.subList(index + 1, productii.size());
                            index = productii.indexOf(nonTerminal);
                        }
                    }
                }

                if (existaSchimbariProduse) {
                    FOLLOW.put(nonTerminal, currentFOLLOW);
                }


            }
        } while (existaSchimbariProduse);
    }

    private boolean copiazaFOLLOW(String source, Set<String> destinationFOLLOW) {
        Set<String> sourceFOLLOW = new HashSet<>();
        if (FOLLOW.containsKey(String.valueOf(source)))
            sourceFOLLOW = FOLLOW.get(source);

        boolean existaSchimbari = false;

        for (String f : sourceFOLLOW) {
            if (!destinationFOLLOW.contains(f)) {

                destinationFOLLOW.add(f);
                existaSchimbari = true;
            }
        }
        return existaSchimbari;
    }

    public void formeazaTabelDeAnaliza() {
        Set<String> coloane = new HashSet<>(terminali);
        coloane.add("$");

        Set<String> linii = new HashSet<>(neterminali);
        linii.addAll(terminali);
        linii.add("$");

        for (String simbolLinie : linii) {
            for (String simbolColoana : coloane) {
                Pair<String, String> pereche = new Pair<>(simbolLinie, simbolColoana);

                if (Objects.equals(simbolLinie, simbolColoana) && !Objects.equals(simbolLinie, "$"))
                    adaugaInTabelDeAnaliza(pereche, "pop", -1);
                else if (Objects.equals(simbolLinie, simbolColoana) && Objects.equals(simbolLinie, "$"))
                    adaugaInTabelDeAnaliza(pereche, "acc", -1);
                else if (simbolLinie.matches("[A-Z]")) {
                    boolean adaugat = false;
                    for (Pair<List<String>, Integer> productii : reguliDeProductie.get(simbolLinie)) {
                        if (apartineFIRST(productii.getFirst().get(0), simbolColoana)) {
                            String valoare = productii.getFirst().stream()
                                    .reduce("", (partialResult, atom) -> partialResult + '"' + atom + '"');
                            adaugaInTabelDeAnaliza(pereche, valoare, productii.getSecond());
                            adaugat = true;
                        }
                        if (apartineFIRST(productii.getFirst().get(0), "ε") && apartineFOLLOW(simbolLinie, simbolColoana)) {

                            String valoare = productii.getFirst().stream()
                                    .reduce("", (partialResult, atom) -> partialResult + '"' + atom + '"');
                            adaugaInTabelDeAnaliza(pereche, valoare, productii.getSecond());
                            adaugat = true;
                        }
                    }
                    if (!adaugat)
                        adaugaInTabelDeAnaliza(pereche, "err", -1);
                } else {
                    adaugaInTabelDeAnaliza(pereche, "err", -1);
                }
            }
        }


        System.out.printf("%-10s", ""); // Empty space for alignment
        for (String simbolColoana : coloane) {
            System.out.printf("%-10s", simbolColoana); // Column headers
        }
        System.out.println();

        for (String simbolLinie : linii) {
            System.out.printf("%-10s", simbolLinie); // Row header
            for (String simbolColoana : coloane) {
                Pair<String, Integer> valoare = tabelDeAnaliza.get(new Pair<>(simbolLinie, simbolColoana));
                if (valoare.getSecond() != -1) {
                    System.out.printf("%-10s", "(" + valoare.getFirst() + "," + valoare.getSecond() + ")");
                } else {
                    System.out.printf("%-10s", valoare.getFirst());
                }
            }
            System.out.println();
        }
    }

    private boolean apartineFOLLOW(String simbolLinie, String simbolColoana) {
        return FOLLOW.get(simbolLinie).contains(simbolColoana);
    }

    private boolean apartineFIRST(String productie, String simbolColoana) {
        if (productie.equals("ε"))
            return simbolColoana.equals("ε");
        if (productie.matches("[A-Z]")) {
            System.out.println(productie);
            return FIRST.get(productie).contains(simbolColoana);
        }
        else
            return productie.equals(simbolColoana);
    }


    private void adaugaInTabelDeAnaliza(Pair<String, String> pereche, String valoare, Integer nrProductie) {
        System.out.println(pereche);
        System.out.println(valoare + " " + nrProductie);
        Pair<String, Integer> p = new Pair<String, Integer>(valoare, nrProductie);
//        if (tabelDeAnaliza.containsKey(pereche) && !Objects.equals(tabelDeAnaliza.get(pereche), p))
//            throw new RuntimeException("Conflict in tabelul de analiza");

        tabelDeAnaliza.put(pereche, p);
    }

    public void printSets() {
        System.out.println("FIRST sets:");
        for (String nonTerminal : FIRST.keySet()) {
            System.out.println(nonTerminal + ": " + FIRST.get(nonTerminal));
        }
        System.out.println("FOLLOW sets:");
        for (String nonTerminal : FOLLOW.keySet()) {
            System.out.println(nonTerminal + ": " + FOLLOW.get(nonTerminal));
        }
    }

    public List<Integer> analizaSecventa(List<String> secventa) {
        List<Integer> sirProductii = new ArrayList<>();
        secventa.add("$");
        Stack<String> simboluri = new Stack<>();
        simboluri.push("$");
        simboluri.push(simbolStart);
        while (true) {

            String s = simboluri.pop();
            Pair<String, String> pereche = new Pair<>(s, secventa.get(0));
            System.out.println(pereche);
            Pair<String, Integer> valoare = tabelDeAnaliza.get(pereche);
            if (Objects.equals(valoare.getFirst(), "err")) {
                throw new RuntimeException("Secventa nu e acceptata");
            }
            else if (Objects.equals(valoare.getFirst(), "acc"))
                return sirProductii;
            else if (Objects.equals(valoare.getFirst(), "pop"))
                secventa = secventa.subList(1, secventa.size());
            else {
                sirProductii.add(valoare.getSecond());
                String productie = valoare.getFirst();
                String[] parts = productie.split("\"");
                for (int i = parts.length - 1; i >= 0; i--) {
                    if (!parts[i].isEmpty() && !parts[i].equals("ε")) {
                        simboluri.push(parts[i]);
                    }
                }
            }
        }
    }
}
