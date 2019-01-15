public class Palindrom {

    public Palindrom(){
        String palindrom;
    }

    public boolean testPalindromSchleife(String palindrom){
        String pwlc = palindrom.toLowerCase(); //pwlc = palindomWithLowerCase
        int stelleStimmt = 0;
        for(int stelle=0; stelle<pwlc.length()/2+pwlc.length()%2; stelle++){
            if(pwlc.charAt(stelle) == pwlc.charAt(pwlc.length()-stelle-1))
                stelleStimmt++;
        }
        return (stelleStimmt == pwlc.length()/2+pwlc.length()%2);
    }

    public boolean testPalindromRekursiv(String palindrom){
        String pwlc = palindrom.toLowerCase();
        if(pwlc.length()<1)
            return true;
        if(pwlc.length()>0){
            if(pwlc.charAt(0) == pwlc.charAt(pwlc.length()-1)){
                testPalindromRekursiv(pwlc.substring(1, pwlc.length()-2));
            }
        }
        return false;
    }
}
