package FlightSimulator.model;

import FlightSimulator.controller.Controller;
import FlightSimulator.data.Airport;
import FlightSimulator.data.Flight;

import java.util.ArrayList;
import java.util.HashMap;

public class DataModel {
	private HashMap<String, Airport> airports;
	private HashMap<String, ArrayList<String>> countries;
	private HashMap<String, String> airportNameToIcao;
	private ArrayList<Flight> flights;
	private ArrayList<Controller> subscribers;
	private Flight selectedFlight;
	private Airport selectedFrom, selectedTo;


	public DataModel(HashMap<String, Airport> airports, HashMap<String, ArrayList<String>> countries, HashMap<String, String> nameToICAO) {
		subscribers = new ArrayList<>();
		this.airports = airports;
		this.countries = countries;
		airportNameToIcao = nameToICAO;
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

	public void notifyNewSelectedAirport(Airport airport){
		if(selectedFrom == null || (selectedFrom != null && selectedTo != null)){
			selectedFrom = airport;
			selectedTo = null;
			notifyControllers(airport, Controller.FROMAIRPORTDATA);
		} else if(selectedTo == null){
			selectedTo = airport;
			notifyControllers(airport, Controller.TOAIRPORTDATA);
		}
	}

	public void subscribe(Controller controller){
		subscribers.add(controller);
	}

	public HashMap<String, String> getAirportNameToIcao() {
		return airportNameToIcao;
	}

	public HashMap<String, Airport> getAirports() {
		return airports;
	}
}
