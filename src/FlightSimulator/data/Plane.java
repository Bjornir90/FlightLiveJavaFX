package FlightSimulator.data;

public class Plane {
	private boolean isMilitary;
	private String type;
	private int id;
	private String icaoCode;

	public Plane(boolean isMilitary, String type, int id, String icaoCode) {
		this.isMilitary = isMilitary;
		this.type = type;
		this.id = id;
		this.icaoCode = icaoCode;
	}
}
