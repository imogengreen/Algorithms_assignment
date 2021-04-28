import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DijkstraSP.java.html
public class DijkstraSP {

	public double[] distTo;
	public DirectedEdge[] edgeTo;
	public IndexMinPQ<Double> pq;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int V=countNumberOfVertices();
		Scanner input = new Scanner(System.in);
	     System.out.println("Enter the beginning bus stop");
	     while(!input.hasNext("[A-Za-z]+")){
	 	   System.out.println("Please enter a valid value.");
	 	   input.next();
	 	   }
	     String stop_From=input.nextLine();
	     int stop_idFrom = findStop_ID(stop_From);
	    
	     System.out.println("Enter the end bus stop");
	     while(!input.hasNext("[A-Za-z]+")){
		   System.out.println("Please enter a valid value.");
		   input.next();
	    }
	     String stop_To=input.nextLine();
	     int stop_idTo =findStop_ID(stop_To);
	     
	     EdgeWeightedDigraph  SP= new EdgeWeightedDigraph(V);
	     readForEdges(SP);
	     
	   // double[] distTo=dijkstraShortestPath(SP, stop_idFrom);
	     
	          
	}
	
	private static void readForEdges(EdgeWeightedDigraph SP) {
		// TODO Auto-generated method stub
		int transfer_Type=0;
	    double transfer_Time=0;
	    double cost=0;
	   
		File file = new File("transfers.txt");
		try {
		    Scanner scanner = new Scanner(file);  
		    scanner.nextLine();
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[]par = line.trim().split(",",-1);
		        int sourceId= (Integer.parseInt(par[0]));
		        int destination= (Integer.parseInt(par[1]));
	 			transfer_Type=Integer.parseInt(par[2]);
	 			 //transfer type is 2 , so reading minimum cost and
	 			 //dividing by 100
			     if(transfer_Type==2) {
			      transfer_Time=Double.parseDouble(par[3]);
	        	  cost = transfer_Time/100;
	        	 // System.out.println(cost);
	        	  SP.addEdge(sourceId,destination,cost);
	        	//  System.out.println(sourceId.id);
	        	  
			     }
			     //transfer type is 0 so cost is 2
		         if(transfer_Type==0) {
				 cost=2.0;
				 SP.addEdge(sourceId,destination,cost);
				// System.out.println(sourceId.id);
				} 		
		    }   
		}catch(IOException e){ 
			 e.printStackTrace();
		}

		// adding edges from stop_times.txt
		File file1 = new File("stop_times.txt");
		try {
		    Scanner scanner = new Scanner(file1);       
	        int previousTripId = -1;
	        int previousStop_Id = -1;

	        scanner.nextLine();
	       while(scanner.hasNextLine()) {
	       String[] currentLine = scanner.nextLine().trim().split(",",-1);
	       int currentTripId = Integer.parseInt(currentLine[0]);
	       int currentStop_Id = Integer.parseInt(currentLine[3]);

	      if (currentTripId==previousTripId)
	       {
	    	    int source= (previousStop_Id);
	    	    int destination=(currentStop_Id);
	    	    double weight=1.0;
	    	    SP.addEdge(source,destination,weight);
	    	   //System.out.println("//!hey you");
	       }
	      previousTripId = currentTripId;
	      previousStop_Id = currentStop_Id;
	     }

		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	private static int findStop_ID(String stop_Name) {
		// TODO Auto-generated method stub
		int stop_id = 0;
		String stopSearch_Name =null ;
		File file = new File("stops.txt");
		try {
		    Scanner scanner = new Scanner(file);
		    scanner.nextLine();
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[]par = line.trim().split(",");
				stop_id=Integer.parseInt(par[0]);
				stopSearch_Name=par[2];
		        if(stopSearch_Name.equalsIgnoreCase(stop_Name)) { 
		            System.out.println(stop_id);
		            break;
		        }
		    }
		} catch(IOException e) { 
			 e.printStackTrace();
		}
	return stop_id;
	}

	private static int countNumberOfVertices() {
		// TODO Auto-generated method stub
		// the max id of a bus stop has been used 
		// as it gives an ideal representation of 
		// the number of bus stops in the text files
		int stop_id = 0;
		String stopSearch_Name =null ;
		File file = new File("stops.txt");
		 int max= Integer.MIN_VALUE;
		try {
		    Scanner scanner = new Scanner(file);
		    scanner.nextLine();
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[]par = line.trim().split(",");
	 			stop_id=Integer.parseInt(par[0]);
	 			if(stop_id>max) {
	 				max=stop_id;
	 			}
		    }
		}catch(IOException e){
			e.printStackTrace();
		}
		  return max+1;
		} 
	
	public  double[] dijkstraShortestPath(EdgeWeightedDigraph digraph, int s) {

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
}
