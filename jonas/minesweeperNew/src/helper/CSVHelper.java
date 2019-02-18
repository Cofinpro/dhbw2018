package helper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class CSVHelper {

    private final String path;

    public CSVHelper(String path){
        this.path = path;
    }
    public Collection<String[]> readCSV(){
        Collection<String[]> result = new ArrayList<>();
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                result.add(parts);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (br != null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
    public void writeCSV(String[] csvToStrings){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for (String line : csvToStrings){
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
