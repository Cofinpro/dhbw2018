package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GoldPrice {

    private static GoldPrice instance;
    private static double dollarPerGramOfGold;
    private static final double GRAMSPEROUNCE = 31.1034768;

    private GoldPrice() {
        try {
            updateDollarPerGramOfGold();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static GoldPrice getInstance() {
        if (instance == null) {
            instance = new GoldPrice();
        }
        return instance;
    }

    public double getDollarPerGramOfGold() {
        return dollarPerGramOfGold;
    }

    public double calculateWorth(double goldAmountInGram) {
        return Math.round(goldAmountInGram * dollarPerGramOfGold*100)/100.0;
    }

    private void updateDollarPerGramOfGold() throws MalformedURLException {
        URL url = new URL("https://s3.amazonaws.com/rawstore.datahub.io/51d15364c2414adf86794677d621c14b.csv");
        InputStream iS = null;
        try {
            iS = url.openStream();
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS)); //inputStreamReader --> bR can read the iS

            String s = null;
            String lastLine = "";

            while((s = bR.readLine())!= null){
                lastLine = s;
            }
            String[] parts = lastLine.split(",");
            double dollarPerOunce = Double.parseDouble(parts[1]);
            dollarPerGramOfGold = dollarPerOunce/ GRAMSPEROUNCE;


            iS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
