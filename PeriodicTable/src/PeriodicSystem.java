import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.*;

public class PeriodicSystem {
    private SortedSet<ChemicalElement> chemicalElements;

    private PeriodicSystem() {
        chemicalElements = new TreeSet<>();
        readInElements();
    }

    public static PeriodicSystem getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public SortedSet<ChemicalElement> getChemicalElementsCopy() {
        return new TreeSet<>(chemicalElements);
    }
    private void readInElements() {
        String path = "C:\\Users\\SLammes\\IdeaProjects\\dhbw2018\\PeriodicTable\\src\\ps.csv";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line;
            //the first line doesn't contain the data so we read over it
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int atomicNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                String symbol = parts[2];
                int group = Integer.parseInt(parts[3]);
                int period = Integer.parseInt(parts[4]);
                ChemicalElement chemicalElement = new ChemicalElement(atomicNumber, name, symbol, period, group);
                chemicalElements.add(chemicalElement);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ChemicalElement getChemicalElement(int period, int group) {
        if (period < 1 || period > 7) {
            throw new IllegalArgumentException("The period should be between 1 and 7 but is " + period);
        }
        if (group < 1 || group > 18) {
            throw new IllegalArgumentException("The group should be between 1 and 18 but is " + group);
        }
        for (ChemicalElement current : chemicalElements) {
            if (current.getGroup() == group && current.getPeriod() == period) {
                return current;
            }
        }
        throw new NotImplementedException();
    }

    private static class SingletonHelper {
        private static PeriodicSystem INSTANCE = new PeriodicSystem();
    }
}
