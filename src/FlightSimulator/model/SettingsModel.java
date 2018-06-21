package FlightSimulator.model;

import FlightSimulator.controller.Controller;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingsModel {
	private HashMap<String, Color> colors;
	private double planeSize, citySize;
	private ArrayList<Controller> subscribers;

	public SettingsModel(){
		subscribers = new ArrayList<>();
		colors = new HashMap<>();
		colors.put("plane", Color.AZURE);
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

	public double getPlaneSize() {
		return planeSize;
	}

	public void setPlaneSize(double planeSize) {
		this.planeSize = planeSize;
		notifyControllers(planeSize, Controller.SIZEDATA);

	}

	public double getCitySize() {
		return citySize;
	}

	public void setCitySize(double citySize) {
		this.citySize = citySize;
		notifyControllers(citySize, Controller.SIZEDATA);
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
