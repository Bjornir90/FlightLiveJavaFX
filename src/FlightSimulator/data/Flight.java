package FlightSimulator.data;

import javafx.scene.control.Alert;

public class Flight {
	private Airport departureAirport, arrivalAirport;
	private float[] position;
	private float altitude;
	private float speed;
	private String name;
	private Plane plane;

	public Flight(Airport departureAirport, Airport arrivalAirport, float[] position, float altitude, float speed, String name, int Id, boolean mil, String type, String Icao) {
		if(departureAirport == null || arrivalAirport == null){
			System.err.println("Missing airport parameters");
			return;
		}
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.position = position;
		this.altitude = altitude;
		this.speed = speed;
		this.name = name;
		this.plane = new Plane(mil, type, Id, Icao);
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	@Override
	public String toString() {
		return "Flight{" +
				"departureAirport=" + departureAirport +
				", arrivalAirport=" + arrivalAirport +
				", position=" + position +
				", altitude=" + altitude +
				", speed=" + speed +
				", name='" + name + '\'' +
				", plane=" + plane +
				'}';
	}

	public float[] getPosition() {
		return position;
	}

	public float getAltitude() {
		return altitude;
	}

	public float getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}

	public Plane getPlane() {
		return plane;
	}
}
