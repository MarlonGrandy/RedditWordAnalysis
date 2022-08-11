
/* 
Author: Marlon Grandy
Date: 4/4/2022
File: AscendingString.java
Use: Judge where to insert a new node depending on its Key Value pair using comparator interface.
project 9
*/
import java.util.Comparator;

public class AscendingString implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) { //compare method for strings
        {
            if (o1 == o2) //if the string are equal returns zero 
                return 0;
            if (o1 == null) //if the o1 is null o2 must be bigger
                return 1;
            if (o2 == null) // if o2 is null o1 must be bigger
                return -1;
            return o1.compareTo(o2); // calls string comareTo method and returns the result 
        }
    }
}
