import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelName;

    @FXML
    private TextField textFieldName;

    @FXML
    private Button buttonPrint;

    @FXML
    void printName(ActionEvent event) {
        labelName.setText(textFieldName.getText());
    }

    @FXML
    void initialize() {
        assert labelName != null : "fx:id=\"labelName\" was not injected: check your FXML file 'JavaFX.fxml'.";
        assert textFieldName != null : "fx:id=\"textFieldName\" was not injected: check your FXML file 'JavaFX.fxml'.";
        assert buttonPrint != null : "fx:id=\"buttonPrint\" was not injected: check your FXML file 'JavaFX.fxml'.";

    }
}
