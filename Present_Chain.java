// Class that implements the linked list using nodes
public class PresentChain<Integer extends Comparable<Integer>> {
    
    private Node<Integer> head;

    // Initialize the head and the head.next to values that would not be included in the actual range
    public PresentChain() {
        this.head = new Node<>(-5000001);
        this.head.next = new Node<>(500001);
    }

    private boolean check(Node<Integer> pred, Node<Integer> curr) {
        return !pred.flag && !curr.flag && pred.next == curr;
    }

    public boolean insert(int tag) {

        while (true) {
            Node<Integer> pred = this.head;
            Node<Integer> curr = head.next;

            while (curr.tag < tag) {
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            try {
                curr.lock();
                try {
                    if (check(pred, curr)) {
                        if (curr.tag == tag) {
                            return false;
                        } else {
                            Node<Integer> node = new Node<>(tag);
                            node.next = curr;
                            pred.next = node;
                            return true;
                        }
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }


    public boolean remove (int tag) {
        while (true) {
            Node<Integer> pred = this.head;
            Node<Integer> curr = head.next;

            while (curr.tag < tag) {
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            try {
                curr.lock();
                try {
                    if (check(pred, curr)) {
                        if (curr.tag != tag) {
                            return false;
                        }
                    } else {
                        curr.flag = true;
                        pred.next = curr.next;
                        return true;
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }


    public boolean contains(int tag) {
		Node<Integer> curr = this.head;
		while (curr.tag < tag)
			curr = curr.next;
		return curr.tag == tag && !curr.flag;
	}


    @Override
	public synchronized String toString() {
		Node<Integer> tmp = head.next;
		StringBuilder sb = new StringBuilder("");
		while (tmp != null) {
			sb.append(tmp.tag + ", ");
			tmp = tmp.next;
		}
		return sb.toString();
	}
    
}