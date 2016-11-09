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

    /**
     * loads initial scene
     * @param primaryStage primary stage to be loaded
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Safe Water App");
        stage.setMinWidth(390);
        stage.setMinHeight(500);
        gotoWelcome();
        primaryStage.show();
    }

    /**
     * main function for the application
     * @param args args array
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * switches scene to welcome
     * @throws Exception
     */
    public void gotoWelcome() throws Exception {
        WelcomeController welcome = (WelcomeController) replaceSceneContent("../view/welcome.fxml");
        welcome.setApp(this);
    }

    /**
     * switches scene to login
     * @throws Exception
     */
    public void gotoLogin() throws Exception {
        LoginController login = (LoginController) replaceSceneContent("../view/loginPage.fxml");
        login.setApp(this);
    }

    /**
     * switches scene to home
     * @throws Exception
     */
    public void gotoHome() throws Exception {
        HomeController home = (HomeController) replaceSceneContent("../view/home.fxml");
        home.setApp(this);
    }

    /**
     * switches scene to new user registration
     * @throws Exception
     */
    public void gotoNewUserRegistration() throws Exception {
        NewUserRegistrationController newUserRegistration =
                (NewUserRegistrationController) replaceSceneContent("../view/newUserRegistration.fxml");
        newUserRegistration.setApp(this);
    }

    /**
     * switches scene to users list
     * @throws Exception
     */
    public void gotoUsersList() throws Exception {
        UserListController userList = (UserListController) replaceSceneContent("../view/usersList.fxml");
        userList.setApp(this);
    }

    /**
     * switches scene to requests list
     * @throws Exception
     */
    public void gotoRequestsList() throws Exception {
        UserRequestListController requestListController = (UserRequestListController)
                replaceSceneContent("../view/userReqestsList.fxml");
        requestListController.setApp(this);
    }

    /**
     * switches scene to profile
     * @throws Exception
     */
    public void gotoProfile() throws Exception {

        ProfileController profileController = (ProfileController) replaceSceneContent("../view/profile.fxml");
        profileController.setApp(this);

    }

    /**
     * switches scene to create report
     * @throws Exception
     */
    public void goToCreateReport() throws Exception {

        NewWaterReportController newWaterController = (NewWaterReportController) replaceSceneContent("../view/newWaterReport.fxml");
        newWaterController.setApp(this);

    }

    /**
     * switches scene to view source reports
     * @throws Exception
     */
    public void goToViewSourceReports() throws Exception {

        ViewSourceReportsController newSourceController = (ViewSourceReportsController) replaceSceneContent("../view/viewSourceReports.fxml");
        newSourceController.setApp(this);

    }

    /**
     * switches scene to view map of source reports
     * @throws Exception
     */
    public void goToMapView() throws Exception {
        MapViewController newMapController = (MapViewController) replaceSceneContent("../view/mapView.fxml");
        newMapController.setApp(this);
    }

    public void goToNewPurityReport() throws Exception {

        NewPurityReportController newPurityReportController = (NewPurityReportController) replaceSceneContent("../view/newPurityReport.fxml");
        newPurityReportController.setApp(this);

    }

    public void goToViewPurityReports() throws Exception {

        ViewPurityReportsController controller = (ViewPurityReportsController) replaceSceneContent("../view/viewPurityReports.fxml");
        controller.setApp(this);

    }

    public void goToPurityGraph() throws Exception {

        HistoricalReportEntryController controller = (HistoricalReportEntryController) replaceSceneContent("../view/enterHistoricalReport.fxml");
        controller.setApp(this);

    }

    /**
     * function to help switch between scenes
     * @param fxml the fxml of the new scene
     * @return the controller of the new scene
     * @throws Exception
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        AnchorPane page;
        try (InputStream in = getClass().getResourceAsStream(fxml)) {
            page = loader.load(in);
        }
        Scene scene = new Scene(page, 600, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
