package FlightSimulator;

public class Flight {
	private Airport departureAirport, arrivalAirport;
	private float[] position;
	private float altitude;
	private float speed;
	private String name;
	private Plane plane;

	public Flight(Airport departureAirport, Airport arrivalAirport, float[] position, float altitude, float speed, String name) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.position = position;
		this.altitude = altitude;
		this.speed = speed;
		this.name = name;
	}


}
