package Chemical;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PeriodicSystem{

    private static PeriodicSystem instance;

    private PeriodicSystem(){}

    public static PeriodicSystem getInstance(){
        if (instance == null){
            instance = new PeriodicSystem();
        }
        return instance;
    }

    private static ArrayList<ChemicalElement> chemicalElementArrayList;


    public ChemicalElement getChemicalElement(int row, int column) {

        if(row > 7){
            throw new IllegalArgumentException();
        }
        if (column > 18){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < chemicalElementArrayList.size();i++){
            if (chemicalElementArrayList.get(i).row == row && chemicalElementArrayList.get(i).column == column){

                System.out.println(chemicalElementArrayList.get(i));
                System.out.println();
                return chemicalElementArrayList.get(i);
            }
        }
        throw new NotImplementedException();
    }

    public void printChemicalElement(ChemicalElement e ){
        System.out.println(e);
    }
    public static ChemicalElement addChemicalElement(String [] metadata){
        String atomicnumber = metadata[0];
        String name = metadata[1];
        String symbol = metadata[2];
        String column = metadata[3];
        String row = metadata[4];
        int atomicNumberInt = Integer.parseInt(atomicnumber);
        int rowNumberInt = Integer.parseInt(row);
        int columnNumberInt = Integer.parseInt(column);

        return new ChemicalElement(atomicNumberInt,name,symbol,rowNumberInt,columnNumberInt);
    }
    private static ArrayList<ChemicalElement> readChemicalElementsFromCSV(String ps){
        chemicalElementArrayList = new ArrayList<>();
        String path = "C:\\Users\\JTrautmann\\chemischElemente.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();             //ignoriert erste Zeile
            String line = br.readLine();

            while (line != null){
                String[] attributes = line.split(",");
                ChemicalElement element = addChemicalElement(attributes);

                chemicalElementArrayList.add(element);
                line = br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return chemicalElementArrayList;
    }

    public static void main(String[] args) {
        PeriodicSystem system = new PeriodicSystem();
        ArrayList<ChemicalElement> chemicalElementArrayList = readChemicalElementsFromCSV("ps.cvs");
        Scanner reader = new Scanner(System.in);
        System.out.println("Bitte Reihe eingeben: ");
        int n = reader.nextInt();
        System.out.println("Bitte Spalte eingeben: ");
        int m = reader.nextInt();


        system.getChemicalElement(n,m);


        System.out.println("Ordnungszahl, Name, Symbol, Reihe, Spalte");
        for (ChemicalElement e : chemicalElementArrayList){
            system.printChemicalElement(e);

        }
    }
}
