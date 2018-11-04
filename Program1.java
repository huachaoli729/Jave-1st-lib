/**
 * This program contain the algorithm method for "Finding the largest and 2nd largest 
 * element in a given list". It not only shows the largest and 2nd largest element in
 * the list, but also shows following things: 
 *              1 .the list of elements; 
 *              2. Every key comparison that is done, and who won
 *              3. The list of who lost to each winner.
 * This algorithm's complexicity is "n +[logn] - 2".
 * 
 * Notice: Please put only one list in the algorithm to sort. All the elements in the 
 * "data.txt" file are considered as elements in one list.
 * 
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 310 Assignment1, 2015.Oct.5th
 * 
 * Contents:
 * main(String[]),
 *      This method excutes an algorithm to find largest and 2nd largest in the list. 
 *      It read list data from "C://data.txt" file, and then find out the largest and 
 *      2nd largest in the list. 
 *      This algoritm shows:
 *                1. The list of elements
 *                2. Every key comparison that is done, and who won
 *                3. The largest
 *                4. The 2nd largest
 *                5. The list of who lost to each winner.
 *   
 *      
 *         
 *      
 *  
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Program1 
{
	 /**
	  *      This method excutes an algorithm to find largest and 2nd largest in the list. 
      *      It read list data from "C://data.txt" file, and then find out the largest and 
      *      2nd largest in the list. 
      *      This algoritm shows:
      *                1. The list of elements
      *                2. Every key comparison that is done, and who won
      *                3. The largest
      *                4. The 2nd largest
      *                5. The list of who lost to each winner. 
      *        
      *                
	  * @param args - This contains all the arguements.
	  * @throws IOException -It occurs when IOException happens.
	  */
	 public static void main(String[] args) throws IOException
	 {
		 File f = new File("C://data.txt");
		 //Check if "data.txt" is exist in current directory.
		 if(!f.exists())
	     {
			 System.out.println("Please put the txt file in current derectory, and name it as data.txt.");
	     }
		 //Generate an arraylist to store the list in data.txt
    	 ArrayList<Integer> theList = new ArrayList<Integer>();
    	 int largest;
    	 int largest2;
         //Convert the list in txt into an arraylist.
	     try 
	     {
	    	 Scanner input = new Scanner(f);
	    	 while (input.hasNextInt()) 
	         {
	    		 theList.add(input.nextInt());
	         }//while
	         input.close();
	     }
	     catch (FileNotFoundException e) {}

	     System.out.println("=====================" +"\n");
	     //Print the list.
	     for (int i = 0;  i < theList.size(); i++)
	     {
	    	 System.out.print(theList.get(i)+" ");
	     }
	     System.out.println("");
	     HashMap<Integer,ArrayList<Integer>> map =new HashMap<Integer,ArrayList<Integer>>();

	     //algorithm start.
	     System.out.println("Finding largest...");
	     int key;
	     int counter= 0;
	     int counter2 = theList.size()-1;
	     while (counter < counter2)
	     {
	    	 //"theList[counter]" compared to  "theList[counter+1]".
	    	 if (theList.get(counter) < theList.get(counter+1))
	    	 {
	    		 //Winner is "theList.get(counter+1)"
	    		 theList.add(theList.get(counter+1));
	    		 counter2++;
	    		 key = theList.get(counter+1);
	    		 if (map.get(key) == null)
	    		 {
	    			 ArrayList<Integer> aList = new ArrayList<Integer>();
	    			//Create winner's list in Hashmap.
	    			 map.put(key, aList);
	    			 //Add list value in winner's list.
	    			 map.get(key).add(theList.get(counter));
	    			 
	    		 }
	    		 else
	    		 {
	    			 map.get(key).add(theList.get(counter));
	    		 }
	    		 System.out.println("Comparison: "+ theList.get(counter) +" to " +theList.get(counter+1) + "  Winner: " + theList.get(counter+1));
	    		 counter = counter + 2;
	    	 }
	    	 else
	    	 {
	    		 //Winner is "theList.get(counter)".
	    		 theList.add(theList.get(counter));
	    		 counter2++;
	    		 key = theList.get(counter);
	    		 if (map.get(key) == null)
	    		 {
	    			 ArrayList<Integer> aList = new ArrayList<>();
	    			//Create winner's list in Hashmap.
	    			 map.put(key, aList);
	    			//Add list value in winner's list.
	    			 map.get(key).add(theList.get(counter+1));
	    			 
	    		 }
	    		 else
	    		 {
	    			 map.get(key).add(theList.get(counter+1));
	    		 }
	    		 System.out.println("Comparison: "+ theList.get(counter) +" to " + theList.get(counter+1) + "  Winner: " + theList.get(counter));
	    		 counter = counter + 2;
	    	 }
	     }//Largest found.
	     largest = theList.get(theList.size()-1);
	     System.out.println("");
	     
	     //Start finding 2nd largest's algorithm.
	     System.out.println("Finding 2nd largest...");
	     //Get largest winner's list to find 2nd largest.
	     ArrayList<Integer> list = map.get(largest);
	     largest2 = list.get(0);
	     for(int i =1; i < list.size(); i++)
	     {
	    	 if (list.get(i)>largest2)
	    	 {
	    		 System.out.println("Comparison: "+ largest2 +" to " +list.get(i) + "  Winner: " + list.get(i));
	    		 largest2 = list.get(i);
	    	 }
	    	 else
	    	 {
	    		 System.out.println("Comparison: "+ largest2 +" to " +list.get(i) + "  Winner: " + largest2);
	    	 }
	     }
	     System.out.println("");
	     	     
	     //Algorithm finished, show the results.
	     System.out.println("Winner is:  " + largest);
	     System.out.println("2nd place:  " + largest2);
	     System.out.println("Results:");
	     ArrayList<Integer> beatList = new ArrayList<Integer>();
	     for (Integer aKey : map.keySet()) 
	     {
	         System.out.print(aKey +" won against [");
	         beatList = map.get(aKey);
	         for(int k=0; k< beatList.size(); k++){
	        	 if (k>0)
	        	 {
	        		 System.out.print(", " +beatList.get(k));
	        	 }
	        	 else
	        	 {
	        		 System.out.print(beatList.get(k));
	        	 }
	         }
	         System.out.print("] "+ "\n");
	     }
	     System.out.println("=====================");
	 }//Program ends
}