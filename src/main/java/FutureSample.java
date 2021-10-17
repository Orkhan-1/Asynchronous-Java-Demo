import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureSample implements Callable<String> {

    public static void main(String[] args) throws Exception {
        System.out.println("Request received");
        String uuid = UUID.randomUUID().toString();
        System.out.println("UUID is generated: "+ uuid);

        Callable<String> callable = new FutureSample();
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Future<String> future = service.submit(callable);
            futures.add(future);
        }

        for (int i=0; i<10; i++) {
            System.out.println(futures.get(i).get());
        }

        Future<String> future = service.submit(callable);
        System.out.println(future.get());
        System.out.println("File stored with id: "+ uuid);
        service.shutdown();
    }


    @Override
    public String call() throws Exception {
        Thread.sleep(800);
        return "File saved";
    }
}
