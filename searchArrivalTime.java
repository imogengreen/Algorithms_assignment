/*
* @author Imogen Green
* @Hilary Term 2021
*
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.Scanner;

//must take the second column as this contains our arrival times
public class searchArrivalTime {
    public static void searchFile(String filename) {

        if (filename != null) {
            // create our HashMap
            // NB: if we don't check for the presence of the key, our value will be overwritten --> create an ArrayList to store objects
            HashMap<String, ArrayList<scheduleInformation>> allSchedules = new HashMap<String, ArrayList<scheduleInformation>>();
            ArrayList<scheduleInformation> scheduleList;
            try {
                // read in the data from the input file stop_times.txt
                FileReader data = new FileReader(filename);
                BufferedReader br = new BufferedReader(data);
                String line = br.readLine();
                while (line != null) { 
                    
                    // create an array for information about every schedule in the file
                    String[] scheduleInfo = line.split(",", -1);  
                    
                    // need to check if arrival time is valid (at index 1 in file)
                    String checkTime = scheduleInfo[1];
                    //System.out.println(checkTime);
                    String[] arrivalTimeCheck = checkTime.split(":"); // creates an array with hh in index 0, mm in index 1, ss in index 2                                                  
                    //System.out.println(Arrays.toString(arrivalTimeCheck));
                    
                    double[] arrivals = new double[arrivalTimeCheck.length]; // will be an array of three elements only,  but as doubles for comparison                                                   
                    try {
                        // to compare times, we need to parse to doubles to check our HH:MM:SS are valid  
                        for (int i = 0; i < arrivalTimeCheck.length; i++) {
                            arrivals[i] = Double.parseDouble(arrivalTimeCheck[i]);
                        }
                        //System.out.println("Parsed!");
                    } 
                    catch (NumberFormatException e) {
                        //System.out.println("Not a number");
                    }
                    
                    // now, compare each of the three elements to make sure that the time is valid
                    // (max time 23:59:59)
                    if ((arrivals[0] < 24) || (arrivals[1] < 60) || (arrivals[2] < 60)) {
                        // rejoice! Continue doing what you wanted to do; namely, create our object to be stored in ArrayList
                        scheduleInformation newRoute = scheduleDetails(scheduleInfo);   //pass all the information
                        
                        //now, we must add newRoute information to the HashMap
                        if (allSchedules.containsKey(checkTime)) {      
                            scheduleList = allSchedules.get(checkTime);     //add to the ArrayList at that key
                            scheduleList.add(newRoute);
                        } 
                        else {                                          
                            scheduleList = new ArrayList<scheduleInformation>();    //create the ArrayList at that key
                            scheduleList.add(newRoute);
                            allSchedules.put(checkTime, scheduleList);  	        // adds to the hashmap
                        }
                    }
                    // otherwise, time is not valid --> do not add to hashmap; read next line
                    line = br.readLine();
                }
                br.close();

                /*** Rough user input handling code to return the info you need from the arrival time you input (the key)
                Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNext()){
                        String userInput = scanner.next();
                        allSchedules.get(userInput);        //this gets the value stored at the key
                    }
                    scanner.close();    
                    ***/ 
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }

    }
    public static scheduleInformation scheduleDetails(String[] scheduleInfo){
        //create and instantiate the object to store all of the information from stop_times.txt
        scheduleInformation newRoute = new scheduleInformation();
        newRoute.tripID = scheduleInfo[0];
        newRoute.arrivalTime = scheduleInfo[1];  
        newRoute.departureTime = scheduleInfo[2];
        newRoute.stopID = scheduleInfo[3];
        newRoute.stopSequence = scheduleInfo[4];
        newRoute.stopHeadsign = scheduleInfo[5];
        newRoute.pickupType = scheduleInfo[6];
        newRoute.dropoffType = scheduleInfo[7];
        newRoute.shapeDistanceTravelled = scheduleInfo[8];

        return newRoute;
    }

    public static void main(String[] args) {
        searchFile("stop_times.txt");
    }
}