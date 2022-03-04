package by.epam.lab;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtilityClass {
    public static final String LETTERS = "\\p{Alpha}";
    public static final String NATURAL_NUMBER = "[1-9]\\d*";
    public static final Pattern LETTERS_PATTERN = Pattern.compile(LETTERS);
    public static final Pattern NUMBER_PATTERN = Pattern.compile(NATURAL_NUMBER);
    
}
