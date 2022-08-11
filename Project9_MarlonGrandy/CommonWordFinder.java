
/*
Marlon Grandy
4/18/2022
Professor Harper
CommonWordFinder.java: finds highest frequencies of words in a txt file 
project 9
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class CommonWordFinder {
    private PQHeap<KeyValuePair<String, Integer>> heap;
    private int totalWords;

    public CommonWordFinder() { //constructor initializing a new heap and totalwords to 0
        this.heap = new PQHeap(new AscendingString2(), 16);
        totalWords = 0;
    }

    public void writer(String fileString) { //writes a new heap from a wordcount text file
        try {
            FileReader fr = new FileReader(fileString);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            String[] total = line.split("[^a-zA-Z0-9']");
            totalWords = Integer.parseInt(total[4]);

            while ((line = br.readLine()) != null) {
                String[] words = line.split("[^a-zA-Z0-9']+");
                heap.add(new KeyValuePair<String, Integer>(words[0], Integer.parseInt(words[1]))); //adds new KVP to heap

            }
            br.close();

        } catch (FileNotFoundException ex) { // catches any errors thrown
            System.out.println("unable to open file " + fileString);
        } catch (IOException ex) {
            System.out.println("error reading file " + fileString);
        }

    }

    public void analyzer(int N, String fileString) { //analyzes the heap and removes the N highest frequency words
        KeyValuePair<String, Integer>[] common = (KeyValuePair<String, Integer>[]) new KeyValuePair[N]; //initalizes an array of KVPs
        String[] files = fileString.split("[^a-zA-Z0-9_.^]+"); //splits the file argument to get all file names

        for (String file : files) { //for each file name create a heap and print out the N most common words
            System.out.println(file);
            writer(file);
            for (int i = 0; i < N; i++) {

                common[i] = heap.remove();
                System.out.println(common[i].getKey() + " , " + (double) common[i].getValue() / ((double) totalWords));

            }
            heap.clear(); //clear the heap 
            common = (KeyValuePair<String, Integer>[]) new KeyValuePair[N];
            totalWords = 0;
        }
    }

    public static void main(String[] args) { //main run method
        CommonWordFinder cwf = new CommonWordFinder(); //create new object
        WordCounter wc = new WordCounter();
        //make a BST
        wc.analyze("reddit_comments_2015.txt");
    //from BST make a wordCount file
        wc.writeWordCountFile("redditOut2015.txt");
        //make a heap and print out N most common words 
        cwf.analyzer(10, "redditOut2015.txt");

    }

}
