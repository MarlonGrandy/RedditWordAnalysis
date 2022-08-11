
/*
Name: Marlon Grandy
File: WordCounter.java
Fall 2022
CS 231 Project 7
Use: class creates a BST from a file with each word 
being a different key and each value being the frequency the word appears
*/
//import statements 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class WordCounter {
    private int totalWordCount; // total word count in the entire file
    private BSTMap<String, Integer> BSTMap;

    public WordCounter() { // constructor that makes an empty BSTMap and sets the total word count to zero.
        BSTMap = new BSTMap<String, Integer>(new AscendingString());
        totalWordCount = 0;
    }

    public void analyze(String filename) {// generates the word counts from a file of words.
        try {
            FileReader filevar = new FileReader(filename);
            BufferedReader newbuffer = new BufferedReader(filevar);
            String line;
            while ((line = newbuffer.readLine()) != null) {
                {
                    String[] words = line.split("[^a-zA-Z0-9']+"); // splits on anything except letters and numbers

                    for (int i = 0; i < words.length; i++) {
                        String word = words[i].trim().toLowerCase(); // trims any whitespace and makes results lowercase
                        if (word.length() > 0) {
                            totalWordCount++; // incriment total word count

                            if (BSTMap.get(word) == null)
                                BSTMap.put(word, 1); // creates a new node with frequency of key 1
                            else
                                BSTMap.put(word, BSTMap.get(word) + 1); // incrments value by 1 to reflect word
                                                                        // frequency

                        }
                    }
                }

            }
            newbuffer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("error reading file " + filename);
        }

    }

    public int getTotalWordCount() {// returns the total word count
        return totalWordCount;
    }

    public int getUniqueWordCount() { // return the size of the BST Map
        return BSTMap.size();

    }

    public int getCount(String word) {// returns the frequency value associated with this word.
        return BSTMap.get(word);
    }

    public double getFrequency(String word) { // returns the value associated with word divided by the total word count.
        return (float) BSTMap.get(word) / getTotalWordCount();
    }

    public void writeWordCountFile(String filename) { // writes total word count and each key-val pair to txt file
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter buf = new BufferedWriter(fw);
            buf.write("total word count: " + getTotalWordCount()); // writes the total word count to the file
            buf.newLine(); // adds a new line

            for (KeyValuePair<String, Integer> KV : BSTMap.entrySet()) {
                // for each key-val pair, the key and val are written to the txt file on a
                // different line
                buf.write("" + KV);
                buf.newLine();
            }
            buf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readWordCountFile(String filename) { // reads the contents of a word count file and reconstructs the
                                                     // fields of the WordCount object
        try {
            FileReader filevar = new FileReader(filename);
            BufferedReader newbuffer = new BufferedReader(filevar);
            String line;
            newbuffer.readLine(); // reads the first line to not include total word count in BST
            while ((line = newbuffer.readLine()) != null) {
                String[] pair = line.split("[^a-zA-Z0-9']+");
                // defines what to split the line on nd creates array list of size 2: a key and
                // val
                BSTMap.put(pair[0], Integer.valueOf(pair[1])); // calls BSTMap put method on each parsed key-val pair

            }
            newbuffer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("error reading file " + filename);
        }

    }

    public static void main(String[] args) { // test method for WordCounter
        WordCounter wc = new WordCounter();
        System.out.println(System.currentTimeMillis());
        wc.analyze("reddit_comments_2008.txt");
        System.out.println(System.currentTimeMillis());
        System.out.println("Total " + wc.getTotalWordCount());
        System.out.println("Unique " + wc.getUniqueWordCount());
        // System.out.println(wc.BSTMap.entrySet());
        // System.out.println(wc.BSTMap.values());
        // System.out.println(wc.BSTMap.keySet());
        wc.writeWordCountFile("redditOut2008.txt");
        // wc.readWordCountFile("BSTOut.txt");
        // wc.getUniqueWordCount();
        System.out.println(wc.BSTMap.values());
    }

}
