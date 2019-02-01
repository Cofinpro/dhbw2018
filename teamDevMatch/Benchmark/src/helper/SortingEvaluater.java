package helper;

import interfaces.Sorter;

import java.util.Dictionary;

public class SortingEvaluater<T> {

    public long getTimeMillis(Sorter<T> sorter, T[] array) {
        long startTime = System.currentTimeMillis();
        sorter.sortArray(array);
        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;
        return deltaTime;
    }
}
