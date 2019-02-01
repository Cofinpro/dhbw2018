package sample.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimonSortTest {

    @Test
    public void quickSort1() {
        SimonSort<Integer> mySort = new SimonSort<>();
        Integer[] array = new Integer[] { 8, 10, 7 };
        mySort.quickSort(array);
        Integer[] sortedArray = new Integer[] { 7, 8, 10 };
        assertArrayEquals(sortedArray, array);
    }

    @Test
    public void quickSort2() {
        SimonSort<Integer> mySort = new SimonSort<>();
        Integer[] array = new Integer[] { 10, 8 };
        mySort.quickSort(array);
        Integer[] sortedArray = new Integer[] { 8, 10 };
        assertArrayEquals(sortedArray, array);
    }

    @Test
    public void quickSort3() {
        SimonSort<Integer> mySort = new SimonSort<>();
        Integer[] array = new Integer[] { 10, 7, 12, 8, 3, 2, 6 };
        mySort.quickSort(array);
        Integer[] sortedArray = new Integer[] { 2, 3, 6, 7, 8, 10, 12 };
        assertArrayEquals(sortedArray, array);
    }
}