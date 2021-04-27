import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class  GraphForShortestPath {
	
	public int V =countNumberOfVertices();
	public double[][] graph;
	//V stands for number of vertices in the graph or stops  
	GraphForShortestPath(){
	    	graph=new double[V][V];
			for(int i=0; i<V; i++){
	            for(int j=0; j<V; j++){
	                graph[i][j]=Double.NaN;
	            }
	        }
		intialize();	
	}
	
	public void insertEdge(Edge newE) {
		graph[newE.from][newE.to]=newE.cost;
	}
	private void intialize() {
		
		
		// adding respective costs weighted edges between nodes
		//from transfers.txt
//		int stopId_To=0;
//		int stopId_From=0;
		int transfer_Type=0;
        double transfer_Time=0;
	    double cost=0;
	   
		File file = new File("transfers.txt");
		try {
		    Scanner scanner = new Scanner(file);  
		    scanner.nextLine();
		    Edge temp = new Edge();
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[]par = line.trim().split(",");
		        temp.to=Integer.parseInt(par[0]);
		        temp.from = Integer.parseInt(par[1]);
     			transfer_Type=Integer.parseInt(par[2]);
     			 //transfer type is 2 , so reading minimum cost and
     			 //dividing by 100
    		     if(transfer_Type==2) {
    		      transfer_Time=Double.parseDouble(par[3]);
	        	  temp.cost = transfer_Time/100;
	        	  insertEdge(temp);
    		     }
    		     //transfer type is 0 so cost is 2
		         if(transfer_Type==0) {
				 temp.cost=2;
				 insertEdge(temp);
				 //System.out.println(temp.to);
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
            Edge temp1 = new Edge();
           while(scanner.hasNextLine()) {
           String[] currentLine = scanner.nextLine().trim().split(",");
           int currentTripId = Integer.parseInt(currentLine[0]);
           int currentStop_Id = Integer.parseInt(currentLine[3]);

          if (currentTripId==previousTripId)
           {
        	    temp1.from=previousStop_Id;
        	    temp1.to=currentStop_Id;
        	    temp1.cost=1;
        	    insertEdge(temp1);
        	   // System.out.println(temp1.to);
           }
          previousTripId = currentTripId;
          previousStop_Id = currentStop_Id;
         }
 
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public String ShortestTrack(int stop_idFrom, int stop_idTo) {
		
		if(stop_idFrom == stop_idTo) {
			
			return ("You are at the same stop so cost is zero");
		}
		boolean visited[] = new boolean[V];
    	double distanceTo[] = new double[V];
    	int edgeTo[] = new int[V];
    	
    	for(int i = 0; i < V; i++) {
    		if(i != stop_idFrom)
    		{
    			distanceTo[i] = Double.POSITIVE_INFINITY;
    		}
    	}
	
		visited[stop_idFrom] = true;// as starting node is already visited 
    	distanceTo[stop_idFrom] = 0; // starting node distance to itself is zero
    	int startStop = stop_idFrom;
    	int totalVerticesVisited=0;
    	
    	while(totalVerticesVisited<V) {
    		for(int i = 0; i < graph[startStop].length; i ++) {
    			if( !Double.isNaN(graph[startStop][i]) && visited[i] == false) {
        			relaxEdge(startStop, i, distanceTo, edgeTo,graph);
        		}
    		}
    		
    	
    	visited[startStop] = true;
		
		
		double shortestDist = Integer.MAX_VALUE;
		for(int i = 0; i < distanceTo.length; i++) {
			if(visited[i] != false && shortestDist > distanceTo[i]) {
				startStop = i;
				shortestDist = distanceTo[i];
			}
		}
		
		totalVerticesVisited++;
	}
    	
    	if(distanceTo[stop_idTo]==Double.POSITIVE_INFINITY) {
    		return "not existent";
    	}
    	
    	int Start = stop_idFrom;
    	int Stop =  stop_idTo;
    	String path = "";
    	while(Start!= Stop) {
    		path = edgeTo[Start] + path;
    		Stop = edgeTo[Stop];
    	}
    	path= path + " " + Stop ;
    	
    	return ("cost is :" + distanceTo[stop_idTo] + " through " + path);
   
    	
	}
	
	private void relaxEdge(int startStop, int endStop, double[] distanceTo, int[] edgeTo, double[][] matrixD) {
		// TODO Auto-generated method stub
		if(distanceTo[endStop] > distanceTo[startStop] + matrixD[startStop][endStop]) {
    		distanceTo[endStop] = distanceTo[startStop] + matrixD[startStop][endStop];
    		edgeTo[endStop] = startStop;
		}
	}

	private int countNumberOfVertices() {
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

}
