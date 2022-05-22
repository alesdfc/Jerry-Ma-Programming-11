import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class SceneController {
    private int total;

    private int bet;

    private int temp;

    @FXML
    private Button stopButton;

    @FXML
    private Button rollButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label rollOutcome;

    @FXML
    private Label totalRoll;

    @FXML
    private Label points;

    @FXML
    private TextField bettedPoints;

    @FXML
    private Button buttonBet;

    // Requires: actionevent from button
    // Modifies: betPoints, totalPoint
    // Effects: takes away points from totalpoint to bet
    @FXML
    void confirmBet(ActionEvent event) {
        Integer totalPoint = Integer.parseInt(points.getText());
        Integer betPoints = Integer.parseInt(bettedPoints.getText());
        if (betPoints <= totalPoint && betPoints >= 0 && bettedPoints.getText() != "") {
            temp = totalPoint - betPoints;
            points.setText(Integer.toString(temp));
            buttonBet.setDisable(true);
            bettedPoints.setDisable(true);
            rollButton.setDisable(false);
            stopButton.setDisable(false);
        } else {
            buttonBet.setDisable(false);
        }
        bet = Integer.parseInt(bettedPoints.getText());
    }

    // Requires: actionevent from button
    // Modifies: none
    // Effects: generates a randomm number from 1-6 to mimic a dice roll
    @FXML
    void reRoll(ActionEvent event) {

        rollButton.setText("Roll Again");
        double d = java.lang.Math.random() * 6 + 1;
        int random = (int) d;
        total += random;
        rollOutcome.setText("Result of Roll: " + Integer.toString(random));
        totalRoll.setText("Total: " + Integer.toString(total));

    }

    // Requires: actionEvent
    // Modifies: none
    // Effects: stops the ability to reroll and repays the user their points
    // depending on the total
    @FXML
    void stopRoll(ActionEvent event) {
        rollButton.setDisable(true);
        stopButton.setDisable(true);
        rollButton.setText("Roll");

        if (total > 21) {
            bet = 0;
        } else if (total == 21) {
            bet *= 2;
            points.setText(Integer.toString(temp += bet));
            bet = 0;
        } else if (total == 20) {
            bet *= 1.5;
            points.setText(Integer.toString(temp += bet));
            bet = 0;
        } else if (total == 19) {
            bet *= 1.25;
            points.setText(Integer.toString(temp += bet));
            bet = 0;
        } else if (total == 18) {
            bet *= 0.5;
            points.setText(Integer.toString(temp += bet));
            bet = 0;
        } else if (total < 18) {
            bet = 0;
        }

        buttonBet.setDisable(false);
        bettedPoints.clear();
        bettedPoints.setDisable(false);
        total = 0;
        rollOutcome.setText("Result of Roll: ");
        totalRoll.setText("Total: ");

    }

    @FXML
    void initialize() {
        assert rollOutcome != null : "fx:id=\"rollOutcome\" was not injected: check your FXML file 'JavaFX.fxml'.";
        assert totalRoll != null : "fx:id=\"totalRoll\" was not injected: check your FXML file 'JavaFX.fxml'.";
        assert points != null : "fx:id=\"points\" was not injected: check your FXML file 'JavaFX.fxml'.";
        assert bettedPoints != null : "fx:id=\"bettedPoints\" was not injected: check your FXML file 'JavaFX.fxml'.";

    }
}
