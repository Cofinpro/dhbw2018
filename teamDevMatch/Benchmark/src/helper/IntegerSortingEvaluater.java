package helper;

import interfaces.Sorter;

import java.util.Dictionary;
import java.util.Random;

public class IntegerSortingEvaluater {

    public static IntegerArrayHelper integerArrayHelper = new IntegerArrayHelper(1000000);

    public long getTimeMillis(Sorter<Integer> sorter, Integer[] array) {
        long startTime = System.currentTimeMillis();
        sorter.sortArray(array);
        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;
        return deltaTime;
    }

    public long getTimeMillis(Sorter<Integer> sorter, int arrayLength) {
        Integer[] array = integerArrayHelper.getIntArray(arrayLength);
        return getTimeMillis(sorter, array);
    }

    public long getTimeMillis(Sorter<Integer> sorter, int arrayLength, int iterations){
        long average = 0;
        for (int i = 0; i < iterations; i++){
            average = average + getTimeMillis(sorter,arrayLength);
        }
        return average/iterations;
    }

}
