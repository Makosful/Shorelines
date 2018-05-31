package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.gui.model.LogWindowModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    //Search
    private List<String> checked;
    private String searchText = "";
    
    @FXML
    private Button btnClose;
    @FXML
    private TableView<ConversionLog> tblFullLog;
    @FXML
    private TableView<ConversionLog> tblErrorLog;
    @FXML
    private TableColumn<ConversionLog, Date> date;
    @FXML
    private TableColumn<ConversionLog, String> email;
    @FXML
    private TableColumn<ConversionLog, String> type;
    @FXML
    private TableColumn<ConversionLog, String> message;
    @FXML
    private TableColumn<ConversionLog, String> name;
    @FXML
    private TableColumn<ConversionLog, Date> errorDate;
    @FXML
    private TableColumn<ConversionLog, String> errorMessage;
    @FXML
    private TableColumn<ConversionLog, String> errorName;
    @FXML
    private TableColumn<ConversionLog, String> errorEmail;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TabPane tabPane;



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
        checked = new ArrayList();
        
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
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        type.setCellValueFactory(new PropertyValueFactory<>("logType"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        name.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        
        //Error log cell factory
        errorDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        errorEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
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
            searchText = newSearchValue;
            model.searchLogs(searchText, checked);
            if(tabPane.getSelectionModel().getSelectedItem().getText().equals("Errors"))
            {
                tblErrorLog.setItems(model.getErrorLog());
            }
        });
    }

    
    @FXML
    private void handleCheckBox(ActionEvent event)
    {
        CheckBox cBox = (CheckBox) event.getSource();
        if(cBox.isSelected())
        {
            checked.add(cBox.getText());
        }
        else
        {
            checked.remove(cBox.getText());
        }
        model.searchLogs(searchText, checked);
        
        if(tabPane.getSelectionModel().getSelectedItem().getText().equals("Errors"))
        {
            tblErrorLog.setItems(model.getErrorLog());
        }
       
    }

}
