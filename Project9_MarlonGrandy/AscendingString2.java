
/* 
Author: Marlon Grandy
Date: 4/4/2022
File: AscendingString.java
Use: Judge where to insert a new node depending on its Key Value pair using comparator interface.
project 9
*/
import java.util.Comparator;

public class AscendingString2 implements Comparator<KeyValuePair<String, Integer>> {

    @Override
    public int compare(KeyValuePair<String, Integer> o1, KeyValuePair<String, Integer> o2) { // compare method for
                                                                                             // strings
        {
            if (o1.getValue() == null) // if the o1 is null o2 must be bigger
                return 1;
            if (o2.getValue() == null) // if o2 is null o1 must be bigger
                return -1;
            if (o1.getValue() == o2.getValue()) // if the string are equal returns zero
                return 0;

            return o1.getValue().compareTo(o2.getValue()); // calls string comareTo method and returns the result
        }
    }
}
