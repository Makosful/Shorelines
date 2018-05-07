/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.github.makosful.shoreline.gui.model.HelpWindowModel;

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class HelpWindowController implements Initializable
{

    private HelpWindowModel model;
    private final List<Image> images = new ArrayList<>();
    private int imageIdx = 0;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        SetImages();

        File file = new File("./res/logo.png");
        Stage stage = new Stage();

        Image icon = new Image(file.toURI().toString());
        stage.getIcons().add(icon);

    }

    private void SetImages()
    {
        model.getImages();
    }

    @FXML
    private void handlePreviousPicture(ActionEvent event)
    {
        if (images.size() > 0)
        {
            imageIdx = (imageIdx - 1 + images.size()) % images.size();
            displayImage();
        }
    }

    @FXML
    private void handleNextPicture(ActionEvent event)
    {
        if (!images.isEmpty())
        {
            imageIdx = (imageIdx + 1) % images.size();
            displayImage();
        }
    }

    private void displayImage()
    {
        if (images != null)
        {
            imageView.setImage(images.get(imageIdx));
        }
    }

}
