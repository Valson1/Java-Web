package by.epam.lab.utils;

public class TestConstantsUtility {
    public static final String CSV_FILE_NAME1 = "src/in.csv";
    public static final String CSV_FILE_NAME2 = "wefwedas";

    public static final String EXPECTED_STRING = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;milk;1.31;2;2.62;Purchase;bread;1.54;3;4.62;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    public static final String EXPECTED_STRING_DELETE_LEFT = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    public static final String EXPECTED_STRING_DELETE_MIDDLE = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70";
    public static final String EXPECTED_STRING_MIDDLE_ADD = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;milk;1.31;2;2.62;Purchase;bread;1.54;3;4.62;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;www;0.02;12;0.24;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    public static final String EXPECTED_STRING_LEFT_ADD = "Purchase;www;0.02;12;0.24;" + EXPECTED_STRING;
    public static final String EXPECTED_STRING_RIGHT_ADD = EXPECTED_STRING_LEFT_ADD + ";Purchase;www;0.02;12;0.24";
    public static final String EXPECTED_STRING_SORTED = "Purchase;milk;1.31;2;2.62;Purchase;bread;1.45;5;7.25;Purchase;bread;1.54;3;4.62;DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    public static final String EXPECTED_STRING_DELETE = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;milk;1.31;2;2.62;Purchase;bread;1.54;3;4.62;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70";
}
