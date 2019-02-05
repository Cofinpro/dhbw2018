package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MetalAccount extends BankAccount{

    private static final String ACCOUNT_TYPE = "Metall Konto";
    private static final double MONTHLY_INTEREST = 0.0;
    private static final double MONTHLY_FEES_PERCENTAGE = 0.0;
    private static final double MONTHLY_FEES_ABSOLUTE = 100.0;
    private static final double GRAMSPEROUNCE = 31.1034768;
    private double goldAmountInGram;
    private double dollarPerGramOfGold;

    public MetalAccount(Customer customer) {
        super(customer);
    }

    public MetalAccount(Customer owner, String bankAccountNumber, double goldAmountInGram, String creationDate) {
        super (owner, bankAccountNumber, creationDate);
        this.goldAmountInGram = goldAmountInGram;
    }

    public String getAccountType() {
        return ACCOUNT_TYPE;
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
            dollarPerGramOfGold = dollarPerOunce/ GRAMSPEROUNCE;


            iS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getMonthlyInterest() {
        return MONTHLY_INTEREST;
    }

    @Override
    public double getMonthlyFeesPercentage() {
        return MONTHLY_FEES_PERCENTAGE;
    }

    @Override
    public double getMonthlyFeesAbsolute() {
        return MONTHLY_FEES_ABSOLUTE;
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
    public String makeCSVString() {
        return getOwner().getUserName()+","+getClass().getSimpleName()+","+getBankAccountNumber()+","+goldAmountInGram+","+getCreationDate();
    }
}
