package com.example.demo;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class App extends Application {

    // Tile Images
    private Image[] numsImage = new Image[9];
    private Image bombClicked = new Image("file:./graphics/bomb_clicked.png");
    private Image bombWrong = new Image("file:./graphics/bomb_wrong.png");
    private Image blockUnclicked = new Image("file:./graphics/block_unclicked.png");
    private Image bomb = new Image("file:./graphics/bomb.png");
    private Image flag = new Image("file:./graphics/flag.png");

    // Board Information
    private int size = 40;
    private int column;
    private int row;
    private int mines;
    private Board board;

    // Information Labels
    private Label label;
    private Label labelWin;
    private Label time;

    // Reset and Size buttons
    private Button reset;
    private Button smallBoard;
    private Button mediumBoard;
    private Button largeBoard;
    private Button saveBoard;
    private Button loadBoard;

    // Game States
    private boolean hasWon = false;
    private boolean hasLost = false;

    // Miscellaneous
    private Insets winMessage = new Insets(20);
    private GridPane grid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    // Starts the application with the necessary information and graphics
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Minesweeper");

        // Adds all of the number tile graphics into an array of images
        for (int i = 1; i < numsImage.length; i++) {
            Image img = new Image("file:./graphics/" + i + ".png");
            numsImage[i] = img;
        }
        numsImage[0] = new Image("file:./graphics/block_clicked.png");
        stage.setResizable(false);

        // Creates a default 16x30 board with 99 mines (Large)
        board = new Board(16, 30, 99);
        row = 16;
        column = 30;
        mines = 99;

        // Initializes the user interface
        stage.setScene(createScene());
        stage.setMaximized(true);
        stage.show();
        changeView();
    }

    // Resizes images given into it to an Image view of a specific size
    public ImageView resize(Image image) {

        // Turns the image into an ImageView
        ImageView imageV = new ImageView(image);

        // Resizes the images with the same ratio
        imageV.setPreserveRatio(true);
        imageV.setFitWidth(size);
        imageV.setFitHeight(size);
        return imageV;
    }

    // Creates the UI on the stage
    public Scene createScene() {
        final BorderPane root = new BorderPane();

        // Creates the labels to be used
        label = new Label("" + board.getFlagsLeft(), resize(flag));
        labelWin = new Label("");
        reset = new Button("Reset");
        smallBoard = new Button("Small");
        mediumBoard = new Button("Medium");
        largeBoard = new Button("Large");
        saveBoard = new Button("Save");
        loadBoard = new Button("Load");
        time = new Label("0");

        // Sets the sizes of the buttons
        largeBoard.setMinSize(70, 30);
        largeBoard.setMaxSize(70, 30);
        mediumBoard.setMaxSize(70, 30);
        mediumBoard.setMaxSize(70, 30);
        smallBoard.setMaxSize(70, 30);
        smallBoard.setMaxSize(70, 30);
        saveBoard.setMinSize(70, 30);
        saveBoard.setMaxSize(70, 30);
        loadBoard.setMinSize(70, 30);
        loadBoard.setMaxSize(70, 30);

        // Makes the reset button create a new board with the previous size then change
        // the view
        reset.setOnMouseClicked(e -> {
            board = new Board(row, column, mines);
            changeView();
            labelWin.setText("");
        });

        // Makes the size buttons create a new board with the pre-determined sizes then
        // change the view
        smallBoard.setOnMouseClicked(e -> {
            // Creates a new 8x8 board object with 10 mines and changes the view
            board = new Board(8, 8, 10);
            row = 8;
            column = 8;
            mines = 10;
            changeView();
            labelWin.setText("");
        });
        mediumBoard.setOnMouseClicked(e -> {
            // Creates a new 16x16 board object with 40 mines and changes the view
            board = new Board(16, 16, 40);
            row = 16;
            column = 16;
            mines = 40;
            changeView();
            labelWin.setText("");
        });
        largeBoard.setOnMouseClicked(e -> {
            // Creates a new 16x30 board object with 99 mines and changes the view
            board = new Board(16, 30, 99);
            row = 16;
            column = 30;
            mines = 99;
            changeView();
            labelWin.setText("");
        });
        saveBoard.setOnMouseClicked(e -> {
            // Saves the boardstate by serializing
            saveBoard(board);
        });
        loadBoard.setOnMouseClicked(e -> {
            // Loads the boardstate by deseralizing
            board = loadBoard(board);
            changeView();
        });

        // Creates HBoxes and VBoxes for organization and appearance
        final HBox data = new HBox(label, reset, time);
        final VBox sizes = new VBox(smallBoard, mediumBoard, largeBoard, saveBoard, loadBoard);
        final HBox gameStatus = new HBox(labelWin);

        // Updates the timer every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                time.setText(Long.toString(board.getElapsedTime() / 1000));// setText(board.getElapsedTime());
            }
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();

        // Sets alignment for the VBoxes and HBoxes
        sizes.setAlignment(Pos.CENTER_RIGHT);
        data.setAlignment(Pos.TOP_CENTER);
        gameStatus.setAlignment(Pos.BOTTOM_CENTER);
        gameStatus.setPadding(winMessage);
        data.setSpacing(100);
        sizes.setSpacing(10);
        root.setCenter(grid);
        grid.setHgap(0);
        root.setTop(data);
        root.setRight(sizes);
        root.setBottom(gameStatus);
        grid.setVgap(0);
        grid.setAlignment(Pos.CENTER);
        return new Scene(root);

    }

    // Changes the board as the user interacts
    public void changeView() {

        // Sets a variable equal to the current state of the game
        hasWon = board.getHasWon();
        hasLost = board.getHasLost();

        grid.getChildren().clear();
        grid.requestFocus();

        // Creates a button for every tile of the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid.add(createTile(i, j), j, i);
            }
        }

        // Displays the amount of flags available to the user
        label.setText("" + board.getFlagsLeft());

        // Displays a win/lose message depending on the current state of the game
        if (hasWon) {
            labelWin.setText("YOU WON! CONGRATULATIONS");
        }
        if (hasLost) {
            labelWin.setText("YOU LOST! TRY AGAIN!");
        }
    }

    // Creates a tile with the required information
    public Button createTile(int row, int column) {

        // Creates a new button and sets the size
        Button button = new Button();
        button.setMinSize(size, size);
        button.setMaxSize(size, size);

        // Checks the board to see what the state of that tile is
        switch (board.getTileBoard()[row][column]) {
            case FLAGWRONG:
                if (hasLost) {
                    button.setGraphic(resize(bombWrong));
                } else {
                    button.setGraphic(resize(flag));
                }
                break;
            case FLAGRIGHT:
                button.setGraphic(resize(flag));
                break;
            case NUMBERNOTCLICKED:
                button.setGraphic(resize(blockUnclicked));
                break;
            case MINENOTCLICKED:
                if (hasLost || hasWon) {
                    button.setGraphic(resize(bomb));
                } else {
                    button.setGraphic(resize(blockUnclicked));
                }
                break;
            case MINECLICKED:
                button.setGraphic(resize(bombClicked));
                break;
            case NUMBERCLICKED:
                button.setGraphic(resize(numsImage[board.getIntBoard()[row][column]]));
                break;
        }

        // Not reached an ending state
        if (!hasLost && !hasWon) {
            // Makes the buttons run the left/right click function if left/right clicked
            button.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    board.leftClick(row, column);
                } else if (e.getButton() == MouseButton.SECONDARY) {
                    board.rightClick(row, column);
                }
                changeView();
            });
        }
        return button;
    }

    // Saves the current boardstate by serializing the board in a .ser file
    public void saveBoard(Board board) {
        try {
            FileOutputStream fileOut = new FileOutputStream("BoardSave.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(board);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /Minesweeper/BoardSave.ser");
        } catch (IOException hello) {
            System.out.println("we got an IOException on our hands");
        }
    }

    // Returns the new board after reading from the boardsave.ser file
    public Board loadBoard(Board board) {
        try {
            FileInputStream fileIn = new FileInputStream("BoardSave.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            board = (Board) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Ayo this thing should've loaded");
            return board;
        } catch (IOException i) {
        } catch (ClassNotFoundException c) {
            System.out.println("Board class not found");
        }
        return board;
    }
}