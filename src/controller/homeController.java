package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.User;

/**
 * Created by dtheriault3 on 9/25/16.
 */
public class homeController {
    @FXML
    private Button logoutButton;

    private User user;

    @FXML
    private void handleLogoutPressed() {
        user = null;
        Platform.exit();
    }
}
