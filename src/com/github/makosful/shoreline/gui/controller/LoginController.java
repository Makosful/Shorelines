/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.gui.model.LogInModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author B
 */
public class LoginController implements Initializable
{
    private LogInModel liModel;
    private Alert errorAlert;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        liModel = new LogInModel();
        setCredentials();
    }

    @FXML
    private void logIn(ActionEvent event)
    {
        try
        {
            if (checkBoxRememberMe.isSelected())
            {
                liModel.savePassword(txtFieldUsername.getText(), txtFieldPassword.getText());
            }
            else
            {
                liModel.savePassword("", "");
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
            String[] credentials = liModel.getPassword();
            txtFieldUsername.setText(credentials[0]);
            txtFieldPassword.setText(credentials[1]);
        }
        catch (BLLException ex)
        {
            
        }
    }

}
