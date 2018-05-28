/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Storm
 */
public class HelpWindowModel
{

    private final List<Image> images = new ArrayList<>();

    public void loadImages()
    {
        for (int i = 1; i < 6; i++)
        {
            File file = new File("./res/instruction" + i + ".png");
            Image img = new Image(file.toURI().toString());
            images.add(img);
        }
    }

    public List<Image> getImages()
    {
        return images;
    }
}
