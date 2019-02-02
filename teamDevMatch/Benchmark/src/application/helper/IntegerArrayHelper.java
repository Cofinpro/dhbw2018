package application.helper;

import application.models.BenchmarkSettings;

import java.util.Arrays;
import java.util.Random;

public class IntegerArrayHelper {

    private Integer[] intArray;

    public IntegerArrayHelper () {
        int maxLength = BenchmarkSettings.getInstance().getEndArrayLength();
        intArray = new Integer[maxLength];
        Random random = new Random();
        for (int i = 0; i < maxLength; i++) {
            intArray[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }

    public Integer[] getIntArray(int length) {
        if (length > intArray.length)
            throw new IllegalArgumentException("The array created by the constructor is too short");
        Integer[] part = Arrays.copyOfRange(intArray, 0,length);

        return part;
    }
}
