/**
 * This class implement the user Interface
 * f
 * 
 * @author Maria Cairns
 * @version HT 2021
 */

import java.util.Scanner; 
public class Interface {
    
    public static void checkUserInput(){
    }
    public static void getShortestPath(){
        //find shortest paths bet 2 bus stops (after user inputting the bus stops )
        //returning the list of stops enroute as well as the assoicated cost
    	DijkstraSP myObj = new DijkstraSP();
    	myObj.main(null);

   }
    public static void getArrivalTime() {
        //searching for all trips within a given arrivaltime
        ///returning full details of all the trips matchign the criteria ( 0, 1 or more)
        //error checking for valid times max time allowed is 23.59.59
    	
    	System.out.println("Enter Time");
        Scanner timeInput = new Scanner(System.in);
        String userInputTime = timeInput.next();   
        searchArrivalTime.searchFile("stop_times.txt", userInputTime);
    }
    public static void getBusStop() {
        //Search for a bus stop by full name, or first few characters in the name
        //returning the full stop info fo reach stop matching the search criteria
    	System.out.println("Enter Stop");
        Scanner userStop = new Scanner(System.in);
        String userInputStop = userStop.next();   
		searchBusStop.fileBuffer("stops.txt", userInputStop); //this works		
    }
    
    public static void repeatMessage() {
        System.out.println("Enter 1 to find the shortest path between 2 bus stops of choice,\nEnter 2 to search for a bus stop by a full or partial name ");
        System.out.println("Enter 3 to search for trips within a given arrival time,\nEnter 0 to exit the program\n");
        Scanner myScanner = new Scanner(System.in);
        int input = myScanner.nextInt();
    }

    public static void main(String[] args){
        System.out.println("Enter 1 to find the shortest path between 2 bus stops of choice,\nEnter 2 to search for a bus stop by a full or partial name ");
        System.out.println("Enter 3 to search for trips within a given arrival time,\nEnter 0 to exit the program\n");
        Scanner myScanner = new Scanner(System.in);
        int input = myScanner.nextInt();
        
      
       while( input == 1 | input == 2| input == 3)  {
       
        if (input == 1){
        	//System.out.println("1 test");
            getShortestPath();
            //call to shortest path method
            break;
        } 
        if (input == 2){
            //call to search bus stop method 
        	//System.out.println("2 test");
        	getBusStop();
        	break;        	
        }  if (input == 3){
            //call to method to search trips within a given arrival time
        	//System.out.println("3 test");
        	getArrivalTime();
        	//System.out.println("3 test2");
        	break;
        }

     }
    }
}