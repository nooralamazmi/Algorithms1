package com.nooralam.algo.graphs;

import java.util.Random;


/**
 * Download the text file here. (Right click and save link as) ("kargerMinCut.txt")
 * 
 * The file contains the adjacency list representation of a simple undirected graph. 
 * There are 200 vertices labeled 1 to 200. 
 * 
 * The first column in the file represents the vertex label, 
 * and the particular row (other entries except the first column) tells all the vertices 
 * that the vertex is adjacent to. So for example, the 6th row looks 
 * like : "6 155 56 52 120 ......". 
 * This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) 
 * the vertices with labels 155,56,52,120,......,etc
 * 
 * Your task is to code up and run the randomized contraction algorithm for the min cut problem 
 * and use it on the above graph to compute the min cut. 
 * (HINT: Note that you'll have to figure out an implementation of edge contractions. 
 * Initially, you might want to do this naively, creating a new graph from the old every 
 * time there's an edge contraction. But you should also think about more efficient implementations.) 
 * 
 * (WARNING: As per the video lectures, please make sure to run the algorithm many 
 * times with different random seeds, and remember the smallest cut that you ever find.) 
 * Write your numeric answer in the space provided. So e.g., 
 * if your answer is 5, just type 5 in the space provided.
 * 
 * 
 * @author nooralam
 *
 */

public class KargerMinCut {


	public static void main(String[] args) {

		DynamicGraph dynamicGraph = new DynamicGraph("kargerMinCut.txt");
		int totalNodes = dynamicGraph.nodes();

		int min = Integer.MAX_VALUE;

		int iteration = 0;
		while (iteration < totalNodes) {

			Random randomGenerator = new Random();

			while (dynamicGraph.nodes() > 2) {

				int numNodes = dynamicGraph.nodes();
				int index = randomGenerator.nextInt(numNodes);

				int x = dynamicGraph.getAdjacencyList(index).get(0);
				int y = dynamicGraph.getAdjacencyList(index).
						get(randomGenerator.nextInt(dynamicGraph.getAdjacencyList(index).size() - 1) + 1);

				dynamicGraph.mergeNodes(x, y);
			}

			int numCrossingEdges = dynamicGraph.getAdjacencyList(0).size() - 1;
			if (numCrossingEdges != dynamicGraph.getAdjacencyList(1).size() - 1) {
				System.out.println("## SOME ERROR ##");
			}
			
			if (numCrossingEdges < min) {
				min = numCrossingEdges;
				System.out.println("Min cut in Cut value in iteration : " +iteration + " : " +  
						 +numCrossingEdges);
			}
			
			dynamicGraph = new DynamicGraph("kargerMinCut.txt");
			iteration++;
		}
		
		System.out.println("Min Cut of this graph is : " +min);

		/*
		 // Display the Graph
		for (int i = 0; i < dynamicGraph.nodes(); i++) {
			Iterable<Integer> list = dynamicGraph.getAdjacencyList(i);
			for (Integer node : list) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
		*/

	}

}
