package Chemical;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PeriodicSystem {
    public static final int MAX_ROW = 7;
    public static final int MAX_COLUMN = 18;


    private static PeriodicSystem instance;

    private PeriodicSystem() {
    }

    public static PeriodicSystem getInstance() {
        if (instance == null) {
            instance = new PeriodicSystem();
        }
        return instance;
    }
    private static ArrayList<ChemicalElement> chemicalElementArrayList;


    public static ChemicalElement getChemicalElement(int row, int column) {

        if(row > PeriodicSystem.MAX_ROW){
            throw new IllegalArgumentException();
        }
        if (column > PeriodicSystem.MAX_COLUMN){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i <chemicalElementArrayList.size();i++){
            if (chemicalElementArrayList.get(i).row == row &&chemicalElementArrayList.get(i).column == column){

               /* System.out.println(chemicalElementArrayList.get(i));
                System.out.println();*/
                return chemicalElementArrayList.get(i);
            }
        }
        throw new NotImplementedException();
    }
    public static ChemicalElement getChemicalElement(int atomicnumber){
        return chemicalElementArrayList.get(atomicnumber-1);
    }

    public static ChemicalElement setChemicalElement(String[] metadata) {
        String atomicnumber = metadata[0];
        String name = metadata[1];
        String symbol = metadata[2];
        String column = metadata[3];
        String row = metadata[4];
        int atomicNumberInt = Integer.parseInt(atomicnumber);
        int rowNumberInt = Integer.parseInt(row);
        int columnNumberInt = Integer.parseInt(column);

        return new ChemicalElement(atomicNumberInt, name, symbol, rowNumberInt, columnNumberInt);
    }
    public static ArrayList<ChemicalElement> getElementsInPeriodicSystem(){
        chemicalElementArrayList = new ArrayList<>();
        String path = "C:\\Users\\JTrautmann\\chemischElemente.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();             //ignoriert erste Zeile
            String line = br.readLine();

            while (line != null){
                String[] attributes = line.split(",");
                ChemicalElement element = setChemicalElement(attributes);

                chemicalElementArrayList.add(element);
                line = br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return chemicalElementArrayList;
    }

    public static void printChemicalElement(ChemicalElement e ){
        System.out.println(e);

    }
    public static void main(String[] args) {

        ArrayList<ChemicalElement> chemicalElementArrayList =getElementsInPeriodicSystem();
        Scanner reader = new Scanner(System.in);
        System.out.println("Bitte Reihe eingeben: ");
        int userInputRow = reader.nextInt();
        System.out.println("Bitte Spalte eingeben: ");
        int userInputColumn = reader.nextInt();
        getChemicalElement(userInputRow,userInputColumn);

        System.out.println("Ordnungszahl, Name, Symbol, Reihe, Spalte");
        for (ChemicalElement e : chemicalElementArrayList){
            printChemicalElement(e);

        }
    }

}