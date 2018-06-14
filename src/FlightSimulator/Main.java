package FlightSimulator;

import FlightSimulator.controller.LoadingController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
	    App app = new App();
	    /*DataConnection con = new DataConnection();
	    ArrayList<Flight> requestResult = con.makeLiaisonRequest("LFPO", "KJFK");
	    System.out.println("requestResult = " + requestResult);*/


	    FXMLLoader loader = new FXMLLoader(getClass().getResource("controller/GraphicInterface.fxml"));
	    Parent root = loader.load();
	    LoadingController controller = loader.getController();
	    controller.setApp(app);
	    controller.initialize();
	    primaryStage.setTitle("Flight Live JavaFX");
	    primaryStage.setScene(new Scene(root));
	    primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
