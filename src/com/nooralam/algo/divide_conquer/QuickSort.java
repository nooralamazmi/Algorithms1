package com.nooralam.algo.divide_conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * The file (QuickSort.txt)  contains all of the integers between 1 and 10,000 
 * (inclusive, with no repeats) in unsorted order. 
 * The integer in the ith row of the file gives you the ith entry of an input array.
 * 
 * Your task is to compute the total number of comparisons used to sort the given input file by QuickSort. 
 * As you know, the number of comparisons depends on which elements are chosen as pivots, 
 * so we'll ask you to explore three different pivoting rules.
 * 
 * You should not count comparisons one-by-one. 
 * Rather, when there is a recursive call on a subarray of length m, you should simply add m−1 
 * to your running total of comparisons. 
 * (This is because the pivot element is compared to each of the other m−1 elements in the 
 * subarray in this recursive call.)
 *  
 * @author nooralam
 *
 */

public class QuickSort {
	
	public static final String FILE_NAME = /*"tempTest.txt";*/ "QuickSort.txt";
	public static final int ARRAY_MAX_SIZE = /*11;*/ 10000;
	
	private static int comparisonsCount = 0; 
	
	
	public static void main(String[] args) {
		
		int[] array = getInputNumbers(FILE_NAME);
		if(array == null) return;
		
		//System.out.println("Original Array ...");
		//displayArray(array, ARRAY_MAX_SIZE);
		
		quickSort(array, 0, ARRAY_MAX_SIZE - 1);
		
		//System.out.println("Sorted Array ...");
		//displayArray(array, ARRAY_MAX_SIZE);
		
		System.out.println("Total number of comparisions : " +comparisonsCount);		
	}
	
	
	
	
	private static void quickSort(int[] array, int low, int hi) {
		
		// Base case for recursion.
		if(low >= hi) return;
		
		comparisonsCount += (hi - low);
		int partationIndex = getPartationIndex(array, low, hi);
		
		quickSort(array, low, partationIndex - 1);
		quickSort(array, partationIndex + 1, hi);
	}
	
	
	/*
	 * PARTATION the array around the pivot and return its index.
	 */
	private static int getPartationIndex(int[] array, int low, int hi) {
		
		// Using median of three elements, swap the media with the first element in array.
		int medianIndex = getMediaIndex(array, low, hi);
		swap(array, low, medianIndex);
		
		int pivot = array[low];
		
		int i = low + 1;
		
		for (int j = low + 1; j <= hi; j++) {
			if(array[j] < pivot) {
				swap(array, i, j);
				i++;
			}
		}
		
		swap(array, low, i-1);
		
		return i - 1;
	}
	
	
	
	/*
	 *  Compute the number of comparisons (as in Problem 1), using the "median-of-three" pivot rule. 
	 *  [The primary motivation behind this rule is to do a little bit of extra work to get 
	 *  much better performance on input arrays that are nearly sorted or reverse sorted.] 
	 *  
	 *  In more detail, you should choose the pivot as follows. 
	 *  Consider the first, middle, and final elements of the given array. 
	 *  (If the array has odd length it should be clear what the "middle" element is; 
	 *  for an array with even length 2k, use the kth element as the "middle" element. 
	 *  So for the array 4 5 6 7, the "middle" element is the second one ---- 5 and not 6!) 
	 *  Identify which of these three elements is the median 
	 *  (i.e., the one whose value is in between the other two), 
	 *  and use this as your pivot.
	 *   
	 *  As discussed in the first and second parts of this programming assignment, 
	 *  be sure to implement Partition exactly as described in the video lectures 
	 *  (including exchanging the pivot element with the first element just before the main 
	 *  Partition subroutine).
	 *  
	 *  EXAMPLE: For the input array 8 2 4 5 7 1 you would consider 
	 *  the first (8), middle (4), and last (1) elements; 
	 *  since 4 is the median of the set {1,4,8}, you would use 4 as your pivot element.
	 *  
	 *  SUBTLE POINT: A careful analysis would keep track of the comparisons made in identifying the 
	 *  median of the three candidate elements. You should NOT do this. 
	 *  That is, as in the previous two problems, 
	 *  you should simply add m−1 to your running total of comparisons every time you recurse 
	 *  on a subarray with length m. 
	 */
	private static int getMediaIndex(int[] array, int low, int hi) {
		
		int numberOfElements = hi - low + 1;
		if(numberOfElements == 2) 
			return low;
	
		int aIndex = low;
		int bIndex = low + ((hi - low) / 2 );
		int cIndex  = hi;
		
		if(array[aIndex] > array[bIndex]) {
			if(array[bIndex] > array[cIndex]) return bIndex;
			if(array[aIndex] > array[cIndex]) return cIndex;
			return aIndex;
		} else {
			if(array[bIndex] < array[cIndex]) return bIndex;
			if(array[aIndex] < array[cIndex]) return cIndex;
			return aIndex;
		}
	}
	
	
	
	/*
	 * SWAP two numbers.
	 */
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	
	/*
	 * READ the input file to get the numbers to sort.
	 */
	private static int[] getInputNumbers(String fileName) {
		
		int[] array = new int[ARRAY_MAX_SIZE];
		Scanner s = null;
		int index = 0;
		
		try {
			
			s = new Scanner(new BufferedReader(new FileReader(fileName)));
			while(s.hasNext()) {
				if(s.hasNextInt()) {
					array[index++] = s.nextInt();
				} else {
					s.next();
				}
			}
		
		} catch (IOException e) {
			
			System.out.println("Exception reading file : " +fileName);
			e.printStackTrace();
			return null;
			
		} finally {
			if(s != null) {
				s.close();
			}
		}
		
		return array;
	}
	
	
	private static void displayArray(int[] array, int count) {
		
		for (int i = 0; i < count; i++)
			System.out.println(array[i]);
	}

}

