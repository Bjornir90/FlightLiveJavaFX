package FlightSimulator;

import FlightSimulator.Parser;

import java.util.ArrayList;

public class App {
    public static ArrayList<Airport> airports;

    public App(){

        airports = new ArrayList<>();
    }

    public void appel(){
        Parser.parse(airports);
        String country = airports.get(1).getCountry();
        ArrayList<String> cities=Parser.getCitiesOfCountry(airports, country);
        for (String i : cities){
            System.out.println(i);
        }
    }
}
