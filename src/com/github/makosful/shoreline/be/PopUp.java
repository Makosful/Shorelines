/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Storm
 */
public class PopUp
{

    private static String txtFromInput;

    public static void display()
    {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        Label lbl1 = new Label("Enter your custom text below or leave empty");
        TextField txtInput = new TextField();

        popupwindow.setOnCloseRequest((event) ->
        {
            txtFromInput = "UNDEFINED";
        });

        txtInput.setOnAction((ActionEvent e) ->
        {
            if (txtInput.getText().isEmpty() || txtInput.getText() == null)
            {
                txtFromInput = "UNDEFINED";
            }
            else
            {
                txtFromInput = txtInput.getText();
            }
            popupwindow.close();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(lbl1, txtInput);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 250, 100);

        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    public static String getInputText()
    {
        return txtFromInput;
    }
}
