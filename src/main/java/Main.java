public class Main {

    public static int amount = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread() {

            int threadOwnAmount = 0;

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    amount++;
                    threadOwnAmount++;
                }
                System.out.println(amount);
            }
        };
        Thread thread2 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    amount++;
                }
                System.out.println(amount);
            }
        };

        thread2.start();
        thread1.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {

        }

        System.out.println(amount);
    }
}
