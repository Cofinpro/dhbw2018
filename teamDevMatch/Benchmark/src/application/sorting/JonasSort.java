package application.sorting;

import application.interfaces.Sorter;

public class JonasSort <T extends Comparable<T>> implements Sorter<T> {

    public void sort(T[] input){

        T temp;
        for (int i = 0; i < input.length-1;i++){
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
