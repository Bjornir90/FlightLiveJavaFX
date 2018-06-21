package FlightSimulator.controller;

import FlightSimulator.App;
import FlightSimulator.data.Airport;
import FlightSimulator.data.Flight;
import FlightSimulator.model.DataModel;
import FlightSimulator.utils.DataConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LoadingController {
	private App app;
	private DataConnection dataConnection;

	@FXML
	ChoiceBox<String> departureCountry;
	@FXML
	ChoiceBox<String> departureCity;
	@FXML
	ChoiceBox<String> arrivalCountry;
	@FXML
	ChoiceBox<String> arrivalCity;
	@FXML
	ChoiceBox<String> departureAirport;
	@FXML
	ChoiceBox<String> arrivalAirport;
	@FXML
	Button validateButton;
	@FXML
	Button settingsButton;
	@FXML
	TextField sizeField;
	@FXML
	ListView<String> flightsList;
	@FXML
	Label planeIdLabel;
	@FXML
	Label departureAirportLabel;
	@FXML
	Label arrivalAirportLabel;
	@FXML
	Label planeTypeLabel;
	@FXML
	Label militaryBoolLabel;
	@FXML
	Label planeSpeedLabel;
	@FXML
	Label planeHeightLabel;

	@FXML
	public void initialize() {
		dataConnection = new DataConnection();
		//TODO move to another method
		arrivalCity.setDisable(true);
		departureCity.setDisable(true);
		departureAirport.setDisable(true);
		arrivalAirport.setDisable(true);
		validateButton.setDisable(true);
		departureCountry.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				departureCity.setDisable(false);
				departureAirport.getItems().clear();
				departureAirport.setDisable(true);
				departureCity.setItems(FXCollections.observableArrayList(app.getCountries().get(newValue)));
			} else {
				departureCity.setDisable(true);
			}
		});
		departureCity.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			if(newValue != null){
				departureAirport.setDisable(false);
				ArrayList<String> dataToPass = new ArrayList<>();
				for(Airport airport : app.getAirports().values()){
					if(airport.getCity().equals(newValue)){
						dataToPass.add(airport.getName());
					}
				}
				departureAirport.setItems(FXCollections.observableArrayList(dataToPass));
			} else {
				validateButton.setDisable(true);
			}
		}));
		departureAirport.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			if(newValue != null){
				if(arrivalAirport.getSelectionModel().getSelectedItem() != null){
					validateButton.setDisable(false);
				}
			}
		}));
		arrivalCountry.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				arrivalCity.setDisable(false);
				arrivalAirport.getItems().clear();
				arrivalAirport.setDisable(true);
				arrivalCity.setItems(FXCollections.observableArrayList(app.getCountries().get(newValue)));
			} else {
				arrivalCity.setDisable(true);
			}
		});
		arrivalCity.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			if(newValue != null){
				arrivalAirport.setDisable(false);
				ArrayList<String> dataToPass = new ArrayList<>();
				for(Airport airport : app.getAirports().values()){
					if(airport.getCity().equals(newValue)){
						dataToPass.add(airport.getName());
					}
				}
				arrivalAirport.setItems(FXCollections.observableArrayList(dataToPass));
			} else {
				validateButton.setDisable(true);
			}
		}));
		arrivalAirport.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			if(newValue != null){
				if(departureAirport.getSelectionModel().getSelectedItem() != null){
					validateButton.setDisable(false);
				}
			}
		}));

		if(app != null) {
			ObservableList<String> observableListCountry = FXCollections.observableArrayList(app.getCountries().keySet());
			Collections.sort(observableListCountry);
			departureCountry.setItems(observableListCountry);
			arrivalCountry.setItems(observableListCountry);

			InterfaceController interfaceController = new InterfaceController(departureCountry, arrivalCountry, departureCity, arrivalCity, departureAirport, arrivalAirport, sizeField, validateButton, settingsButton, flightsList, planeIdLabel, planeHeightLabel, planeSpeedLabel, planeTypeLabel, militaryBoolLabel, departureAirportLabel, arrivalAirportLabel);

			DataModel dataModel = new DataModel(app.getAirports(), app.getCountries());

			validateButton.setOnMouseClicked(event -> {
				String departure = departureAirport.getSelectionModel().getSelectedItem(), arrival = arrivalAirport.getSelectionModel().getSelectedItem();
				if(departure != null && arrival != null){
					ArrayList<Flight> flights = dataConnection.makeLiaisonRequest(app.getAirports().get(app.getAirportNameToID().get(departure)), app.getAirports().get(app.getAirportNameToID().get(arrival)), app.getAirports());
					System.out.println("flights = " + flights);
					if(flights.isEmpty()){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("No flights have been found.");
						alert.setHeaderText("Empty data");
						alert.setTitle("No flights");
						alert.show();
						return;
					}
					dataModel.notifyNewFlightList(flights);
				}
			});

			flightsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue != null){
					dataModel.notifyNewSelectedFlight(newValue);
				}
			});

			interfaceController.setDataModel(dataModel);
			dataModel.subscribe(interfaceController);
		}


	}

	public void setApp(App app) {
		this.app = app;
	}
}
