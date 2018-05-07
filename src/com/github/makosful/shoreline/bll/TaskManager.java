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

    public TaskManager()
    {
        
    }

    public Task makeTask(List<HashMap> list)
    {
        Task task = new Task()
        {
            @Override
            protected Object call() throws Exception
            {
                for (HashMap map : list)
                {
                    
                }
                return null;
            }
        };
        return task;
    }
}
