package FlightSimulator.data;

public class Plane {
	private boolean isMilitary;
	private String type;
	private int id;
	private String icaoCode;

	public Plane(boolean isMilitary, String type, int id, String icaoCode) {
		this.isMilitary = isMilitary;
		this.type = type;
		this.id = id;
		this.icaoCode = icaoCode;
	}

	public boolean isMilitary() {
		return isMilitary;
	}

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Plane plane = (Plane) o;

		if (isMilitary != plane.isMilitary) return false;
		if (id != plane.id) return false;
		if (type != null ? !type.equals(plane.type) : plane.type != null) return false;
		return icaoCode != null ? icaoCode.equals(plane.icaoCode) : plane.icaoCode == null;
	}

	@Override
	public int hashCode() {
		int result = (isMilitary ? 1 : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + id;
		result = 31 * result + (icaoCode != null ? icaoCode.hashCode() : 0);
		return result;
	}
}
