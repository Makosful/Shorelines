package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import java.util.LinkedHashMap;
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

    public Task makeTask(Map<String, String> map, String filePath, String fileName) throws BLLException
    {
        String fPath = filePath;
        String fName = fileName;
        Map fMap = new LinkedHashMap<>(map);
        task = new Task()
        {

            @Override
            public String toString()
            {
                return fileName;
            }

            @Override
            protected Object call()
            {
                try
                {
                    dalManager.setReader(fPath);
                    dalManager.fileGetHeader(fPath);
                    List<Map> list = dalManager.fileGetValues(fMap, fPath);
                    dalManager.createFile(list, fName);
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
