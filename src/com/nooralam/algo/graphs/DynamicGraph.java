package com.nooralam.algo.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Graph representation as adjacency list, here the graph can change dynamically.
 * The first node in adjacency list represents the head node where as other 
 * nodes are adjacent to it.
 * @author nooralam
 *
 */
public class DynamicGraph {
	
	private int nodes;
	private int edges;
	private LinkedList<LinkedList<Integer>> adjacencyList;
	
	/*
	 * Create a graph from input file.
	 */
	DynamicGraph(String file) {
		
		adjacencyList = new LinkedList<LinkedList<Integer>>();
		
		BufferedReader bufferedReader = null;
		Scanner scanner = null;
		String line;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			
			while ((line = bufferedReader.readLine()) != null) {
				if(scanner != null) scanner.close();
				scanner = new Scanner(line);
				
				int headNode = scanner.nextInt();
				
				LinkedList<Integer> list = new LinkedList<Integer>();
				list.addLast(headNode);
				adjacencyList.addLast(list);
				nodes++;
				
				while (scanner.hasNext()) {
					if (scanner.hasNextInt()) {
						int adjacentNode = scanner.nextInt();
						addEdge(headNode, adjacentNode);
					} else {
						scanner.next();
					}
				}
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ioe) {
				}
			}
			if (scanner != null) {
				scanner.close();
			}
		}
	}
	
	
	public int nodes() { return nodes; }
	
	public int edges() { return edges; }
	
	public void addEdge(int headNode, int adjacentNode) {
		
		for (LinkedList<Integer> list : adjacencyList) {
			if (list.get(0) == headNode) {
				list.addLast(adjacentNode);
				edges++;
				return;
			}
		}
	}
	
	public LinkedList<Integer> getAdjacencyList(int nodeIndex) {
		return adjacencyList.get(nodeIndex); 
	}
	
	
	/**
	 * This function will merge two nodes and remove self loops, not parallel loops
	 * Hence the graph will be modified (Dynamic Graph)
	 * @param x -> first node to be merged.
	 * @param y -> second node to be merged.
	 */
	public void mergeNodes(int x, int y) {
		
		int xIndex = getListIndexOfHeadNode(x);
		int yIndex = getListIndexOfHeadNode(y);
		
		if (yIndex < xIndex) {
			int tmp = xIndex;
			xIndex = yIndex;
			yIndex = tmp;
			
			tmp = x; x = y; y = tmp;			
		}
		
		
		// merge adjacency list at xIndex with yIndex.
		adjacencyList.get(xIndex).addAll(adjacencyList.get(yIndex));
		adjacencyList.remove(yIndex);
		nodes--;
		
		// Replace node "y" with node "x" in entire graph.
		int index = -1;
		for (LinkedList<Integer> list : adjacencyList) {
			index = -1;
			for (Integer node : list) {
				index++;
				if (node == y) {
					list.set(index, x);
				}
			}
		}
		
		// Remove self-loops for list with head "x" (i.e x<-->x)
		index = 0;
		LinkedList<Integer> list =  adjacencyList.get(xIndex);
		int count = list.size();
		for (int i = 1; i < count; i++) {
			index++;
			if (list.get(index) == x) {
				list.remove(index);
				index--;
				edges--;
			}
		}		
	}
	
	
	private int getListIndexOfHeadNode(int headNode) {
		int listIndex = -1;
		for (LinkedList<Integer> list : adjacencyList) {
			listIndex++;
			if (list.get(0) == headNode) {
				return listIndex;
			}
		}
		return -1;
	}
}
