import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Node class for each node in the linked list
public class Node<Integer extends Comparable<Integer>> {
    int tag; // Present tag
    Node<Integer> next; // Pointer to the next node
    boolean flag; // Mark logical deletion
    Lock lock; // For synchronization

    // Initialize variables and assign the tag number
    Node(int tag) {
        this.tag = tag;
        this.next = null;
        this.flag = false;
        this.lock = new ReentrantLock();
    }

    // Synchronization
    void lock() {
        lock.lock();
    }

    void unlock() {
        lock.unlock();
    }
}
