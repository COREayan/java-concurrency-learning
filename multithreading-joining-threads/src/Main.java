import java.math.BigInteger;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Main 1
        Thread thread = new Thread(new BlockingTask());
//        System.out.println("starting thread : " + thread.getName());
        thread.start();
//        System.out.println("thread started : " + thread.getName());

//        System.out.println("interrupting thread : " + thread.getName());
        thread.interrupt();
//        System.out.println("thread interrupted : " + thread.getName());

        // Main 2
        Thread thread2 = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000")));
        thread2.start();
//        thread2.sleep(5000);
        thread2.interrupt();

        // Main 3
        Thread thread3 = new Thread(new LongComputationTask(new BigInteger("2000000"), new BigInteger("10000000")));
        thread3.setDaemon(true);
        thread3.start();
//        Thread.sleep(100);
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override 
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private  BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}