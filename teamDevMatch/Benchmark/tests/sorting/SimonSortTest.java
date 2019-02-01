package sorting;

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

    @Test
    public void quickSort4() {
        SimonSort<Integer> mySort = new SimonSort<>();
        Integer[] array = new Integer[] { 1298,983217,72475,798275,129878732,9832174,8742532,98214727,824365,288,98725982,874327432,4325798,784398432,8743203,893212,10909,8098436,85320,94850,285,9432863,253286325,96832,60932,8636093,78437598,8376986,87576,47,543,35376,567,46,36,4346,364,753,3735,754,3744737,2464865 };
        mySort.quickSort(array);
        Integer[] sortedArray = new Integer[] { 36,46,47,285,288,364,543,567,753,754,1298,3735,4346,10909,35376,60932,72475,85320,87576,94850,96832,798275,824365,893212,983217,2464865
                ,3744737,4325798,8098436,8376986,8636093,8742532,8743203,9432863,9832174,78437598,98214727,98725982,129878732,253286325,784398432,874327432 };
        assertArrayEquals(sortedArray, array);
    }
}