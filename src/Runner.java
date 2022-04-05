import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.beans.Segment;

public class Runner {
    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader(FILE_NAME2))) {
	    List<Segment> segments = new ArrayList<>();
	    while (sc.hasNextLine()) {
		List<String> coordinates = new ArrayList<>(Arrays.asList(sc.nextLine().split(REGEX)));
		for (Iterator<String> it = coordinates.iterator(); it.hasNext();) {
		    if (it.next().isEmpty()) {
			it.remove();
		    }
		}
		Segment segment = new Segment(coordinates, 1);
		Collections.sort(segments);
		int index = Collections.binarySearch(segments, segment);
		if (index >= 0) {
		    segments.get(index).addNum();
		} else {
		    segments.add(segment);
		}
	    }
	    segments.sort(new Comparator<Segment>() {
		@Override
		public int compare(Segment o1, Segment o2) {
		    return o2.getNum() - o1.getNum();
		}
	    });
	    System.out.println(segments);
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}
