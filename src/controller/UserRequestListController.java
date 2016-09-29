package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public class UserRequestListController implements Initializable {

    @FXML
    private ListView<String> userListView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label accountTypeLabel;

    @FXML
    private Button rejectButton;

    @FXML
    private Button acceptButton;

    private MainFXApplication mainApplication;

    private ArrayList<User> users;

    private int currentIndex;

    private User currentUser;

    private boolean loaded = false;

    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void onListViewClicked() {
        int newIndex = userListView.getSelectionModel().getSelectedIndex();
        if (newIndex != -1 || newIndex != currentIndex) {
            selectListItem(newIndex);
        }
    }

    @FXML
    //This is a very temporary solution. I'm not sure how to solve this issue.
    //For now, the list loads when the mouse is moved.
    private void onMouseMoved() {
        if (!loaded) {
            loaded = true;
            loadList();
        }
    }

    @FXML
    private void onBackButtonPressed() throws Exception {
        mainApplication.gotoHome();
    }

    @FXML
    private void onRejectButtonPressed() throws Exception {
        mainApplication.db.deleteRequest(currentUser.getUsername());
        selectListItem(-1);
        loadList();
    }

    @FXML
    private void onAcceptButtonPressed() throws Exception {

    }

    private void loadList() {
        //Set the list of users' names for the ListView
        ArrayList<String> userNames = new ArrayList<>();
        users = mainApplication.db.getRequests();
        userNames.addAll(users.stream().map(User::getName).collect(Collectors.toList()));
        ObservableList<String> items = FXCollections.observableArrayList(userNames);
        userListView.setItems(items);
    }

    private void selectListItem(int index) {
        if (index == -1) {
            currentIndex = index;
            nameLabel.setText("name");
            usernameLabel.setText("username");
            accountTypeLabel.setText("accouttype");

            rejectButton.setDisable(true);
            acceptButton.setDisable(true);
        } else {
            currentIndex = index;
            currentUser = users.get(currentIndex);
            if (currentUser.getName() != null) {
                nameLabel.setText(currentUser.getName());
            }
            if (currentUser.getUsername() != null) {
                usernameLabel.setText(currentUser.getUsername());
            }
            if (currentUser.getAccountType() != null) {
                accountTypeLabel.setText(currentUser.getAccountType().getAccountTypeValue());
            }
            rejectButton.setDisable(false);
            acceptButton.setDisable(false);
        }
    }

}