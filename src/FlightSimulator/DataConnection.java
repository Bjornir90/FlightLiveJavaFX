package FlightSimulator;

import org.asynchttpclient.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DataConnection {

	private AsyncHttpClient client;
	private Response response;

	public DataConnection(){
		DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
				.setConnectTimeout(500)
				.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
				.setKeepAlive(false);
		client = Dsl.asyncHttpClient(clientBuilder);
	}

	public String makeRequest(Airport airport){
		return makeRequest(airport.getIcaoCode());
	}

	private String makeRequest(String airportCode){
		//Créer une requête de type GET
		BoundRequestBuilder getRequest = client.prepareGet("https://public-api.adsbexchange.com/VirtualRadar/AircraftList.json?fAirC="+airportCode);
		ListenableFuture<Response> future = getRequest.execute();
		try {
			Response result = future.get();
			return result.getResponseBody();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Flight> makeLiaisonRequest(String airportCode1, String airportCode2){
		if(airportCode1.equals(airportCode2)){
			System.err.println("The airports code can't be the same");
			return null;
		}
		String result1 = makeRequest(airportCode1);
		String result2 = makeRequest(airportCode2);
		FlightList flightList1 = Parser.parseResponse(result1);
		FlightList flightList2 = Parser.parseResponse(result2);
		ArrayList<Flight> list1 = Parser.getResponseFlight(flightList1);
		ArrayList<Flight> list2 = Parser.getResponseFlight(flightList2);
		Airport airport1 = null, airport2 = null;
		for(Airport a : App.airports){
			if(a.getIcaoCode().equals(airportCode1)){
				airport1 = a;
			} else if (a.getIcaoCode().equals(airportCode2)){
				airport2 = a;
			}
		}
		ArrayList<Flight> liaisonList = new ArrayList<>();
		for(Flight f : list1){
			if(f.getArrivalAirport() == airport1 && f.getDepartureAirport() == airport2 || f.getArrivalAirport() == airport2 && f.getDepartureAirport() == airport1){
				liaisonList.add(f);
			}
		}
		for(Flight f : list2){
			if(f.getArrivalAirport() == airport1 && f.getDepartureAirport() == airport2 || f.getArrivalAirport() == airport2 && f.getDepartureAirport() == airport1){
				liaisonList.add(f);
			}
		}
		return liaisonList;
	}

}
