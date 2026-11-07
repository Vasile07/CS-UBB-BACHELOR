import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        AutomatFinit af = new AutomatFinit("af.txt");
        af.afiseazaAutomatFinit();

        String secventa = "1564444";
        if(af.eAcceptat(secventa))
            System.out.println("Secventa " + secventa + " e acceptata");
        else
            System.out.println("Secventa " + secventa + " nu e acceptata");

        System.out.println();
        System.out.println("Cel mai lung prefix:");
        System.out.println(af.celMaiLungPrefix(secventa));

        AutomatFinit afCPP = new AutomatFinit("afC++Const.txt");

        afCPP.afiseazaAutomatFinit();

        List<String> constante = List.of("124","023","028","0x1A4F","0X4123A","0x12G","0b101","0b01","0","+12ul","-12ul","12l","0xAbFeul","+0b01lu");
        for(String constanta: constante)
            if(afCPP.eAcceptat(constanta))
                System.out.println("Constanta " + constanta + " e acceptata de AF");
            else
                System.out.println("Constanta " + constanta + " nu e acceptata de AF");



        AutomatFinit afInClass = new AutomatFinit("afInClass.txt");

        afInClass.afiseazaAutomatFinit();

        List<String> secvente = List.of("ab","aa","bb","aabb","aaabb","aaabbbbbbb");
        for(String secventaInClass: secvente)
            if(afInClass.eAcceptat(secventaInClass))
                System.out.println("Secventa " + secventaInClass + " e acceptata de AF");
            else
                System.out.println("Secventa " + secventaInClass + " nu e acceptata de AF");

        System.out.println("Prefix");

        System.out.println(afInClass.celMaiLungPrefix("aabbbbaab"));
    }
}