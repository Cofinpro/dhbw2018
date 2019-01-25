class TikTakKleinerNullException extends IllegalArgumentException {
    TikTakKleinerNullException() {
        super("Die Zahl ist kleiner als Null");
    }
    TikTakKleinerNullException(String fehlermeldung) {
        super(fehlermeldung);
    }
}