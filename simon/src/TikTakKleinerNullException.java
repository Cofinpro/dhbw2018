public class TikTakKleinerNullException extends IllegalArgumentException {
    public TikTakKleinerNullException(int count) {
        super("Count darf nicht kleiner 0 sein, hat aber den Wert" + count);
    }
}
