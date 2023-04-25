import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent extends Thread{
    private String name;
    public Agent (String name){
        this.name=name;
    }
    @Override
    public void run(){}

    public List<String> layOnTable(){
        int rand = new Random().nextInt(3);
        //erzeugt Liste, die alle Zutaten enthält
        List<String> zutaten = new ArrayList<>(List.of("Tabak", "Papier", "Streichholz"));
        //löscht eine der Zutaten
        zutaten.remove(rand);
        //gibt 2 zufällige Zutaten zurück
        return zutaten;
    }

    public String getAgentenName(){
        return name;
    }

}
