package sorting;

import interfaces.Sorter;

public class SimonSort<T extends Comparable<T>> implements Sorter<T> {
    public void quickSort(T[] input) {
        quickSort(input, 0, input.length-1);
    }

    private void quickSort(T[] input, int startIndex, int endIndex) {
        if (endIndex - startIndex < 1) {
            return;
        }
        int smallIndex = startIndex;
        int bigIndex = startIndex;
        T pivot = input[endIndex];
        while (smallIndex != endIndex) {
            while (input[bigIndex].compareTo(pivot) < 0 && bigIndex < endIndex) {
                bigIndex++;
            }
            smallIndex = bigIndex;
            while (input[smallIndex].compareTo(pivot) > 0 && smallIndex < endIndex) {
                smallIndex++;
            }
            T temp = input[bigIndex];
            input[bigIndex] = input[smallIndex];
            input[smallIndex] = temp;
        }
        quickSort(input, startIndex, bigIndex-1);
        quickSort(input, bigIndex+1, endIndex);
    }

    @Override
    public void sortArray(T[] array) {
        quickSort(array);
    }
}
