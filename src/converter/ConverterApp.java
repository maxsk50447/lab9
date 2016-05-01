package converter;

import java.awt.EventQueue;

public class ConverterApp {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterUI converterMachine = new ConverterUI(new UnitConverter()); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
