/*
 * This program is to Implement the Tournament method in Java, and use it to find the 2nd largest element in a list of N integers. 
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Algorithm1 {

 public static void main(String[] args) throws IOException
 {
	 File f = new File("D:\\abc.txt");
	 if(!f.exists())
	 {
		   System.out.println("Please put the txt file under D drive£¬and name it as abc.txt");
	 }
     InputStream input = new FileInputStream(f);
     BufferedReader b = new BufferedReader(new InputStreamReader(input));
     StringBuffer buffer = new StringBuffer();
     String value = b.readLine();
  
     while(value != null){
    	 buffer.append(" "+value);
    	 value = b.readLine();
    }

     String[] temp = buffer.toString().replaceFirst(" ","").split("\\s+");
     int[] number = new int[temp.length];
     System.out.println("The input list is:");
     for(int i=0;i<temp.length;i++)
     {
    	 try{
    		 number[i] = Integer.parseInt(temp[i]);
    		 System.out.print(number[i]+" ");
    		 }catch(Exception e){
    			 System.out.println("File exists none integer letters!");
    			 //Check if the file has none integer input.
    		 }
     }//Print the input.
	 System.out.println("");
     
     int array1[] = new int [100];
	   for (int a =number.length-1; a>=0;a--)
	   {
	   array1[number.length-1-a] = number[a];
	   }
	   int start = 0;
	   int end = number.length;
	   while (start < end-1){
		   if (array1[start] < array1[start+1])
		   {
			   array1[end] = array1[start+1];
		   }
		   else 
		   {
			   array1[end] = array1[start];
		   }
		   start = start +2;
		   end++;
	   }
	   //Calculate the tournament tree.
	   int order= 2;
	   System.out.println("");
	   System.out.println("The tournament tree is:");
	   for (int i = 0; i<end;i++)
	   {
		   if (i<order-1)
		   {
			   System.out.print(array1[end-i-1]+" ");
		   }
		   else
		   {
			   System.out.println("");
			   order = order *2;
			   System.out.print(array1[end-i-1]+" ");
		   }	      
	   }//Print the tournament tree.
	   System.out.println("");
	   System.out.println("");
	   
	   
	   System.out.println("Now finding 2nd Largest");
	   int secondLarge[] =new int [20];
	   int order2 = 2;
	   int large2= 0;
	   int largest = array1[end-1];
	   int counter =0;
	   for (int i = 1; i<end;i++){
		   if (i<order2 -1)
		   {
			   if (array1[end-i-1] <largest && array1[end-i-1] >large2)
			   {
				   secondLarge[counter] = array1[end-i-1];
			   }					   
		   }
		   else 
		   {
			   if(counter+1 != 1){
				   System.out.println("   Level "+(counter+1));
				   System.out.println("     Checking "+ largest);
				   System.out.println("     Checking "+ secondLarge[counter]);
			   }
			   counter++;
			   order2 = order2 *2;
			   if (array1[end-i-1] <largest && array1[end-i-1] >large2)
			   {
				   secondLarge[counter] = array1[end-i-1];
			   }
		   }
       }
	   System.out.println("   Level "+(counter+1));
	   System.out.println("     Checking "+ largest);
	   System.out.println("     Checking "+ secondLarge[counter]);//This is for the level which do not have full number.
	   //Calculate and Print the 2nd largest at each level
	   
	   
	   int second = 0;
	   for(int i =0;i<counter+1;i++)
	   {
		   if(secondLarge[i]>second)
		   {
			   second = secondLarge[i];
		   }
	   }//Calculate the 2nd largest number.
	   System.out.println("The 2nd Largest element is: "+ second);
	   //Print the 2nd largest number in the list.

 }
}
 