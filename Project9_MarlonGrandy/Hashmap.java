/*
Marlon Grandy
4/18/2022
Professor Harper
Hashmap.java: creates a Hashmap with all the required methds 
project 9
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;

public class Hashmap<K, V> implements MapSet<K, V> {
    private int collision;
    private int capacity;
    private int size;
    private LinkedList<KeyValuePair<K, V>>[] ll;
    private int loadFactor;

    // Hashmap constructor that starts with default size hash table
    public Hashmap() { 
        this.ll = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[16];
        loadFactor = ll.length ;
    }

    // Hashmap constructor that starts with the suggecsted capacity hash table
    public Hashmap(int capacity) {
        this.capacity = capacity;
        this.ll = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[capacity];
        loadFactor = ll.length ;
    }

    public int collide() { // returns the collision attribute
        return collision;
    }

    public int calcLoad() { //calculates the desired resize load

        return size / ll.length;
    }

    public void resize() { //resizes the array
        collision = 0;
        LinkedList<KeyValuePair<K, V>>[] ll2 = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[ll.length * 2]; //new larger array
        LinkedList<KeyValuePair<K, V>>[] llCopy = ll; //copy of old array
        ll = ll2;

        for (int i = 0; i < llCopy.length; i++) { //adds all the values from the old array to the new array
            if (llCopy[i] != null) {
                for (KeyValuePair<K, V> kv : llCopy[i]) {
                    put(kv.getKey(), kv.getValue());
                }
            }
        }
        loadFactor = ll.length;
        System.out.println("resizing");

    }

    @Override
    public V put(K new_key, V new_value) { //adds values to the data structure 
        int hashMod = Math.abs(new_key.hashCode()) % ll.length; //generates hashed index
        if (ll[hashMod] == null) { //if there is no key at the index make a new key
            ll[hashMod] = new LinkedList<KeyValuePair<K, V>>();
            ll[hashMod].add(new KeyValuePair<K, V>(new_key, new_value));
            if (calcLoad() > loadFactor) {
                resize();
            }
            size++;
            return new_value;
        }
        if (containsKey(new_key)) { //if the key already exists in the Hashmap, finds the key and replaces the value 

            for (KeyValuePair<K, V> kv : ll[hashMod]) {
                if (kv.getKey() == new_key) {
                    kv.setValue(new_value);
                    return new_value;
                }
            }
        } else { //if there is a key in the hashed index, add to the linked list 
            collision++; //incriment collisions

            ll[hashMod].add(new KeyValuePair<K, V>(new_key, new_value));
            size++; //incriments size 
            if (calcLoad() > loadFactor) { //resizes the array if the calcualted load exceeds the laod factor
                resize();
            }
            return new_value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) { //checks if a key exists in the hashmap 
        int hashMod = Math.abs(key.hashCode()) % ll.length;
        if (ll[hashMod] != null) { //goes to keys hashed index
            for (KeyValuePair<K, V> kv : ll[hashMod]) { //iterates throught the lnked list and looks for key
                if (key.equals(kv.getKey())) {
                    return true; //if mkey exists
                }
            }
        }
        
        return false; //if key does not exist 
    }

    @Override
    public V get(K key) { //gets the value accosiated with a key 
        V val = null;
        int hashMod = Math.abs(key.hashCode() % ll.length);
        if (ll[hashMod] != null) { //goes to hashed index
            for (KeyValuePair<K, V> kv : ll[hashMod]) { //looks through the lnked list to find key
                if (key == kv.getKey()) {
                    val = kv.getValue(); //gets the value 
                    return val;

                }
            }

        }

        return null;

    }

    @Override
    public ArrayList<K> keySet() { //converts hashmap keys to arrayList
        ArrayList<K> keyArray = new ArrayList<K>();
        for (int i = 0; i < ll.length; i++) {
            if (ll[i] != null) {
                for (KeyValuePair<K, V> kv : ll[i]) {
                    keyArray.add(kv.getKey());
                }
            }
        }
        return keyArray;
    }

    @Override
    public ArrayList<V> values() { //converts hashmap values to arrayList
        ArrayList<V> valArray = new ArrayList<V>();
        for (int i = 0; i < ll.length; i++) {
            if (ll[i] != null) {
                for (KeyValuePair<K, V> kv : ll[i]) {
                    valArray.add(kv.getValue());
                }
            }
        }
        return valArray;
    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {  //converts hashmap key-value pairs to arrayList
        ArrayList<KeyValuePair<K, V>> keyValArray = new ArrayList<KeyValuePair<K, V>>();
        for (int i = 0; i < keySet().size(); i++) { // iterates through keySet to get indexer varaible (i)
            keyValArray.add(new KeyValuePair<K, V>(keySet().get(i), values().get(i))); // calls value() and keySet() to make
                                                                                  // key-value pair
        }
        return keyValArray;
    }

    @Override
    public int size() { //returns the size of the arrayList 
        return keySet().size();
    }

    @Override
    public void clear() { //clears the array list 
        this.ll = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[16];
    }

    public static void main(String[] args) { //test method 
        Hashmap<String, Integer> h = new Hashmap<String, Integer>();
        System.out.println("Adding to Hash table");
        h.put("hello", 10);
        h.put("why", 1);
        h.put("marlon", 11);
        h.put("busy", 15);
        System.out.println("Getting length");
        System.out.println("calcLoad" + h.calcLoad());
        System.out.println(h.ll.length);
        h.put("bears", 10);
        System.out.println(h.size);
        h.put("bears", 2);
        System.out.println(h.size);
        h.put("beard", 10);
        System.out.println(h.size);
        h.put("beara", 10);
        h.put("bearj", 10);
        h.put("bearx", 10);
        h.put("bearr", 10);
        h.put("bearq", 10);
        h.put("bearu", 10);
        h.put("bearp", 10);
        h.put("bearo", 10);
        h.put("bearn", 10);
        System.out.println("Getting length");

        System.out.println(h.ll.length);
        System.out.println("Getting vals by key");
        System.out.println(h.get("busy"));
        System.out.println(h.get("hi"));
        System.out.println("getting size");
        System.out.println(h.size());
        System.out.println("converting to arrayList");
        System.out.println(h.entrySet());
        System.out.println(h.values());
        System.out.println(h.keySet());
        System.out.println("Contains keytrue -> false");
        System.out.println(h.containsKey("marlon")); // should be true
        System.out.println(h.containsKey("grandy")); // should be false

    }

}