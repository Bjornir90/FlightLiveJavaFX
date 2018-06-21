package FlightSimulator.controller;

import FlightSimulator.data.Flight;
import FlightSimulator.view.*;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class InterfaceController extends Controller{
	private HashMap<String, MyChoiceBox<String>> choiceBoxes;
	private HashMap<String, MyLabel> labels;
	private Button validate, settings;
	private MyTextField size;
	private MyListView flightList;

	public InterfaceController(ChoiceBox<String> departureCountry, ChoiceBox<String> arrivalCountry, ChoiceBox<String> departureCity, ChoiceBox<String> arrivalCity, ChoiceBox<String> departureAirport, ChoiceBox<String> arrivalAirport, TextField size, Button validate, Button settings, ListView<String> list, Label planeIdLabel, Label planeHeightLabel, Label planeSpeedLabel, Label planeTypeLabel, Label militaryBoolLabel, Label departureAirportLabel, Label arrivalAirportLabel){
		choiceBoxes = new HashMap<>();
		labels = new HashMap<>();
		choiceBoxes.put("departureCountry", new MyChoiceBox<>(departureCountry));
		choiceBoxes.put("arrivalCountry", new MyChoiceBox<>(arrivalCountry));
		choiceBoxes.put("departureCity", new MyChoiceBox<>(departureCity));
		choiceBoxes.put("arrivalCity", new MyChoiceBox<>(arrivalCity));
		choiceBoxes.put("departureAirport", new MyChoiceBox<>(departureAirport));
		choiceBoxes.put("arrivalAirport", new MyChoiceBox<>(arrivalAirport));
		labels.put("planeId", new MyLabel(planeIdLabel));
		labels.put("planeHeight", new MyLabel(planeHeightLabel));
		labels.put("planeType", new MyLabel(planeTypeLabel));
		labels.put("planeSpeed", new MyLabel(planeSpeedLabel));
		labels.put("military", new MyLabel(militaryBoolLabel));
		labels.put("departureAirport", new MyLabel(departureAirportLabel));
		labels.put("arrivalAirport", new MyLabel(arrivalAirportLabel));

		this.validate = validate;
		this.settings = settings;
		this.size = new MyTextField(size);
		this.flightList = new MyListView(list);
		for(MyView view : choiceBoxes.values()){
			this.subscribe(view);
		}
		for(MyView view : labels.values()){
			this.subscribe(view);
		}
		this.subscribe(this.size);
		this.subscribe(this.flightList);
	}

	@Override
	public void notifyControllerOfNewData(Object o, int dataType) {
		//subscribers.forEach(view -> view.notifyViewOfNewData(o));
		if(dataType == Controller.FLIGHTDATA){
			Flight flight = (Flight) o;
			labels.get("planeId").notifyViewOfNewData(flight.getPlane().getId());
			labels.get("planeType").notifyViewOfNewData(flight.getPlane().getType());
			labels.get("planeHeight").notifyViewOfNewData(flight.getAltitude());
			labels.get("planeSpeed").notifyViewOfNewData(flight.getSpeed());
			labels.get("military").notifyViewOfNewData(flight.getPlane().isMilitary());
			labels.get("departureAirport").notifyViewOfNewData(flight.getDepartureAirport().getName());
			labels.get("arrivalAirport").notifyViewOfNewData(flight.getArrivalAirport().getName());
		} else if (dataType == Controller.LISTDATA){
			ArrayList<Flight> list = (ArrayList) o;
			flightList.notifyViewOfNewData(list);
		}
	}

	@Override
	public void notifyControllerOfNewSettings(Object data, int dataType) {
		System.out.println("data = " + data);
		//Nothing to do
	}
}
