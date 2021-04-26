/*
* @author Imogen Green
* @Hilary Term 2021
*
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;

//must take the second column as this contains our arrival times
public class searchArrivalTime {
    public static void searchFile(String filename /*, String userInput*/) {

        if (filename != null) {
            // create our HashMap
            HashMap<String, ArrayList<scheduleInformation>> allSchedules = new HashMap<String, ArrayList<scheduleInformation>>();
            
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
                    if (arrivals.length == 3){
                        try {
                            // to compare times, we need to parse to doubles to check our HH:MM:SS are valid  
                            for (int i = 0; i < arrivalTimeCheck.length; i++) {
                                arrivals[i] = Double.parseDouble(arrivalTimeCheck[i]);
                            }

                            // now, compare each of the three elements to make sure that the time is valid
                            // (max time 23:59:59)
                            if ((arrivals[0] < 24) && (arrivals[1] < 60) && (arrivals[2] < 60)) {
                                // rejoice! Continue doing what you wanted to do; namely, create our object to be stored in ArrayList
                                addToMap(checkTime, scheduleInfo, allSchedules);
                            }
                        } 
                        catch (NumberFormatException e) {
                           System.out.println("Not a time");
                        }
                    }    
                    // otherwise, time is not valid --> do not add to hashmap; read next line
                    line = br.readLine();
                }
                br.close();
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }

    }
   public static scheduleInformation scheduleDetails(String[] scheduleInfo){
        //create and instantiate the object to store all of the information from stop_times.txt
        scheduleInformation newRoute = 
        new scheduleInformation(scheduleInfo[0], scheduleInfo[1], scheduleInfo[2], scheduleInfo[3], scheduleInfo[4], 
        scheduleInfo[5], scheduleInfo[6], scheduleInfo[7], scheduleInfo[8]);

        return newRoute;
    }

    /*public static void getTripInformation(HashMap <String, ArrayList<scheduleInformation>> allSchedules, String userInputTime){
        //gets information for the trips with required arrival times and returns all necessary information
        
    }*/

    public static void addToMap(String checkTime, String[] scheduleInfo, HashMap<String, ArrayList<scheduleInformation>> allSchedules){
        scheduleInformation newRoute = scheduleDetails(scheduleInfo);   //pass all the information
        
        // NB: if we don't check for the presence of the key, our value will be overwritten --> create an ArrayList to store objects
        ArrayList<scheduleInformation> scheduleList;                
        
        //now, we must add newRoute information to the HashMap
        if (allSchedules.containsKey(checkTime)) {      
            scheduleList = allSchedules.get(checkTime);     
            scheduleList.add(newRoute);                 //add to the ArrayList at that key
        } 
        else {                                          
            scheduleList = new ArrayList<scheduleInformation>();    //create the ArrayList at that key
            scheduleList.add(newRoute);
            allSchedules.put(checkTime, scheduleList);  	        // adds to the hashmap
        }
    }

    public static void main(String[] args) {
       // String userInput = "";
        /*** Rough user input handling code to return the info you need from the arrival time you input (the key)
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            String userInput = scanner.next();
            allSchedules.get(userInput);        //this gets the value stored at the key
        }
        scanner.close();    
        ***/ 
        searchFile("stop_times.txt" /*, userInput*/);
    }
}