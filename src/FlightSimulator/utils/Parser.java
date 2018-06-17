package FlightSimulator.utils;

import FlightSimulator.data.Airport;
import FlightSimulator.data.Flight;
import FlightSimulator.utils.FlightList;
import FlightSimulator.utils.FlightParsing;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

	public static HashMap<String, Airport> parse() {
		HashMap<String, Airport> airports = new HashMap<>();
		try
		{
			FileReader file = new FileReader("airports.csv");
			BufferedReader bufRead = new BufferedReader(file);

			String line = bufRead.readLine();
			while (line != null) {
				String[] array = line.split(",");

				String airportName = array[0];
				String cityName = array[1];
				String country = array[2];
				String icaoId = array[3];
				float[] position = new float[2];
				position[0] = Float.parseFloat(array[4]);
				position[1] = Float.parseFloat(array[5]);

				line = bufRead.readLine();

				Airport airport = new Airport(cityName,country,airportName,icaoId,position);
				airports.put(airportName, airport);
			}

			bufRead.close();
			file.close();

		} catch (IOException e)

		{
			System.err.println("Fatal error : could not read airports.csv");
			e.printStackTrace();
			System.exit(1);
		}
		return airports;
	}

	/**
	 *
	 * @param airports list of all the airports
	 * @param country considered country
	 * @return list of cities in this country
	 */
	public static ArrayList<String> getCitiesOfCountry(ArrayList<Airport> airports, String country){
		ArrayList<String> cities = new ArrayList<>();
		for (Airport i : airports){
			if(country.equals(i.getCountry())){
				cities.add(i.getCity());
			}
		}
		return cities;
	}


	/**
	 *
	 * @param airports list of airports
	 * @param city considered city
	 * @return list of airport names
	 */
	public static ArrayList<String> getAirportNamesOfCity(ArrayList<Airport> airports, String city){
		ArrayList<String> airportNames = new ArrayList<>();
		for (Airport i : airports){
			if(city.equals(i.getCity()) && !(city.equals(""))){
				airportNames.add(i.getName());
			}
		}
		return airportNames;
	}

	/**
	 * Take a json string and map it to a FlightList, using Jackson library.
	 * @param json The json that contain the data to be parsed
	 * @return The flightList created by Jackson
	 */
	public static FlightList parseResponse(String json){
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //Ignorer les champs inutiles
		try {
			FlightList flights = mapper.readValue(json, FlightList.class); //Créer l'objet de plus haut niveau dans le dictionnaire json
			return flights;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Turns a FlightList, which contains FlightParsing, into an ArrayList of Flight, that will be usable by the application.
	 * @param list The original FlightList
	 * @return The resulting list of Flight, that contains every Flight that was in the FlightList
	 */
	public static ArrayList<Flight> getResponseFlight(FlightList list, HashMap<String, Airport> airports){
		ArrayList<Flight> result = new ArrayList<>();
		for(FlightParsing flightParsing : list.getAcList()){
			Airport from, to;
			from = airports.get(flightParsing.From.substring(0, 4));
			to = airports.get(flightParsing.To.substring(0, 4));
			float[] position = new float[2];
			position[0] = flightParsing.Long;
			position[1] = flightParsing.Lat;
			Flight flight = new Flight(from, to, position, flightParsing.Alt, flightParsing.Spd, flightParsing.Icao);
			result.add(flight);
		}
		return result;
	}

}