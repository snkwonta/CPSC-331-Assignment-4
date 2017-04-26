package example;

import java.util.*;

public class Node {
	public int key;
	public int numNeighbors;
	public ArrayList<Integer> list = new ArrayList<Integer>();
	
	public Node(int key1){
		key = key1;
		numNeighbors = 0;
		list = new ArrayList<Integer>();
	}
	
	public int getKey(){
		return key;
	}
	
	public int numofNeighbors(){
		return numNeighbors;
	}
	
	public ArrayList<Integer>getNeighbours() {
		return list;
	}
	
	public void addNeighbors(Node i) {
		numNeighbors++;
		list.add(i.getKey());
	}
}

