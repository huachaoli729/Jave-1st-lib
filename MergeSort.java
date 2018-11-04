/**
 *  This class includes the method to run Merge Sort to sort the integer list, calculating the number of 
 *  key comparisons and key movements done in the sorting algorithm.
 * 
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 310 Assignment2, 2015.Nov.2nd.
 * 
 */

public class MergeSort 
{
	private int mid;
	private int list[];
	private int tmp[];
	private int length;
	private int compare = 0;
	private int move = 0;
	
    /**
     *  This method includes the method to run Merge Sort to sort the integer list, calculating the number of 
     *  key comparisons and key movements done in the sorting algorithm.
     *  
     * @param list2- registered as the list to be sorted
     * @param length2- the length of the list to be sorted
     */
	public MergeSort(int[] list2, int length2) 
	{
		list = list2;
		length = length2;
		tmp = new int[length];
		mergeSort(list, tmp,  0,  length - 1);
	}
	
	/**
	 *  This method is to split the list in half and to merge the two list together
	 * @param a- the list to sort
	 * @param tmp- the duplicated list for the list to be sorted
	 * @param low- the lowest index of the list
	 * @param high- the highest index of the list
	 */
	void mergeSort(int[] arr, int[] tmp2, int low, int high)
	{
		list = arr;
		tmp = tmp2;
		if( low == high )
		{
			return;
		}
		else
		{
			compare++;
			mid = (low + high) / 2;
			//Split the list in half
			mergeSort(list, tmp, low, mid);
			mergeSort(list, tmp, mid + 1, high);
			//merge the list
			merge(low, mid, high);
		}
	}
	
	/**
	 *  This is the method is the key operations to merge the two sorted list together.
	 * @param low- the start index of the first half of the list to merge
	 * @param mid- the end index of the first half of the list to merge
	 * @param high- the end index of the first half of the list to merge
	 * @return 
	 */
	 void merge(int low, int mid, int high )
	{
		for (int i = low; i < high+1; i++) 
		{
            tmp[i] = list[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i < mid+1 && j < high+1) 
        {
        	compare = compare+2;
            if (tmp[i] < tmp[j]) 
            {
                compare++;
                list[k] = tmp[i];
                i++;
            } 
            else 
            {
            	compare++;
                list[k] = tmp[j];
                j++;
            }
            k++;
        }
        while (i < mid+1) 
        {
        	compare++;
            list[k] = tmp[i];
            k++;
            i++;
        }
        while(j < high+1)
        {
        	compare++;
            list[k] = tmp[j];
            k++;
            j++;
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
