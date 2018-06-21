package FlightSimulator.controller;

import FlightSimulator.utils.Fx3DGroup;
import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.net.URL;

public class PlanetController {
    private AnchorPane pane;
    private Pane sub;
    private static final double TEXTURE_LAT_OFFSET = -0.2f;
    private static final double TEXTURE_LON_OFFSET = 2.8f;

    public PlanetController(AnchorPane pane,Pane sub) {
        this.pane = pane;
        this.sub=sub;
    }

    public Group displayEarth(){

        Group root3D = new Group();
        SubScene subScene = new SubScene(root3D,600,600,true, SceneAntialiasing.BALANCED);


        ObjModelImporter objImporter = new ObjModelImporter();
        try{
            URL modelUrl = this.getClass().getResource("Earth/earth.obj");
            objImporter.read(modelUrl);
        }catch (ImportException e){
            System.out.println(e.getMessage());
        }
        MeshView[] meshViews = objImporter.getImport();
        Group earth = new Group(meshViews);
        root3D.getChildren().add(earth);

        // Add a camera group
        PerspectiveCamera camera = new PerspectiveCamera(true);
        new CameraManager(camera, sub, root3D);

        // Add point light
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(-180);
        light.setTranslateY(-90);
        light.setTranslateZ(-120);
        light.getScope().addAll(root3D);
        root3D.getChildren().add(light);

        // Add ambient light
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.getScope().addAll(root3D);
        root3D.getChildren().add(ambientLight);


        subScene.setCamera(camera);
        subScene.setFill(Color.BLACK);

        sub.getChildren().add(subScene);
        return root3D;
    }

    public Group displayTown(Group parent, String name, double latitude, double longitude){
        Sphere sphere = new Sphere(0.01);
        Group towns = new Group();
        towns.getChildren().add(sphere);
        towns.setId(name);
        Point3D location = geoCoordTo3dCoord(latitude,longitude);
        Translate move = new Translate(location.getX(),location.getY(),location.getZ());
        towns.getTransforms().add(move);
        parent.getChildren().add(towns);
        return towns;
    }

    public Group displayPlane(Group parent, String id, double latitude, double longitude){
        Group planes= new Group();
        ObjModelImporter objImporter = new ObjModelImporter();
        try{
            URL modelUrl = this.getClass().getResource("Plane/plane.obj");
            objImporter.read(modelUrl);
        }catch (ImportException e){
            System.out.println(e.getMessage());
        }
        MeshView[] meshViews = objImporter.getImport();

        Fx3DGroup planeScale = new Fx3DGroup(meshViews);
        Fx3DGroup planeOffset = new Fx3DGroup(planeScale);
        Fx3DGroup plane = new Fx3DGroup(planeOffset);
        Point3D location = geoCoordTo3dCoord(latitude,longitude);
        Fx3DGroup translate = new Fx3DGroup(plane);
        planeScale.set3DScale(0.02);
        planeOffset.set3DTranslate(0,-1,0);
        System.out.println(location.getX()+", "+location.getY()+", " + location.getZ());
        translate.set3DTranslate(location.getX(),location.getY(),location.getZ());
        //Group plane = new Group(meshViews);
        planes.getChildren().add(plane);
        planes.setId(id);

        parent.getChildren().add(plane);
        return planes;
    }


    // From Rahel Lüthy : https://netzwerg.ch/blog/2015/03/22/javafx-3d-line/
    public Cylinder createLine(Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Cylinder line = new Cylinder(0.01f, height);

        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return line;
    }

    public static Point3D geoCoordTo3dCoord(double lat, double lon) {
        double lat_cor = lat + TEXTURE_LAT_OFFSET;
        double lon_cor = lon + TEXTURE_LON_OFFSET;
        return new Point3D(
                -java.lang.Math.sin(java.lang.Math.toRadians(lon_cor))
                        * java.lang.Math.cos(java.lang.Math.toRadians(lat_cor)),
                -java.lang.Math.sin(java.lang.Math.toRadians(lat_cor)),
                java.lang.Math.cos(java.lang.Math.toRadians(lon_cor))
                        * java.lang.Math.cos(java.lang.Math.toRadians(lat_cor)));
    }



}
