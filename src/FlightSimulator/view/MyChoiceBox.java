package FlightSimulator.view;

import FlightSimulator.data.Airport;
import FlightSimulator.data.Flight;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class MyChoiceBox extends MyView{
	private ChoiceBox<String> element;

	public MyChoiceBox(ChoiceBox element){
		this.element = element;
	}

	@Override
	protected void notifyController() {

	}

	@Override
	public void notifyViewOfNewData(Object o) {
		if(o instanceof Airport) {
			Airport airport = (Airport) o;
			//element.getSelectionModel().select(airport.getName());
			element.setValue(airport.getName());
		}
	}


}
