package FlightSimulator.view;

import FlightSimulator.controller.Controller;
import FlightSimulator.data.Flight;

import java.util.ArrayList;

public abstract class MyView {
	private Controller controller;
	protected abstract void notifyController();
	public abstract void notifyViewOfNewData(Object o);
}
