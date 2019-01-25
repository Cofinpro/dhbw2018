package Chemical;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PeriodicSystemController {

    private ChemicalElement current;
    public static final int MAX_ROW = 7;
    public static final int MIN_ROW_MIN_COLUMN = 1;
    public static final int MAX_COLUMN = 18;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField outputTextField;


    @FXML
    void onNextElementRequested(ActionEvent event) {
            current = PeriodicSystem.getChemicalElement(current.getAtomicnumber()+1);
            outputTextField.setText(current.toString());
    }

    @FXML
    void initialize() {

        current = PeriodicSystem.getChemicalElement(1,1);
        outputTextField.setText(current.toString());

    }

}


