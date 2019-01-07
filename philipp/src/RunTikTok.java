//Objektorientiert
public class RunTikTok {
    public static void main(String[] args) {
        for(int i=1; i<=100; i++) {
            TikTok tt = new TikTok(i);
            System.out.println(tt);
        }
    }
}
