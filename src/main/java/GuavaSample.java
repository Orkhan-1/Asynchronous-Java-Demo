import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Request received");
        String uuid = UUID.randomUUID().toString();
        System.out.println("UUID is generated: " + uuid);

        ExecutorService service = Executors.newFixedThreadPool(10);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<String> listenableFuture = listeningExecutorService
                .submit(() -> saveFile("LargeFile", uuid));
        System.out.println("File stored with id: " + uuid);

        listenableFuture.get();
        listeningExecutorService.shutdown();
        service.shutdown();
    }

    public static String saveFile(String largeFile, String uuid) throws InterruptedException {
        Thread.sleep(800);
        return "File saved";
    }
}
