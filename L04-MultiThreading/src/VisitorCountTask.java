public class VisitorCountTask implements Runnable {
    private int count=0;

    public synchronized void incrementCount(){
            count++;
    }
    @Override
    public void run() {
        incrementCount();
    }

    public int getCount() {
        return count;
    }
}
