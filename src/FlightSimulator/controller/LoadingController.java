package FlightSimulator.controller;

import FlightSimulator.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class LoadingController {
	private App app;

	@FXML
	ChoiceBox<String> countryDropBox;
	@FXML
	ChoiceBox<String> cityDropBox;
	@FXML
	ChoiceBox<String> departureDropBox;
	@FXML
	ChoiceBox<String> arrivalDropBox;
	@FXML
	Button validateButton;
	@FXML
	Button settingsButton;
	@FXML
	TextField sizeField;
	@FXML
	ListView<String> flightsList;

	@FXML
	public void initialize() {
		cityDropBox.setDisable(true);
		departureDropBox.setDisable(true);
		arrivalDropBox.setDisable(true);
		//TODO : Give each controller the data Collection it needs.
		if(app != null) {
			ObservableList<String> observableListCountry = FXCollections.observableArrayList(app.getCountries());
			countryDropBox.setItems(observableListCountry);
		}
	}

	public void setApp(App app) {
		this.app = app;
	}
}
