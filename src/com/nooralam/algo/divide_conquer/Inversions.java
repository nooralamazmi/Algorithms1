package com.nooralam.algo.divide_conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * This file contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, 
 * with no integer repeated.
 * 
 * Your task is to compute the number of inversions in the file given, 
 * where the ith row of the file indicates the ith entry of an array.
 * 
 * Because of the large size of this array, you should implement the fast divide-and-conquer 
 * algorithm covered in the video lectures. 
 * 
 * The numeric answer for the given input file should be typed in the space below.
 * 
 * So if your answer is 1198233847, then just type 1198233847 
 * in the space provided without any space / commas / any other punctuation marks. 
 * 
 * @author nooralam
 *
 */

public class Inversions {

	public static final int ARRAY_MAX_SIZE = 100000;
	public static final String FILE_NAME = "IntegerArray.txt";

	public static void main(String[] args) {

		int[] array = new int[ARRAY_MAX_SIZE];
		Scanner s = null;
		int index = 0;

		try {
			s = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));
			while(s.hasNext()) {
				if(s.hasNextInt()) {
					array[index++] = s.nextInt();
				} else {
					s.next();
				}
			}

		} catch (IOException e){

			System.out.println("Exception reading file : " + FILE_NAME);
			e.printStackTrace();

		} finally {

			if(s != null) {
				s.close();
			}

		}
		
		// printArray(array, ARRAY_MAX_SIZE);
		
		long inversions = getInversions(array, 0, ARRAY_MAX_SIZE - 1);
		
		System.out.println("Total number of inversions : " +inversions);
	}


	public static void printArray(int[] array, int total) {
		for(int i=0; i < total; i++) {
			System.out.println(array[i]);
		}
	}
	
	
	
	public static long getInversions(int[] array, int low, int high) {
		
		// Base case for recursion.
		if(low >= high) { return 0; }
		
		int mid = (low + high) /2;
		
		long leftInversions  = getInversions(array, low, mid);
		long rightInversions = getInversions(array, mid + 1, high);
		
		long splitInversions = getSplitInversions(array, low, mid, high);
		
		return leftInversions + rightInversions + splitInversions;
	}
	
	
	public static long getSplitInversions(int[] array, int low, int mid, int high) {
		
		//System.out.println("Split Inversions");
		
		long inversions = 0;
		
		int[] tmpArray = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int index = 0;
		
		while(i <= mid && j <= high) {
			if(array[i] <= array[j]) {
				tmpArray[index++] = array[i++];
			} else {
				// Inversion.
				inversions += ((mid - i) + 1);
				tmpArray[index++] = array[j++];
			}
		}
		
		if(i <= mid) {
			while(i <= mid) {
				tmpArray[index++] = array[i++];
			}
		}
		
		if(j <= high) {
			while(j <= high) {
				tmpArray[index++] = array[j++];
			}
		}
		
		// Copy the tmpArray to original array
		for(i = 0; i < high - low +1; i++) {
			array[low + i] = tmpArray[i];
		}
		
		return inversions;
	}

}









