import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;

public class PSViewController {

    private ChemicalElement chemicalElement;
    public Main main; //instance of Main class

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private Button prevElement;

    @FXML
    private TextField field;

    @FXML
    private Button nextElement;


    void initialize(){
        chemicalElement = PeriodicSystem.getInstance().getElementByAtomicNumber(1);
        handleTextOut();
    }
    @FXML
    void handlePrev() {
        chemicalElement = PeriodicSystem.getInstance().getElementByAtomicNumber(chemicalElement.getAtomicNumber()-1);
        handleTextOut();
    }

    @FXML
    void handleNext() {
        chemicalElement = PeriodicSystem.getInstance().getElementByAtomicNumber(chemicalElement.getAtomicNumber()+1);
        handleTextOut();
    }

    @FXML
    void handleTextOut() {
        field.setText(chemicalElement.toString());
    }

}
