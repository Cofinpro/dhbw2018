package application.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {
    SelectionSort<Integer> philippSort = new SelectionSort<>();
    private Integer[] arrayToSort;
    private Integer[] arrayCorrect;

    @Test
    void selectionSort() {
        arrayToSort = new Integer[]{15, 44, 26, 94, 50, 27, 36, 40, 80, 13, 74, 60, 29, 52, 83, 86, 57, 96, 75, 2, 67, 72, 100, 43, 55, 71, 28, 63, 58, 30, 4, 56, 61, 3, 6, 49, 18, 77, 88, 69, 70, 53, 35, 33, 20, 87, 45, 17, 23, 59};
        arrayCorrect = new Integer[]{2,3,4,6,13,15,17,18,20,23,26,27,28,29,30,33,35,36,40,43,44,45,49,50,52,53,55,56,57,58,59,60,61,63,67,69,70,71,72,74,75,77,80,83,86,87,88,94,96,100};

        philippSort.sortArray(arrayToSort);
        assertArrayEquals(arrayToSort,arrayCorrect);

    }
}