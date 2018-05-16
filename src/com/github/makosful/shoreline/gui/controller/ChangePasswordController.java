package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.model.Cache;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class ChangePasswordController implements Initializable
{

    private IBLL bll;
    private Cache cache;

    @FXML
    private PasswordField txtOldPass;
    @FXML
    private PasswordField txtNewPass;
    @FXML
    private PasswordField txtNewPassCon;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblError;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        bll = new BLLManager();
        cache = Cache.getInstance();

        setupListeners();
    }

    private void setupListeners()
    {
        txtNewPass.textProperty()
                .addListener((ObservableValue<? extends String> observable,
                              String oldValue, String newValue) ->
                {
                    handleMatch();
                });
        txtNewPassCon.textProperty()
                .addListener((ObservableValue<? extends String> observable,
                              String oldValue, String newValue) ->
                {
                    handleMatch();
                });
    }

    @FXML
    private void handleSave(ActionEvent event)
    {

        try
        {
            final boolean match = bll.passwordMatch(cache.getUser(), txtOldPass.getText());

            if (!match)
            {
                lblError.setText("The old password is wrong");
            }

            final boolean changed = bll.changePassword(cache.getUser(), txtNewPass.getText());

            if (changed)
            {
                lblError.setText("Password changed");
            }
            else
            {
                lblError.setText("Something went wrong");
            }
        }
        catch (BLLException ex)
        {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCancel(ActionEvent event)
    {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    private void handleMatch()
    {
        String style;
        if (txtNewPass.getText().equals(txtNewPassCon.getText()))
        {
            style = "-fx-border-width: 1px;"
                    + "-fx-border-color: red;";

            btnSave.setDisable(false);
            lblError.setText("");
        }
        else
        {
            style = "-fx-border-width: 1px;"
                    + "-fx-border-color: red;";

            btnSave.setDisable(false);
            lblError.setText("The new passwords doesn't match");
        }
        txtNewPass.styleProperty().setValue(style);
        txtNewPassCon.styleProperty().setValue(style);
    }

}
