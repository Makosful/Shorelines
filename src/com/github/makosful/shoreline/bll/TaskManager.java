package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.TaskString;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import java.util.List;
import java.util.Map;
import javafx.concurrent.Task;

/**
 *
 * @author Axl
 */
public class TaskManager
{

    private final IDAL dalManager;
    private Task task;

    public TaskManager()
    {
        dalManager = new DALManager();
    }

    public Task makeTask(List<Map> list, String path) throws BLLException
    {
        task = new TaskString()
        {
            @Override
            public String toString()
            {
                return path;
            }
            
            @Override
            protected Object call() throws Exception
            {
                try
                {
                    dalManager.createFile(list, path);
                    return task;
                }
                catch (DALException ex)
                {
                    
                }
                return null;
            }
        };
        return task;
    }
}
