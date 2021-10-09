import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkedBinaryHeap<T extends Comparable<T>> implements MaxPQ<T> {
    private Node root;
    private int count;

    public LinkedBinaryHeap() {
        root = null;
        count = 0;
    }

    private class Node {
        T info;
        Node left, right, parent;

        private Node(T info, Node left, Node right, Node parent) {
            this.info = info;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public String toString() {
            String rootString = this.info.toString();
            String leftString = " ";
            String rightString = " ";

            if (this.left != null) {
                leftString = this.left.toString();
            }
            if (this.right != null) {
                rightString = this.right.toString();
            }
            return rootString + leftString + rightString;
        }
    }


    public void enqueue(T info) {
        if (root == null) {
            root = new Node(info, null, null, null);
            count++;
            return;
        }
        int target = count + 1;
        List<Integer> path = new ArrayList<Integer>();
        int nextTarget = target;
        while (nextTarget != 1) {
            int a = (int) Math.floorDiv(nextTarget, 2);
            path.add(a);
            nextTarget = a;
        }

        Node tempNode = root;

        Collections.reverse(path);
        path.remove(0);

        for (int item : path) {
            if (item % 2 == 0) {
                tempNode = tempNode.left;
            } else {
                tempNode = tempNode.right;
            }
        }

        Node newNode = new Node(info, null, null, tempNode);
        if (target % 2 == 0) {
            tempNode.left = newNode;
        } else {
            tempNode.right = newNode;
        }

        swim(newNode);
        count++;
    }

    public T dequeue() {

        Node tempNode = root;

        if (count == 0) {
            throw new java.util.NoSuchElementException("Binary Heap Underflow");
        }

        findNode();
        sink(root);

        exch(root, tempNode);


    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    boolean less(Node a, Node b) {
        return a.info.compareTo(b.info) < 0;
    }

    private void exch(Node a, Node b) {
        T temp = a.info;
        a.info = b.info;
        b.info = temp;
    }

    private void swim(Node a) {
        while (a.parent != null && less(a.parent, a)) {
            exch(a.parent, a);
            a = a.parent;
        }
    }

    private void sink(Node a) {
        while (a.left != null && a.right != null) {
            if (less(a.left, a.right)) {
                if (less(a, a.right)) {
                    exch(a.right, a);
                    a = a.right;
                }
            }
            if (less(a.right, a.left)) {
                if (less(a, a.left)) {
                    exch(a.left, a);
                    a = a.left;
                }
            }
        }
    }

    private Node findNode() {

        int target = count;
        List<Integer> path = new ArrayList<Integer>();
        int targetDequeue = target;
        while (targetDequeue != 1) {
            int a = (int) Math.floorDiv(targetDequeue, 2);
            path.add(a);
            targetDequeue = a;
        }

        Node nodeFinder = root;

        Collections.reverse(path);
        path.remove(0);

        for (int item : path) {
            if (item % 2 == 0) {
                nodeFinder = nodeFinder.left;
                nodeFinder.left = root;
            } else {
                nodeFinder = nodeFinder.right;
                nodeFinder.right = root;
            }
        }
        return nodeFinder;

    }

    public String toString() {
        if (root == null) {
            return " ";
        }
        return root.toString();

    }

    public static void main(String[] args) {
        MaxPQ<String> list = new LinkedBinaryHeap<String>();
        String[] animals = {"dog", "cat", "goat", "cow"};
        for (String animal : animals) {
            list.enqueue(animal);
            System.out.format("%s", list.toString());
        }
    }

}
