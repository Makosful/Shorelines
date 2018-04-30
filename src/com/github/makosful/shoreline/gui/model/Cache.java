package com.github.makosful.shoreline.gui.model;

import com.github.makosful.stage.utils.StageManager;

/**
 * This Model class will serve as a data cache, storing data that should be
 * shared between Model classes.
 * The Singleton pattern is used to make all Model classes retireve a single
 * instance of all the data.
 * This clas should only have methods related to handling the data, eg. Getters,
 * Setters, List Adders, List Removers, Variable Clears.
 *
 * @author Axl
 */
public class Cache
{

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private static Cache instance = null;

    public static Cache getInstance()
    {
        if (instance == null)
        {
            instance = new Cache();
        }
        return instance;
    }
    //</editor-fold>

    // Fields
    private StageManager stageManager;

    // Singleton constructor
    private Cache()
    {
    }

    //<editor-fold defaultstate="collapsed" desc="Stage Manager">
    /**
     * Gets the current Stage Manager
     *
     * @return Returns a reference to the current StageManager
     */
    public StageManager getStageManager()
    {
        return this.stageManager;
    }

    /**
     * Sets the Stage Manager. This should typically only be done when the app
     * starts
     *
     * @param sm The StageManage to use
     */
    public void setStageManager(StageManager sm)
    {
        this.stageManager = sm;
    }

    /**
     * Clears the StageManager
     */
    public void clearStageManager()
    {
        this.stageManager = null;
    }

    /**
     * Changes the Scene to the one with the same registered ID.
     * The Scene IDs can be found in the Main class
     *
     * @see
     * com.github.makosful.shoreline.Main#registerScenes(com.github.makosful.stage.utils.StageManager)
     *
     * @param sceneId The ID of the stage
     */
    public void changeScene(int sceneId)
    {
        stageManager.setActiveScene(sceneId);
    }

    public void m()
    {
    }
    //</editor-fold>
}
