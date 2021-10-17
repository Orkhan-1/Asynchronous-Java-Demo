import java.util.UUID;

public class ThreadSample {

    public static void main(String[] args) {
        System.out.println("Request received");
        String uuid = UUID.randomUUID().toString();
        System.out.println("UUID is generated: "+ uuid);
        saveFile ("LargeFile", uuid);
        System.out.println("File stored with id: "+ uuid);
    }

    private static void saveFile(String largeFile, String uuid) {

        new Thread  (()-> {
            //Save to storage
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("File saved");
        }).start();
    }
}
