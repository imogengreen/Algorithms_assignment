import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ShortestPathFinder {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	       
	       GraphForShortestPath graphis= new GraphForShortestPath();
	      	       System.out.println( graphis.ShortestTrack(stop_idFrom,stop_idTo));
	       
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

	
	
	}
	


