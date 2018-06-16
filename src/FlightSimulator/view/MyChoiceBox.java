package FlightSimulator.view;

import FlightSimulator.data.Flight;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class MyChoiceBox<T> extends MyView{
	private ChoiceBox<T> element;

	public MyChoiceBox(ChoiceBox<T> element){
		this.element = element;
	}

	@Override
	protected void notifyController() {

	}

	@Override
	public void notifyViewOfNewData(Object o) {

	}


}
