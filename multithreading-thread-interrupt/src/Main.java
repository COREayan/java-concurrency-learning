import java.math.BigInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
//        System.out.println("starting thread : " + thread.getName());
        thread.start();
//        System.out.println("thread started : " + thread.getName());

//        System.out.println("interrupting thread : " + thread.getName());
        thread.interrupt();
//        System.out.println("thread interrupted : " + thread.getName());

        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000")));
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
    }
}