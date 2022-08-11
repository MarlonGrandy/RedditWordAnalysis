/*
Marlon Grandy
4/18/2022
Professor Harper
WordTrendsFinder.java: finds frequenciues of a list of words in different txt files
project 9
*/


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;


public class WordTrendsFinder {
    private int totalWordCount; // total word count in the entire file
    private BSTMap<String, Integer> BSTMap; //new BSTMap object

    public WordTrendsFinder() { // constructor that makes an empty BSTMap and sets the total word count to zero.
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
                        }
                        if (BSTMap.get(word) == null)
                            BSTMap.put(word, 1); // creates a new node with frequency of key 1
                        else
                            BSTMap.put(word, BSTMap.get(word) + 1); // incrments value by 1 to reflect word frequency

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

    public void findTrends(String files, String words) { //prints out the frequencies from a set of words using command line arrguments
        String[] files2 = files.split("[^a-zA-Z0-9_.']+");
        String[] words2 = words.split("[^a-zA-Z0-9_.']+");

        for (String file : files2) { //fore each file, print out the frequency for each words from the BST map
            System.out.println(file);
            analyze(file);
            for (String word : words2) {
                System.out.println(word + " , " + (double) BSTMap.get(word) / (double) totalWordCount);
            }
            BSTMap.clear();
            totalWordCount = 0;
        }
    }

    public static void main(String[] args) { //main method
        WordTrendsFinder trend = new WordTrendsFinder();
        trend.BSTMap.clear();
        trend.findTrends(args[0], args[1]);
        // trend.analyze();
    }
}
