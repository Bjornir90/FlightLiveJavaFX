package FlightSimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
	    App app = new App();
	    app.appel();
	    DataConnection con = new DataConnection();
	    String request = con.makeRequest("LLBG");
	    // System.out.println("request = " + request);
	    FlightList flights = Parser.parseResponse(request);
	    System.out.println(Parser.getResponseFlight(flights));



	    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
	    primaryStage.setTitle("Hello World");
	    primaryStage.setScene(new Scene(root, 300, 275));
	    primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
