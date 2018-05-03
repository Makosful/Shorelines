package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
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

    public void addTask(List<ExcelRow> list)
    {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> submit = executor.submit(new Task<Boolean>()
        {
            @Override
            protected Boolean call() throws Exception
            {
                try
                {
                    for (ExcelRow row : list)
                    {
                        Map map = new LinkedHashMap();
                        Map planning = new LinkedHashMap();

                        map.put("siteName", row.getSiteName());
                        map.put("assetSerialNumber", row.getAssetSerialNumber());
                        map.put("type", row.getOrderType());
                        map.put("externalWorkOrderId", row.getWorkOrderId());
                        map.put("systemStatus", row.getSystemStatus());
                        map.put("userStatus", row.getUserStatus());
                        map.put("createdOn", row.getCreatedOn());
                        map.put("createdBy", row.getCreatedBy());
                        map.put("name", row.getNameDescription());
                        map.put("priority", row.getPriority());
                        map.put("status", row.getStatus());

                        planning.put("latestFinishDate", row.getLfDate());
                        planning.put("earliestStartDate", row.getEsDate());
                        planning.put("latestStartDate", row.getLsDate());
                        planning.put("estimatedTime", row.getEsTime());

                        map.put("planning", planning);

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
