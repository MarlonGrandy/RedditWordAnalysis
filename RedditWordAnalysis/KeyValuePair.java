/* 
Author: Marlon Grandy
Date: 4/4/2022
File: KeyValuePair.java
Use: defines a key and a value with getter and setter methods
*/
public class KeyValuePair<Key, Value> {
    Value value;
    Key key;

    public KeyValuePair(Key k, Value v) { // the constructor initializing the key and value fields.
        key = k;
        value = v;
    }

    public Key getKey() {// returns the key.
        return key;
    }

    public Value getValue() {// returns the value.
        return value;
    }

    public void setValue(Value v) { // sets the value.
        value = v;
    }

    public String toString() {// returns a String containing both the key and value.
        return key + "  " + value;
    }

    public static void main(String[] args) { // test function for class methods
        KeyValuePair<Integer, Integer> KVP = new KeyValuePair<Integer, Integer>(5, 6);
        System.out.println(KVP.getValue());
        System.out.println(KVP.getKey());
        KVP.setValue(1);
        System.out.println(KVP.toString());
    }

}