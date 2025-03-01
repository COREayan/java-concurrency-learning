package example2;

public class Main {
    public static void main(String[] args) {
        
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            // Code that executes on the new thread
            System.out.println("Hello from " + Thread.currentThread().getName());
        }
    }
}
