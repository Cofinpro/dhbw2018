package application.sorting;

import application.interfaces.Sorter;

import java.util.Random;

public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {

    private int count = 0;

    public static void main(String[] args) {
        /*Integer[] intArray = {271, -21, 10, 7, 2, 77, 42, 12};
        //Arrays.sort(intArray);
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.bubbleSort(intArray);
        for (Integer integer : intArray) {
            System.out.println(integer);
        }*/
        Integer[] integers = new Integer[100];
        Random random = new Random();
        for (int i = 0; i< integers.length; i++) {
            integers[i] = new Integer(random.nextInt(1000));
        }
        new BubbleSortRekursiv<Integer>().sort(integers);
    }

    public void bubbleSort(T[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < input.length-i; j++) {
                System.out.println(++count);
                if (input[j].compareTo(input[j-1]) < 0) {
                    T temp = input[j-1];
                    input[j-1] = input[j];
                    input[j] = temp;
                }
            }
        }
    }

    @Override
    public void sortArray(T[] array) {
        bubbleSort(array);
    }
}
