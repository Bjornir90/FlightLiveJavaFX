package FlightSimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
	    App app = new App();
	    app.appel();
	    DataConnection con = new DataConnection();
	    ArrayList<Flight> requestResult = con.makeLiaisonRequest("LFPO", "KJFK");
	    System.out.println("requestResult = " + requestResult);



	    Parent root = FXMLLoader.load(getClass().getResource("GraphicInterface.fxml"));
	    primaryStage.setTitle("Flight Live JavaFX");
	    primaryStage.setScene(new Scene(root, 700, 400));
	    primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
