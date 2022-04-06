package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

public class SegmentNumber implements Comparable<SegmentNumber> {
    private final int len;
    private int num;

    public SegmentNumber(int len) {
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
    public int compareTo(SegmentNumber o) {
	return len - o.len;
    }
}
