/* Collaboration Statement: In order to help learn course concepts,
I worked on the homework with Joshua Donegal, discussed homework topics
and issues with Joshua Donegal, and/or consulted related material that
can be found at N/A. */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * This class represents the class CSPaint. It extends Application
 * @author Saumya Jain
 * @version 1.0
 */

public class CSPaint extends Application {

/**
 * This is the main method
 * @param args These are the arguments entered in the console.
 */
    public static void main(String[] args) {
        launch(args);
    }
    private int numObjects;

/**
 * This is the start method.
 * @param stage The stage is taken in as a parameter to be presented.
 * @throws Exception The method throws an exception.
 */
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp = new BorderPane();
        bp.setPrefSize(700, 450);
        Scene s = new Scene(bp);
        stage.setScene(s);
        stage.show();
        numObjects = 0;

        Pane p = new Pane();
        p.setPrefSize(650, 450);
        p.setStyle("-fx-background-color: #ffffff");
        bp.setCenter(p);

        ToggleGroup group = new ToggleGroup();
        RadioButton drawButton = new RadioButton("Draw");
        RadioButton eraseButton = new RadioButton("Erase");
        RadioButton circleButton = new RadioButton("Circle");
        RadioButton arcButton = new RadioButton("Arc");
        RadioButton bgButton = new RadioButton("Change Background Color");
        drawButton.setToggleGroup(group);
        eraseButton.setToggleGroup(group);
        circleButton.setToggleGroup(group);
        bgButton.setToggleGroup(group);
        arcButton.setToggleGroup(group);
        TextField tf = new TextField();
        Button b = new Button("Clear Canvas");
        VBox v = new VBox();
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        v1.getChildren().addAll(drawButton, eraseButton, circleButton,
            arcButton, bgButton, tf);
        v2.getChildren().addAll(b);
        v.getChildren().addAll(v1, v2);
        v1.setSpacing(15);
        v.setSpacing(215);
        bp.setLeft(v);
        Label mouseCoords = new Label();
        Label numObj = new Label("Number of Shapes: " + numObjects);
        HBox h = new HBox();
        h.getChildren().addAll(mouseCoords, numObj);
        h.setSpacing(10);
        bp.setBottom(h);

        p.setOnMouseDragged(e -> {
                if (drawButton.isSelected()) {
                    String colorString = tf.getText();
                    Color color = Color.WHITE;
                    try {
                        color = Color.valueOf(colorString);
                    } catch (IllegalArgumentException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialogue");
                        alert.setHeaderText("Invalid Color");
                        alert.setContentText("Invalid Color Entered!");
                        alert.showAndWait();
                    }
                    Circle c = new Circle(2);
                    c.setFill(color);
                    if (e.getX() < 0) {
                        c.setVisible(false);
                    } else {
                        c.setCenterX(e.getX());
                    }
                    if (e.getY() < 0) {
                        c.setVisible(false);
                    } else {
                        c.setCenterY(e.getY());
                    }
                    p.getChildren().add(c);
                }
                if (eraseButton.isSelected()) {
                    Circle c = new Circle(10);
                    c.setFill(Color.WHITE);
                    c.setCenterX(e.getX());
                    c.setCenterY(e.getY());
                    p.getChildren().add(c);
                }
            });
        p.setOnMouseClicked(e -> {
                if (circleButton.isSelected()) {
                    String colorString = tf.getText();
                    Color color = Color.WHITE;
                    try {
                        color = Color.valueOf(colorString);
                    } catch (IllegalArgumentException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialogue");
                        alert.setHeaderText("Invalid Color");
                        alert.setContentText("Invalid Color Entered!");
                        alert.showAndWait();
                    }
                    Circle c = new Circle(15);
                    c.setFill(color);
                    c.setCenterX(e.getX());
                    c.setCenterY(e.getY());
                    p.getChildren().add(c);
                    numObjects++;
                    numObj.setText("Number of Shapes: " + numObjects);
                }
                if (bgButton.isSelected()) {
                    String colorString = tf.getText();
                    Color color = Color.WHITE;
                    try {
                        color = Color.valueOf(colorString);
                    } catch (IllegalArgumentException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialogue");
                        alert.setHeaderText("Invalid Color");
                        alert.setContentText("Invalid Color Entered!");
                        alert.showAndWait();
                    }
                    p.setStyle("-fx-background-color: " + colorString);
                }
                if (arcButton.isSelected()) {
                    String colorString = tf.getText();
                    Color color = Color.WHITE;
                    try {
                        color = Color.valueOf(colorString);
                    } catch (IllegalArgumentException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialogue");
                        alert.setHeaderText("Invalid Color");
                        alert.setContentText("Invalid Color Entered!");
                        alert.showAndWait();
                    }
                    Arc a = new Arc();
                    a.setCenterX(e.getX());
                    a.setCenterY(e.getY());
                    a.setRadiusX(30);
                    a.setRadiusY(50);
                    a.setStartAngle(30);
                    a.setLength(40);
                    a.setType(ArcType.ROUND);
                    a.setFill(color);
                    p.getChildren().add(a);
                    numObjects++;
                    numObj.setText("Number of Shapes: " + numObjects);
                }
            });
        p.setOnMouseMoved(e -> {
                mouseCoords.setText("(" + String.valueOf(e.getX()) + ", "
                    + String.valueOf(e.getY()) + ")");
            });
        b.setOnMouseClicked(e -> {
                p.getChildren().clear();
                numObjects = 0;
                numObj.setText("Number of Shapes: " + numObjects);
            });
    }
}