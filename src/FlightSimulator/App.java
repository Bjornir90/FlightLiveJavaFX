package FlightSimulator;

import FlightSimulator.data.Airport;
import FlightSimulator.utils.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class App {
    private HashMap<String, Airport> airports;
    private HashMap<String, ArrayList<String>> countries;
    private HashMap<String, String> airportNameToID;

    public App(){

        countries = new HashMap<>();
        airportNameToID = new HashMap<>();

        airports = Parser.parse(airportNameToID);
        for(Airport airport : airports.values()){
        	String country = airport.getCountry();
        	String city = airport.getCity();
        	if (!countries.containsKey(country)){
        		countries.put(country, new ArrayList<>());
		        System.out.println("country = " + country);
	        }
	        if(!countries.get(country).contains(city)){
        		countries.get(country).add(city);
	        }
        }

        for(Airport airport : airports.values()){
        	Collections.sort(countries.get(airport.getCountry()));
        }

    }

	public HashMap<String, Airport> getAirports() {
		return airports;
	}

	public HashMap<String, ArrayList<String>> getCountries() {
		return countries;
	}

	public HashMap<String, String> getAirportNameToID() {
		return airportNameToID;
	}
}
