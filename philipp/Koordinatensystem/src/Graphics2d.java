import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Diese Klasse existert nur zu herumexperimentieren mit Canvas

public class Graphics2d extends Application {

    static GraphicsContext graphicsContext;

    @Override
    public void start(Stage stage) throws Exception{
        Canvas canvas = new Canvas(600, 600); // canvas ist ein Node zum Zeichnen
        stage.setTitle("Visuelle Ausgabe");
        graphicsContext = canvas.getGraphicsContext2D();//das, worauf man zeichnen kann
        //graphicsContext.drawImage(); //Bilder malen
        //graphicsContext.fillRect(); //zum Formen zeichnen (verschiedene fillBlas)
        //wichtig: erstellte Formen sind im Fenster oben links verankert
        //graphicsContext.setFill(Color.RED); //fill Farbe ändern, auch mit setFill(new Color...)
        //graphicsContext.strokeRect(); //füllt das RE nicht mit Farbe, sondern ist nur Rand
        //graphicsContext.strokeText("Hello world", 20,20); //Texte einfügen

        stage.setScene(new Scene(new Pane(canvas))); //stage ist ein Fenster?
        stage.show(); //Fenster öffnen?
    }

    public static void main(String[] args) {
        launch(args);
    }
}
