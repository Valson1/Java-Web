package by.epam.lab.utils;

import by.epam.lab.beans.Product;

public class ConstantsUtility {
    public static final String SEPARATOR = ";";
    public static final String ELEMENT_SEPARATOR = "=>";

    public static final String FILE_NOT_FOUND = "File is not found";
    public static final String FILE_NAME = "src/in.txt";

    public static final int PRODUCT_NAME_FIELD = 0;
    public static final int PRODUCT_PRICE_FIELD = 1;
    public static final int NUMBER_UNITS_FIELD = 2;
    public static final int DISCOUNT_FIELD = 3;

    public static final int PURCHASE_NUMBER_FIELDS = Product.class.getDeclaredFields().length + 1;

    public static final String FIRST_PURCHASE_MAP = "First purchase map";
    public static final String LAST_PURCHASE_MAP = "Last purchase map";
    public static final String WEEKDAY_MAP = "Last purchase map";
    public static final String FIRST_WEEKDAY = "First weekday: ";
    public static final String LAST_WEEKDAY = "Last weekday: ";
    public static final String KEY_NOT_FOUND = "Key is not found";

    public static final String COST = "cost = ";
    public static final String BYN = " BYN";

    public static final String FORMAT = "%d.%02d";

    public static final int VALUE_DIVISOR = 100;

}
