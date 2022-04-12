package by.epam.lab.beans;

public class SegmentNumber {
    private final static String SEPARATOR = ";";
    private final int len;
    private final int num;

    public SegmentNumber(int len, int num) {
	this.len = len;
	this.num = num;
    }

    public int getLen() {
	return len;
    }

    public int getNum() {
	return num;
    }

    @Override
    public String toString() {
	return len + SEPARATOR + num;
    }
}
