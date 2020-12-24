package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private double mouseX, mouseY;
    private boolean bSingleDeck = false;
    private boolean bDoubleDeck = false;
    private boolean bTripleDeck = false;
    private boolean bQuadroDeck = false;
    private boolean bHorizontal = true;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root1 = new Group();
        Group root2 = new Group();
        Group root3 = new Group();
        Group root4 = new Group();
        Group root5 = new Group();

        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        Scene scene3 = new Scene(root3);
        Scene scene4 = new Scene(root4);
        Scene scene5 = new Scene(root5);

        Canvas canvas1 = new Canvas(800, 600);
        Canvas canvas2 = new Canvas(800, 600);
        Canvas canvas3 = new Canvas(800, 600);
        Canvas canvas4 = new Canvas(800, 600);
        Canvas canvas5 = new Canvas(800, 600);

        root1.getChildren().add(canvas1);
        root2.getChildren().add(canvas2);
        root3.getChildren().add(canvas3);
        root4.getChildren().add(canvas4);
        root5.getChildren().add(canvas5);

        primaryStage.setTitle("BattleShip");

        Label labelEntryText = new Label("Игра морской бой");
        labelEntryText.setFont(new Font("Arial", 30));
        labelEntryText.relocate(150, 150);

        Label labelCreateShip1 = new Label("Задание кораблей игрок1");
        labelEntryText.setFont(new Font("Arial", 30));
        labelEntryText.relocate(150, 150);

        Button buttonRules = new Button();
        Button buttonStart = new Button();
        Button buttonNextScene2 = new Button();
        Button buttonNextScene3 = new Button();

        buttonRules.relocate(400, 300);
        buttonStart.relocate(200, 300);
        buttonNextScene2.relocate(400, 500);
        buttonNextScene3.relocate(400, 500);

        buttonRules.setText("Правила игры");
        buttonStart.setText("Старт игры");
        buttonNextScene2.setText("Далее");
        buttonNextScene3.setText("Далее");


        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        GraphicsContext gc3 = canvas3.getGraphicsContext2D();
        GraphicsContext gc4 = canvas4.getGraphicsContext2D();
        GraphicsContext gc5 = canvas5.getGraphicsContext2D();
        drawShapes(gc2);


        EventHandler<MouseEvent> eventButtonRulesClicked = e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Правила игры");
            alert.setHeaderText("Правила игры");
            alert.setContentText("Правила игры");
            alert.showAndWait();
        };


        EventHandler<MouseEvent> eventButtonStartClicked = e -> {
            root1.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Смена игрока");
            alert.setHeaderText("Первому игроку приготовиться");
            alert.showAndWait();
            primaryStage.setScene(scene2);
            primaryStage.show();
        };


        EventHandler<MouseEvent> eventButtonNextScene2 = e -> {
            root2.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Смена игрока");
            alert.setHeaderText("Второму игроку приготовиться");
            alert.showAndWait();
            drawShapes(gc3);
            primaryStage.setScene(scene3);
            primaryStage.show();

        };


        EventHandler<MouseEvent> eventButtonNextScene3 = e -> {
            root3.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Смена игрока");
            alert.setHeaderText("Первому игроку приготовиться");
            alert.showAndWait();
            drawShapes2(gc4);
            drawShapes2(gc5);
            primaryStage.setScene(scene4);
            primaryStage.show();

        };


        buttonRules.setOnMouseClicked(eventButtonRulesClicked);
        buttonStart.setOnMouseClicked(eventButtonStartClicked);
        buttonNextScene2.setOnMouseClicked(eventButtonNextScene2);
        buttonNextScene3.setOnMouseClicked(eventButtonNextScene3);
        root1.getChildren().add(labelEntryText);
        root1.getChildren().add(buttonRules);
        root1.getChildren().add(buttonStart);
        root2.getChildren().add(buttonNextScene2);
        root3.getChildren().add(buttonNextScene3);
        primaryStage.setScene(scene1);


        canvas2.setOnMouseClicked(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
            if (bSingleDeck) {
                drawCell(gc2, mouseX, mouseY);
            }
            bSingleDeck = singleDeck(gc2, mouseX, mouseY);
            if (bDoubleDeck) {
                if (bHorizontal) {
                    for (int i = 0; i < 2; i++) {
                        drawCell(gc2, mouseX, mouseY);
                        mouseX += 20;
                    }
                } else {
                    for (int i = 0; i < 2; i++) {
                        drawCell(gc2, mouseX, mouseY);
                        mouseY += 20;
                    }
                }
                // bHorizontal = true;
            }
            bDoubleDeck = doubleDeck(gc2, mouseX, mouseY);
            if (bTripleDeck) {
                if (bHorizontal) {
                    for (int i = 0; i < 3; i++) {
                        drawCell(gc2, mouseX, mouseY);
                        mouseX += 20;
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        drawCell(gc2, mouseX, mouseY);
                        mouseY += 20;
                    }
                }
                // bHorizontal = true;
            }
            bTripleDeck = tripleDeck(gc2, mouseX, mouseY);
            if (bQuadroDeck) {
                if (bHorizontal) {
                    for (int i = 0; i < 4; i++) {
                        drawCell(gc2, mouseX, mouseY);
                        mouseX += 20;
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        drawCell(gc2, mouseX, mouseY);
                        mouseY += 20;
                    }
                }
                //bHorizontal = true;
            }
            bQuadroDeck = quadroDeck(gc2, mouseX, mouseY);
        });


        scene2.setOnKeyPressed((keyEvent -> {
            drawCell(gc2, 50, 100);
            if (keyEvent.getCode() == KeyCode.V) {
                bHorizontal = false;
                System.out.print("v");

            } else if (keyEvent.getCode() == KeyCode.H) {
                bHorizontal = true;
                System.out.println("h");
            }
        }));


        canvas3.setOnMouseClicked(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
            if (bSingleDeck) {
                drawCell(gc3, mouseX, mouseY);
            }
            bSingleDeck = singleDeck(gc3, mouseX, mouseY);
            if (bDoubleDeck) {
                if (bHorizontal) {
                    for (int i = 0; i < 2; i++) {
                        drawCell(gc3, mouseX, mouseY);
                        mouseX += 20;
                    }
                } else {
                    for (int i = 0; i < 2; i++) {
                        drawCell(gc3, mouseX, mouseY);
                        mouseY += 20;
                    }
                }
                // bHorizontal = true;
            }
            bDoubleDeck = doubleDeck(gc3, mouseX, mouseY);
            if (bTripleDeck) {
                if (bHorizontal) {
                    for (int i = 0; i < 3; i++) {
                        drawCell(gc3, mouseX, mouseY);
                        mouseX += 20;
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        drawCell(gc3, mouseX, mouseY);
                        mouseY += 20;
                    }
                }
                // bHorizontal = true;
            }
            bTripleDeck = tripleDeck(gc3, mouseX, mouseY);
            if (bQuadroDeck) {
                if (bHorizontal) {
                    for (int i = 0; i < 4; i++) {
                        drawCell(gc3, mouseX, mouseY);
                        mouseX += 20;
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        drawCell(gc3, mouseX, mouseY);
                        mouseY += 20;
                    }
                }
                //bHorizontal = true;
            }
            bQuadroDeck = quadroDeck(gc3, mouseX, mouseY);
        });

        scene3.setOnKeyPressed((keyEvent -> {
            drawCell(gc3, 50, 100);
            if (keyEvent.getCode() == KeyCode.V) {
                bHorizontal = false;
                System.out.print("v");

            } else if (keyEvent.getCode() == KeyCode.H) {
                bHorizontal = true;
                System.out.println("h");
            }
        }));

        canvas4.setOnMouseClicked(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
            Attack(gc4, mouseX, mouseY);
            try {
                gc4.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("first");
            /*try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            canvas4.setVisible(false);
            //root4.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Промах");
            alert.setHeaderText("Второму игроку приготовиться");
            alert.showAndWait();
            System.out.println("second");
            canvas5.setVisible(true);
            //drawShapes2(gc5);
            primaryStage.setScene(scene5);
            primaryStage.show();
        });


        canvas5.setOnMouseClicked(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
            Attack(gc5, mouseX, mouseY);
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            canvas5.setVisible(false);
            //root5.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Промах");
            alert.setHeaderText("Первому игроку приготовиться");
            alert.showAndWait();
            //drawShapes2(gc4);
            canvas4.setVisible(true);
            primaryStage.setScene(scene4);
            primaryStage.show();
        });


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void drawShapes(GraphicsContext gc) {
        int x0 = 50;
        int y0 = 100;
        int gridSideX = 200;
        int gridSideY = 200;
        int dividingLine = 20;
        gc.setStroke(Color.BLUE);
        gc.strokeRect(x0, y0, gridSideX, gridSideY); // левое поле


        for (int i = 0; i < 9; i++) {
            gc.fillText(String.valueOf(i), x0 - 15, y0 - 5 + dividingLine); // цифры слева
            gc.strokeLine(x0 + dividingLine, y0, x0 + dividingLine, y0 + gridSideY); // вертикальные линии
            gc.strokeLine(x0, y0 + dividingLine, x0 + gridSideY, y0 + dividingLine);// горизонтальные линии
            dividingLine += 20;
        }
        gc.fillText("9", 35, 295);
        gc.fillText("A    B    C    D    E    F    G    H    I    J", 56, 95); // верхние буквы
        int w = 0;
        for (int i = 4; i >= 1; i--) {
            gc.strokeRect(50, 330 + w, 20 * i, 20);   // примеры кораблей
            w += 30;
        }
    }

    private void drawShapes2(GraphicsContext gc) {


        int x0 = 50;
        int y0 = 100;
        int gridSideX = 200;
        int gridSideY = 200;
        int dividingLine = 20;
        gc.setStroke(Color.BLUE);
        gc.strokeRect(x0, y0, gridSideX, gridSideY); // левое поле
        for (int i = 0; i < 9; i++) {
            gc.fillText(String.valueOf(i), x0 - 15, y0 - 5 + dividingLine); // цифры слева
            gc.strokeLine(x0 + dividingLine, y0, x0 + dividingLine, y0 + gridSideY); // вертикальные линии
            gc.strokeLine(x0, y0 + dividingLine, x0 + gridSideY, y0 + dividingLine);// горизонтальные линии
            dividingLine += 20;
        }
        gc.fillText("9", 35, 295);
        gc.fillText("A    B    C    D    E    F    G    H    I    J", 56, 95); // верхние буквы

        dividingLine = 20;
        x0 = 450;
        y0 = 100;

        gc.setStroke(Color.BLUE);
        gc.strokeRect(x0, y0, gridSideX, gridSideY); // правое поле
        for (int i = 0; i < 9; i++) {
            gc.fillText(String.valueOf(i), x0 - 15, y0 - 5 + dividingLine); // цифры слева
            gc.strokeLine(x0 + dividingLine, y0, x0 + dividingLine, y0 + gridSideY); // вертикальные линии
            gc.strokeLine(x0, y0 + dividingLine, x0 + gridSideY, y0 + dividingLine);// горизонтальные линии
            dividingLine += 20;
        }
        gc.fillText("9", 435, 295);
        gc.fillText("A    B    C    D    E    F    G    H    I    J", 456, 95); // верхние буквы

    }

    private void drawCell(GraphicsContext gc, double x, double y) {
        if (x > 50 && x < 250 && y > 100 && y < 300) {
            int i = (int) (x - 50) / 20;
            int j = (int) (y - 100) / 20;
            int w = 20;
            int rect_x = i * w;
            int rect_y = j * w;
            gc.setFill(Color.GREEN);
            gc.fillRect(rect_x + 50, rect_y + 100, w, w);

        }
    }

    private boolean singleDeck(GraphicsContext gc, double x, double y) {
        if (x > 50 && x < 70 && y > 420 && y < 440) {
            gc.setFill(Color.RED);
            gc.fillRect(51, 421, 18, 18);
            return true;
        } else {
            gc.setFill(Color.WHITE);
            gc.fillRect(51, 421, 18, 18);
            return false;
        }
    }

    private boolean doubleDeck(GraphicsContext gc, double x, double y) {
        if (x > 50 && x < 90 && y > 390 && y < 410) {
            gc.setFill(Color.RED);
            gc.fillRect(51, 391, 38, 18);
            return true;
        } else {
            gc.setFill(Color.WHITE);
            gc.fillRect(51, 391, 38, 18);
            return false;
        }
    }

    private boolean tripleDeck(GraphicsContext gc, double x, double y) {
        if (x > 50 && x < 110 && y > 360 && y < 380) {
            gc.setFill(Color.RED);
            gc.fillRect(51, 361, 58, 18);
            return true;
        } else {
            gc.setFill(Color.WHITE);
            gc.fillRect(51, 361, 58, 18);
            return false;
        }
    }

    private boolean quadroDeck(GraphicsContext gc, double x, double y) {
        if (x > 50 && x < 130 && y > 330 && y < 350) {
            gc.setFill(Color.RED);
            gc.fillRect(51, 331, 78, 18);
            return true;
        } else {
            gc.setFill(Color.WHITE);
            gc.fillRect(51, 331, 78, 18);
            return false;
        }
    }

    private void Attack(GraphicsContext gc, double x, double y) {
        if (x > 450 && x < 650 && y > 100 && y < 300) {
            int i = (int) (x - 50) / 20;
            int j = (int) (y - 100) / 20;
            int w = 20;
            int rect_x = i * w;
            int rect_y = j * w;
            gc.setFill(Color.GREEN);
            gc.fillRect(rect_x + 50, rect_y + 100, w, w);

        }
    }
}
