
public class FinancialAction {
	public static String toRubles(int value) {
		return value / 100 + "." + value / 10 % 10 + value % 10;
	}
}
