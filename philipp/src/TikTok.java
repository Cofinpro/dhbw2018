// Objekt
public class TikTok {

    private int i;

    public TikTok(int i){
        this.i = i;
    }
    public String toString(){
        if(i%15 == 0)
            return "tiktok";
        if(i%5 == 0)
            return "tok";
        if(i%3 == 0)
            return "tik";
        else
            return "" + i;
    }
}
