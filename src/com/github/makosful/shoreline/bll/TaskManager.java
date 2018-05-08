package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.concurrent.Task;

/**
 *
 * @author Axl
 */
public class TaskManager
{

    private IDAL dalManager;
    Task task;

    public TaskManager()
    {
        dalManager = new DALManager();
    }

    public Task makeTask(List<Map> list)
    {
         task = new Task()
        {
            @Override
            protected Object call() throws Exception
            {

                dalManager.jsonAdd(list);
                dalManager.jsonWrite();
                return task;
            }
        };
        return null;
    }
}
