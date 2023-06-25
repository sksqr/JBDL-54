public class MemoryIssueDemo {

    public static void main(String[] args) {

        Signal signal = new Signal();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!signal.isStop()){
                    System.out.println("Executing in thread : "+Thread.currentThread().getName());
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                signal.setStop(true);
                System.out.println("Setting to true in "+Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done");

    }
}
