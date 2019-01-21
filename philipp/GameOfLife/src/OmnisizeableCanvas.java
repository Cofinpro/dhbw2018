import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class OmnisizeableCanvas extends Canvas {

    static double width;
    static double height;
    GraphicsContext gc = getGraphicsContext2D();


    public OmnisizeableCanvas() {
        widthProperty().addListener(e -> triggeredByResize());
        heightProperty().addListener(e -> triggeredByResize());
        setOnMouseClicked(event -> {
            double xClick = event.getX();
            double yClick = event.getY();
            triggeredByClick(xClick, yClick);
        });
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    public void triggeredByResize() {
        width = getWidth();
        height = getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.clearRect(0,0,width,height);
        gc.fillRect(0,0,width,height);

        drawLivingCells();
        drawGrid();
    }

    private void triggeredByClick(double xClick, double yClick){
        int gfRows = GameOfLife.getInstance().getGameField().length;
        int gfColumns = GameOfLife.getInstance().getGameField()[0].length;
        double gfRowHeight = height/gfRows;
        double gfColumnWidth = width/gfColumns;
        for(int countColumn = 0; countColumn<gfColumns; countColumn++){ //go through columns
            if(countColumn*gfColumnWidth < xClick && (countColumn+1)*gfColumnWidth > xClick) { //if xClick is in column countColumn
                for (int countRow = 0; countRow < gfRows; countRow++) {
                    if (countRow * gfRowHeight < yClick && (countRow + 1) * gfRowHeight > yClick) {
                        switchCellAlive(countRow,countColumn);
                    } //ask simon weather breaks could be useful
                }
            }
        }
        drawLivingCells();
        drawGrid();
    }
    private void switchCellAlive(int row, int column){
        if(GameOfLife.getInstance().getGameField()[row][column].alive)
            GameOfLife.getInstance().getGameField()[row][column].alive = false;
        else
            GameOfLife.getInstance().getGameField()[row][column].alive = true;
    }

    public void drawGrid(){
        GameOfLifeCell[][] gF = GameOfLife.getInstance().getGameField();
        int gfRows = gF.length;
        double gfRowHeight = height/gfRows;
        int gfColumns = gF[0].length;
        double gfColumnWidth = width/gfColumns;

        for(int countRow = 0; countRow<gfRows; countRow++) {
            gc.strokeLine(0,(countRow+1)*gfRowHeight,width,(countRow+1)*gfRowHeight);
        }
        for(int countColumn = 0; countColumn<gfColumns; countColumn++){
            gc.strokeLine((countColumn+1)*gfColumnWidth,0,(countColumn+1)*gfColumnWidth,height);
        }
    }

    public void drawLivingCells(){
        GameOfLifeCell[][] gf = GameOfLife.getInstance().getGameField();
        double gfColumnWidth = width/gf[0].length;
        double gfRowHeight = height/gf.length;
        for (int row = 0; row < gf.length; row++){
            for (int column = 0; column < gf[row].length; column++){
                if (gf[row][column].alive) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(column*gfColumnWidth, row*gfRowHeight, gfColumnWidth, gfRowHeight);
                }
                else {
                    gc.setFill(Color.DARKGRAY);
                    gc.fillRect(column*gfColumnWidth, row*gfRowHeight, gfColumnWidth, gfRowHeight);
                }
            }
        }
    }
}