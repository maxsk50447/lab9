
public enum Length {
	METER("Meter", 1.00),
	CENTIMETER("Centimeter", 0.01),
	KILOMETER("Kilometer", 1000.0),
	MILE("Mile", 1609.344),
	FOOT("Foot", 0.30480),
	WA("Wa", 2.0),
	LIGHTYEAR("Light-yeat",9460730472580800.0);
	private final double value;
	private final String name;
	private Length(String name, double value){
		this.name = name;
		this.value = value;
	}
	public double getValue() {	return value; }
	public String getName(){ return name; }
}
