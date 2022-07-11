package by.epam.lab.beans.marks;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.MarkFormat;

public class DecimalMark implements MarkFormat {
    private static final int DECIMAL_MARK_DIVIDER = 1000;
    private final int mark;

    public DecimalMark(int mark) {
	this.mark = mark;
    }

    public DecimalMark(double mark) {
	this((int) Math.round(mark * MARK_DIVIDER_HUNDRED));
    }

    public DecimalMark(String mark) {
	this((int) (Double.parseDouble(mark) * MARK_DIVIDER_TEN));
    }

    public String meanMarkFormat() {
	return String.format(TWO_DIGITS_DECIMAL_FORMAT, mark / DECIMAL_MARK_DIVIDER,
		mark / MARK_DIVIDER_TEN % MARK_DIVIDER_HUNDRED);
    }

    @Override
    public String toString() {
	return String.format(ONE_DIGIT_DECIMAL_FORMAT, mark / MARK_DIVIDER_TEN, mark % MARK_DIVIDER_TEN);
    }

    @Override
    public int getMark() {
	return mark;
    }
}
