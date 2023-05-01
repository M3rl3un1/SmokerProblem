import java.lang.Thread;

public class Smoker extends Thread {
    private String nameSmoker;
    private Zutat zutat;
    private Zutat fehlendeZutat1;
    private Zutat fehlendeZutat2;

    private BoundedBuffer<Zutat> tisch;
    public Smoker(String name, Zutat zutat, BoundedBuffer<Zutat> tisch){
        this.nameSmoker=name;
        this.zutat=zutat;
        this.tisch = tisch;

    }

    public void run(){
        getFehlendeZutaten();
        while(!Thread.currentThread().isInterrupted()){
            try{
                synchronized (tisch){
                    if(tisch.enthaelt(fehlendeZutat1) && tisch.enthaelt(fehlendeZutat2)){
                        tisch.remove(fehlendeZutat1);
                        tisch.remove(fehlendeZutat2);
                        smoke();
                    }
                }
            }catch(InterruptedException ex){
                this.interrupt();
            }
            synchronized (tisch){
                tisch.notifyAll();
            }
        }
    }

    public void smoke()throws InterruptedException{
        System.err.println(nameSmoker+" hat alle Zutaten und raucht jetzt!");
        System.err.println();
        Thread.sleep(150);
    }

    public void getFehlendeZutaten(){
        if(zutat == Zutat.PAPIER){
            fehlendeZutat1 = Zutat.TABAK;
            fehlendeZutat2 = Zutat.STREICHHOLZ;
        }else if(zutat == Zutat.STREICHHOLZ){
            fehlendeZutat1 = Zutat.TABAK;
            fehlendeZutat2 = Zutat.PAPIER;
        }else{
            fehlendeZutat1 = Zutat.PAPIER;
            fehlendeZutat2 = Zutat.STREICHHOLZ;
        }
    }
}
