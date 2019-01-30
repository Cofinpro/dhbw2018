package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.CustomerManager;
import models.MetalAccount;

public class MetalAccountController extends BankAccountController {
    @FXML private TextField goldAmountTextField;

    private MetalAccount metalAccount;

    @FXML
    public void initialize() {
        super.initialize();
        metalAccount = (MetalAccount) CustomerManager.getInstance().getInspectedBankAccount();
        goldAmountTextField.setText(String.valueOf(metalAccount.getGoldAmount()));
    }
}
