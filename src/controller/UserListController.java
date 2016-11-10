package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class UserListController implements Initializable {

    @FXML
    private ListView<String> userListView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label accountTypeLabel;

    @FXML
    private Label emailAddressLabel;

    @FXML
    private Label homeAddressLabel;

    private MainFXApplication mainApplication;

    private ArrayList<User> users;

    private int currentIndex;

    private boolean loaded = false;

    /**
     * sets the main application for the controller
     * @param application the application to set
     */
    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    /**
     * initializes the controller
     * @param location the url location
     * @param resources the bundle of resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * handler for list view click
     */
    @FXML
    private void onListViewClicked() {
        int newIndex = userListView.getSelectionModel().getSelectedIndex();
        if (newIndex != -1 || newIndex != currentIndex) {
            currentIndex = newIndex;
            User currentUser = users.get(currentIndex);

            if (currentUser.getTitle() != null) {
                titleLabel.setText(currentUser.getTitle());
            }
            if (currentUser.getName() != null) {
                nameLabel.setText(currentUser.getName());
            }
            if (currentUser.getUsername() != null) {
                usernameLabel.setText(currentUser.getUsername());
            }
            if (currentUser.getAccountType() != null) {
                accountTypeLabel.setText(currentUser.getAccountType().getAccountTypeValue());
            }
            if (currentUser.getEmailAddress() != null) {
                emailAddressLabel.setText(currentUser.getEmailAddress());
            }
            if (currentUser.getHomeAddress() != null) {
                homeAddressLabel.setText(currentUser.getHomeAddress());
            }
        }
    }

    /**
     * handler for mouse moved
     */
    @FXML
    private void onMouseMoved() {
        if (!loaded) {
            loaded = true;
            //Set the list of users' names for the ListView
            ArrayList<String> userNames = new ArrayList<>();
            users = mainApplication.db.getUsers();
            userNames.addAll(users.stream().map(User::getName).collect(Collectors.toList()));
            ObservableList<String> items = FXCollections.observableArrayList(userNames);
            userListView.setItems(items);
        }
    }

    /**
     * handler for back button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onBackButtonPressed() throws Exception {
        mainApplication.gotoHome();
    }

}