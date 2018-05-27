/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.controller.HelpWindowController;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Storm
 */
public class HelpWindowModel
{

    private final Cache cache;
    private final IBLL bll;
    private ArrayList<Image> images;

    public HelpWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

    }

    public ArrayList<Image> getImages()
    {
        for (int i = 0; i < 5; i++)
        {
            images.add(new Image("./res/instructions" + i + ".png"));
        }
        return images;
    }
}
