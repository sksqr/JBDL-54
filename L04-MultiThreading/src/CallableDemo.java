
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CallableTask callableTask = new CallableTask();
        List<Callable<String>> callableList = new ArrayList<>();
        for(int i=0; i<10; i++){
            callableList.add(callableTask);
        }
        List<Future<String>> futureList = null;
        try {
            futureList = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(Future future : futureList){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

//        Future<String> future = executorService.submit(callableTask);
//        System.out.println("IsDone:"+future.isDone());
//        try {
//            System.out.println(future.get());
//            System.out.println("IsDone:"+future.isDone());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        executorService.shutdown();
        System.out.println("Done");
    }
}
