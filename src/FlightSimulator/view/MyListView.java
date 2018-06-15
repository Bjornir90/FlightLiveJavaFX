package FlightSimulator.view;

import javafx.scene.control.ListView;

public class MyListView<T> {
	private ListView<T> element;

	public MyListView(ListView<T> element) {
		this.element = element;
	}
}
