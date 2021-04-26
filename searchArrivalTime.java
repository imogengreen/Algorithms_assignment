import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

//must take the second column as this contains our arrival times
public class searchArrivalTime{
    public static void searchFile (String filename){
       
       while (filename != null){
           try{
               //read in the data from the input file stop_times.txt
               FileReader data = new FileReader(filename);
               BufferedReader br = new BufferedReader(data);
               String line = br.readLine();
               while (line != null){        //while there is a next line in the file
                    //create an array for information about every schedule in the file 
                    String[] scheduleInfo = line.split(",");
                    //System.out.println(Arrays.toString(scheduleInfo));

                    //need to check if arrival time is valid (at index 1 in file)
                    String checkTime = scheduleInfo[1];
                    //System.out.println(checkTime);
                    
                    String[] arrivalTimeCheck = checkTime.split(":");   //creates an array with hh in index 0, mm in index 1, ss in index 2
                    //System.out.println(Arrays.toString(arrivalTimeCheck));
                     
                    double[] arrivals = new double[arrivalTimeCheck.length];  //will be an array of three elements only, but as integers for comparison
                    try{
                        //to compare times, we need to parse to integers to check our HH:MM:SS are valid
                        for (int i=0; i<arrivalTimeCheck.length; i++){
                            arrivals[i] = Double.parseDouble(arrivalTimeCheck[i]);
                        }
                        System.out.println(Arrays.toString(arrivals));
                    }
                    catch(NumberFormatException e){
                        System.out.println("not a number");
                    }
                    
                    //create our HashMap
                    HashMap<String, scheduleInformation> allSchedules = new HashMap<String, scheduleInformation>();
                    //now, compare each of the three elements to make sure that the time is valid (max time 23:59:59)
                   if ((arrivals[0] < 24) || (arrivals[1] < 60) || (arrivals[2] < 60))
                    {
                        //rejoice! Continue doing what you wanted to do; namely, create and add to the data structure
                        scheduleInformation newRoute = new scheduleInformation();
                        newRoute.tripID = scheduleInfo[0];
                        //newRoute.arrivalTime = scheduleInfo[1];
                        newRoute.departureTime = scheduleInfo[2];
                        newRoute.stopID = scheduleInfo[3];
                        newRoute.stopSequence = scheduleInfo[4];
                        newRoute.stopHeadsign = scheduleInfo[5];
                        newRoute.pickupType = scheduleInfo[6];
                        newRoute.dropoffType = scheduleInfo[7];
                        newRoute.shapeDistanceTravelled = scheduleInfo[8];

                        allSchedules.put(checkTime, newRoute);  //adds the route to the hashmap
                    }
                    //otherwise, time is not valid --> do not add to hashmap 
               }
               br.close();
           }
           catch(Exception e){
               System.out.println(e);
           }
       }

    }
    /*public static File removeFromFile(String filename){
        File file = new File(filename);
        return file;
    }*/
    public static void main (String[] args){
        searchFile("stop_times.txt");
    }

}