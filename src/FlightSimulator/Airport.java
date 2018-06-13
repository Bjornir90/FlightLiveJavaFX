package FlightSimulator;

import java.util.ArrayList;

public class Airport {
	private String city, country, name;
	private String icaoCode;
	private float[] position;
	private ArrayList<Flight> departureFrom, arrivalTo;

	public Airport(String city, String country, String name, String icaoCode, float[] position) {
		this.city = city;
		this.country = country;
		this.name = name;
		this.icaoCode = icaoCode;
		this.position = position;
	}

	@Override
	public String toString() {
		return "Airport{" +
				"city='" + city + '\'' +
				", country='" + country + '\'' +
				", name='" + name + '\'' +
				", icaoCode='" + icaoCode + '\'' +
				", position=" + position[0]+ ", " + position[1]+
				'}';
	}


	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	public float[] getPosition() {
		return position;
	}

	public ArrayList<Flight> getDepartureFrom() {
		return departureFrom;
	}

	public ArrayList<Flight> getArrivalTo() {
		return arrivalTo;
	}


}

