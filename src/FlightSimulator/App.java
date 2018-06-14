package FlightSimulator;

import FlightSimulator.data.Airport;
import FlightSimulator.utils.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class App {
    private HashMap<String, Airport> airports;
    private ArrayList<String> countries, cities;

    public App(){

        countries = new ArrayList<>();
        cities = new ArrayList<>();

        airports = Parser.parse();
        for(Airport airport : airports.values()){
        	String country = airport.getCountry();
        	String city = airport.getCity();
        	if (!countries.contains(country)){
        		countries.add(country);
	        }
	        if(!cities.contains(city)){
        		cities.add(city);
	        }
        }


    }

}
