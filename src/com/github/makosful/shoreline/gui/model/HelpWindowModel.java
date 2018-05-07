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

    private final Cache cache;
    private final IBLL bll;
    private ArrayList<Image> images;
    private List<File> fileList;

    public HelpWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

    }

    public ArrayList<Image> createImages()
    {
        for (int i = 0; i < 5; i++)
        {
            fileList.add(new File("./res/instruction" + i + ".png"));
            images.add(new Image(fileList.toString()));
            System.out.println(fileList);
        }
        return images;
    }
}
