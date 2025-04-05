import java.util.*;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final int NUMBER_OF_TASKS = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press enter to start");
        sc.nextLine();
        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);

        long start = System.currentTimeMillis();
        performTasks();
        System.out.println();
    }

    private static void performTasks() {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i=0; i<NUMBER_OF_TASKS; i++) {
                executorService.submit(() -> blockingIoOperation());
            }
        }
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    blockingIoOperation();
//                }
//            });
//        }
    }

    private static void blockingIoOperation() {
        System.out.println("Executing a blocking from thread : " + Thread.currentThread());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}