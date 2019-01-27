package helper;

import java.text.DecimalFormat;

public class OutputHelper {
    public static DecimalFormat getDecimalFormatForFigures() {
        return new DecimalFormat("0.00");
    }
}
