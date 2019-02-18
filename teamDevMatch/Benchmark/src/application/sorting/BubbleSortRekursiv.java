package application.sorting;

import application.interfaces.Sorter;

import java.util.Random;

public class BubbleSortRekursiv<T extends Comparable<T>> implements Sorter<T> {

    private int count = 0;

    public static void main(String[] args) {
        Integer[] integers = new Integer[100];
        Random random = new Random();
        for (int i = 0; i< integers.length; i++) {
            integers[i] = new Integer(random.nextInt(1000));
        }
        new BubbleSortRekursiv<Integer>().sort(integers);
    }

    public void sort(T[] input){
        T temp;
        for (int i = 0; i < input.length-1;i++){
            System.out.println(++count);
            if (input[i].compareTo(input[i+1]) == 1){
                temp =  input[i];
                input[i] = input[i+1];
                input[i+1] = temp;
                sort(input);
            }
        }
    }

    @Override
    public void sortArray(T[] array) {
        sort(array);
    }
}
