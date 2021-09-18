
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LZW {

    public static List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)//load ascii table 
            dictionary.put("" + (char)i, i);
 
        String current = "";
        List<Integer> encodedValues = new ArrayList<Integer>();
        for (char next : uncompressed.toCharArray()) {
            String combined = current + next;
            if (dictionary.containsKey(combined))//checks if combined is already in dictionary
                current = combined;
            else {
                encodedValues.add(dictionary.get(current));
                // Add combined letters to the dictionary
                dictionary.put(combined, dictSize++);
                current = "" + next;
            }
        }
 
        // Output the ascii code for each character(s) in encodedValues.
        if (!current.equals(""))
            encodedValues.add(dictionary.get(current));
            //nums=encodedValues; 
            //System.out.println(nums); 
        return encodedValues;
    }
    
    public static String decompress(List<Integer>numbers){
        int dictLength =256; // keeps track of how big the hash map is 
        HashMap <Integer, String> map = new HashMap <Integer,String> (); // intialized and delcres hash map 
        for (int i=0; i<256; i++){
            map.put(i, ""+(char)i); // created dictionary assigning ascii values to the first 255 characters
        }
        
        int current=0; 
        int next=0; 
        String word="";// this will eventually be the whole word that will get returned 
        String combined ="";// string that contains current and next as one string 
        String wordC=""; // the string version of the number of the current value
        String wordN=""; // the string version of the number of the next value
        
        int size=numbers.size(); 
        for (int i=0; i<size; i++){
            if (i<size-1) // makess sure that you wont get out of bounds
            {
            		current=numbers.get(i); // gets first thing in arraylist
            		next=numbers.get(i+1); // gets second thing in arraylist
                    if (next<dictLength){ // checks to see that next is already in the dictionary
                    	
                    	wordC=map.get(current); // converts the numbers into a string 
                        wordN=map.get(next); // same as above but for next 
                        combined=wordC+wordN.substring(0,1);
                        word+=combined;// combines current and next's first letter
                        map.put(dictLength,combined); // puts the new combined letters into the dictionary 
                        dictLength++;// increments counter to fit the new size of map
                    } 
                    else
                    {
                        wordN=map.get(current); // sets WordN to the current word 
                        String letter=wordN.substring(0,1); // gets the first letter of the next word 
                        wordN=wordN+letter; // sets wordN to wordN to the first letter of WordN
                        word += wordN;
                        map.put(dictLength,wordN); // adds to Hashmap
                        dictLength++; // increments counter
                    }
            	}
            }
        /*for (int i=0; i<numbers.size(); i++){
            int index=numbers.get(i); 
           word+=map.get(index); 
        }*/
        return (word);
    }
    
    public static void main(String[] args) throws IOException {
    	final long startTime = System.currentTimeMillis();
    	String filename = ("lzw-file3.txt");
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(filename));
           System.out.println("Output:");
            while((line = br.readLine()) != null){//the bufferedreader reads each line, stores it, and passes it onto the "compress" method that performs LZW compression.
                List<Integer> compressed = compress(line); 
                //System.out.println (compressed); 
                System.out.println(decompress(compressed)); 

            }
        }finally {
            br.close();
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }
} 