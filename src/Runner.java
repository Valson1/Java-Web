import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import by.epam.lab.beans.ExtraTrial;
import by.epam.lab.beans.LigthTrial;
import by.epam.lab.beans.StrongTrial;
import by.epam.lab.beans.Trial;

import static by.epam.lab.utils.ConstantUtils.*;

public class Runner {
    public static void main(String[] args) {
	List<Trial> trials = new ArrayList<>(Arrays.asList(new Trial("Semen", 10, 90), new Trial("Gena", 60, 70),
		new Trial("Trump", 40, 50), new LigthTrial("Sek", 60, 10), new LigthTrial("Kevin", 66, 67),
		new StrongTrial("Sam", 70, 90), new StrongTrial("Kon", 44, 78), new ExtraTrial("Stone", 100, 90, 80),
		new ExtraTrial("Serkan", 60, 60, 53)));
	// print all trials
	System.out.println(ALL_TRIALS_HEADER);
	trials.forEach(System.out::println);

	// print number of unpassed trials
	System.out.println(NUMBER_UNPASSED_TRIALS_HEADER);
	System.out.println(trials.stream().filter(Trial::isTrialPass).count());

	// sort trials by sum marks in current list
	trials.sort((trial,otherTrial) -> trial.compareTo(otherTrial));

	// print sum marks of each trial
	ToIntFunction<Trial> sumMarks = Trial::sumMarks;
	System.out.println(ALL_TRIALS_MARKS_SUM_HEADER);
	trials.stream().mapToInt(sumMarks).forEach(System.out::println);

	// create new list with unpassed trials and clear all marks(check all marks are
	// equal 0) and print list
	System.out.println(UNPASSED_TRIALS_HEADER);
	List<Trial> unpassedTrials = trials.stream().filter(trial -> !trial.isTrialPass()).map(Trial :: clearMarks).peek(System.out :: println).collect(Collectors.toList());
	System.out.println(MARKS_CONDITION_MESSAGE + unpassedTrials.stream().allMatch(Trial::isClear));

	// create array of sum marks and output in determine format
	int[] sumMarksArray = trials.stream().mapToInt(sumMarks).toArray();
	StringBuilder result = Arrays.stream(sumMarksArray).parallel().collect(StringBuilder :: new, (str,sum) -> str.append(sum),(str,sum) -> str.append(ARRAY_SEPARATOR).append(sum));
	System.out.println(result);
    }
}