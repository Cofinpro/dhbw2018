package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MetalAccount extends BankAccount{

    private static final String accountType = "MetalAccount";
    private static final double monthlyInterest = 0.0; //in decimal
    private static final double monthlyFeesPercentage = 0.0; //in decimal
    private static final double monthlyFeesAbsolute = 100.0; //in €
    private double balance;
    private double goldAmount; //in gram

    public MetalAccount() {
        super();
    }

    public MetalAccount(String bankAccountNumber, double balance, String creationDate) {
        super (bankAccountNumber, balance, creationDate);
    }

    public String getAccountType() {
        return accountType;
    }

    private void calculateWorthOfGold() throws IOException {
        URL url = new URL("https://s3.amazonaws.com/rawstore.datahub.io/51d15364c2414adf86794677d621c14b.csv");
        InputStream iS = url.openStream();
        BufferedReader bR = new BufferedReader(new InputStreamReader(iS)); //inputStreamReader --> bR can read the iS

        String s = null;
        String lastLine = "";

        while((s = bR.readLine())!= null){
           lastLine = s;
        }
        String[] parts = lastLine.split(",");
        double dollarPerOunce = Double.parseDouble(parts[1]);
        double dollarPerGram = dollarPerOunce/31.1034768;
        balance = Math.round(goldAmount*dollarPerGram*100)/100.0;

        iS.close();
    }

    @Override
    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    @Override
    public double getMonthlyFeesPercentage() {
        return monthlyFeesPercentage;
    }

    @Override
    public double getMonthlyFeesAbsolute() {
        return monthlyFeesAbsolute;
    }

    @Override
    public double getBalance() {
        //balance is calculated (depends on goldAmount and gold price)
        try {
            calculateWorthOfGold();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.balance;
    }

    public double getGoldAmount() {
        return goldAmount;
    }

    @Override
    public String csvString() {
        return accountType+","+getBankAccountNumber()+","+getBalance()+","+getCreationDate();
    }
}
