package FlightSimulator.utils;

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

	@Override
	public String toString(){
		String toReturn = new String();
		for(FlightParsing f : acList){
			toReturn += f.Icao + " Type : " + f.Type + " Military : " + f.Mil + " Altitude : " + f.Alt + "\n";
		}
		return toReturn;
	}
}