import java.lang.Thread;

public class Smoker extends Thread {
    private String name;
    private String zutat;
    private Object monitor;
    public Smoker(String name, String zutat, Object monitor){
        this.name=name;
        this.zutat=zutat;
        this.monitor=monitor;
    }

    public void run(){
        try{
            willRauchen();
            smoke();
        }catch(InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }

    public void smoke()throws InterruptedException{
        synchronized (monitor){
            int sleepTime = (int) (1000*Math.random());
            System.err.println(name+" raucht!" );
            Thread.sleep(sleepTime);
            System.err.println(name+" ist fertig mit rauchen!");
            monitor.notifyAll();
        }

    }
    public void willRauchen(){
        //gibt wunsch auf Konsole aus
        System.err.println(name+" möchte rauchen!");
    }
    public String layOnTable(){
        return zutat;
    }

    public String getSmokerName(){
        return name;
    }
}
