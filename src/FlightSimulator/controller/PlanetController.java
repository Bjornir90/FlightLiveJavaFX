package FlightSimulator.controller;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

public class PlanetController {
    private Pane pane;

    public PlanetController(Pane pane) {
        this.pane = pane;
        displayEarth(pane);
    }

    public void displayEarth(Pane pane){
        Sphere sphere = new Sphere(1);
        pane.getChildren().add(sphere);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        Group cameraGroup = new Group(camera);
        pane.getChildren().add(cameraGroup);
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(-180);
        light.setTranslateY(-90);
        light.setTranslateZ(-120);
        light.getScope().addAll(pane);
        pane.getChildren().add(light);
        Scene scene = new Scene(pane, 600, 600, true);
        scene.setCamera(camera);
    }

}
