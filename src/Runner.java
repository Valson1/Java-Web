import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.beans.Segment;

public class Runner {
    private static double powTwo(double number) {
	return number * number;
    }

    private static int segmentLength(String[] coordinates) {
	return (int) Math.round(Math.sqrt(
		powTwo(Double.parseDouble(coordinates[X1_INDEX]) - Double.parseDouble(coordinates[X2_INDEX])) + powTwo(
			Double.parseDouble(coordinates[Y1_INDEX]) - Double.parseDouble(coordinates[Y2_INDEX]))));
    }

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader(FILE_NAME3))) {
	    List<Segment> segments = new ArrayList<>();
	    while (sc.hasNextLine()) {
		Segment segment = new Segment(segmentLength(sc.nextLine().split(REGEX)));
		int index = Collections.binarySearch(segments, segment);
		if (index >= 0) {
		    segments.get(index).addNum();
		} else {
		    segments.add(-index - 1, segment);
		}
	    }
	    segments.sort(new Comparator<Segment>() {
		@Override
		public int compare(Segment o1, Segment o2) {
		    return o2.getNum() - o1.getNum();
		}
	    });
	    for (Segment segment : segments) {
		System.out.println(segment);
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}
