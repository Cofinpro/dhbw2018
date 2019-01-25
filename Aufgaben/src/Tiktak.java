public class Tiktak {
    public static void main(String[] args){
        for(int i = 1;i < 101;i++){
            if (i % 3 == 0){
                System.out.println("tik");
            }
            else if(i % 5 == 0){
                System.out.println("tak");
            }
            else if(i % 15 == 0){
                System.out.println("tiktak");
            }
            else{
                System.out.println(i);
            }
        }
    }
}
