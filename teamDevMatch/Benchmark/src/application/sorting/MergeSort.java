package application.sorting;

import application.interfaces.Sorter;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sortArray(T[] array) {
        if (array.length <= 1) {
            return;
        }
        int middle = (int)(array.length/2);
        T[] firstArray = Arrays.copyOfRange(array,0, middle);
        sortArray(firstArray);
        T[] secondArray = Arrays.copyOfRange(array, middle, array.length);
        sortArray(secondArray);
        int firstCounter = 0;
        int secondCounter = 0;
        for (int i = 0; i < array.length; i++) {
            if (firstCounter == firstArray.length) {
                array[i] = secondArray[secondCounter];
                secondCounter++;
            } else if (secondCounter == secondArray.length) {
                array[i] = firstArray[firstCounter];
                firstCounter++;
            } else if (firstArray[firstCounter].compareTo(secondArray[secondCounter]) < 0){
                array[i] = firstArray[firstCounter];
                firstCounter++;
            } else {
                array[i] = secondArray[secondCounter];
                secondCounter++;
            }
        }
    }
}
