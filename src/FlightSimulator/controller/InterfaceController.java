package FlightSimulator.controller;

import FlightSimulator.data.Flight;
import FlightSimulator.view.MyChoiceBox;
import FlightSimulator.view.MyListView;
import FlightSimulator.view.MyTextField;
import FlightSimulator.view.MyView;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class InterfaceController extends Controller{
	private HashMap<String, MyChoiceBox<String>> choiceBoxes;
	private Button validate, settings;
	private MyTextField size;
	private MyListView flightList;

	public InterfaceController(ChoiceBox<String> departureCountry, ChoiceBox<String> arrivalCountry, ChoiceBox<String> departureCity, ChoiceBox<String> arrivalCity, ChoiceBox<String> departureAirport, ChoiceBox<String> arrivalAirport, TextField size, Button validate, Button settings, ListView<String> list){
		choiceBoxes = new HashMap<>();
		choiceBoxes.put("departureCountry", new MyChoiceBox<>(departureCountry));
		choiceBoxes.put("arrivalCountry", new MyChoiceBox<>(arrivalCountry));
		choiceBoxes.put("departureCity", new MyChoiceBox<>(departureCity));
		choiceBoxes.put("arrivalCity", new MyChoiceBox<>(arrivalCity));
		choiceBoxes.put("departureAirport", new MyChoiceBox<>(departureAirport));
		choiceBoxes.put("arrivalAirport", new MyChoiceBox<>(arrivalAirport));
		this.validate = validate;
		this.settings = settings;
		this.size = new MyTextField(size);
		this.flightList = new MyListView(list);
		for(MyView view : choiceBoxes.values()){
			this.subscribe(view);
		}
		this.subscribe(this.size);
		this.subscribe(this.flightList);
	}

	@Override
	public void notifyControllerOfNewData(Object o) {
		subscribers.forEach(view -> view.notifyViewOfNewData(o));
	}

	@Override
	public void notifyControllerOfNewSettings() {

	}
}
