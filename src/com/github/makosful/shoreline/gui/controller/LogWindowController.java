package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.gui.model.LogWindowModel;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private TableView<ConversionLog> tblFullLog;
    @FXML
    private TableView<ConversionLog> tblErrorLog;
    @FXML
    private TableColumn<ConversionLog, Date> date;
    @FXML
    private TableColumn<ConversionLog, Integer> userId;
    @FXML
    private TableColumn<ConversionLog, String> type;
    @FXML
    private TableColumn<ConversionLog, String> message;
    @FXML
    private TableColumn<ConversionLog, String> name;
    @FXML
    private TableColumn<ConversionLog, Date> errorDate;
    @FXML
    private TableColumn<ConversionLog, Integer> errorUserId;
    @FXML
    private TableColumn<ConversionLog, String> errorMessage;
    @FXML
    private TableColumn<ConversionLog, String> errorName;
    @FXML
    private TextField txtFieldSearch;


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

        createTableViewFactory();
        
        addSearchListener();
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

    private void createTableViewFactory()
    {
        //Get all logs and add them to the tableView
        tblFullLog.setItems(model.getFullLog());
        tblErrorLog.setItems(model.getErrorLog());
        
        //Full log cell factory
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        userId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        type.setCellValueFactory(new PropertyValueFactory<>("logType"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        name.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        
        //Error log cell factory
        errorDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        errorUserId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        errorMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        errorName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
    }

    /**
     * Add a changelistener to the textfields textproperty, so when the text changes 
     * in the textfield a new seach is made
     */
    private void addSearchListener()
    {
        txtFieldSearch.textProperty().addListener((observable, oldSearchValue, newSearchValue) -> {
            model.searchLogs(newSearchValue);
        });
    }

}
