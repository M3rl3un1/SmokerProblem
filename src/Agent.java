import java.lang.Thread;
import java.util.Random;

public class Agent extends Thread{
    private Zutat zutat1;
    private Zutat zutat2;
    private Zutat zutat3;
    private String nameAgent;
    private BoundedBuffer<Zutat> tisch;
    private Random random;

    public Agent (String name, BoundedBuffer<Zutat> tisch){
        this.nameAgent = name;
        this.tisch = tisch;
        zutat1 = Zutat.PAPIER;
        zutat2 = Zutat.TABAK;
        zutat3 = Zutat.STREICHHOLZ;
        random = new Random();
    }
    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                synchronized(tisch) {
                    int randomZahl = random.nextInt(3);
                    if(randomZahl ==0){
                        tisch.enter(zutat1);
                        tisch.enter(zutat2);
                        System.err.println(nameAgent+ " legt "+ zutat1+ " und "+zutat2+ " auf den Tisch!");
                        Thread.sleep(150);
                        tisch.wait();
                    }else if(randomZahl == 1){
                        tisch.enter(zutat2);
                        tisch.enter(zutat3);
                        System.err.println(nameAgent+ " legt "+ zutat2+ " und "+zutat3+ " auf den Tisch!");
                        Thread.sleep(150);
                        tisch.wait();
                    }else{
                        tisch.enter(zutat1);
                        tisch.enter(zutat3);
                        System.err.println(nameAgent+ " legt "+ zutat1+ " und "+zutat3+ " auf den Tisch!");
                        Thread.sleep(150);
                        tisch.wait();
                    }
                }
            }catch(InterruptedException ex){
                this.interrupt();
            }
        }
    }


    public String getAgentenName(){
        return nameAgent;
    }

}
