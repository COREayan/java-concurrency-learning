import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {
    private static final int NUMBER_OF_VIRTUAL_THREADS = 200;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> System.out.println("Inside thread: " + Thread.currentThread());
//        Thread platformThread = new Thread(runnable);
//        Thread platformThread = Thread.ofPlatform().unstarted(runnable);

//        platformThread.start();
//        platformThread.join();

//        Thread virtualThread = Thread.ofVirtual().unstarted(runnable);
//
//        virtualThread.start();
//        virtualThread.join();

        List<Thread> virtualThreads = new ArrayList<>();

        for (int i=0; i<NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread virtualThread = Thread.ofVirtual().unstarted(runnable);
            virtualThreads.add(virtualThread);
        }

        for (Thread virtualThread : virtualThreads) {
            virtualThread.start();
        }

        for (Thread virtualThread : virtualThreads) {
            virtualThread.join();
        }
    }
}
