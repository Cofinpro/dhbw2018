package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MetalAccount extends BankAccount{

    private static final String accountType = "MetalAccount";
    private static final double monthlyInterest = 0.0; //in decimal
    private static final double monthlyFeesPercentage = 0.0; //in decimal
    private static final double monthlyFeesAbsolute = 100.0; //in €
    private double goldAmountInGram; //in gram
    private double dollarPerGramOfGold;

    public MetalAccount() {
        super();
    }

    public MetalAccount(Customer owner, String bankAccountNumber, double goldAmountInGram, String creationDate) {
        super (owner, bankAccountNumber, creationDate);
        this.goldAmountInGram = goldAmountInGram;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getGoldAmountInGram() {
        return goldAmountInGram;
    }

    public double getDollarPerGramOfGold() {
        return dollarPerGramOfGold;
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
            dollarPerGramOfGold = dollarPerOunce/31.1034768;


            iS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            updateDollarPerGramOfGold();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Math.round(goldAmountInGram * dollarPerGramOfGold*100)/100.0;
    }

    @Override
    public void deposit(double depositAmount){
        this.goldAmountInGram += depositAmount/dollarPerGramOfGold;
    }

    @Override
    public void disburse(double disburseAmount) {
        this.goldAmountInGram -= disburseAmount/dollarPerGramOfGold;
    }

    @Override
    public String csvString() {
        return getOwner().getUserName()+","+getClass().getSimpleName()+","+getBankAccountNumber()+","+goldAmountInGram+","+getCreationDate();
    }
}
