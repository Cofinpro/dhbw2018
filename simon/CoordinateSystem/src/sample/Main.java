package sample;

import com.sun.org.apache.bcel.internal.classfile.Code;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public DrawableCoordinateSystem coordinateSystem;
    public TextArea output;
    public TextField input;
    public CoordinateSystemDisplayingCanvas canvas;

    public Main() {
        coordinateSystem = new DrawableCoordinateSystem(500,500,10);
        input = new TextField();
        input.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleInput();
            }
        });
        output = new TextArea();
        output.setEditable(false);
        output.setFocusTraversable(false);
        output.setMinHeight(70);
        displayStatus();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Coordinate System");

        VBox vBox = new VBox();
        vBox.getChildren().add(output);
        vBox.getChildren().add(input);
        vBox.getChildren().add(createCoordinateSystem(vBox));

        primaryStage.setScene(new Scene(vBox));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.show();
    }

    private Canvas createCoordinateSystem(VBox vBox) {
        canvas = new CoordinateSystemDisplayingCanvas(coordinateSystem);
        canvas.widthProperty().bind(vBox.widthProperty());
        canvas.heightProperty().bind(vBox.heightProperty().subtract(input.heightProperty()).subtract(output.heightProperty()));
        return canvas;
    }

    private void handleInput() {
        if (input.getText().length() < 1) {
            displayStatus();
        }
        char firstChar = input.getText().charAt(0);
        String command = input.getText().substring(1);
        switch (firstChar) {
            case 'x':
                setMaxX(command);
                break;
            case 'y':
                setMaxY(command);
                break;
            case 'p':
                addPoint(command);
                break;
            case 'g':
                addPolynomicalFunction(command);
                break;
            case 'e':
                addExponentialFunction(command);
                break;
            default:
                displayStatus();
        }


    }

    private void addExponentialFunction(String command) {
        String[] substrings = command.split(",");
        try {
            String name = substrings[0];
            double a = Double.parseDouble(substrings[1]);
            double b = Double.parseDouble(substrings[2]);
            ExponentialFunction f = new ExponentialFunction(name, a, b);
            coordinateSystem.add(f);
            canvas.draw();
            output.setText("Added the function");
            input.setText("");
        } catch (NumberFormatException e) {
            output.setText(e.toString());
        } catch (IndexOutOfBoundsException e) {
            if (substrings.length != 3) {
                output.setText("Wrong input");
            } else {
                throw e;
            }
        } catch (IllegalArgumentException e) {
            output.setText(e.toString());
        }
    }

    private void addPolynomicalFunction(String command) {
        String[] substrings = command.split(",");
        String name = substrings[0];
        double[] bases = new double[substrings.length-1];
        try {
            for (int i = 0; i < bases.length; i++) {
                bases[i] = Double.parseDouble(substrings[i+1]);
            }
            coordinateSystem.add(new PolynomialFunction(name, bases));
            coordinateSystem.drawEverything(canvas.getGraphicsContext2D());
            output.setText("Added a polynomial function");
            input.setText("");
        } catch (NumberFormatException e) {
            output.setText(e.toString());
        } catch (IllegalArgumentException e) {
            output.setText(e.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            if (substrings.length < 1) {
                output.setText("Wrong input for adding a polynomial function.");
            }
        }
    }

    private void setMaxY(String command) {
        try {
            double newMaxY = Double.parseDouble(command);
            coordinateSystem.maxY = newMaxY;
            canvas.draw();
            input.setText("");
            displayStatus();
        } catch (NumberFormatException e) {
            output.setText(e.toString());
        }
}

    private void setMaxX(String command) {
        try {
            double newMaxX = Double.parseDouble(command);
            coordinateSystem.maxX = newMaxX;
            canvas.draw();
            input.setText("");
            displayStatus();
        } catch (NumberFormatException nfe) {
            output.setText(nfe.toString());
        }
    }

    private void addPoint(String command) {
        String[] substrings = command.split(",");
        if (substrings.length != 2) {
            output.setText("Fehlerhaft");
        } else {
            try {
                double x = Double.parseDouble(substrings[0]);
                double y = Double.parseDouble(substrings[1]);
                Point point = new Point(x, y);
                coordinateSystem.add(new Point(x, y));
                coordinateSystem.drawEverything(canvas.getGraphicsContext2D());
                output.setText("Punkt hinzugefügt");
            } catch (NumberFormatException e) {
                output.setText(e.toString());
            } catch (IllegalArgumentException e) {
                output.setText(e.toString());
            }
        }
        input.setText("");
    }
    private void displayStatus() {
        output.setText("Max x: " + coordinateSystem.maxX + "\nMax y: " + coordinateSystem.maxY + "\nx for setting the maxX value\ny for setting the maxY value\np for creating a point\ng for adding a polynomial function");
    }
}
