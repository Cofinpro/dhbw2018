//Objektorientiert
public class RunTikTok {
    public static void main(String[] args) {
        TikTok tikTok = new TikTok();

        for(int i=-1; i<=100; i++) {
            System.out.println(tikTok.checkNumber(i));
        }
    }
}
