import java.util.List;


public class BinaryArrayToNumber {

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        int help = 0;


        for (int i = 0; i <= binary.size()-1;i++){
            if (binary.get(i) == 1){
                help = help + (int)Math.pow(2,binary.size()-1-i);
            }else {
                help = help;
            }
            if (i == binary.size() && binary.get(i) == 1){
                help = help+1;
            }else{
                help = help;
            }


        }
        return help;
    }
}