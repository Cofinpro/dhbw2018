import java.util.Scanner;

public class RunChemicalElement {
    public static void main(String[] args) {
        System.out.println("What do you want to do?\n" +
                "1) Print the whole Periodic Table\n" +
                "2) Print all alkali metals\n" +
                "3) Print all fluids\n" +
                "4) Print all gases\n" +
                "5) Compare two elements by their atomic number\n");
        Scanner scanner = new Scanner(System.in);
        char newUserInput;
        do {
            System.out.println("Please enter a number");
            int userInputNumber = scanner.nextInt();
            System.out.println(buildString(userInputNumber));

            System.out.println("Do you want to start another request? (y/n): ");
            newUserInput = scanner.next().charAt(0);
        }
        while (newUserInput == 'y');
    }

    private static String buildString(int userInputNumber){

        switch (userInputNumber) {
            case 1:
                return PeriodicSystem.getInstance().toString();
            case 2:
                return "WIP";
            case 3:
                return "WIP";
            case 4:
                return "WIP";
            case 5:
                return "WIP";

            default:
                return "Please enter one of the given numbers!";
        }
    }
}
