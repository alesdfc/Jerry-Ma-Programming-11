import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

public class SceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField getName;

    @FXML
    private TextField getPhone;

    @FXML
    private TextField getBirthday;

    @FXML
    private TextField getHobby;

    @FXML
    private Button submitButton;

    @FXML
    private ListView<Friend> friendList;

    @FXML
    private Button removeButton;

    @FXML
    private Text nameDisplay;

    @FXML
    private Text phoneDisplay;

    @FXML
    private Text birthdayDisplay;

    @FXML
    private Text hobbyDisplay;

    private SelectionModel<Friend> deletion;

    // Requires: actionevent from button
    // Modifies: textfields
    // Effects: clears textfields and creates friend object in the list.
    @FXML
    void createFriend(ActionEvent event) {
        String name = getName.getText();
        String phone = getPhone.getText();
        String birthday = getBirthday.getText();
        String hobby = getHobby.getText();
        Friend temp = new Friend(name, phone, birthday, hobby);
        friendList.getItems().add(temp);
        getName.clear();
        getPhone.clear();
        getBirthday.clear();
        getHobby.clear();
    }

    // Requires: actionevent from button
    // Modifies: doesn't even work yet
    // Effects:doesn't even work yet
    @FXML
    void deleteFriend(ActionEvent event) {
        friendList.getItems().remove(friendList.getSelectionModel().getSelectedIndex());
        nameDisplay.setText("");
        phoneDisplay.setText("");
        birthdayDisplay.setText("");
        hobbyDisplay.setText("");
    }

    // Requires: actionevent from button
    // Modifies: display text
    // Effects: shows information about the selected friend
    @FXML
    void displayFriend(MouseEvent event) {
        Friend temp;
        temp = friendList.getSelectionModel().getSelectedItem();
        nameDisplay.setText(temp.getName());
        phoneDisplay.setText(temp.getPhone());
        birthdayDisplay.setText(temp.getBirthday());
        hobbyDisplay.setText(temp.getHobby());
    }

    @FXML
    void initialize() {
        assert getName != null : "fx:id=\"name\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert getPhone != null : "fx:id=\"phone\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert getBirthday != null : "fx:id=\"birthday\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert getHobby != null : "fx:id=\"hobby\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert submitButton != null
                : "fx:id=\"submitButton\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert friendList != null : "fx:id=\"friendList\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert removeButton != null
                : "fx:id=\"removeButton\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert nameDisplay != null : "fx:id=\"nameDisplay\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert phoneDisplay != null
                : "fx:id=\"phoneDisplay\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert birthdayDisplay != null
                : "fx:id=\"birthdayDisplay\" was not injected: check your FXML file 'friendsbook.fxml'.";
        assert hobbyDisplay != null
                : "fx:id=\"hobbyDisplay\" was not injected: check your FXML file 'friendsbook.fxml'.";

    }
}
