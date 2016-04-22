/**
 * 
 * UnitConverter use to convert value from firstUnit to other Unit
 *
 */
public class UnitConverter {
	/**
	 * Convert use to calculate and convert into other unit
	 * @param amount value that you want to convert to other unit
	 * @param fromUnit the unit that is the first unit of your value
	 * @param toUnit the unit that you have select to change to it
	 * @return double value from new unit that have already calculate
	 */
	public double convert(double amount, Unit fromUnit, Unit toUnit){
		return amount*fromUnit.getValue()/toUnit.getValue();
	}
	/**
	 * Gets the Units values
	 * @return the values of length unit
	 */
	public Unit[] getUnits(){
		return Length.values();
	}
}
