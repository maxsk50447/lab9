/**
 * 
 * Application to test that UnitConventer can use
 *
 */
public class ConverterApp {
	public static void main(String[] args) {
		for(Length x : Length.values()){
			System.out.printf("%s = %f\n", x.toString(), x.getValue());
		}
		//test the converter method
		UnitConverter test = new UnitConverter();
		System.out.println(test.convert(5.0, Length.METER, Length.CENTIMETER));
	}
}
