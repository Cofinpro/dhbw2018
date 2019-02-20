package persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CsvReaderWriter {
    private final String path;

    public CsvReaderWriter(String path) {
        this.path = path;
    }

    public Collection<String[]> readCSV() {
        Collection<String[]> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                result.add(parts);
            }
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
        return result;
    }

    public void writeCSV(Collection<String[]> stringArraysToWrite) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String[] stringArray : stringArraysToWrite) {
                for (String string : stringArray) {
                    bw.write(string);
                    bw.write(",");
                }
                bw.newLine();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
