import java.util.List;
import java.util.Random;

public class Tisch extends Thread{
    private List<Agent> agenten;
    private List<Smoker> smoker;
    private Object monitor=new Object();

    private int anzahlZigaretten=0;
    public Tisch(){
        starten();
    }
    public void starten(){
        agenten = List.of(new Agent("Agent 1"),new Agent("Agent 2"));

        smoker = List.of(new Smoker("Willi", "Papier", monitor),new Smoker("Marianne", "Tabak", monitor),new Smoker("Klaus", "Streichholz", monitor));
    }
    @Override
    public void run(){
        while(!isInterrupted()){
            //Erhöht die Anzahl der gerauchten Zigaretten
            anzahlZigaretten++;
            Random random = new Random();
            //erzeugt die Smoker und Agenten
            starten();
            synchronized (monitor){
                //Zeigt die wievielte Zigarette geraucht werden soll
                System.err.println("Zigarette "+anzahlZigaretten+" soll geraucht werden!");
                //waehlt einen Agenten aus, der die nächsten Zutaten auf den Tisch legt
                Agent aktuellerAgent = agenten.get(random.nextInt(2));
                //waehlt zufällig einen Raucher aus
                Smoker aktuellerSmoker = smoker.get(random.nextInt(3));
                //generiert die Zutaten, die der Agent auf den Tisch legt
                List<String> zutatenAgent = aktuellerAgent.layOnTable();
                System.err.println(aktuellerAgent.getAgentenName()+" legt "+zutatenAgent.get(0)+" und "+zutatenAgent.get(1)+" auf den Tisch");
                //zeigt welche Zutat der ausgewählte Smoker hat
                String zutatSmoker = aktuellerSmoker.layOnTable();
                System.err.println(aktuellerSmoker.getSmokerName()+" besitzt "+zutatSmoker+"!");

                // sucht den Smoker mit der fehlenden Zutat
                while(zutatenAgent.contains(zutatSmoker)){
                    aktuellerSmoker= smoker.get(random.nextInt(3));
                    zutatSmoker = aktuellerSmoker.layOnTable();
                    System.err.println(aktuellerSmoker.getSmokerName()+" besitzt "+zutatSmoker+", aber nicht die fehlende Zutat!");
                }
                try{
                    //richtiger Smoker gefunden -- Smoker darf rauchen, alle anderen warten
                    aktuellerSmoker.start();
                    monitor.wait();
                }catch(InterruptedException ex){
                    throw new RuntimeException("Fertig geraucht! Es wurden "+anzahlZigaretten+" Zigaretten geraucht.");
                }
                }
            }
        }
    }

