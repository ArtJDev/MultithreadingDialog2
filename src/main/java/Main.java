import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<MyCallable> myCallableList = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            myCallableList.add(new MyCallable("поток " + i));
        }

        final List<Future<Integer>> tasks = pool.invokeAll(myCallableList);

        for (Future<Integer> future : tasks) {
            System.out.println("Количество напечатанных сообщений = " + future.get());
        }

        System.out.println();
        System.out.println("Количество напечатанных сообщений = " + pool.invokeAny(myCallableList));

        pool.shutdown();
    }
}