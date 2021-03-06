package FlightSimulator.controller;

import FlightSimulator.App;
import FlightSimulator.data.Airport;
import FlightSimulator.data.Flight;
import FlightSimulator.model.DataModel;
import FlightSimulator.model.SettingsModel;
import FlightSimulator.utils.DataConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LoadingController {
	private App app;
	private DataConnection dataConnection;
	private ArrayList<Group> departureTowns;
	private ArrayList<Group> arrivalTowns;

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
    AnchorPane root;
	@FXML
	Pane pane3D;


	@FXML
	public void initialize() {
		dataConnection = new DataConnection();
		departureTowns = new ArrayList<>();
		arrivalTowns = new ArrayList<>();
		arrivalCity.setDisable(true);
		departureCity.setDisable(true);
		departureAirport.setDisable(true);
		arrivalAirport.setDisable(true);
		validateButton.setDisable(true);
	}

	public void onAppLoaded(){
		PlanetController planetController = new PlanetController(root,pane3D);
		Group parent = planetController.displayEarth();
		SettingsModel settingsModel = new SettingsModel();
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
			for (Group g : departureTowns) {
				if (!(g.getId().equals(departureAirport.getSelectionModel().getSelectedItem()))) {
					g.getChildren().clear();
					//towns.remove(g);
				}
			}
			if(newValue != null){
				if(arrivalAirport.getSelectionModel().getSelectedItem() != null){
					validateButton.setDisable(false);
				}
				departureCity.setValue(app.getAirports().get(app.getAirportNameToID().get(newValue)).getCity());
			}
		}));

		arrivalCountry.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if(newValue != null){
				arrivalCity.setDisable(false);
				arrivalAirport.getItems().clear();
				arrivalAirport.setDisable(true);
				arrivalCity.setItems(FXCollections.observableArrayList(app.getCountries().get(newValue)));
				/*for (Group g : towns) {
					if (!(app.getAirports().containsKey(app.getAirportNameToID().get(g.getId())) && app.getAirports().get(app.getAirportNameToID().get(g.getId())).getCountry().equals(arrivalCountry))) {
						g.getChildren().clear();
						//towns.remove(g);
					}
				}*/
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
			for (Group g : arrivalTowns) {
				if (!(g.getId().equals(arrivalAirport.getSelectionModel().getSelectedItem()))) {
					g.getChildren().clear();
					//towns.remove(g);
				}
			}
			if(newValue != null){
				if(departureAirport.getSelectionModel().getSelectedItem() != null){
					validateButton.setDisable(false);
				}
				arrivalCity.setValue(app.getAirports().get(app.getAirportNameToID().get(newValue)).getCity());
			}
		}));


			ObservableList<String> observableListCountry = FXCollections.observableArrayList(app.getCountries().keySet());
			Collections.sort(observableListCountry);
			departureCountry.setItems(observableListCountry);
			arrivalCountry.setItems(observableListCountry);

			InterfaceController interfaceController = new InterfaceController(departureCountry, arrivalCountry, departureCity, arrivalCity, departureAirport, arrivalAirport, sizeField, validateButton, settingsButton, flightsList, planeIdLabel, planeHeightLabel, planeSpeedLabel, planeTypeLabel, militaryBoolLabel, departureAirportLabel, arrivalAirportLabel);

			DataModel dataModel = new DataModel(app.getAirports(), app.getCountries(), app.getAirportNameToID());


			departureCountry.setOnAction(event -> {
				String departure = departureCountry.getSelectionModel().getSelectedItem();
				if(departure != null) {
					for(Airport airport : app.getAirports().values()) {
						if (departure.equals(airport.getCountry())){
							departureTowns.add(planetController.displayTown(parent, airport.getName(), airport.getPosition()[0], airport.getPosition()[1]));
						}
					}
				}
			});

			departureAirport.setOnAction(event -> {
				String departure = departureAirport.getSelectionModel().getSelectedItem();
				if(departure != null) {
					if (app.getAirports().containsKey(app.getAirportNameToID().get(departure))){
						departureTowns.add(planetController.displayTown(parent, app.getAirports().get(app.getAirportNameToID().get(departure)).getName(), app.getAirports().get(app.getAirportNameToID().get(departure)).getPosition()[0], app.getAirports().get(app.getAirportNameToID().get(departure)).getPosition()[1]));

					}
				}
			});

			arrivalCountry.setOnAction(event -> {
				String arrival = arrivalCountry.getSelectionModel().getSelectedItem();
				if(arrival != null) {
					for(Airport airport : app.getAirports().values()) {
						if (arrival.equals(airport.getCountry())){
							arrivalTowns.add(planetController.displayTown(parent, airport.getName(), airport.getPosition()[0], airport.getPosition()[1]));
						}
					}
				}
			});

			arrivalAirport.setOnAction(event -> {
				String arrival = arrivalAirport.getSelectionModel().getSelectedItem();
				if(arrival != null) {
					if (app.getAirports().containsKey(app.getAirportNameToID().get(arrival))){
						arrivalTowns.add(planetController.displayTown(parent, app.getAirports().get(app.getAirportNameToID().get(arrival)).getName(), app.getAirports().get(app.getAirportNameToID().get(arrival)).getPosition()[0], app.getAirports().get(app.getAirportNameToID().get(arrival)).getPosition()[1]));

					}
				}
			});

			validateButton.setOnMouseClicked(event -> {
				String departure = departureAirport.getSelectionModel().getSelectedItem(), arrival = arrivalAirport.getSelectionModel().getSelectedItem();
				if(departure != null && arrival != null){
					ArrayList<Flight> flights = dataConnection.makeLiaisonRequest(app.getAirports().get(app.getAirportNameToID().get(departure)), app.getAirports().get(app.getAirportNameToID().get(arrival)), app.getAirports());
					if(flights.isEmpty()){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("No flights have been found.");
						alert.setHeaderText("Empty data");
						alert.setTitle("No flights");
						alert.show();
						return;
					}
					dataModel.notifyNewFlightList(flights);
					for(Flight flight : flights) {
						System.out.println(flight.getPosition());
						planetController.displayPlane(parent, flight.getName(), flight.getPosition()[0],flight.getPosition()[1]);
					}
				}
			});

		settingsButton.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsPopup.fxml"));
				Parent root = loader.load();
				PopupController popupController = loader.getController();
				popupController.setModel(settingsModel);
				settingsModel.subscribe(popupController);
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Settings");
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Error, could not load pop-up interface !");
				System.exit(1);
			}
		});

			flightsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue != null){
					dataModel.notifyNewSelectedFlight(newValue);
				}
			});

			interfaceController.setDataModel(dataModel);
			dataModel.subscribe(interfaceController);
			planetController.setSettingsModel(settingsModel);
			planetController.setDataModel(dataModel);
			settingsModel.subscribe(planetController);

			/*PlanetController planetController = new PlanetController(root,pane3D);
			Group parent = planetController.displayEarth();*/
			//planetController.displayTown(parent, departureAirport.getSelectionModel().getSelectedItem(),);
	}

	public void setApp(App app) {
		this.app = app;
		onAppLoaded();
	}
}
