public class busStopInfo {

	public  String stop_id;
	public  String stop_code;
	public  String stop_desc;
	public  String stop_lat;
	public  String stop_lon;
	public  String zone_id;
	public  String stop_url;
	public  String location_type;
	public  String parent_station;

	busStopInfo() {
		
		this.stop_id =stop_id;
        this.stop_code = stop_code;
        this.stop_desc = stop_desc;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.zone_id = zone_id;
        this.stop_url = stop_url;
        this.location_type = location_type;
        this.parent_station = parent_station;
	}
	
	public String getStopId() {
		return this.stop_id;
	}
	
	public String getStopCode() {
		return this.stop_code;
	}
	
	public String getStopDesc() {
		return this.stop_desc;
	}
	
	public String getStopLat() {
		return this.stop_lat;
	}
	
	public String getStopLon() {
		return this.stop_lon;
	}
	
	public String getZoneId() {
		return this.zone_id;
	}
	
	public String getStopUrl() {
		return this.stop_url;
	}
	
	public String getLocationType() {
		return this.location_type;
	}
	
	public String getParentStation() {
		return this.parent_station;
	}
}
