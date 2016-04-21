
public class UnitConverter {
	public double convert(double amount, Length fromUnit, Length toUnit){
		return amount*fromUnit.getValue()/toUnit.getValue();
	}
	public Length[] getUnits(){
		return Length.values();
	}
}
