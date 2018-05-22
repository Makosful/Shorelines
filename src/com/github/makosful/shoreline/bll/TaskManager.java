package com.github.makosful.shoreline.bll;

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

    private IDAL dalManager;
    private Task task;

    public TaskManager()
    {

    }

    public Task makeTask(Map<String, String> map, String path) throws BLLException
    {
        task = new Task()
        {

            @Override
            public String toString()
            {
                return path;
            }

            @Override
            protected Object call()
            {
                try
                {
                    List<Map> list = dalManager.fileGetValues(map);
                    dalManager.createFile(list, path);
                }
                catch (DALException ex)
                {
                    ex.printStackTrace();
                }
                return task;
            }
        };
        return task;
    }

    public void setDalManager(IDAL dal)
    {
        dalManager = dal;
    }

}
