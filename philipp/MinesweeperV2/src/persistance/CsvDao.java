package persistance;

import model.HighScores;

import java.util.ArrayList;
import java.util.Collection;

public class CsvDao {

    public ArrayList<String[]> readHighScoresFromCsv() {

        CsvReaderWriter readerWriter = new CsvReaderWriter("res\\highScores.csv");
        Collection<String[]> highScoreCollection = readerWriter.readCSV();

        return new ArrayList<>(highScoreCollection);
    }

    public void writeHighScoresToCsv () {

        CsvReaderWriter readerWriter = new CsvReaderWriter("res\\highScores.csv");
        readerWriter.writeCSV(HighScores.getInstance().getUsersWithHighscores());
    }
}
