package com.github.makosful.shoreline.gui.model;

/**
 * This Model handles the data specific to the MainWindow MVC
 *
 * @author Axl
 */
public class MainWindowModel
{

    // Reference to the shared data cache
    private final Cache model;

    public MainWindowModel()
    {
        model = Cache.getInstance();
    }
}
