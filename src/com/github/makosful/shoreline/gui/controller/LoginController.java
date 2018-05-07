package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.gui.model.LoginModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author B
 */
public class LoginController implements Initializable
{

    private Alert errorAlert;

    private LoginModel model;

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private CheckBox checkBoxRememberMe;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnForgotPassword;

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
        setCredentials();
    }

    @FXML
    private void logIn(ActionEvent event)
    {
        try
        {
            if (checkBoxRememberMe.isSelected())
            {
                model.savePassword(txtFieldUsername.getText(), txtFieldPassword.getText());
            }
            else
            {
                model.savePassword("", "");
            }

        }
        catch (BLLException ex)
        {
            errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("Something went wrong, credentials failed to save.");
            errorAlert.show();
        }
    }

    public void setCredentials()
    {
        try
        {
            String[] credentials = model.getPassword();
            txtFieldUsername.setText(credentials[0]);
            txtFieldPassword.setText(credentials[1]);
        }
        catch (BLLException ex)
        {

        }
    }

}
