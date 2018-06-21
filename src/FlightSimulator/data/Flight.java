package FlightSimulator.data;

import javafx.scene.control.Alert;

import java.util.Arrays;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Flight flight = (Flight) o;

		if (Float.compare(flight.altitude, altitude) != 0) return false;
		if (Float.compare(flight.speed, speed) != 0) return false;
		if (!departureAirport.equals(flight.departureAirport)) return false;
		if (!arrivalAirport.equals(flight.arrivalAirport)) return false;
		if (!Arrays.equals(position, flight.position)) return false;
		if (name != null ? !name.equals(flight.name) : flight.name != null) return false;
		return plane != null ? plane.equals(flight.plane) : flight.plane == null;
	}

	@Override
	public int hashCode() {
		int result = departureAirport.hashCode();
		result = 31 * result + arrivalAirport.hashCode();
		result = 31 * result + (position != null ? position.hashCode() : 0);
		result = 31 * result + (altitude != +0.0f ? Float.floatToIntBits(altitude) : 0);
		result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (plane != null ? plane.hashCode() : 0);
		return result;
	}
}
