package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.gui.model.LogWindowModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class LogWindowController implements Initializable
{

    private LogWindowModel model;

    @FXML
    private Button btnClose;
    @FXML
    private ListView<ConversionLog> lstFullLog;
    @FXML
    private ListView<ConversionLog> lstErrorLog;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new LogWindowModel();

        lstFullLog.setItems(model.getFullLog());
        lstErrorLog.setItems(model.getErrorLog());
    }

    /**
     * Closes the window
     *
     * @param event
     */
    @FXML
    private void handleClose(ActionEvent event)
    {
        // Get button
        // Get Scene
        // Get Window
        // Cast to Stage
        // Call close on Stage
        ((Stage) btnClose.getScene().getWindow()).close();
    }

}
