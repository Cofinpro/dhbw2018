package application.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class CSVHelper {
    public static Collection<String[]> readCSV(String path) {
        Collection<String[]> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                result.add(parts);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeCSV(String path, String[] linesToWrite) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            for (String line : linesToWrite) {
                bw.write(line);
                bw.newLine();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
