package converter;
/**
 * 
 * Definition of Length with other unit
 *
 */
public enum Length implements Unit{
	/**
	 * Define the member of the enumeration
	 * have 2 important attribute
	 * name and value
	 * name = String of unit
	 * value = value to multiplier to meters
	 */
	METER("Meter", 1.00),
	CENTIMETER("Centimeter", 0.01),
	KILOMETER("Kilometer", 1000.0),
	MILE("Mile", 1609.344),
	FOOT("Foot", 0.30480),
	WA("Wa", 2.0),
	LIGHTYEAR("Light-yeat",9460730472580800.0);
	/**
	 * Value that is multiplier to convert unit to other unit
	 */
	private final double value;
	/**
	 * Name of unit
	 */
	private final String name;
	/**
	 * Constructor of enumeration
	 * @param name name of unit
	 * @param value value that you want to convert into other unit
	 */
	private Length(String name, double value){
		this.name = name;
		this.value = value;
	}
	/**
	 * Gets the value from unit you choose
	 * @return value of unit in meter
	 */
	public double getValue() {	return value; }
	/**
	 * Gets the name from unit you choose
	 * @return name of the unit
	 */
	public String getName(){ return name; }
}
