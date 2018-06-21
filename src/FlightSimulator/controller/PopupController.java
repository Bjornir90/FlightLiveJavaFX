package FlightSimulator.controller;

import FlightSimulator.model.SettingsModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


public class PopupController extends Controller{
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
	Label citySizeLabel;
	@FXML
	Label planeSizeLabel;

	@FXML
	public void initialize(){
		planeSize.setMin(0.01);
		planeSize.setMax(0.2);
		citySize.setMin(0.01);
		citySize.setMax(0.2);
		planeSize.setBlockIncrement(0.01);
		citySize.setBlockIncrement(0.01);
		validateButton.setOnMouseClicked(event -> {
			Stage popupStage = (Stage) validateButton.getParent().getScene().getWindow();
			popupStage.close();
		});
	}

	private void onModelLoaded(){
		planeSize.setValue(model.getPlaneSize());
		citySize.setValue(model.getCitySize());
		planeSizeLabel.setText(model.getPlaneSize()+" px");
		citySizeLabel.setText((model.getCitySize()+" px"));

		planeColor.setValue(model.getColor("plane"));
		cityColor.setValue(model.getColor("city"));

		planeSize.valueProperty().addListener((observable, oldValue, newValue) ->	model.setPlaneSize((double) newValue));
		citySize.valueProperty().addListener((observable, oldValue, newValue) ->    model.setCitySize((double) newValue));
		planeColor.valueProperty().addListener((observable, oldValue, newValue) ->	model.setColor(newValue, "plane"));
		cityColor.valueProperty().addListener((observable, oldValue, newValue) -> 	model.setColor(newValue, "city"));
	}

	public void setModel(SettingsModel model) {
		this.model = model;
		onModelLoaded();
	}

	@Override
	public void notifyControllerOfNewData(Object o, int dataType) {
		//Nothing to do
	}

	@Override
	public void notifyControllerOfNewSettings(Object data, int dataType) {
		if(dataType == Controller.PLANESIZEDATA){
			planeSizeLabel.setText(model.getPlaneSize()+" px");
		} else if (dataType == Controller.CITYSIZEDATA){
			citySizeLabel.setText((model.getCitySize()+" px"));
		}
	}
}
