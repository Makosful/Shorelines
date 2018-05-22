package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.UserNew;
import com.github.makosful.shoreline.gui.model.SignupModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class SignUpWindowController implements Initializable
{

    // Objects
    private SignupModel model;

    //<editor-fold defaultstate="collapsed" desc="FXML Variables">
    @FXML
    private VBox boxNameFull;
    @FXML
    private VBox boxNameFirst;
    @FXML
    private Label lblNameFirst;
    @FXML
    private TextField txtNameFirst;
    @FXML
    private VBox boxNameLast;
    @FXML
    private Label lblNameLast;
    @FXML
    private TextField txtNameLast;
    @FXML
    private VBox boxNameUser;
    @FXML
    private Label lblNameUser;
    @FXML
    private TextField txtNameUser;
    @FXML
    private VBox boxEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private VBox boxPasswords;
    @FXML
    private VBox boxPass;
    @FXML
    private Label lblPass;
    @FXML
    private PasswordField txtPass;
    @FXML
    private VBox boxPassConf;
    @FXML
    private Label lblPassConf;
    @FXML
    private PasswordField txtPassConf;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
    //</editor-fold>

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new SignupModel();

        setupListeners();
    }

    //<editor-fold defaultstate="collapsed" desc="FXML Methods">
    @FXML
    private void handleCreate(ActionEvent event)
    {
        final UserNew user = new UserNew(txtNameFirst.getText(),
                                         txtNameLast.getText(),
                                         txtNameUser.getText(),
                                         txtEmail.getText(),
                                         txtPass.getText());

        model.createUser(user);
    }

    @FXML
    private void handleCancel(ActionEvent event)
    {
        model.cancel();
    }
    //</editor-fold>

    private void setupListeners()
    {
        txtPass.textProperty()
                .addListener((ObservableValue<? extends String> observable,
                              String oldValue, String newValue) ->
                {
                    applyStyle();
                });

        txtPassConf.textProperty()
                .addListener((ObservableValue<? extends String> observable,
                              String oldValue, String newValue) ->
                {
                    applyStyle();
                });
    }

    private void applyStyle()
    {
        String style;
        if (!txtPass.getText().equals(txtPassConf.getText()))
        {
            style = "-fx-border-width: 1px;"
                    + "-fx-border-color: red;";
        }
        else
        {
            style = "-fx-border-width: 1px;"
                    + "-fx-border-color: green;";
        }
        txtPass.styleProperty().setValue(style);
        txtPassConf.styleProperty().setValue(style);
    }
}
