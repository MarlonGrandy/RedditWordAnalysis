
/*
Name: Marlon Grandy
BSTMap.java
4/11/2022
CS 231 Project 9
Class creates a binary search tree and implements all the necesarry methods to m9niplate the tree
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
    private TNode root; // stores the value of the root node
    private int size; // stores the number of nodes in the BST
    private Comparator<K> comp;
    private V getVal;

    public BSTMap(Comparator<K> comp) { // constructor: takes in a Comparator object and sets field values
        root = null;
        this.comp = comp;
        size = 0;
        getVal = null;
    }

    public Comparator<K> getComp() { // returns tghe comparator object
        return comp;
    }

    public ArrayList<V> values() {// -returns an ArrayList that contains all of the values in the map
        return values(root, new ArrayList<V>());
    }

    public ArrayList<V> values(TNode forward, ArrayList<V> list) { // returns an arrayList of values from the BST
                                                                   // through recursion

        if (root == null) {
            return null;
        }
        list.add(forward.KVP.getValue()); // adds the current node value to the list
        if (forward.left != null) {
            values(forward.left, list); // calls values() on the left node if not null
        }

        if (forward.right != null) {
            values(forward.right, list); // calls values() on the right node if not null
        }
        return list;

    }

    public int heightoftree(TNode root) { // return the height of tree
        if (root == null)
            return -1;
        else {
            int left = heightoftree(root.left); // return the height of leftsubtree
            int right = heightoftree(root.right); // return the height of rightsubtree
            if (left > right) // compare the height of left and right subtree
                return left + 1;
            else
                return right + 1;
        }
    }

    public boolean containsKey(K key) {// returns true if the map already contains a node with the specified key.
        if (get(key) != null) {
            System.out.println(heightoftree(root));
            return true;

        }

        return false;
    }

    public ArrayList<K> keySet() {// -returns an ArrayList that contains all of the values in the map
        return keySet(root, new ArrayList<K>());
    }

    public ArrayList<K> keySet(TNode forward, ArrayList<K> list) { // - returns an ArrayList that contains all of the
                                                                   // keys in the map.
        if (root == null) {
            return null;
        }
        list.add(forward.KVP.getKey()); // adds key to ArrayList
        if (forward.left != null) {
            keySet(forward.left, list); // recursively goes to left node
        }

        if (forward.right != null) {
            keySet(forward.right, list); // recursively goes to right node
        }
        return list;

    }

    public ArrayList<KeyValuePair<K, V>> entrySet() {// -returns an ArrayList that contains all of the values in the map
        System.out.println("begin");
        return entrySet(root, new ArrayList<KeyValuePair<K, V>>());
    }

    public ArrayList<KeyValuePair<K, V>> entrySet(TNode forward, ArrayList<KeyValuePair<K, V>> keyVal) { // -returns an
                                                                                                         // ArrayList of
                                                                                                         // KeyValuePair
                                                                                                         // objects in
                                                                                                         // pre-order.

        if (root == null) {
            return null;
        }
        keyVal.add(forward.KVP); // adds key to ArrayList
        if (forward.left != null) {
            entrySet(forward.left, keyVal); // recursively goes to left node
        }

        if (forward.right != null) {
            entrySet(forward.right, keyVal); // recursively goes to right node
        }
        return keyVal;

    }

    public void clear() { // -clears the map
        root = null;
        size = 0;
    }

    public int collide() { // mapSet method for collisions - used in Hashmap
        return 0;
    }

    public V put(K key, V value) { // calls the recursive put method in TNode or creates new root
        // check for and handle the special case
        // call the root node's put method
        if (root != null) {
            V dat = root.put(key, value, comp); // calls put in TNode if there is a root
            return dat;
        }
        root = new TNode(key, value); // if no root exists, a root is created
        size++; // size is incrimented to reflect a new root being created
        return null;
    }

    // gets the value at the specified key or null
    public V get(K key) {
        // check for and handle the special case
        // call the root node's get method
        if (root != null) {
            return root.get(key, comp);
        }
        return null;
    }

    public int size() { // returns size
        return size;
    }

    private class TNode { // nested TNode class
        private TNode right;
        private TNode left;
        private KeyValuePair<K, V> KVP;

        public TNode(K k, V v) { // constructor, given a key and a value
            KVP = new KeyValuePair<K, V>(k, v);
            left = right = null;
        }

        public V put(K key, V value, Comparator<K> comp) {
            /*
             * Takes in a key, a value, and a comparator and inserts
             * the TNode in the subtree rooted at this node
             * Returns the value associated with the key in the subtree
             * rooted at this node or null if the key does not already exist
             */

            int com = comp.compare(key, KVP.getKey());
            if (com < 0) { // if the key is less than5 the node key
                if (left == null) { // if there is no left node insert the new node as the left node
                    left = new TNode(key, value);
                    size++; // incriment size to reflect a new node being added

                } else { // if there is a left node, through recursion, go to node child on left and
                         // repeat

                    left.put(key, value, comp);

                }
                return null;
            } else if (com > 0) { // if the key is greater than the current node
                if (right == null) { // if there is no right node insert the new node as a right child
                    right = new TNode(key, value);
                    size++; // incriment size to reflect a new node being added

                } else { // if there is a right node, through recursion, go to node child on right and
                         // repeat
                    right.put(key, value, comp);
                }
                return null;
            } else { // if not greater or less than an equivilent node position has been found
                V data = KVP.getValue();
                KVP.setValue(value); // sets the value to the keys new value
                return data; // returns value at the node
            }
        }

        public V get(K key, Comparator<K> comp) {

            // Takes in a key and a comparator
            // Returns the value associated with the key or null
            int com = comp.compare(key, KVP.getKey()); // call to the comparator

            if (com < 0) { // if the key is less than the node key
                if (left == null) { // if there is no left node return null
                    getVal = null;
                } else { // if there is a left node check to see, through recursion, if the key is less
                         // than
                    left.get(key, comp);
                }
            } else if (com > 0) { // if the key is greater than the current node
                if (right == null) { // if there is no right node return null
                    getVal = null;
                } else { // if there is a right node, through recursion, check to see if node is greater
                    right.get(key, comp);

                }

            } else { // if not greater or less than a node has been found

                getVal = KVP.getValue(); // sets getVal to the value
            }
            return getVal;
        }
    }

    public static void main(String[] argv) { // test function

        BSTMap<String, Integer> bst = new BSTMap<String, Integer>(new AscendingString()); // creates a BSTMap

        bst.put("twenty", 20);
        bst.put("ten", 10);
        bst.put("eleven", 11);
        bst.put("five", 5);
        bst.put("six", 6);
        System.out.println(bst.size());
        System.out.println(bst.get("eleven"));
        System.out.println(bst.get("ten"));
        System.out.println(bst.get("twenty"));
        System.out.println(bst.get("six"));
        System.out.println(bst.get("one")); // returns null because node key does not exist
        System.out.println(bst.values());
        System.out.println(bst.keySet());
        System.out.println(bst.size());
        System.out.println(bst.entrySet());

    }

}
