package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.AccountType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public class NewUserRegistrationController implements Initializable {

    @FXML
    private Label infoLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private ComboBox<AccountType> accountTypeComboBox;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    private MainFXApplication mainApplication;

    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountTypeComboBox.setItems(getAccountTypes());
        infoLabel.setVisible(false);
        okButton.setVisible(false);
    }

    private static ObservableList<AccountType> getAccountTypes() {
        ObservableList<AccountType> list = FXCollections.observableArrayList();
        list.addAll(AccountType.values());
        return list;
    }

    @FXML
    public void onRegisterButtonPressed() {
        if (!passwordTextField.getText().equals(confirmPasswordTextField
                .getText())) {
            infoLabel.setText("Your passwords do not match.");
            infoLabel.setVisible(true);
        } else if (nameTextField.getText().equals("") ||
                usernameTextField.getText().equals("") ||
                passwordTextField.getText().equals("") ||
                confirmPasswordTextField.getText().equals("") ||
                accountTypeComboBox.getValue() == null) {
            infoLabel.setText("Please complete all of the fields below.");
            infoLabel.setVisible(true);
        } else {
            mainApplication.db.registerUser(nameTextField.getText(),
                    usernameTextField.getText(), passwordTextField.getText(),
                    accountTypeComboBox.getValue());
            infoLabel.setText("You have successfully registered.");
            infoLabel.setVisible(true);
            okButton.setVisible(true);
            registerButton.setDisable(true);
            cancelButton.setDisable(true);
        }
    }

    @FXML
    private void onCancelButtonPressed() throws Exception {
        mainApplication.gotoWelcome();
    }

    @FXML
    private void onOkButtonPressed() throws Exception {
        mainApplication.gotoWelcome();
    }
}