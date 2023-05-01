import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tisch <Zutat> tisch = new Tisch<>(2);


        Agent agent1 = new Agent("Agent1", tisch);
        Agent agent2 = new Agent("Agent2", tisch);

        Smoker smoker1 = new Smoker("Marianne", Zutat.TABAK, tisch);
        Smoker smoker2 = new Smoker("Horst", Zutat.PAPIER, tisch);
        Smoker smoker3 = new Smoker("Manu", Zutat.STREICHHOLZ,tisch);

        agent1.start();
        agent2.start();
        smoker1.start();
        smoker2.start();
        smoker3.start();

        Thread.sleep(6000);


        agent1.interrupt();
        agent2.interrupt();
        smoker1.interrupt();
        smoker2.interrupt();
        smoker3.interrupt();

    }
}