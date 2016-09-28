package controller;

import fxapp.MainFXApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dtheriault3 on 9/25/16.
 */
public class HomeController implements Initializable{
    @FXML
    private Button logoutButton;

    private User user;

    private MainFXApplication mainApplication;

    public void setApp(MainFXApplication application){
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleLogoutPressed() throws Exception {
        user = null;
        mainApplication.gotoWelcome();
    }
}
