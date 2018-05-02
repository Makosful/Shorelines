package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.gui.model.LoginModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author B
 */
public class LoginController implements Initializable
{

    private LoginModel model;

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnForgotPassword;
    @FXML
    private CheckBox checkBoxRememberMe;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new LoginModel();
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {
        model.attemptLogin(txtFieldUsername.getText(), txtFieldPassword.getText());
    }

}
