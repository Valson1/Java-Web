package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

public class Segment implements Comparable<Segment> {
    private final int len;
    private int num;

    public Segment(int len) {
	this.len = len;
	this.num = 1;
    }

    public int getLen() {
	return len;
    }

    public int getNum() {
	return num;
    }

    public void addNum() {
	num++;
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
