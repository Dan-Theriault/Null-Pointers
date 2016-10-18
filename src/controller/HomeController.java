package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/** Controller for the home view.
 *
 * Created by dtheriault3 on 9/25/16.
 */
public class HomeController implements Initializable{

    private MainFXApplication mainApplication;

    public void setApp(MainFXApplication application){
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleLogoutPressed() throws Exception {
        mainApplication.gotoWelcome();


    }

    @FXML
    private void onUsersButtonPressed() throws Exception {
        mainApplication.gotoUsersList();
    }

    @FXML
    private void onRequestsButtonPressed() throws Exception {
        mainApplication.gotoRequestsList();
    }

    @FXML
    private void handleProfilePressed() throws Exception {

        mainApplication.gotoProfile();

    }

    @FXML
    private void handleCreateReportPressed() throws Exception {

        mainApplication.goToCreateReport();

    }

    @FXML
    private void viewSourceReports() throws Exception {

        mainApplication.goToViewSourceReports();

    }
}
