package by.epam.lab.beans.marks;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.MarkFormat;

public class HalfMark implements MarkFormat {
    private static String FORMAT = "%d";
    private static int MARK_DIVIDER = 2;
    private static double DOUBLE_MARK_DIVIDER = 2.0;
    
    private final int mark;

    public HalfMark(int mark) {
	this.mark = (int) (mark / DOUBLE_MARK_DIVIDER * MARK_DIVIDER_TEN);
    }

    public HalfMark(String mark) {
	this.mark = (int) (Double.parseDouble(mark) * MARK_DIVIDER);
    }

    public HalfMark(double mark) {
	this.mark = (int) Math.round(mark / MARK_DIVIDER * MARK_DIVIDER_HUNDRED);
    }

    public int getMark() {
	return mark;
    }

    @Override
    public String toString() {
	int beforePointMark = mark / MARK_DIVIDER_TEN;
	int afterPointMark = mark % MARK_DIVIDER_TEN;
	return afterPointMark == 0 ? String.format(FORMAT, beforePointMark)
		: String.format(ONE_DIGIT_DECIMAL_FORMAT, beforePointMark, afterPointMark);

    }

    @Override
    public String meanMarkFormat() {
	return String.format(TWO_DIGITS_DECIMAL_FORMAT, mark / MARK_DIVIDER_HUNDRED, mark % MARK_DIVIDER_HUNDRED);
    }

}
