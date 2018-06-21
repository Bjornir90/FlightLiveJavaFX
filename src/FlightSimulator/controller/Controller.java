package FlightSimulator.controller;

import FlightSimulator.data.Flight;
import FlightSimulator.model.DataModel;
import FlightSimulator.model.SettingsModel;
import FlightSimulator.view.MyView;

import java.util.ArrayList;

public abstract class Controller {
	protected ArrayList<MyView> subscribers;
	protected DataModel dataModel;
	protected SettingsModel settingsModel;
	public static final int COLORDATA = 0, PLANESIZEDATA = 1, CITYSIZEDATA = 2;//For settings data
	public static final int FLIGHTDATA = 0, LISTDATA = 1;//For flights data

	public Controller(){
		subscribers = new ArrayList<>();
	}

	public abstract void notifyControllerOfNewData(Object o, int dataType);
	public abstract void notifyControllerOfNewSettings(Object data, int dataType);


	public void subscribe(MyView view){
		subscribers.add(view);
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public void setSettingsModel(SettingsModel settingsModel) {
		this.settingsModel = settingsModel;
	}
}
