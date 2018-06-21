package FlightSimulator.model;

import FlightSimulator.controller.Controller;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingsModel {
	private HashMap<String, Color> colors;
	private double planeSize;
	private double citySize;
	private ArrayList<Controller> subscribers;

	public SettingsModel(){
		subscribers = new ArrayList<>();
		colors = new HashMap<>();
		colors.put("plane", Color.RED);
		colors.put("city", Color.BLUE);
		planeSize = 0.01;
		citySize = 0.01;
	}

	public void setColor(Color color, String name){
		this.colors.replace(name, color);
		if(name.equals("plane")) {
			notifyControllers(colors, Controller.PLANECOLORDATA);
		} else if(name.equals("city")){
			notifyControllers(colors, Controller.CITYCOLORDATA);
		}
	}

	public Color getColor(String name){
		return this.colors.get(name);
	}

	public double getPlaneSize() {
		return planeSize;
	}

	public void setPlaneSize(double planeSize) {
		this.planeSize = planeSize;
		notifyControllers(this.planeSize, Controller.PLANESIZEDATA);

	}

	public double getCitySize() {
		return citySize;
	}

	public void setCitySize(double citySize) {
		this.citySize = citySize;
		notifyControllers(this.citySize, Controller.CITYSIZEDATA);
	}

	public void subscribe(Controller controller){
		this.subscribers.add(controller);
	}

	private void notifyControllers(Object data, int dataType){
		for(Controller controller : subscribers){
			controller.notifyControllerOfNewSettings(data, dataType);
		}
	}
}
