import java.util.Scanner;
import java.util.SortedSet;

public class ConsoleApplication {
    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        PeriodicSystem periodicSystem = PeriodicSystem.getInstance();
        System.out.println("Hello User!");
        boolean running = true;
        while (running) {
            System.out.println("Your options");
            System.out.println("1) Show all elements");
            System.out.println("2) Inspect a specific element");
            System.out.println("x) end");
            String input = SCANNER.nextLine();
            System.out.println("***************************");
            switch (input) {
                case "1":
                    showAllElements();
                    break;
                case "2":
                    inspectSpecificElement();
                    break;
                case "x":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown input. Try again.");
                    break;
            }
        }
        System.out.println("Goodbye!");
    }

    /**
     * lets the user choose a specific element in order to then inspect it
     */
    private static void inspectSpecificElement() {
        PeriodicSystem periodicSystem = PeriodicSystem.getInstance();
        System.out.println("Choose the element:");
        try {
            System.out.print("Group: ");
            String inputLine = SCANNER.nextLine();
            int group = Integer.parseInt(inputLine);
            System.out.print("Period: ");
            inputLine = SCANNER.nextLine();
            int period = Integer.parseInt(inputLine);
            ChemicalElement chemicalElement = periodicSystem.getChemicalElement(period, group);
            inspectSpecificElement(chemicalElement);
        } catch (NumberFormatException nfe) {
            System.out.println("Group and Period can only be of type number. You didn't enter a number. Fail.");
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    /**
     * lets the user inspect an element
     * @param chemicalElement the element inspected
     */
    private static void inspectSpecificElement(ChemicalElement chemicalElement) {
        System.out.println("Name: " + chemicalElement);
        System.out.println("Symbol: " + chemicalElement.getSymbol());
        System.out.println("Atomic Number: " + chemicalElement.getAtomicNumber());
        System.out.println("Group: " + chemicalElement.getGroup());
        System.out.println("Period: " + chemicalElement.getPeriod());
        System.out.println("Alkali: " + chemicalElement.isAlkali());
        System.out.println("Fluid: " + chemicalElement.isFluid());
        System.out.println("Metalloid: " + chemicalElement.isMetalloid());
    }

    private static void showAllElements() {
        PeriodicSystem periodicSystem = PeriodicSystem.getInstance();
        SortedSet<ChemicalElement> chemicalElements = periodicSystem.getChemicalElementsAsSortedSet();
        for (ChemicalElement ce : chemicalElements) {
            System.out.println(ce);
        }
    }
}
