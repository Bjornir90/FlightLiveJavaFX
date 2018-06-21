package FlightSimulator.controller;

import FlightSimulator.model.SettingsModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


public class PopupController {
	private SettingsModel model;

	@FXML
	Slider planeSize;
	@FXML
	Slider citySize;
	@FXML
	ColorPicker planeColor;
	@FXML
	ColorPicker cityColor;
	@FXML
	Button validateButton;

	@FXML
	public void initialize(){
		planeSize.setMin(1.0);
		planeSize.setMax(20.0);
		citySize.setMin(1.0);
		citySize.setMax(20.0);
		planeSize.setBlockIncrement(1.0);
		citySize.setBlockIncrement(1.0);
	}

	private void onModelLoaded(){
		planeSize.setValue(model.getPlaneSize());
		citySize.setValue(model.getCitySize());

		planeColor.setValue(model.getColor("plane"));
		cityColor.setValue(model.getColor("city"));

		planeSize.valueProperty().addListener((observable, oldValue, newValue) ->	model.setPlaneSize((double) newValue));
		citySize.valueProperty().addListener((observable, oldValue, newValue) ->    model.setCitySize((double) newValue));
		planeColor.valueProperty().addListener((observable, oldValue, newValue) ->	model.setColor(newValue, "plane"));
		cityColor.valueProperty().addListener((observable, oldValue, newValue) -> 	model.setColor(newValue, "city"));

		validateButton.setOnMouseClicked(event -> {
			Stage popupStage = (Stage) validateButton.getParent().getScene().getWindow();
			popupStage.close();
		});
	}

	public void setModel(SettingsModel model) {
		this.model = model;
		onModelLoaded();
	}
}
