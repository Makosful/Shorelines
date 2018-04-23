package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.gui.model.MainWindowModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class.
 * This class connects the data from the MainWindowModel to the view, and
 * handles the input
 *
 * @see MainWindowModel The Class handling the data for this MVC
 *
 * @author Axl
 */
public class MainWindowController implements Initializable
{

    private MainWindowModel model;

    /**
     * Initializes the controller class.
     *
     * @param url FXML Specific. Unused.
     * @param rb  FXML Specific. Unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new MainWindowModel();
    }

}
