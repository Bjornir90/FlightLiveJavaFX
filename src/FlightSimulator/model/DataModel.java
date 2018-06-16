package FlightSimulator.model;

import FlightSimulator.controller.Controller;
import FlightSimulator.data.Airport;
import FlightSimulator.data.Flight;

import java.util.ArrayList;
import java.util.HashMap;

public class DataModel {
	private HashMap<String, Airport> airports;
	private HashMap<String, ArrayList<String>> countries;
	private ArrayList<Flight> flights;
	private ArrayList<Controller> subscribers;


	public DataModel(HashMap<String, Airport> airports, HashMap<String, ArrayList<String>> countries) {
		subscribers = new ArrayList<>();
		this.airports = airports;
		this.countries = countries;
	}

	public void notifyNewFlightList(ArrayList<Flight> flights){
		this.flights = flights;
		notifyControllers();
	}

	private void notifyControllers(){

	}

	public void subscribe(Controller controller){
		subscribers.add(controller);
	}
}
