package sample.sorting;

public class JonasSort {
    int[] arrayToSort = {10,43,88,52,57,69,94,66,61,6,76,19,72,15,26,30,58,28,87,84};

    public int[] sort(){

        int temp;
        for (int i = 0; i < arrayToSort.length-1;i++){
            if (arrayToSort[i] > arrayToSort[i+1]){
                temp =  arrayToSort[i];
                arrayToSort[i] = arrayToSort[i+1];
                arrayToSort[i+1] = temp;
                sort();
            }
        }
        return arrayToSort;
    }

    public static void main(String[] args) {

        JonasSort bubblesort = new JonasSort();

        int[] tempArray = bubblesort.sort();

        for (int i = 0; i < tempArray.length;i++){
            System.out.println(i+1+" : "+ tempArray[i]);
        }
    }
}
