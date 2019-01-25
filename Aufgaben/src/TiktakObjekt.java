
public class TiktakObjekt {

    int zahl;
    public TiktakObjekt(int zahl) {
        this.zahl = zahl;
    }
    public boolean kleinerAlsNull(){
        if(zahl < 0){
            throw new TikTakKleinerNullException();
        }
        return false;
    }

    public boolean durch3(){
        if(zahl % 3 == 0){
            System.out.println("tik");
            return true;
        }
        else return false;
    }
    public boolean durch5(){
        if(zahl % 5 == 0){
            System.out.println("tak");
            return true;
        }
        else return false;
    }
    public boolean durch15(){
        if(zahl % 15 == 0){
            System.out.println("tiktak");
            return true;
        }
        return false;
    }
    public boolean nix(){
        if(zahl % 15 !=0){
            if(zahl % 5 !=0){
                if(zahl % 3 !=0){
                    System.out.println(zahl);
                    return true;
                }
            }
        }
        return false;
    }

}
