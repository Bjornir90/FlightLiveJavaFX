package FlightSimulator;

public class FlightList {

	private FlightParsing[] acList; //List of planes
	private  String lastDv; //Timestamp for further query

	public FlightParsing[] getAcList() {
		return acList;
	}

	public void setAcList(FlightParsing[] acList) {
		this.acList = acList;
	}

	public String getLastDv() {
		return lastDv;
	}

	public void setLastDv(String lastDv) {
		this.lastDv = lastDv;
	}
}