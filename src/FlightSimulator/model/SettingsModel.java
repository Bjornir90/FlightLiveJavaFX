package FlightSimulator.model;

import FlightSimulator.controller.Controller;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingsModel {
	private HashMap<String, Color> colors;
	private long planeSize, citySize;
	private ArrayList<Controller> subscribers;

	public SettingsModel(){
		subscribers = new ArrayList<>();
		colors = new HashMap<>();
		colors.put("plane", Color.RED);
		colors.put("city", Color.BLUE);
		planeSize = 10;
		citySize = 10;
	}

	public void setColor(Color color, String name){
		this.colors.replace(name, color);
		notifyControllers(colors, Controller.COLORDATA);
	}

	public Color getColor(String name){
		return this.colors.get(name);
	}

	public long getPlaneSize() {
		return planeSize;
	}

	public void setPlaneSize(double planeSize) {
		long roundedValue = Math.round(planeSize);
		if(roundedValue == this.planeSize) return;//So the controllers don't get spammed with the same value
		this.planeSize = roundedValue;
		notifyControllers(this.planeSize, Controller.PLANESIZEDATA);

	}

	public long getCitySize() {
		return citySize;
	}

	public void setCitySize(double citySize) {
		long roundedValue = Math.round(citySize);
		if(roundedValue == this.citySize) return;//So the controllers don't get spammed with the same value
		this.citySize = roundedValue;
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
