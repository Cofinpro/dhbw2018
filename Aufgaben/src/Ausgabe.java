import java.util.Scanner;
public class Ausgabe{


    public static void main(String [] args){

        int zahl;
        Scanner eingabewert = new Scanner(System.in);
        System.out.println("Bitte Zahl eingeben: ");
        zahl = eingabewert.nextInt();

        TiktakObjekt tt = new TiktakObjekt(zahl);

        for(int i = 1 ; i < tt.zahl+1 ;i++){

            TiktakObjekt a = new TiktakObjekt(i);
            a.nix();
            a.durch15();
            a.durch5();
            a.durch3();
        }
    }
}
