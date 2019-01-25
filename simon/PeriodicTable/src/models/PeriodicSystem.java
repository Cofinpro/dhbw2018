package models;

import exceptions.ChemicalElementNotFoundException;
import exceptions.NotExistingGroupException;
import exceptions.NotExistingPeriodException;

import java.io.*;
import java.util.*;

public class PeriodicSystem {
    //period first then group
    private ChemicalElement[][] chemicalElements;

    private PeriodicSystem() {
        chemicalElements = new ChemicalElement[7][18];
        readInElements();
    }

    public static PeriodicSystem getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private void readInElements() {
        String path = "resources\\ps.csv";
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
                chemicalElements[period-1][group-1] = chemicalElement;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("The csv contains a chemical element with an unacceptable group or period");
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

    public ChemicalElement getFirstChemicalElement() {
        return chemicalElements[0][0];
    }

    public ChemicalElement getNextChemicalElement(ChemicalElement current) {
        ChemicalElement next = null;
        int group = current.getGroup();
        int period = current.getPeriod();
        while (period < 8 && group < 19) {
            if (group < 18) {
                group++;
            } else if (period < 7) {
                period++;
                group = 1;
            }
            next = chemicalElements[period-1][group-1];
            if (next != null) {
                break;
            }
        }
        return next;
    }

    public ChemicalElement getChemicalElement(int period, int group) {
        if (period < 1 || period > 7) {
            throw new NotExistingPeriodException(period);
        }
        if (group < 1 || group > 18) {
            throw new NotExistingGroupException(group);
        }
        if (chemicalElements[period-1][group-1] == null) {
            throw new ChemicalElementNotFoundException(period, group);
        }
        return chemicalElements[period-1][group-1];
    }

    @Override
    public String toString() {
        String representation = "";
        for (int i = 0; i < chemicalElements.length; i++) {
            for (int j = 0; j < chemicalElements[i].length; j++) {
                if (chemicalElements[i][j] != null) {
                    representation += chemicalElements[i][j];
                }
            }
        }
        return  representation;
    }

    public Set<ChemicalElement> getAllMetalloids() {
        Set<ChemicalElement> result = new TreeSet<>();
        for (int i = 0; i < chemicalElements.length; i++) {
            for (int j = 0; j < chemicalElements[i].length; j++) {
                if (chemicalElements[i][j] != null && chemicalElements[i][j].isMetalloid()) {
                    result.add((chemicalElements[i][j]));
                }
            }
        }
        return result;
    }

    private static class SingletonHelper {
        private static PeriodicSystem INSTANCE = new PeriodicSystem();
    }
}
