/*
 * File: LinkedList.java
 * Author: Marlon Grandy
 * Date: 03/7/2022
 * Creates a linked list using inner node and iterator classes. Also, creates any method
 * used to manipulate a linked list: adding, removing, getters and setters. 
 */

//Import statements
import java.util.Iterator; // defines the Iterator interface
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;

public class LinkedList<T> implements Iterable<T> {
    private Node head;
    private int size;

    public LinkedList() {// constructor that initializes the fields so it is an empty list.
        size = 0;
        head = null;
    }

    public void clear() {// resets the fields so it is an empty list.
        size = 0;
        head = null;
    }

    public int size() {// returns the size of the list.
        return size;
    }

    public void addFirst(T item) {// inserts the item at the beginning of the list of nodes.
        Node first = new Node(item);
        first.setNext(head);
        head = first;
        size++;
    }

    public void addLast(T item) { // inserts the item at the end of the list of nodes.

        Node newLast = new Node(item);
        newLast.setNext(null);
        Node trav = head;
        if (head == null) {
            head = newLast;
        } else {
            while (trav.next != null) {
                trav = trav.next;

            }
            trav.next = newLast;
        }

        size++;
    }

    public void add(int index, T item) { // adds a node with data item to index
        Node newMid = new Node(item);
        Node prev = head;
        int count = 0;

        if (head == null) {
            head = newMid;
            size++;
        } else if (index == 0) {
            addFirst(item);
        } else if (index == size - 1) {
            addLast(item);
        }

        else {
            while (prev != null) {
                if (count == index - 1) {
                    Node last = prev.next;
                    prev.setNext(newMid);
                    newMid.setNext(last);
                    size++;
                    break;

                }
                count++;
                prev = prev.next;

            }

        }

    }

    public T remove(int index) { //removes a node from the linked list 
        Node prev = head;
        int curIndex = 0;
        T info = null;
        if (index == 0) { // if removing the first node
            T newData = head.getThing();
            head = prev.next;
            size--;
            curIndex = 0;
            return newData;
        } else if (index + 1 == size) { // if removing the last node
            while (curIndex < index - 1) {
                prev = prev.getNext();
                curIndex++;
            }
            T item2 = prev.next.getThing();
            prev.setNext(null);
            size--;
            curIndex = 0;
            return item2;
        } else {
            while (prev != null) {
                if (curIndex == index - 1) { // prev node is now equal to the node before the removal node
                    info = prev.next.getThing();
                    prev.setNext(prev.getNext().getNext()); // sets the node pointer to node after removal node
                    size--;
                }
                prev = prev.next;
                curIndex++;
            }
            return info;
        }
    }

    public ArrayList<T> toArrayList() {// converts the linked list to an arrayList, returns ArrayLis
        Node trav = head;
        ArrayList<T> dataArray = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            /*
             * iterates through linked list using a traversing varaible and
             * adds thing to ArrayListt
             */
            dataArray.add(trav.getThing());
            trav = trav.next;
        }
        return dataArray;
    }

    public ArrayList<T> toShuffledList() { // returns shuffled ArrayList from linked list things
        Random rand = new Random();
        Node trav = head;
        ArrayList<T> dataArray = new ArrayList<T>();
        ArrayList<T> shuffledArray = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            /*
             * iterates through linked list using a traversing varaible and
             * adds thing to ArrayListt
             */
            dataArray.add(trav.getThing());
            trav = trav.next;
        }
        int count = dataArray.size();
        for (int i = 0; i < count; i++) {
            /*
             * iterates through linked list using a traversing varaible and
             * adds thing to ArrayListt
             */
            int n = rand.nextInt(dataArray.size());
            shuffledArray.add(dataArray.get(n));
            dataArray.remove(n);
        }
        return shuffledArray;
    }

    @Override
    public Iterator<T> iterator() {// return a new LLIterator with head passed to the constructor
        return new LLIterator(head);
    }

    private class Node {
        private Node next; // next pointer
        private T type; // data asigner

        public Node(T item) { // a constructor that initializes next to null and the container field to item.
            this.next = null;
            this.type = item;
        }

        public T getThing() { // returns the value of the container field.
            return type;

        }

        public void setNext(Node n) { // sets next to the given node.
            next = n;

        }

        public Node getNext() { // returns the next field.
            return next;

        }

    }

    private class LLIterator implements Iterator<T> {
        Node nextNode; // next node during traversal

        public LLIterator(Node head) {// the constructor for the LLIterator given the head of a list.
            nextNode = head;
        }

        @Override
        public boolean hasNext() {// returns true if there are still values to traverse
            return nextNode != null;
        }

        @Override
        public T next() {// returns the next item in the list, which is the item contained in the current
                         // node.
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T info = nextNode.getThing();
            nextNode = nextNode.getNext();
            return info;

        }

    }

}
