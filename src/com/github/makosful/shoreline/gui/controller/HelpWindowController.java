/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.gui.model.HelpWindowModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class HelpWindowController implements Initializable
{

    private HelpWindowModel model;

    private int imgIndex = 0;
    private int imgMax;
    private String tutString1, tutString2, tutString3, tutString4, tutString5;

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
    @FXML
    private Label lblimgDisplay;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new HelpWindowModel();

        setupStrings();
        SetImages();

        displayImage();
        updateTutString();

    }

    private void SetImages()
    {
        model.loadImages();
        imgMax = model.getImages().size();
    }

    @FXML
    private void handlePreviousPicture(ActionEvent event)
    {
        if (model.getImages().size() > 0)
        {
            lblimgDisplay.setText(imgIndex + 1 + "/" + imgMax);
            imgIndex = (imgIndex - 1 + imgMax) % imgMax;
            displayImage();
            updateTutString();
        }
    }

    @FXML
    private void handleNextPicture(ActionEvent event)
    {
        if (!model.getImages().isEmpty())
        {
            lblimgDisplay.setText(imgIndex + 1 + "/" + imgMax);
            imgIndex = (imgIndex + 1) % model.getImages().size();
            displayImage();
            updateTutString();
        }
    }

    private void displayImage()
    {
        if (model.getImages() != null && !model.getImages().isEmpty())
        {
            imageView.setImage(model.getImages().get(imgIndex));
            lblimgDisplay.setText(imgIndex + 1 + "/" + imgMax);
            imageView.fitWidthProperty().bind(imageViewPane.widthProperty());
            imageView.fitHeightProperty().bind(imageViewPane.heightProperty());
        }
    }

    private void updateTutString()
    {
        switch (imgIndex)
        {
            case 0:
                lblInstructions.setText(tutString1);
                break;
            case 1:
                lblInstructions.setText(tutString2);
                break;
            case 2:
                lblInstructions.setText(tutString3);
                break;
            case 3:
                lblInstructions.setText(tutString4);
                break;
            case 4:
                lblInstructions.setText(tutString5);
                break;
            default:
                lblInstructions.setText("The documentation / instructions will be listed here.");
                break;
        }
    }

    private void setupStrings()
    {
        tutString1 = "Start by clicking the 'FILE' tab in the menu bar, from there select the option 'SELECT FILE' \nTo select the importable data you wish to convert to JSON.";

        tutString2 = "Locate the file you wish to convert by using the built in file chooser. \nIf you are unable to find your specific file, make sure it is the one highligted by the file format \n(swapped at the bottom right corner).Simply mark your file and hit 'OPEN'";

        tutString3 = "Once the file has succesfully been loaded into the program. The headers will appear as items in the list, \nassociated with a checkbox. Check off the items in the list that you wish to include in your conversion. \nWhen you check them, they will appear in the 'SORTING' list, and they will also be set in the \n'OUTPUT EXAMPLE' list as well. Be sure to sort this accordingly, so your output has a correct binding, \nbetween the key and the value. E.g. 'PRIORITY' should NOT have 'SITE NAME' under it.";

        tutString4 = "Once you've sorted the 'SORTING' list and the 'OUTPUT EXAMPLE' looks correct, \nyou are ready to add this as a task to convert. By clicking the button, referred to with an arrow.";

        tutString5 = "After you've added your current loadout to the task list you can click the 'OPEN TASK WINDOW', \nin order to reveal the Task Handling window where you can select the tasks you wish to convert into .json. \nOnce the task has been selected and the button 'CONVERT SELECTED TASKS' has been clicked, \nthe task(s) will appear in the 'RUNNING TASKS' list and dissapear when they've completed. \nThe file should now be at the same destination as the originally chosen file, but as .json";
    }

}
