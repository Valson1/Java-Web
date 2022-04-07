import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static by.epam.lab.utils.ConstantsUtility.*;

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
	try (Scanner sc = new Scanner(new FileReader(FILE_NAME2))) {
	    Map<Integer, Integer> segmentsMap = new HashMap<>();
	    while (sc.hasNextLine()) {
		int segmentLength = segmentLength(sc.nextLine().split(REGEX));
		Integer value = segmentsMap.putIfAbsent(segmentLength, 1);
		if (value != null) {
		    segmentsMap.put(segmentLength, ++value);
		}
	    }
	    List<Map.Entry<Integer, Integer>> values = new ArrayList<>(segmentsMap.entrySet());
	    Collections.sort(values, new Comparator<Map.Entry<Integer, Integer>>() {
		@Override
		public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
		    return o2.getValue() - o1.getValue();
		}
	    });
	    for (Map.Entry<Integer, Integer> entry : values) {
		System.out.println(entry.getKey() + ELEMENT_SEPARATOR + entry.getValue());
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}