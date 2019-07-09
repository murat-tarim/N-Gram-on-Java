package N_gramPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.tools.ToolProvider;
/**
 * A simple example program that reads a text file line by line and display each line.
 */
public class N_gram {
	
	static long start = System.currentTimeMillis();
	 public static List<String> topKFrequent(String[] words, int k) { //N_gram method(sorting as the most frequent elements)
	        Map<String, Integer> count = new HashMap();
	        for (String word: words) {
	            count.put(word, count.getOrDefault(word, 0) + 1);
	        }
	        List<String> candidates = new ArrayList(count.keySet());
	        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
	                w1.compareTo(w2) : count.get(w2) - count.get(w1));

	        return candidates.subList(0, k);
	 }
	
    public static void main(String[] args) throws IOException {  	

    // create token1
    String token1 = "";
  
    // create Scanner inFile1
    Scanner inFile1 = new Scanner(new File("C:\\Users\\casper\\Desktop\\Novel-Samples\\BOZKIRDA.txt")).useDelimiter("[\\()-.*”,»\\s!;?:\"]+");
    //scan file and split 
    
    
    // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
    // List<String> temps = new LinkedList<String>();
    List<String> temps = new ArrayList<String>();

    // while loop
    while (inFile1.hasNext()) {
      // find next line
      token1 = inFile1.next();
      temps.add(token1);
    }
    inFile1.close();

    String[] tempsArray = temps.toArray(new String[0]);
   	
    System.out.println();
    //System.out.println(tempsArray.length);
    System.out.println("1-gram Top-100");
    System.out.println("--------------");
    System.out.println(topKFrequent(tempsArray, 100)); //finding 1 gram in the text
    //System.out.println(tempsArray.length);
    //System.out.println();
    int count=0;
    int count2=0;
    String bigram_array[]  = new String[tempsArray.length/2]; //it can be maximum bi gram text/2
    for (int i = 0; i < bigram_array.length; i++) {
    	if(bigram_array[i]==null) {
    		bigram_array[i]="";
    	}
		
	}    
    for (int i = 0; i < tempsArray.length; i++) {
    	if(i<tempsArray.length-1)
    	{
    	String bigram=tempsArray[i].concat(" "+tempsArray[i+1]);
    	String temp_bigram;
    	   	  	for (int j = i+2; j < bigram_array.length; j++) {
    	   	  	if(j<tempsArray.length-2) {
    	   	  	 temp_bigram=tempsArray[j].concat(" "+tempsArray[j+1]); //concat words 
    			
    				if(bigram.equalsIgnoreCase(temp_bigram)) { //and scan all array is it equal the concatting word
    					 
    					bigram_array[count]=bigram;
    					count++;
    				}
    	   	  	}
    			}	
					
				}
    		
	}  
    ///finding trigrams
    String trigram_array[]  = new String[tempsArray.length/3]; //it can be maximum bi gram text/3
    for (int i = 0; i < trigram_array.length; i++) { 
    	if(trigram_array[i]==null) {              //delete null values in string
    		trigram_array[i]="";
    	}
		
	}    
    for (int i = 0; i < tempsArray.length; i++) {
    	if(i<tempsArray.length-2)
    	{
    	String trigram=tempsArray[i].concat(" "+tempsArray[i+1]).concat(" "+tempsArray[i+2]);
    	String temp_trigram;
    	   	  	for (int j = i+2; j < trigram_array.length; j++) {
    	   	  	if(j<tempsArray.length-3) {
    	   	  	temp_trigram=tempsArray[j].concat(" "+tempsArray[j+1]).concat(" "+tempsArray[j+2]);
    			
    				if(trigram.equalsIgnoreCase(temp_trigram)) {
    					 
    					trigram_array[count2]=trigram;
    					count2++;
    				}
    	   	  	}
    			}						
				}
    		
	}
    System.out.println();
	System.out.println("Bi-gram Top 100");
	System.out.println("---------------");
	  System.out.println(topKFrequent(bigram_array, 100)); //sort and write bigrams
	  System.out.println();
		System.out.println("Tri-gram Top 100");
		System.out.println("----------------");
		  System.out.println(topKFrequent(trigram_array,6)); //sort and write trigrams (in some text there is not enough trigrams)
		  System.out.println();
		  long end = System.currentTimeMillis();

		  NumberFormat formatter = new DecimalFormat("#0.00000"); //total time calculations
		  System.out.println("Total Running time is " + formatter.format((end - start) / 1000d) + " seconds");
		 
  }
}