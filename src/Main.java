public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tisch tisch = new Tisch();
        tisch.start();

        Thread.sleep(6000);
        tisch.interrupt();
    }
}