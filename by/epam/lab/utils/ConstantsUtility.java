package by.epam.lab.utils;

import by.epam.lab.beans.DiscountUnitsPurchase;
import by.epam.lab.beans.Purchase;

public class ConstantsUtility {
    public static final String SEPARATOR = ";";

    public static final int FIRST_ELEMENT = 0;
    public static final int SECOND_ELEMENT = 1;
    public static final int THIRD_ELEMENT = 2;
    public static final int FOURTH_ELEMENT = 3;
    

    public static final String EXCEPTION_MESSAGE_NAME = "Wrong field name ";
    public static final String EXCEPTION_MESSAGE_PRICE = "Wrong field price ";
    public static final String EXCEPTION_MESSAGE_NUMBER_OF_UNITS = "Wrong field numberOfPurchaseUnits ";
    public static final String EXCEPTION_MESSAGE_DISCOUNT_PRICE = "Wrong field discountForUnit ";
    public static final String EXCEPTION_MESSAGE_CSV_LENGTH = "Number of csv line elements is ";
    public static final String EXCEPTION_MESSAGE_BYN_VALUE = "Wrong value argument ";
    public static final String EXCEPTION_MESSAGE_BYN_COPS = "Wrong kopecks argument";
    public static final String EXCEPTION_MESSAGE_BYN_RUBS = "Wrong rubles argument ";
    public static final String EXCEPTION_MESSAGE_FILE = "File is not found";
    public static final String FORMAT = "%d.%02d";

    public final static int PURCHASE_NUMBER_FIELDS = Purchase.class.getDeclaredFields().length;
    public final static int DISCOUNT_PURCHASE_NUMBER_FIELDS = PURCHASE_NUMBER_FIELDS + DiscountUnitsPurchase.class.getDeclaredFields().length;

    public static final int VALUE_DIVISOR = 100;
}
