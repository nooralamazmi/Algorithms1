package com.nooralam.algo.divide_conquer;

public class MergeSort {
	

	
	
	public static void main(String[] args) {
		
		int[] array = {12, 34, 1, 7, 5, 90, 55, 9, 15, 40 };
		
		mergeSort(array, 0, 9);
		
		System.out.println("Sorted Array is ...");
		for(int i=0; i < array.length; i++) {
			System.out.print(" [" +array[i] + "] ");
		}
		System.out.println("");
	}
	
	
	public static void mergeSort(int[] array, int low, int high) {
		
		if(low >= high) {
			return;
		}
		
		int mid = (low + high) / 2;
		
		mergeSort(array, low, mid);
		mergeSort(array, mid+1, high);
		mergeSortedArrays(array, low, mid, high);
	}
	
	/*
	 * Assuming array is sorted in ascending order.
	 */
	public static void mergeSortedArrays(int[] array, int low, int mid, int high)
	{
		int[] tmpArray = new int[high - low + 1];
		
		int index = 0;
		int aIndex = low;
		int bIndex = mid+1;
		
		// Merge the arrays to tmpArray
		while(aIndex <= mid && bIndex <= high) {
			if(array[aIndex] < array[bIndex]) {
				tmpArray[index++] = array[aIndex++];
			} else {
				tmpArray[index++] = array[bIndex++];
			}
		}
		
		if(aIndex <= mid) {
			while(aIndex <= mid) {
				tmpArray[index++] = array[aIndex++];
			}
		}
		
		if(bIndex <= high) {
			while(bIndex <= high) {
				tmpArray[index++] = array[bIndex++];
			}
		}
		
		// Copy the tmpArray to original Array.
		for(int i=0; i < index; i++) {
			array[low+i] = tmpArray[i];
		}
	}

}





