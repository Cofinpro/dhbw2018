package helper;

import interfaces.Sorter;

import java.util.Dictionary;
import java.util.Random;

public class IntegerSortingEvaluater {

    public long getTimeMillis(Sorter<Integer> sorter, Integer[] array) {
        long startTime = System.currentTimeMillis();
        sorter.sortArray(array);
        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;
        return deltaTime;
    }

    public long getTimeMillis(Sorter<Integer> sorter, int arrayLength) {
        Integer[] array = new Integer[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return getTimeMillis(sorter, array);
    }
}
