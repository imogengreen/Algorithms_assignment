import java.io.BufferedReader;
import java.io.FileReader;
//import java.io.File;
import java.util.Arrays;

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
                    

                    //problem seems to be that our array is not string literal (no quotation marks) so we need to find a way around this
                    


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
                    
                    
                    //now, compare each of the three elements to make sure that the time is valid
                    if ((arrivals[0] >= 24) || (arrivals[1] >= 60) || (arrivals[2] >= 60))
                    {
                        //remove line from the file as this time is not valid --> do this by creating a new file

                    }
                    //otherwise, rejoice! Continue doing what you wanted to do; namely, add to the data structure and SEARCH for that bad boy
                    
                    
                    line = br.readLine();
               }
               br.close();
           }
           catch(Exception e){
               System.out.println(e);
           }
       }

    }
    public static void main (String[] args){
        searchFile("stop_times.txt");
    }

}