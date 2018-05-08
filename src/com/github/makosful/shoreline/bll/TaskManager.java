package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import com.github.makosful.shoreline.dal.Json.JsonWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axl
 */
public class TaskManager
{

    private IDAL dalManager;
    private Runnable task;

    public TaskManager()
    {
        dalManager = new DALManager();
    }

    public Runnable makeTask(List<Map> list, String path) throws BLLException
    {
         task = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    dalManager.createFile(list, path);
                }
                catch (DALException ex)
                {
                    
                }
            }
        };
        return task;
    }
}
