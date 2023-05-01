public interface BoundedBuffer <E>{
    //Fuegt ein Item in den Puffer hinzu
    public void enter(E item) throws InterruptedException;
    //Entnimmt ein Item aus dem Puffer
    public E remove(E item) throws InterruptedException;
    //Schaut ob abgefragtes Element im Puffer enthalten ist
    public boolean enthaelt(E item);
}
