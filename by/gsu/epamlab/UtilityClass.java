package by.gsu.epamlab;

public class UtilityClass {
	public static String toRubles(int value) {
		return String.format("%d.%02d",value / 100,value % 100);
	}
}
