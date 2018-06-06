package FlightSimulator;

import org.asynchttpclient.*;

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

	public String makeRequest(){
		//Créer une requête de type GET
		BoundRequestBuilder getRequest = client.prepareGet("https://public-api.adsbexchange.com/VirtualRadar/AircraftList.json?fOpQ=Air%20France");

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

}
