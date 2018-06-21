package FlightSimulator.data;

import java.util.ArrayList;
import java.util.Arrays;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Airport airport = (Airport) o;

		if (city != null ? !city.equals(airport.city) : airport.city != null) return false;
		if (country != null ? !country.equals(airport.country) : airport.country != null) return false;
		if (name != null ? !name.equals(airport.name) : airport.name != null) return false;
		if (icaoCode != null ? !icaoCode.equals(airport.icaoCode) : airport.icaoCode != null) return false;
		if (!Arrays.equals(position, airport.position)) return false;
		if (departureFrom != null ? !departureFrom.equals(airport.departureFrom) : airport.departureFrom != null)
			return false;
		return arrivalTo != null ? arrivalTo.equals(airport.arrivalTo) : airport.arrivalTo == null;
	}

	@Override
	public int hashCode() {
		int result = city != null ? city.hashCode() : 0;
		result = 31 * result + (country != null ? country.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (icaoCode != null ? icaoCode.hashCode() : 0);
		result = 31 * result + (position != null ? position.hashCode() : 0);
		result = 31 * result + (departureFrom != null ? departureFrom.hashCode() : 0);
		result = 31 * result + (arrivalTo != null ? arrivalTo.hashCode() : 0);
		return result;
	}
}

