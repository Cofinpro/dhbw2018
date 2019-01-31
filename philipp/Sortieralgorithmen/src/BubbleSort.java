import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] zahlenFeld = {271, -21, 10, 7, 2, 77, 42, 12};
        Arrays.sort(zahlenFeld);
        sort(zahlenFeld);
        for (int i=0; i<zahlenFeld.length; i++) {
            System.out.println(zahlenFeld[i]);
        }
    }

    public static int[] sort(int[] zahlenFeld) {
        int temp;
        for (int i = 0; i < zahlenFeld.length; i++) {
            for (int j = 1; j < zahlenFeld.length-i; j++) {
                if (zahlenFeld[j] < zahlenFeld[j-1]) {
                    temp = zahlenFeld[j-1];
                    zahlenFeld[j-1] = zahlenFeld[j];
                    zahlenFeld[j] = temp;
                }
            }
        }
        return zahlenFeld;
    }
}
