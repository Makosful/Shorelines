/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.gui.model.HelpWindowModel;
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

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class HelpWindowController implements Initializable
{

    private HelpWindowModel model;
    private final List<Image> images = new ArrayList<>();
    private final List<String> imageList = new ArrayList<>();
    private int imageIdx = 0;

    @FXML
    private Button btnPrevImg;
    @FXML
    private Button btnNextImg;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblCurrentImage;
    @FXML
    private Label lblInstructions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        Image img1 = new Image("./res/instruction1.png");
//        Image img2 = new Image("./res/instruction2.png");
//        Image img3 = new Image("./res/instruction3.png");
//        Image img4 = new Image("./res/instruction4.png");
//        Image img5 = new Image("./res/instruction5.png");
//
//        imageList.add(img1.toString());
//        imageList.add(img2.toString());
//        imageList.add(img3.toString());
//        imageList.add(img4.toString());
//        imageList.add(img5.toString());

        model = new HelpWindowModel();

//        getImages();
//
//        displayImage();
    }

    @FXML
    private void handlePreviousImage(ActionEvent event)
    {
        if (images.size() > 0)
        {
            imageIdx = (imageIdx - 1 + images.size()) % images.size();
            displayImage();
        }
    }

    @FXML
    private void handleNextImage(ActionEvent event)
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

    private void getImages()
    {
        for (int i = 0; i < 5; i++)
        {
            images.add(new Image(imageList.toString()));
            System.out.println(imageList);
        }
//        model.createImages();
    }
}
