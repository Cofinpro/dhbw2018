public class Adder {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int add(Integer a, Integer b) {
        int valueA = a == null ? 0 : a.intValue();
        int valueB = b == null ? 0 : b.intValue();
        return add(valueA, valueB);
    }

    public static int addRecursive(int a, int b) {
        if (b == 0) {
            return a;
        } else if (b < 0) {
            return addRecursive(a, b+1) -1;
        } else {
            return addRecursive(a, b-1) +1;
        }
    }
}
