import java.util.*;

public class Tisch <E> implements BoundedBuffer<E> {
    private int maxAnzahlZutaten;//Maximale Anzahl von Zutaten auf dem Tisch

    private LinkedList<E> zutatenListe;//Liste der Zutaten auf dem Tisch

    public Tisch(int maxAnzahlZutaten){
        this.maxAnzahlZutaten= maxAnzahlZutaten;
        this.zutatenListe = new LinkedList<E>();
    }

    //Wird von den Agenten (Erzeugern) aufgerufen
    @Override
    public synchronized void enter(E item) throws InterruptedException {
        //Wenn schon alle Zutaten auf dem Tisch liegen, warten!
        while(zutatenListe.size() == maxAnzahlZutaten){
            this.wait();
        }
        //Hinzufügen von Zutaten
        zutatenListe.add(item);
        System.err.println("                   Es wurde eine Zutat von "+Thread.currentThread().getName()+" auf den Tisch gelegt! Es liegen jetzt "+ zutatenListe.size()+" Zutaten auf dem Tisch.");

        //Alle Threads wieder aufwecken. Wird am this-Objekt aufgerufen, weil der Tisch der Puffer(Monitor) ist
        this.notifyAll();
    }

    //Wird von den Smokern aufgerufen
    @Override
    public synchronized E remove(E gebrauchteZutat) throws InterruptedException {
        E removedItem;

        //Warten wenn keine Zutaten auf dem Tisch sind
        while(zutatenListe.size()==0){
            this.wait();
        }

        // Wunsch aus der Liste entfernen
        removedItem = zutatenListe.remove(zutatenListe.indexOf(gebrauchteZutat));
        System.err.println("                   Es wurde eine Zutat von "+Thread.currentThread().getName()+" vom Tisch genommen.");

        //Alle anderen Threads aufwecken
        this.notifyAll();
        return removedItem;
    }

    @Override
    public boolean enthaelt(E item) {
        if(zutatenListe.contains(item)){
            return true;
        }else{
            return false;
        }
    }
}

