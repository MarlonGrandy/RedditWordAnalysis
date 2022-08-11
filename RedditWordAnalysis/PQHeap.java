
/*
Marlon Grandy
4/18/2022
Professor Harper
PQHeap.java
project 9
*/
import java.util.ArrayList;
import java.util.Comparator;

public class PQHeap<T> {
    private int size;
    private Comparator<T> comp;
    Object[] data;

    public PQHeap(Comparator<T> comp, int initialSize) {
        this.comp = comp;
        this.size = -1;
        this.data = new Object[500];
        // data[0] = new KeyValuePair<Integer, Float>(10000000, (float) 1000000000.0);
        // data[0] = 100000000;
    }

    public int size() { //returns the siuze of the heap 
        return this.size;
    }

    // Inserts a new element to max heap
    public void add(T obj) {

        if (size == data.length - 1) { //resizes of array is full
            resize();
        }
        if (size == -1) { //if empty, adds element to 0 index 
            data[size + 1] = obj;
            size++;
        } else { //adds element to next open index 
            data[size + 1] = obj;
            size++;

            // Traverse up and fix violated property
            int current = size; 

            while (comp.compare((T) data[current], (T) data[parent(current)]) > 0) { //while a sap is necessary, swap 
                swap(current, parent(current));
                current = parent(current);
                // }
            }
        }

    }

    public void clear() { //clears the heap
        data = new Object[16];
        size = -1;
    }

    public void resize() { //resize array method 
        {
            Object[] newArr = new Object[data.length * 2];// Creating a new array with space for an extra element
            for (int i = 0; i < data.length; i++) {
                newArr[i] = data[i];// Copying the elements to the new array
            }
            data = newArr;
        }

    }

    public T remove() { //removes the max element from the heap (root)
        T popped;

        if (size == -1) {
            return null;
        }
        if (size == 1 && comp.compare((T) data[0], (T) data[leftChild(0)]) < 0) { //conditional to fix bug

            popped = (T) data[1]; 
            size--;
        } else { //replaces max item and decriments size
            popped = (T) data[0];
            data[0] = data[size];
            size--;
        }

        maxHeapify(0); //call to maxheapify
        return popped;

    }

    public ArrayList<Object> toArray() { //converty heap to ArrayList
        ArrayList<Object> array = new ArrayList<Object>();
        for (int i = 0; i <= size; i++) {
            array.add(data[i]);
        }
        return array;
    }

    public void maxHeapify(int position) { //uses recursion to fix any remove out of order nodes
        if (isLeaf(position)) {

            return;
        }

        if (comp.compare((T) data[position], (T) data[leftChild(position)]) < 0 ||
                comp.compare((T) data[position], (T) data[rightChild(position)]) < 0) { //if there is an out of order node, reorder 

            if (comp.compare((T) data[leftChild(position)], (T) data[rightChild(position)]) > 0) {

                swap(position, leftChild(position)); //if the left node is greater than the right, swap leftChild and parent
                maxHeapify(leftChild(position)); //recursively move down the heap
            } else {

                swap(position, rightChild(position));
                maxHeapify(rightChild(position));
            }
        }
    }

    private int parent(int position) { //defines the position of the parent 
        if (size == 2 && comp.compare((T) data[rightChild(0)], (T) data[leftChild(0)]) > 0)
            return 0;

        return position / 2;
    }

    private int leftChild(int position) { //defines leftChild position 
        if (position == 0) {
            return 1;
        }
        return position * 2;
    }

    private int rightChild(int position) { //defines rightChild position
        if (position == 0) {
            return 2;
        }
        return (2 * position) + 1;
    }

    private boolean isLeaf(int position) { //defines if a node is a leaf
        if (position >= size / 2) {
            return true;
        }
        return false;
    }

    private void swap(int firstPosition, int secondPosition) { //swaps two nodes
        Object temp;
        temp = data[firstPosition];
        data[firstPosition] = data[secondPosition];
        data[secondPosition] = temp;
    }

    public static void main(String[] args) { //test methds
        PQHeap<KeyValuePair<String, Integer>> heap = new PQHeap(new AscendingString2(), 0);
        heap.add(new KeyValuePair<String, Integer>("yellow", 1));
        heap.add(new KeyValuePair<String, Integer>("zzzzzzzz", 10));
        heap.add(new KeyValuePair<String, Integer>("marlon", 3));
        heap.add(new KeyValuePair<String, Integer>("d", 1));
        heap.add(new KeyValuePair<String, Integer>("g", 5));
        heap.add(new KeyValuePair<String, Integer>("2", 50));
        heap.add(new KeyValuePair<String, Integer>("seVEN", 2));
        heap.add(new KeyValuePair<String, Integer>("seVEN", 1));
        System.out.println(heap.toArray());
        heap.remove();
        System.out.println(heap.toArray());
        heap.remove();
        System.out.println(heap.toArray());
    }
}
