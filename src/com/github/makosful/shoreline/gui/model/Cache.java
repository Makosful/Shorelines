package com.github.makosful.shoreline.gui.model;

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

    // Singleton constructor
    private Cache()
    {
    }
}
