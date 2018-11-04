/**
 *  This class includes the method to run Quick Sort to sort the integer list, calculating the number of 
 *  key comparisons and key movements done in the sorting algorithm.
 *  
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 310 Assignment2, 2015.Nov.2nd.
 *
 */
public class QuickSort 
{
	private int list[];
	private int length;
	private int low;
	private int mid;
	private int high;
	private int compare = 0;
	private int move = 0;
	/**
	 *  This class includes the method to run Quick Sort to sort the integer list, calculating the number of 
     *  key comparisons and key movements done in the sorting algorithm.
     *  
	 * @param list2- registered as the list to be sorted
	 * @param length2-  the length of the list to be sorted
	 */
	public QuickSort(int[] list2, int length2) 
	{
		list = list2;
		length = length2;
		quickSort(list,  0,  length-1);
	}
	
	void quickSort(int[] a, int low2, int high2)
	{
		low = low2;
		high = high2;
		if(high- low >3)
		{
			compare++;
			//lowC is the low counter, highC is th high counter, pivotC is the pivot counter
			int lowC = low +1;
			int highC = high;
			int pivotC = low;
			boolean check = false;
			mid = (low+high)/2;
			median(low, mid, high);
			swap(low,mid);
			//low is the number of the pivot counter now.
;
			//Sort by pivot;
			while (lowC < highC)
			{
				compare++;
				while(!check)
				{
					//checking lowC
					if(list[lowC]> list[pivotC])
					{
						compare++;
						check = true;
						swap(lowC,highC);
						highC--;
					}
					else
					{
						compare++;
						lowC++;
					}
				}
				while (check)
				{
					//checking highC
					if(list[highC] <list[pivotC])
					{
						compare++;
						check = false;
						swap(highC, lowC);
						lowC++;
					}
					else
					{
						compare++;
						highC--;
					}
				}
			}
			//Last element to sort, confirm the pivot index
			if(list[lowC] >list[pivotC])
			{
				compare++;
				swap(lowC,pivotC);
				pivotC= lowC;
			}
			else
			{
				compare++;
				swap(lowC+1,pivotC);
			}
			//Split the list in two sub list
			quickSort(a,low, pivotC-1);
			quickSort(a, pivotC+1, high);
		}
		else if (low == high)
		{
			//1 element in the list
			compare++;
			return;	
		}
		else if (high-low == 1)
		{
			//2 elements in the list
			compare++;
			if (list[low] < list[high])
			{ 
				compare++;
			}
			else
			{
				compare++;
				swap(low,high);
			}
		}
		else
		{
			//3 elements in the list
			compare++;
			median(low,low+1,high);
		}
	}
	
	/**
	 *  This method is to swap the value of index a and index b
	 * @param a- the first index number to swap
	 * @param b- the second index number to swap
	 */
	void swap(int a, int b) 
	{
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
		move = move +3;
	}
    
	/**
	 * This method is to sort 3 elements which index is been given. The index number follow the rule: a<b<c
	 * @param a- first index to sort
	 * @param b- second index to sort
	 * @param c- third index to sort
	 */
	void median(int a, int b ,int c)
	{
		if (list[a]> list[b])
		{
			compare++;
			if(list[b]> list[c])
			{
				//c<b<a
				compare++;
				swap(a,c);
			}
			else if (list[a]> list[c])
			{
				//b<c<a
				compare++;
				swap(a,b);
				swap(b,c);
			}
			else
			{
				//b<a<c
				compare++;
				swap(a,b);
			}
		}
		else
		{
			compare++;
			if(list[b]> list[c])
			{
				compare++;
				if(list[a]> list[c])
				{
					//c<a<b
					compare++;
					swap(a,b);
					swap(a,c);
				}
				else
				{
					//a<c<b
					compare++;
					swap(b,c);
				}
			}
			else
			{
				//a<b<c
				compare++;
			}
		}
	}
	
	/**
	 * This method is to return the value of the number of comparisons done in Merge Sort.
	 * @return-return the number of comparisons done in Merge Sort
	 */
	int getCompare()
	{
		return compare;
	}
	
	/**
	 * This method is to return the value of the number of key movements done in Merge Sort.
	 * @return-return the value of the number of key movements done in Merge Sort
	 */
	int getMove()
	{
		return move;
	}
	
	/**
	 * This method is to return the sorted list.
	 * @return-return the sorted list
	 */
	int[] getResult()
	{
		return list;
	}
}

