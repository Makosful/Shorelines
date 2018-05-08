package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

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
                    dalManager.jsonSetOutPut(path);
                    dalManager.jsonAdd(list);
                    dalManager.jsonWrite();
                }
                catch (DALException ex)
                {
                    
                }
            }
        };
        return task;
    }
}
