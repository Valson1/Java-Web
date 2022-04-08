import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import by.epam.lab.utils.ValueMapComparator;

import static by.epam.lab.utils.ConstantsUtility.*;

public class Runner {
    private static double powTwo(double number) {
	return number * number;
    }
    
    private static void printCollection(List<Map.Entry<Integer,Integer>> values) {
	 for (Map.Entry<Integer, Integer> entry : values) {
		System.out.println(entry.getKey() + ELEMENT_SEPARATOR + entry.getValue());
	    }
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
		Integer value = segmentsMap.get(segmentLength);
		if (value == null) {
		    value = 0;
		}
		segmentsMap.put(segmentLength, ++value);
	    }
	    List<Map.Entry<Integer, Integer>> values = new ArrayList<>(segmentsMap.entrySet());
	    Collections.sort(values, new ValueMapComparator());
	    printCollection(values);
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}