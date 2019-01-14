// Objekt
public class TikTok {

    public String checkNumber(int number){
        if(number < 0)
            throw new TikTakKleinerNullException();
        if(number%15 == 0)
            return "tiktok";
        if(number%5 == 0)
            return "tok";
        if(number%3 == 0)
            return "tik";
        else
            return "" + number;
    }
}
