public class Farmacist extends Thread {
    private final Coada coada;

    private final CoadaCasierie coadaCasierie;

    public Farmacist(Coada coada, CoadaCasierie coadaCasierie) {
        this.coada = coada;
        this.coadaCasierie = coadaCasierie;
    }

    @Override
    public void run() {
        Reteta reteta = null;
        do {
            reteta = coada.dequeue();
            if (reteta != null) {
                System.out.println("Reteta nr " + reteta.getCodReteta() + " a fost preluata de catre farmacistul " + this.threadId());
                Integer valoare = reteta.getMedicamente().stream().map(Medicament::getPret).reduce(Integer::sum).get();
                System.out.println("Reteta nr " + reteta.getCodReteta() + " a fost pregatita si pretul este de " + valoare);
                Integer codReteta = reteta.getCodReteta();
                Inregistrare inregistrare = new Inregistrare(null, valoare, codReteta);
                coadaCasierie.add(inregistrare);
                System.out.println("Reteta nr " + reteta.getCodReteta() + " a fost adaugata in coada de la casierie.");
            }
        } while (reteta != null);
    }
}
