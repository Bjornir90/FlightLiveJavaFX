package FlightSimulator2018XXX_naruto_XXX;

import java.util.ArrayList;

public class Airport {
	private String city, country, name;
	private String icaoCode;
	private float[] position;
	private ArrayList<Flight> departureFrom, arrivalTo;

	public Airport(String city, String country, String name, String icaoCode, float[] position, ArrayList<Flight> departureFrom, ArrayList<Flight> arrivalTo) {
		this.city = city;
		this.country = country;
		this.name = name;
		this.icaoCode = icaoCode;
		this.position = position;
		this.departureFrom = departureFrom;
		this.arrivalTo = arrivalTo;
	}
}
