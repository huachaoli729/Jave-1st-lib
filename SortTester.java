/**
 *  This class is the tester of all the sorting algorithm. It originally stored two sorting algorithms: 
 *                    Merge Sort and Quick Sort(with median of 3)
 *  In this class, it contains the method of reading the array from "data.txt" file, which located 
 *  in C drive: C://data.txt. . It sends the list to the sorting algorithm to sort, and print the run
 *  time, key comparisons and key movenments done in the sorting algorithm.
 * 
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 310 Assignment2, 2015.Nov.2nd.
 * 
 * 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortTester 
{
	/**
     *  This class is the tester of all the sorting algorithm. It originally stored two sorting algorithms: 
     *                    Merge Sort and Quick Sort(with median of 3)
     *  In this class, it contains the method of reading the array from "data.txt" file, which located 
     *  in C drive: C://data.txt. . It sends the list to the sorting algorithm to sort, and print the run
     *  time, key comparisons and key movenments done in the sorting algorithm.
	 *  
	 * @param args - It contains the method that reads the array from "data.txt" file, which located 
	 *               in C drive: C://data.txt; sends the list to the sorting algorithm to sort,
     *               and print the run time, key comparisons and key movenments done in the sorting algorithm.
     *               
	 */
	public static void main(String[] args)
	{
		 //Instructions and reminders.
		 System.out.println("Please put the txt file which stored the the to sort in C drive,  " +
		 		"and name it as data.txt. All the numbers in the file are considered as in one list.");
		 
		 File f = new File("C://data.txt");
		 //Check if "data.txt" is exist in current directory.
		 if(!f.exists())
	     {
			 System.out.println("Please put the txt file in C drive, and name it as data.txt.");
	     }
		 //Generate an arraylist to store the list in data.txt
		 int[] theList = new int[2000];
		 int i = 0;

        //Convert the list from txt into an array.
	     try 
	     {
	    	 Scanner input = new Scanner(f);
	    	 while (input.hasNextInt()) 
	         {
	    		 theList[i] = input.nextInt();
	    		 i++;
	         }//while
	         input.close();
	     }
	     catch (FileNotFoundException e) {}
	     
	     System.out.println("The list to sort is: ");
	     for(int a = 0; a <i;a++)
	     {
	    	 System.out.print(theList[a]+" ");
	     }
	     System.out.println("");
	     System.out.println("The list size is: " +i);
	     
	     //Start Merge Sort
	     long start = System.nanoTime();
	     MergeSort a = new MergeSort(theList,i);
	     long mTime = System.nanoTime() -start;
	     //Print the number of runtime,key comparisons, and key movements for Merge Sort.
	     System.out.println("Merge Sort run time is: " +mTime +" nanoseconds");
	     System.out.println("Merge Sort has " +a.getCompare() +" comparsions, and "+a.getMove() +" key movements.");
	     
	     //Start Quick Sort
	     long start2 = System.nanoTime();
	     QuickSort b = new QuickSort(theList,i);
	     long qTime = System.nanoTime() -start2;
	     //Print the number of runtime,key comparisons, and key movements for Quick Sort.
	     System.out.println("Quick Sort run time is: " +qTime +" nanoseconds");
	     System.out.println("Quick Sort has " +b.getCompare() +" comparsions, and "+b.getMove() +" key movements.");
	}
}
