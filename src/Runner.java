import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.beans.SegmentNumber;

public class Runner {
    private static double powTwo(double number) {
	return number * number;
    }

    private static int segmentLength(String[] coordinates) {
	return (int) Math.round(Math.sqrt(
		powTwo(Double.parseDouble(coordinates[X1_INDEX]) - Double.parseDouble(coordinates[X2_INDEX]))
		+ powTwo(Double.parseDouble(coordinates[Y1_INDEX]) - Double.parseDouble(coordinates[Y2_INDEX]))));
    }

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader(FILE_NAME3))) {
	    List<SegmentNumber> segments = new ArrayList<>();
	    while (sc.hasNextLine()) {
		SegmentNumber segment = new SegmentNumber(segmentLength(sc.nextLine().split(REGEX)));
		int index = Collections.binarySearch(segments, segment);
		if (index >= 0) {
		    segments.get(index).addNum();
		} else {
		    segments.add(-index - 1, segment);
		}
	    }
	    segments.sort(new Comparator<SegmentNumber>() {
		@Override
		public int compare(SegmentNumber o1, SegmentNumber o2) {
		    return o2.getNum() - o1.getNum();
		}
	    });
	    for (SegmentNumber segment : segments) {
		System.out.println(segment);
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}