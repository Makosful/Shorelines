package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.concurrent.Task;

/**
 *
 * @author Axl
 */
public class TaskManager
{

    private final IDAL dal;

    public TaskManager()
    {
        dal = new DALManager();
    }

    public void addTask(List<HashMap> list)
    {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> submit = executor.submit(new Task<Boolean>()
        {
            @Override
            protected Boolean call() throws Exception
            {
                try
                {
                    for (HashMap map : list )
                    {
                        dal.jsonAdd(map);
                    }
                    dal.jsonWrite();
                    return true;
                }
                catch (DALException ex)
                {
                    return false;
                }
            }
        });

        executor.shutdown();
    }
}
