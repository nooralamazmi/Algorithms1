package com.nooralam.algo.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to represent a Grpah as Adjacency List
 * @author nooralam
 *
 */
public class Graph {

	private int vertex;     // n
	private int edges;      // m
	private List<Integer>[] adjacencyList;

	public Graph(int vertex) {
		this.vertex = vertex;
		this.adjacencyList = (ArrayList<Integer>[]) new ArrayList[this.vertex + 1];
		for (int i = 1; i <= this.vertex; i++ ) {
			adjacencyList[i] = new ArrayList<Integer>();
			adjacencyList[i].add(i);
		}
	}

	// Create Graph from file input.
	public Graph(int vertex, String file) {
		this(vertex);
		addEdgesFromFile(file);
	}

	private void addEdgesFromFile(String file) {

		BufferedReader bufferedReader = null;
		Scanner scanner = null;
		String line;

		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				scanner = new Scanner(line);
				int x = scanner.nextInt();
				//System.out.print(x + "\t");
				while (scanner.hasNext()) {
					if (scanner.hasNextInt()) {
						int y = scanner.nextInt();
						addEdge(x, y);
						//System.out.print(y + "\t");
					} else {
						scanner.next();
					}
				}
				//System.out.println();
				if(scanner != null) {
					scanner.close();
					scanner = null;
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

	public int getVertexCount() { return vertex; }
	public int getEdgeCount()   { return edges;  }

	
	public void addEdge(int x, int y) {
		adjacencyList[x].add(y);
		edges++;
	}
	
	public void addUndirectedEdge(int x, int y) {
		adjacencyList[x].add(y);
		adjacencyList[y].add(x);
		edges++;
	}

	public Iterable<Integer> getAdjacencyList(int vertex) {
		return adjacencyList[vertex];
	}

}
