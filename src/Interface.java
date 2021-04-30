import java.util.Scanner; 
public class Interface {
    
    public static void checkUserInput(){
    }
   // public static void getShortestPath(){
        //find shortest paths bet 2 bus stops (after user inputting the bus stops )
        //returning the list of stops enroute as well as the assoicated cost
//    	try{
//        	DijkstraSP.altMain();
//
//    	} catch(Exception e) {
//    		System.out.print("shite");
//    	}
//    	

  //  }
//    public static void getArrivalTime() {
//        //searching for all trips within a given arrivaltime
//        ///returning full details of all the trips matchign the criteria ( 0, 1 or more)
//        //error checking for valid times max time allowed is 23.59.59
//    	
//        String userInputTime = "23:59:40";	//***hard coded need to change to be the users input with error checking
//        searchArrivalTime.searchFile("stop_times.txt", userInputTime);
//    }
//    public static void getBusStop() {
//        //Search for a bus stop by full name, or first few characters in the name
//        //returning the full stop info fo reach stop matching the search criteria
//		//searchBusStop.fileBuffer("stops.txt", "STEVESTON"); //this works
//		searchBusStop.fileBuffer("stops.txt", "STEVESTON HWY AT 8700"); //this works
//    }

    public static void main(String[] args){

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter 1 to find the shortest path between 2 bus stops of choice,\nEnter 2 to search for a bus stop by a full or partial name ");
        System.out.println("Enter 3 to search for trips within a given arrival time,\nEnter 0 to exit the program\n");
        
    }
        
//        try {
//        //	getShortestPath();	//this does NOT WORK *******************
//        } catch(Exception e) {
//        	System.out.print(" fuck");
//        }
        //getArrivalTime();	//this works here
    	//System.out.println("yeowwwww");		//**does not print out
    	//getArrivalTime();
    	//getBusStop();

       // String input = myScanner.nextLine();

    	//System.out.println("yeowwwww");
    	
//        if(input == "0"){
//            //loop back to original questions
//        } 
//        if (input == "1"){
//        	System.out.println("yeow");
//            getShortestPath();
//            //call to shortest path method
//        } 
//        if ( input == "2"){
//            //call to search bus stop method 
//        } if (input == "3"){
//            //call to method to search trips within a given arrival time
//        }


        //error checking for bad inputs 
        // and return messages for if the stop does not exist etc, wrong format for the time for bus stop (eg letters instead of numbers), no route possible ec

    //}

//}
}
//************************

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// V stands for vertices or number of nodes in the graph (number of stops)
//		int V = countNumberOfVertices();
//
//		int startBusStop = enterStartBusStop();
//		int destBusStop = enterDesttBusStop();
//		// the shortest path graph object is created
//		EdgeWeightedDigraph SP = new EdgeWeightedDigraph(V);
//
//		// adding the edges to the graph
//		readForEdges(SP);
//
//		DijkstraSP dij = new DijkstraSP();
//
//		// finds the cost / distance of the shortest path
//		dij.dijkstraShortestPath(SP, startBusStop);
//		System.out.println("The shortest path cost between two stops is :" + dij.distTo(destBusStop));
//		dij.pathTo(destBusStop);
//
//	}
//
//	/**
//	 * @param void
//	 * @return int: returns bus stop id of the stop you wish to
//	 *              start from.
//	 * @throws IOException
//	 */
//	public static int enterStartBusStop() {
//
//		// to get user input as in for the user to enter the name of beginning stop
//		// with some basic error handing so the user doesn't use numerics or so
//		Scanner input = new Scanner(System.in);
//		System.out.println("Enter the beginning bus stop");
//		while (!input.hasNext("[A-Za-z]+")) {
//			System.out.println("Please enter a valid value.");
//			input.next();
//		}
//		String stop_From = input.nextLine();
//		// finds stop_id related to the name entered by the user
//		int stop_idFrom = findStop_ID(stop_From);
//		return stop_idFrom;
//	}
//
//	/**
//	 * @param void
//	 * @return int: returns the destination bus stop of the route
//	 * @throws IOException
//	 */
//	public static int enterDesttBusStop() {
//
//		Scanner input = new Scanner(System.in);
//
//		// to get user input as in for the user to enter the name of final/destination
//		// stop
//		// with some basic error handing so the user doesn't use numerics or so
//		System.out.println("Enter the end bus stop");
//		while (!input.hasNext("[A-Za-z]+")) {
//			System.out.println("Please enter a valid value.");
//			input.next();
//		}
//		String stop_To = input.nextLine();
//		// finds stop_id related to the name entered by the user
//		int stop_idTo = findStop_ID(stop_To);
//		return stop_idTo;
//	}
