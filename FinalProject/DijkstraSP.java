/**
 * This class contains methods that implement the cost of going from the 
 * start bus stop to the destination bus stop and lists the bus stops en route
 * 
 * @authors Khushboo Jain(implemented all the classes in relation to question 1)
 *          Alexandra Ichim(printed out bus stop routes)
 * @version HT 2021
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
//https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DijkstraSP.java.html
 * The code has been based of the book by Robert Sedgewick and Kevin Wayne
 * This class implements the Djikstra algorithm for finding the shortest
 * path between two nodes/ vertices as  they are no negative cycles  
 * 
 *
 */
public class DijkstraSP {

	public double[] distTo;
	public DirectedEdge[] edgeTo;
	public IndexMinPQ<Double> pq;
	public static HashMap<Integer, String> hashmap = new HashMap<Integer, String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//V stands for vertices or number of nodes in the graph (number of stops)
		int V = countNumberOfVertices();
		// to get user input as in for the user to enter the name of beginning stop
		//with some basic error handing so the user doesn't use numerics or so  
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the beginning bus stop");
		while (!input.hasNext("[A-Za-z]+")) {
			System.out.println("Please enter a valid value.");
			input.next();
		}
		String stop_From = input.nextLine();
		//finds stop_id related to the name entered by the user  
		int stop_idFrom = findStop_ID(stop_From);
		
		
		// to get user input as in for the user to enter the name of final/destination stop
	    //with some basic error handing so the user doesn't use numerics or so  
		System.out.println("Enter the end bus stop");
		while (!input.hasNext("[A-Za-z]+")) {
			System.out.println("Please enter a valid value.");
			input.next();
		}
		String stop_To = input.nextLine();
		//finds stop_id related to the name entered by the user  
		int stop_idTo = findStop_ID(stop_To);
		

		//the shortest path graph object is created 
		EdgeWeightedDigraph SP = new EdgeWeightedDigraph(V);
		
		// adding the edges to the graph  
		readForEdges(SP);

