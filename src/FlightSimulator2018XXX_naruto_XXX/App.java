package FlightSimulator2018XXX_naruto_XXX;

import java.util.ArrayList;

public class App {
    ArrayList<Airport> airports = new ArrayList<>();

    public App(ArrayList<Airport> airports){
        this.airports = airports;
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
