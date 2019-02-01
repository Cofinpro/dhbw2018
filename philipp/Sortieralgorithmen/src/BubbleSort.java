public class BubbleSort<T extends Comparable<T>> {

    public static void main(String[] args) {
        Integer[] intArray = {271, -21, 10, 7, 2, 77, 42, 12};
        //Arrays.sort(intArray);
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.bubbleSort(intArray);
        for (Integer integer : intArray) {
            System.out.println(integer);
        }
    }

    public void bubbleSort(T[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < input.length-i; j++) {
                if (input[j].compareTo(input[j-1]) < 0) {
                    T temp = input[j-1];
                    input[j-1] = input[j];
                    input[j] = temp;
                }
            }
        }
    }
}
