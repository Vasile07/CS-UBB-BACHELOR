import java.util.Random;

public class Membru extends Thread{
    private final Integer indexUser;
    private final Integer numarTranzactii;
    private final ListaTranzactii listaTranzactii;
    private final Cont cont;

    public Membru(Integer indexUser, Integer numarTranzactii, ListaTranzactii listaTranzactii, Cont cont) {
        this.indexUser = indexUser;
        this.numarTranzactii = numarTranzactii;
        this.listaTranzactii = listaTranzactii;
        this.cont = cont;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < numarTranzactii ; i ++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Random random = new Random();
            TipTranzactie tip = random.nextFloat() < 0.5 ? TipTranzactie.DEPUNERE : TipTranzactie.RETRAGERE;
            TipValuta tipValuta = random.nextFloat() < 0.5 ? TipValuta.LEI : TipValuta.EURO;
            Integer suma = random.nextInt(1, 1000 + 1);
            synchronized (listaTranzactii){
                switch (tip) {
                    case DEPUNERE -> {
                        Integer sold;
                        if (tipValuta == TipValuta.EURO) {
                            cont.depuneEuro(suma);
                            sold = cont.soldEuro();
                        } else {
                            cont.depuneLei(suma);
                            sold = cont.soldLei();
                        }
                        listaTranzactii.adaugaTranzactie(new Tranzactie(indexUser, tip, tipValuta, suma, sold));
                    }
                    case RETRAGERE -> {
                        try {
                            Integer sold;
                            if (tipValuta == TipValuta.EURO) {
                                cont.retrageEuro(suma);
                                sold = cont.soldEuro();
                            } else {
                                cont.retrageLei(suma);
                                sold = cont.soldLei();
                            }
                            listaTranzactii.adaugaTranzactie(new Tranzactie(indexUser, tip, tipValuta, suma, sold));
                        }catch (RuntimeException e){
                            System.err.println(e.getMessage());
                        }
                    }
                }

            }
        }
    }

}
