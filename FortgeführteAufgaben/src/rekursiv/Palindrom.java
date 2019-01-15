package rekursiv;

public class Palindrom {

    public static boolean palindromLoop(String pal) {

        int x = 0;
        int y = pal.length()-1;

        while (y > x){
            if (pal.charAt(y) != pal.charAt(x)){
                return false;
            }
            y--;
            x++;
        }
        return true;
    }

    public static boolean palindromRekusiv(String pal){

        if (pal.length() == 0||pal.length() ==1){
            return true;
        }
        int x = 0;
        int y = pal.length()-1;
        if (pal.charAt(x) == pal.charAt(y)){
            x = x+1;
            y = y-1;
            return palindromRekusiv(pal.substring(x,y));
        }
        return false;
    }
}
