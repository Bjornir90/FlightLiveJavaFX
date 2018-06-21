package FlightSimulator.view;

import FlightSimulator.data.Flight;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MyTextField extends MyView{
	private TextField element;

	public MyTextField(TextField element) {
		this.element = element;
	}

	@Override
	protected void notifyController() {

	}

	@Override
	public void notifyViewOfNewData(Object o) {

	}


}
