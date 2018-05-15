/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.github.makosful.shoreline.gui.model.HelpWindowModel;

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class HelpWindowController implements Initializable
{

    private HelpWindowModel model;

    private int imgIndex = 0;
    private String tutString;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnPrevPic;
    @FXML
    private Label lblInstructions;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnNextPic;
    @FXML
    private AnchorPane imageViewPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new HelpWindowModel();

        SetImages();

        displayImage();
    }

    private void SetImages()
    {
        model.loadImages();
    }

    @FXML
    private void handlePreviousPicture(ActionEvent event)
    {
        if (model.getImages().size() > 0)
        {
            imgIndex = (imgIndex - 1 + model.getImages().size()) % model.getImages().size();
            displayImage();
        }
    }

    @FXML
    private void handleNextPicture(ActionEvent event)
    {
        if (!model.getImages().isEmpty())
        {
            imgIndex = (imgIndex + 1) % model.getImages().size();
            displayImage();
        }
    }

    private void displayImage()
    {
        if (model.getImages() != null && !model.getImages().isEmpty())
        {
            imageView.setImage(model.getImages().get(imgIndex));
            imageView.fitWidthProperty().bind(imageViewPane.widthProperty());
            imageView.fitHeightProperty().bind(imageViewPane.heightProperty());
        }
    }

}
