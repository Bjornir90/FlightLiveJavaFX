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
	private Flight selectedFlight;


	public DataModel(HashMap<String, Airport> airports, HashMap<String, ArrayList<String>> countries) {
		subscribers = new ArrayList<>();
		this.airports = airports;
		this.countries = countries;
	}

	public void notifyNewFlightList(ArrayList<Flight> flights){
		this.flights = flights;
		notifyControllers(flights, Controller.LISTDATA);
	}

	private void notifyControllers(Object o, int dataType){
		for(Controller controller : subscribers){
			controller.notifyControllerOfNewData(o, dataType);
		}
	}

	public void notifyNewSelectedFlight(String flightName){
		for(Flight flight : flights){
			if(flight.getName().equals(flightName)){
				selectedFlight = flight;
				notifyControllers(flight, Controller.FLIGHTDATA);
			}
		}
	}

	public void subscribe(Controller controller){
		subscribers.add(controller);
	}
}
