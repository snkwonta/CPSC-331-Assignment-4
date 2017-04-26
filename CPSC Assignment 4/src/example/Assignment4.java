package example;
import java.io.*;
import java.util.*;

public class Assignment4 {

	public static void main(String[] args) throws FileNotFoundException {
		//Section 1
		//Precondition: user input for vertices and file path
		//Ask user for input of number of vertices and the filename to read from and store user input into variables
		Scanner sc = new Scanner(System.in);									
		System.out.print("Please enter the number of vertices: ");
		int vert = Integer.parseInt(sc.nextLine());
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter the csv file to read from: ");
		String path = sc1.nextLine();
		
		
		String line=null;
		ArrayList<Integer> integer = new ArrayList<Integer>();
		ArrayList<Integer> integer1 = new ArrayList<Integer>();
		ArrayList<Integer> integer3 = new ArrayList<Integer>();
		ArrayList<Integer> integer4 = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while((line = br.readLine()) !=null){
					String[] nodes = line.split(",");
					String num = nodes[0];
					String num1 = nodes[1];
					integer.add(Integer.parseInt(num));
					integer.add(Integer.parseInt(num1));
					integer3.add(Integer.parseInt(num));
					integer4.add(Integer.parseInt(num1));
			}
		}
		catch(IOException ex){
			System.out.println("err happend");
		}
		//Section 2
		//Precondition: array of vertices
		//Postcondition: MPV, LPV, and their neighbors
		//code to find the number of occurrences for each number in the given file
		//when the largest number is found, that becomes the largest and when the smallest is found, that becomes the smallest
		int counter1 = 0;
		while(counter1 < vert){
			Object obj = Collections.frequency(integer, counter1);
			counter1++;
			integer1.add((Integer) obj);
		}
		
		//Once they are found, the array is looped through to find the largest and smallest number of occurrences for all numbers, 
		//and those are the number of edges for the MPV and LPV. 
	      int smallest = integer1.get(0);
          int largest = integer1.get(0);
         
          for(int i=1; i< integer1.size(); i++)
          {
                  if(integer1.get(i) > largest)
                          largest = integer1.get(i);
                  else if (integer1.get(i) < smallest)
                          smallest = integer1.get(i);
                 
          }
		  System.out.println("Number of neighbors for MPV: " + largest);
		
		System.out.println("MPV, Neighbors");
		int f = 0;
		int x = 0;
		int y = x + 1;
		  while(f < integer1.size()){
			  if(integer1.get(f) == largest){
				  System.out.print(f);
				  while(x < integer.size() && y < integer.size()){
					  if(integer.get(x) == f){
						  System.out.print(", " + integer.get(y));
					  } else if(integer.get(y) == f){
						  System.out.print(", " + integer.get(x));
					  }
					  x+=2;
					  y+=2;
				  }
				  x = 0;
				  y = x+1;
				  System.out.println(" ");
			  }
			  f++;
		  }
		
		System.out.println("\nNumber of neighbors for LPV: " + smallest);
		
		// code to print out the number of neighbors for the least popular vertice(s)
		System.out.println("LPV, Neighbors");
		int g = 0;
		int h = 0;
		int q = h + 1;
		  while(g < integer1.size()){
			  if(integer1.get(g) == smallest){
				  System.out.print(g);
				  while(h < integer.size() && q < integer.size()){
					  if(integer.get(h) == g){
						  System.out.print(", " + integer.get(q));
					  } else if(integer.get(q) == g){
						  System.out.print(", " + integer.get(h));
					  }
					  h+=2;
					  q+=2;
				  }
				  h = 0;
				  q = h+1;
				  System.out.println(" ");
			  }
			  g++;
		  }
		  	// code for writing to path in case there is a mistake in input
			for(int it = path.length()-1; it>=0; it--){
				if(path.charAt(it) == File.separatorChar){
					path = path.substring(0, it+1);
					break;
				}
			}
			//Section 3
			//Precondition: vertices in a list
			//Postcondition: adjacency matrix written to a file
			// code to write adjacency matrix to a file and overwrite it if there is another file with the same name
		try{
			String writeto = path + "AdjacencyMatrix.csv";
			PrintWriter write1 = new PrintWriter(writeto, "UTF-8");
			
			// print the numbers across the csv file based on the number of vertices
			write1.print("X");
			int num2 = 0;
			while(num2 < vert){
				write1.print(", "+ num2);
				num2++;
			}
			
			//Print the number of vertices vertically, then the lists are put side by side and if they have an edge with each other, 
			//the adjacency matrix is set ad the number 1 otherwise it is left at the number zero. 
			write1.println();
			final int n = vert;
			int[][] adjMatrix = new int[n][n];
			int m = integer3.size();
		    for(int i=0; i<m; i++){
		        int u = integer3.get(i);
		        int v = integer4.get(i);
		        adjMatrix[u][v] = 1;
		        adjMatrix[v][u] = 1;
		    }
 			for(int i=0; i<vert; i++){
		            for(int k=0; k<vert; k++){
		                write1.print(", " + adjMatrix[i][k] + "");
		            }
		            write1.println(); 
				}
		    	
	        write1.println();
			write1.close();																//close the writer
		}catch(IOException a){															//catch block for errors
			System.out.println("Could not write adjacency matrix");						//error message
		}
		//Section 4
		//Precondition: vertices in a list
		//Postcondition: adjacency list written to a file
		// code to write adjacency list to a file and overwrite it if there is another file with the same name
		try{
			String writeto1 = path + "AdjacencyList.csv";
			PrintWriter write2 = new PrintWriter(writeto1, "UTF-8");
			int t = 0;
			x = 0;
			y = x + 1;
			
			//all the vertices are printed in a list and then a counter loops through the ArrayList holding all the vertices looking for a specific number.
			//They are compared in twos. 
			while(t < vert){
					  write2.print(t);
					  while(x < integer.size() && y < integer.size()){
						  if(integer.get(x) == t){
							 write2.print(", " + integer.get(y));
						  } else if(integer.get(y) == t){
							  write2.print(", " + integer.get(x));
						  }
						  x = x+2;
						  y +=2;
					  }
					  x = 0;
					  y = x+1; 
					  write2.println(" ");
				  t++;
			  }	
			write2.close();																	//close the writer
		}
		catch(IOException a){																//catch block for errors
			System.out.println("Could not write adjacency list");							//error message
		}
	}
}