/*
* @author Imogen Green
* @Hilary Term 2021
*/

public class scheduleInformation {
    //this is to contain all the information regarding a particular trip (our scheduleInformation object)
    //this will be stored in an ArrayList of objects
    public String tripID;
    public String arrivalTime;
    public String departureTime;
    public String stopID;
    public String stopSequence;
    public String stopHeadsign;
    public String pickupType;
    public String dropoffType;
    public String shapeDistanceTravelled;
    
    scheduleInformation(String tripID, String arrivalTime, String departureTime, String stopID, 
    String stopSequence, String stopHeadsign, String pickupType, String dropoffType, String shapeDistanceTravelled){
        this.tripID = tripID;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopID = stopID;
        this.stopSequence = stopSequence;
        this.stopHeadsign = stopHeadsign;
        this.pickupType = pickupType;
        this.dropoffType = dropoffType;
        this.shapeDistanceTravelled = shapeDistanceTravelled;
    }
    public String getTripID(){
        return this.tripID;
    }
    public String getArrivalTime(){
        return this.arrivalTime;
    }
    public String getDepartureTime(){
        return this.departureTime;
    }
    public String getStopID(){
        return this.stopID;
    }
    public String getStopSequence(){
        return this.stopSequence;
    }
    public String getStopHeadsign(){
        return this.stopHeadsign;
    }
    public String getPickupType(){
        return this.pickupType;
    }
    public String getDropoffType(){
        return this.dropoffType;
    }
    public String getShapeDistanceTravelled(){
        return this.shapeDistanceTravelled;
    }
}
