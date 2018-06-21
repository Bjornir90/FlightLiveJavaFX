package FlightSimulator.view;

import FlightSimulator.data.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class MyListView extends MyView{
	private ListView<String> element;

	public MyListView(ListView<String> element) {
		this.element = element;
	}

	@Override
	protected void notifyController() {

	}

	@Override
	public void notifyViewOfNewData(Object o) {
		if(o instanceof ArrayList){
			ArrayList<Flight> flights = (ArrayList<Flight>) o;
			ArrayList<String> flightsData = new ArrayList<>();
			for(Flight f : flights){
				flightsData.add(f.getName());
			}
			ObservableList<String> list = FXCollections.observableList(flightsData);
			element.setItems(list);
		} else {
			System.err.println("ArrayList expected; Received "+o.getClass()+" instead");
			System.exit(1);
		}
	}

}
