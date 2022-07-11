package by.epam.lab.beans.marks;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.MarkFormat;

public class Mark implements MarkFormat {
    private final int mark;

    public Mark(int mark) {
	this.mark = mark;
    }

    public Mark(String mark) {
	this(Integer.parseInt(mark));
    }

    public Mark(double mark) {
	this((int) Math.round(mark * MARK_DIVIDER_HUNDRED));
    }

    public int getMark() {
	return mark;
    }

    @Override
    public String toString() {
	return String.valueOf(mark);
    }

    @Override
    public String meanMarkFormat() {
	return String.format(TWO_DIGITS_DECIMAL_FORMAT, mark / MARK_DIVIDER_HUNDRED, mark % MARK_DIVIDER_HUNDRED);
    }

}
