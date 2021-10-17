import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletebleFuturSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i=0; i<10; i++) {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Request received")
                    .thenApplyAsync((request) -> {
                        System.out.println(request);
                        return UUID.randomUUID().toString();
                    }).thenApplyAsync((uuid) ->
                            saveFile("LargeFile", uuid)

                    ).thenApplyAsync(uuid -> {
                        System.out.println("UUID is generated: " + uuid);
                        return "result";
                    });
            completableFuture.get();
        }

    }

    private static String saveFile(String largeFile, String uuid) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uuid;
    }
}
