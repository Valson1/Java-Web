package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.util.List;

public class Segment implements Comparable<Segment> {
    private final int len;
    private int num;

    public Segment(int len, int num) {
	this.len = len;
	this.num = num;
    }

    public Segment(List<String> coordinates, int num) {
	this(segmentLength(Double.parseDouble(coordinates.get(X1_INDEX)), Double.parseDouble(coordinates.get(Y1_INDEX)),
		Double.parseDouble(coordinates.get(X2_INDEX)), Double.parseDouble(coordinates.get(Y2_INDEX))), num);
    }

    private static int segmentLength(double x1, double y1, double x2, double y2) {
	return (int) Math.round(Math.sqrt(powTwo(x1 - x2) + powTwo(y1 - y2)));
    }

    private static double powTwo(double number) {
	return number * number;
    }

    public int getLen() {
	return len;
    }

    public int getNum() {
	return num;
    }

    public void addNum() {
	num += 1;
    }

    @Override
    public String toString() {
	return len + SEPARATOR + num;
    }

    @Override
    public int compareTo(Segment o) {
	return len - o.len;
    }
}
