import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

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

    @FXML
    private Button exportButton;

    @FXML
    private Button importButton;

    FileChooser file_chooser = new FileChooser();

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

    // Requires: actionevent from button
    // Modifies: none
    // Effects: creates a .txt file with each friend's information
    @FXML
    void exportFriends(ActionEvent event) throws IOException {
        Friend temp;
        ObservableList real = friendList.getItems();
        Stage stage = (Stage) exportButton.getScene().getWindow();
        file_chooser.setInitialFileName("My Friends.txt");
        File file = file_chooser.showSaveDialog(stage);
        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
        for (int i = 0; i < real.size(); i++) {
            temp = (Friend) real.get(i);
            bw.write(temp.getName() + "\n");
            bw.write(temp.getPhone() + "\n");
            bw.write(temp.getBirthday() + "\n");
            bw.write(temp.getHobby() + "\n");
        }
        bw.close();
    }

    // Requires: actionevent from button
    // Modifies: none
    // Effects: takes in a .txt file and creates friend objects
    @FXML
    void importFriends(ActionEvent event) throws IOException {
        Stage stage = (Stage) exportButton.getScene().getWindow();
        File file = file_chooser.showOpenDialog(stage);
        ArrayList<String> text = new ArrayList<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String name;
        String birthday;
        String hobby;
        String phone;
        Friend temp;

        while ((line = br.readLine()) != null) {
            text.add(line);
        }
        br.close();
        for (int i = 0; i < text.size(); i++) {
            if (text.get(2).charAt(0) == '\n') {
                break;
            } else {
                name = text.get(i);
                phone = text.get(i + 1);
                birthday = text.get(i + 2);
                hobby = text.get(i + 3);

                temp = new Friend(name, phone, birthday, hobby);
                friendList.getItems().add(temp);
                i+=3;
            }
        }
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
