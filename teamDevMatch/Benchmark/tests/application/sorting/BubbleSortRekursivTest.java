package application.sorting;


import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

class BubbleSortRekursivTest {

    BubbleSortRekursiv<Integer> jonasSort = new BubbleSortRekursiv<>();
    private Integer[] setOne;
    private Integer[] setTwo;
    private Integer[] setOneSorted;
    private Integer[] setTwoSorted;

    @Test
    void sort() {
        setOne = new Integer[]{15, 44, 26, 94, 50, 27, 36, 40, 80, 13, 74, 60, 29, 52, 83, 86, 57, 96, 75, 2, 67, 72, 100, 43, 55, 71, 28, 63, 58, 30, 4, 56, 61, 3, 6, 49, 18, 77, 88, 69, 70, 53, 35, 33, 20, 87, 45, 17, 23, 59};
        setTwo = new Integer[]{11, 42, 1, 69, 46, 40, 89, 52, 70, 45, 63, 23, 32, 54, 74, 41, 66, 20, 87, 13, 60, 35, 98, 31, 83, 28, 36, 79, 47, 25, 82, 50, 34, 15, 9, 62, 78, 44, 30, 10, 81, 5, 55, 96, 72, 67, 64, 100, 6, 27};
        setOneSorted = new Integer[]{2,3,4,6,13,15,17,18,20,23,26,27,28,29,30,33,35,36,40,43,44,45,49,50,52,53,55,56,57,58,59,60,61,63,67,69,70,71,72,74,75,77,80,83,86,87,88,94,96,100};
        setTwoSorted = new Integer[]{1,5,6,9,10,11,13,15,20,23,25,27,28,30,31,32,34,35,36,40,41,42,44,45,46,47,50,52,54,55,60,62,63,64,66,67,69,70,72,74,78,79,81,82,83,87,89,96,98,100};

        jonasSort.sortArray(setOne);
        jonasSort.sortArray(setTwo);
        assertArrayEquals(setOneSorted,setOne);
        assertArrayEquals(setTwoSorted,setTwo);
    }
}