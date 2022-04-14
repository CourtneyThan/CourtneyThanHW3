import java.util.*;
import java.lang.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Main {
    static int gifts = 500000;
    private static PresentChain<Integer> linked_list = new PresentChain<>();

    // Shuffle an array list of integer to get an unordered bag
    // Create an array of 4 threads for each servant
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> numbers = new ArrayList<>();
        
        for (int i = 1; i <= gifts; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        boolean flag = true;
        Thread[] threads = new Thread[4];

        while (numbers.size() > 0) {
            int num_one = numbers.get(0);
            int num_two = numbers.get(1);

            if (flag) {
                // Insertion
                threads[0] = new Thread(new Threading(num_one));
                threads[1] = new Thread(new Threading(num_two));

                // This is where removal would take place but it does not work
                threads[2] = new Thread(new Threading(0));
                threads[3] = new Thread(new Threading(0));

                threads[0].start();
                threads[0].join();
                threads[1].start();
                threads[1].join();
            } else {
                threads[0] = new Thread(new Threading(0));
                threads[1] = new Thread(new Threading(0));

                // Insertion
                threads[2] = new Thread(new Threading(num_one));
                threads[3] = new Thread(new Threading(num_two));

                threads[2].start();
                threads[2].join();
                threads[3].start();
                threads[3].join();
            }

            numbers.remove(0);
            numbers.remove(0);

            // Alternate the workers
            flag = !flag;
            }
        }

        // System.out.println(linked_list);


    public static class Threading extends Thread {
        int tag;
        public Threading(int tag) {
            this.tag = tag;
        }
        
        public void run() {
            linked_list.insert(tag);
            System.out.println(tag);
        }
    }
}