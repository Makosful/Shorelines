package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class ForgotPasswordController implements Initializable
{

    private IBLL bll;

    @FXML
    private Label lblEmail;
    @FXML
    private Label lblError;
    @FXML
    private TextField txtEmail;
    @FXML
    private ButtonBar btnSend;

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
    }

    @FXML
    private void handleSend(ActionEvent event)
    {
        try
        {
            if (!bll.getUserByMail(txtEmail.getText()))
            {
                lblError.setText("The mail wasn't found");
            }
            else
            {
                lblError.setText("Success");
            }
        }
        catch (BLLException ex)
        {
            lblError.setText(ex.getLocalizedMessage());
            System.out.println(ex.getStackTrace());
        }
    }

}
