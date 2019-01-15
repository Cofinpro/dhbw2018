public class TikTak {
    private int count;

    public TikTak(int count) {
        if (count < 0) {
            throw new TikTakKleinerNullException(count);
        }
        this.count = count;
    }

    @Override
    public String toString() {
        if (count % 15 == 0) {
            return "tiktak";
        }

        if (count % 5 == 0) {
            return "tak";
        }

        if (count % 3 == 0) {
            return "tik";
        }

        return String.valueOf(count);
    }
}
