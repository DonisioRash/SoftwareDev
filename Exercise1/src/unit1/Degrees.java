package unit1;

import javax.swing.JOptionPane;

public class Degrees {

	public static void main(String[] args) {
		String numInput = JOptionPane.showInputDialog("Please enter Temprature in Celsius:");
		
		float celsius = Float.parseFloat(numInput);
		
		float fahrenheit = (celsius * 9.0f / 5.0f) + 32;
		
		 // Round to nearest whole number
		//The Java Math class has many methods that allows you to perform mathematical tasks on numbers.
        long roundedCelsius = Math.round(celsius);
        long roundedFahrenheit = Math.round(fahrenheit);
		
		JOptionPane.showMessageDialog(null, "Temperature in degrees Celsius: " + roundedCelsius + "C" +
                "\nTemperature in degrees Fahrenheit: " + roundedFahrenheit + "F");
	}

}
