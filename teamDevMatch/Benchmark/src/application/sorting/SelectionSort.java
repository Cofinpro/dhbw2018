package application.sorting;

import application.interfaces.Sorter;

public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    public static void main(String[] args) {

        Integer[] intArray = {271, -21, 10, 7, 2, 77, 42, 12};
        SelectionSort<Integer> philippSort = new SelectionSort<>();
        philippSort.selectionSort(intArray);
        for (Integer integer : intArray) {
            System.out.println(integer);
        }
    }

    public void selectionSort(T[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i].compareTo(input[j]) > 0) {
                    T temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
    }

    @Override
    public void sortArray(T[] array) {
        selectionSort(array);
    }
}