		DijkstraSP dij = new DijkstraSP();
		
		
		//finds the cost / distance of the shortest path 
		dij.dijkstraShortestPath(SP, stop_idFrom);
		System.out.println("The shortest path cost between two stops is :" + dij.distTo(stop_idTo));
		dij.pathTo(stop_idTo);

	}
	/**
	 * @param EdgeWeightedDigraph SP : the graph object 
	 * @return void: the function reads the two files transfers.txt and 
	 *               stop_times.txt and accordingly adds the edges  
	 * @throws IOException
	 */
	

	private static void readForEdges(EdgeWeightedDigraph SP) {
		int transfer_Type = 0;
		double transfer_Time = 0;
		double cost = 0;

		//here the transfers.txt contains the from and to stop IDs
		//and also cost so accordingly the edges are being added between two 
		//stops. There are two kinds of cost based on transfer type 
		// 2 for transfer type and minimum time /100
		File file = new File("transfers.txt");
		try {
			Scanner scanner1 = new Scanner(file);
			scanner1.nextLine();
			while (scanner1.hasNextLine()) {
				String line = scanner1.nextLine();
				String[] par = line.trim().split(",", -1);
				int sourceId = (Integer.parseInt(par[0]));
				int destination = (Integer.parseInt(par[1]));
				transfer_Type = Integer.parseInt(par[2]);
				// transfer type is 2 , so reading minimum cost and
				// dividing by 100
				if (transfer_Type == 2) {
					transfer_Time = Double.parseDouble(par[3]);
					cost = transfer_Time / 100;
					// System.out.println(cost);
					SP.addEdge(sourceId, destination, cost);
					// System.out.println(sourceId.id);

				}
				// transfer type is 0 so cost is 2
				if (transfer_Type == 0) {
					cost = 2.0;
					SP.addEdge(sourceId, destination, cost);
					// System.out.println(sourceId.id);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// adding edges from stop_times.txt
		// here the edges are based on comparison of the trip_ids
		//if two consecutive trip ids match 
		// then the previous stop id is the from stop
		// the current stop id is the to stop 
		// cost is a constant of 1 
		File file1 = new File("stop_times.txt");
		try {
			Scanner scanner2 = new Scanner(file1);
			int previousTripId = -1;
			int previousStop_Id = -1;

			scanner2.nextLine();
			while (scanner2.hasNextLine()) {
				String[] currentLine = scanner2.nextLine().trim().split(",", -1);
				int currentTripId = Integer.parseInt(currentLine[0]);
				int currentStop_Id = Integer.parseInt(currentLine[3]);

				if (currentTripId == previousTripId) {
					int source = (previousStop_Id);
					int destination = (currentStop_Id);
					double weight = 1.0;
					SP.addEdge(source, destination, weight);
					// System.out.println("//!hey you");
				}
				previousTripId = currentTripId;
				previousStop_Id = currentStop_Id;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * @param String stop_Name : the stop Name 
	 * @return int stop_id: this function returns the stop_id corresponding to the stop name  
	 *                      after searching through the stops.txt file 
	 * @throws IOException
	 */
	private static int findStop_ID(String stop_Name) {

		int stop_id = 0;
		String stopSearch_Name = null;
		File file = new File("stops.txt");
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] par = line.trim().split(",");
				stop_id = Integer.parseInt(par[0]);
				stopSearch_Name = par[2];
				hashmap.put(stop_id, stopSearch_Name);
				if (stopSearch_Name.equalsIgnoreCase(stop_Name)) {
					System.out.println(stop_id);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stop_id;
	}
	
	/**
	 * 
	 * @return int max: this function returns the max id number of vertice or stop_id so the 
	 *                  graph can be made 
	 * @throws IOException
	 */
	

	private static int countNumberOfVertices() {
		// TODO Auto-generated method stub
		// the max id of a bus stop has been used
		// as it gives an ideal representation of
		// the number of bus stops in the text files
		int stop_id = 0;
		String stopSearch_Name = null;
		File file = new File("stops.txt");
		int max = Integer.MIN_VALUE;
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] par = line.trim().split(",");
				stop_id = Integer.parseInt(par[0]);
				if (stop_id > max) {
					max = stop_id;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return max + 1;
	}
	
	/**
	 * @param EdgeWeightedDigraph digraph: the graph object 
	 * @param int s: the beginning stop Name 
	 * @return int stop_id: this function returns an array which consist of distances
	 *                      from the given source or beginning node to the 
	 *                      other nodes /vertices/stops
	 * 
	 */

	public double[] dijkstraShortestPath(EdgeWeightedDigraph digraph, int s) {

		distTo = new double[digraph.V()];
		edgeTo = new DirectedEdge[digraph.V()];

		for (int v = 0; v < digraph.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;

		distTo[s] = 0.0;
		// relax vertices in order of dist from s
		pq = new IndexMinPQ<Double>(digraph.V());
		pq.insert(s, distTo[s]);

		while (!pq.isEmpty()) {
			int v = pq.delMin();

			for (DirectedEdge e : digraph.adj(v)) {
				relax(e);
			}
		}
		return distTo;
	}
	
	/**
	 * @param DirectedEdge e: its the edge  
	 * 
	 * @return void: this function relaxes the edges accordingly 
	 */

	public void relax(DirectedEdge e) {

		int v = e.getFrom(), w = e.getTo();

		if (distTo[w] > distTo[v] + e.getCost()) {
			distTo[w] = distTo[v] + e.getCost();
			edgeTo[w] = e;

			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			}

			else {
				pq.insert(w, distTo[w]);
			}
		}
	}

	/**
	 * @param int v: the final / destination stopId 
	 *
	 * @return  double distTo[v]: This function returns the distance to the 
	 *                            destination stop 
	 */

	public double distTo(int v) {
		return distTo[v];
	}

	/**
	 * @param v: the destination bus stop 
	 * @return void: the function checks to see whether or not the bus stop is valid
	 * @throws IOException
	 */
	public void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex" + v + "is not between 0 and" + (V - 1));
	}

	/**
	 * @param v: the destination bus stop 
	 * @return boolean: checking to see if there exists a path between the start and 
	 *                  destination bus stop
	 * @throws IOException
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/**
	 * @param v: the destination bus stop 
	 * @return void: the function prints out all the bus stops on the route from the 
	 *               starting bus stop to the destination bus stop
	 * @throws IOException
	 */
	public void pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v))
			System.out.println("no path to be found");
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.getFrom()]) {
			path.push(e);
		}

		//printing out info about each of the bus stop on the route
		while (path.size() > 1) {
			if (hashmap.containsKey(path.peek().getFrom())) {
				System.out.println(
						"Stop name: " + hashmap.get(path.peek().getFrom()) + " Stop id: " + path.pop().getFrom());
			} else {
				System.out.println("Stop name: Name not found" + " Stop id: " + path.pop().getFrom());
			}
		}

		//printing out the stop information before we reach destination stop
		DirectedEdge lastEdge = path.pop();
		if (hashmap.containsKey(lastEdge.getFrom())) {
			System.out.println("Stop name: " + hashmap.get(lastEdge.getFrom()) + " Stop id: " + lastEdge.getFrom());
		} else {
			System.out.println("Stop name: Name not found" + " Stop id: " + lastEdge.getFrom());
		}

		//printing out the destination stop information 
		if (hashmap.containsKey(lastEdge.getTo())) {
			System.out.println("Stop name: " + hashmap.get(lastEdge.getTo()) + " Stop id: " + lastEdge.getTo());
		} else {
			System.out.println("Stop name: Name not found" + " Stop id: " + lastEdge.getTo());
		}
	}
}