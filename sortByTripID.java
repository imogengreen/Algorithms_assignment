/*
 *  @author Imogen Green
 *  @Hilary Term 2021
 *
*/

//this is a small class to deal with comparisons of tripID for sorting on return
import java.util.Comparator;

public class sortByTripID implements Comparator<scheduleInformation>{
    
    public int compare(scheduleInformation a, scheduleInformation b) {
        String temp1 = a.tripID;
        String temp2 = b.tripID;
        return temp1.compareTo(temp2);
    }
}
