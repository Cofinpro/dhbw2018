import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class PeriodicSystem {

    private static PeriodicSystem instance = new PeriodicSystem();

    private ChemicalElement[][] pse = new ChemicalElement[7][18];

    private PeriodicSystem() {
        readCSV();
    }

    private void readInElements(ChemicalElement chemicalElement) {
        pse[chemicalElement.period][chemicalElement.group] = chemicalElement;
    }

    private ChemicalElement getElementByPosition(int period, int group) {
        if (period < 1 || period > 7 || group < 1 || group > 18)
            throw new IndexOutOfBoundsException();
        ChemicalElement pickedElement = pse[period][group];
        if (pickedElement == null)
            throw new IllegalArgumentException("This element doesn't exist");
        return pickedElement;
    }

    private void printAll(PeriodicSystem periodicSystem) {
        for (int countPeriod = 0; countPeriod < pse.length; countPeriod++) {
            for (int countGroup = 0; countGroup < pse[countPeriod].length; countGroup++)
                System.out.print(pse[countPeriod][countGroup]);
        }
    }

    private void readCSV() {
        try {
            File csvElements = new File("C://Users/phmayer/IdeaProjects/dhbw2018/philipp/PSE/elements.csv");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvElements));

            String line;
            bufferedReader.readLine();

            while (null != (line = bufferedReader.readLine())) {
                String[] split = line.split(",");
                int atomicNumber = Integer.parseInt(split[0]);
                String name = split[1];
                String symbol = split[2];
                int group = Integer.parseInt(split[3]);
                int period = Integer.parseInt(split[4]);

                ChemicalElement chemicalElement = new ChemicalElement(atomicNumber, period, group, symbol, name);
                pse[period - 1][group - 1] = chemicalElement;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String elementsString = "";
        for (int countPeriod = 0; countPeriod < pse.length; countPeriod++) {
            for (int countGroup = 0; countGroup < pse[countPeriod].length; countGroup++) {
                if (pse[countPeriod][countGroup] != null) {
                    elementsString += pse[countPeriod][countGroup];
                    elementsString += "\n";
                }
            }
        }
        return elementsString;
    }

    public static PeriodicSystem getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(PeriodicSystem.getInstance().toString());
    }
}
