package controller;

import fxapp.MainFXApplication;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by aaronwasserman on 10/6/16.
 */
public class ProfileController implements Initializable {

    @FXML
    private Label profileUsername;

    @FXML
    private Label profileAccountType;

    @FXML
    private TextField profileTitle;

    @FXML
    private TextField profileName;

    @FXML
    private TextField profileEmail;

    @FXML
    private TextField profileHomeAddress;

    private MainFXApplication mainApplication;

    private boolean loaded = false;

    /**
     * sets the mainApplication variable
     * @param application the main application
     */
    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    /**
     * initializes the profileController
     * @param location the url location
     * @param resources the bundle of resources needed to initialize
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    /**
     * is called when the back button is pressed
     * @throws Exception
     */
    @FXML
    public void backButtonPressed() throws Exception{

        mainApplication.gotoHome();

    }

    /**
     * currently how we are populating the text fields of the profile page
     */
    @FXML
    //This is a very temporary solution. I'm not sure how to solve this issue.
    //For now, the list loads when the mouse is moved.
    private void onMouseMoved() {
        if (!loaded) {
            loaded = true;

            profileUsername.setText(mainApplication.db.getGlobalUser().getUsername());
            profileAccountType.setText(mainApplication.db.getGlobalUser().getAccountType().getAccountTypeValue());
            profileTitle.setText(mainApplication.db.getGlobalUser().getTitle());
            profileName.setText(mainApplication.db.getGlobalUser().getName());
            profileEmail.setText(mainApplication.db.getGlobalUser().getEmailAddress());
            profileHomeAddress.setText(mainApplication.db.getGlobalUser().getHomeAddress());

        }
    }

    @FXML
    private void saveChangesPressed() {

        mainApplication.db.updateUser(profileTitle.getText(), profileName.getText(), profileEmail.getText(), profileHomeAddress.getText());

    }


}
