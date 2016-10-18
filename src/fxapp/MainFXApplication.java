package fxapp;

import controller.*;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import model.Database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.InputStream;

/** This is the runner for the JavaFX application.
 *
 * Created by nickhutchinson on 9/21/16.
 */
public class MainFXApplication extends Application {

    public Database db = new Database();


    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Safe Water App");
        stage.setMinWidth(390);
        stage.setMinHeight(500);
        gotoWelcome();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void gotoWelcome() throws Exception {
        WelcomeController welcome = (WelcomeController) replaceSceneContent("../view/welcome.fxml");
        welcome.setApp(this);
    }

    public void gotoLogin() throws Exception {
        LoginController login = (LoginController) replaceSceneContent("../view/loginPage.fxml");
        login.setApp(this);
    }

    public void gotoHome() throws Exception {
        HomeController home = (HomeController) replaceSceneContent("../view/home.fxml");
        home.setApp(this);
    }

    public void gotoNewUserRegistration() throws Exception {
        NewUserRegistrationController newUserRegistration =
                (NewUserRegistrationController) replaceSceneContent("../view/newUserRegistration.fxml");
        newUserRegistration.setApp(this);
    }

    public void gotoUsersList() throws Exception {
        UserListController userList = (UserListController) replaceSceneContent("../view/usersList.fxml");
        userList.setApp(this);
    }

    public void gotoRequestsList() throws Exception {
        UserRequestListController requestListController = (UserRequestListController)
                replaceSceneContent("../view/userReqestsList.fxml");
        requestListController.setApp(this);
    }

    public void gotoProfile() throws Exception {

        ProfileController profileController = (ProfileController) replaceSceneContent("../view/profile.fxml");
        profileController.setApp(this);

    }

    public void goToCreateReport() throws Exception {

        NewWaterReportController newWaterController = (NewWaterReportController) replaceSceneContent("../view/newWaterReport.fxml");
        newWaterController.setApp(this);

    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        AnchorPane page;
        try (InputStream in = getClass().getResourceAsStream(fxml)) {
            page = loader.load(in);
        }
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
