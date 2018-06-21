package FlightSimulator.view;

import FlightSimulator.data.Flight;
import javafx.scene.control.Label;

public class MyLabel extends MyView {
	private Label element;

	public MyLabel(Label element) {
		this.element = element;
	}

	@Override
	protected void notifyController() {

	}

	@Override
	public void notifyViewOfNewData(Object o) {
		element.setText(o.toString());
	}
}
