import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Counter {
    private AtomicInteger Bonifico;
    private AtomicInteger Accredito;
    private AtomicInteger Bollettino;
    private AtomicInteger F24;
    private AtomicInteger PagoBancomat;

    public Counter(){
        Bonifico = new AtomicInteger();
        Accredito = new AtomicInteger();
        Bollettino = new AtomicInteger();
        F24 = new AtomicInteger();
        PagoBancomat = new AtomicInteger();
    }

    public void increment(String causale){
        switch (causale) {
            case "Bonifico":
                Bonifico.set(Bonifico.get() + 1);
                break;
            case "Accredito":
                Accredito.set(Accredito.get() + 1);
                break;
            case "Bollettino":
                Bollettino.set(Bollettino.get() + 1);
                break;
            case "F24":
                F24.set(F24.get() + 1);
                break;
            case "PagoBancomat":
                PagoBancomat.set(PagoBancomat.get() + 1);
                break;
        }
    }

    public void printResults(){
        System.out.println("Numero di causali registrate, raccolte per tipo:");
        System.out.println("Bonifico: " + Bonifico.get());
        System.out.println("Accredito: " + Accredito.get());
        System.out.println("Bollettino: " + Bollettino.get());
        System.out.println("F24: " + F24.get());
        System.out.println("PagoBancomat: " + PagoBancomat.get());

    }



}
