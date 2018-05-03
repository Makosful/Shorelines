package com.github.makosful.shoreline.dal.Json;

import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Axl
 */
public class JsonReader implements IReader
{

    private final Gson gson;

    private JsonElement element;

    public JsonReader()
    {
        gson = new Gson();
    }

    @Override
    public boolean loadFile(String file)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            // Ensures the JSON can be read even if it's on multiple lines
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            // Converts the JSON to a Map
            GsonBuilder builder = gson.newBuilder();
            Object obj = builder.create().fromJson(sb.toString(), Object.class);
            element = gson.toJsonTree(obj);

            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    @Override
    public List<String> getHeaders()
    {
        if (element == null)
        {
            String s = "Call loadJson on this object before using this method";
            throw new IllegalStateException(s);
        }
        else if (!element.isJsonArray())
        {
            List<String> list = new ArrayList();
            for (String string : element.getAsJsonObject().keySet())
            {
                list.add(string);
            }
            return list;
        }
        else if (element.isJsonArray())
        {
            List<String> list = new ArrayList();
            JsonArray jArray = element.getAsJsonArray();
            JsonObject jObject = jArray.get(0).getAsJsonObject();

            for (String string : jObject.keySet())
            {
                list.add(string);
            }
            return list;
        }
        else
        {
            String s = "The loaded JSON is of an unsupported type";
            throw new IllegalStateException(s);
        }
    }

    @Override
    public List<Map> getValues(Map<String, String> keys)
    {
        if (element == null)
        {
            String s = "Call loadJson on this object before using this method";
            throw new IllegalStateException(s);
        }
        else if (!element.isJsonArray())
        {
            List<Map> list = new ArrayList();
            JsonObject obj = element.getAsJsonObject();
            Map map = createMap(obj, keys);

            list.add(map);
            return list;
        }
        else if (element.isJsonArray())
        {
            List<Map> list = new ArrayList();
            for (JsonElement e : element.getAsJsonArray())
            {
                JsonObject obj = e.getAsJsonObject();

                Map map = createMap(obj, keys);

                list.add(map);
            }

            return list;
        }
        else
        {
            String s = "The loaded JSON is of an unsupported type";
            throw new IllegalStateException(s);
        }
    }

    /**
     * @deprecated
     *
     * @param file
     * @param cellOrder
     * @param conversion
     *
     * @throws DALException
     */
    @Override
    public void readFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Map createMap(JsonObject obj, Map<String, String> keys)
    {
        Map map = new LinkedHashMap();
        String t;
        t = "siteName";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "assetSerialnumber";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "type";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "externalWorkOrderId";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "systemStatus";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "userStatus";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "createdOn";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "createdBy";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "name";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "priority";
        map.put(t, obj.get(keys.get(t)).getAsString());
        t = "status";
        map.put(t, obj.get(keys.get(t)).getAsString());
        Map planning = new LinkedHashMap();
        t = "latestFinishDate";
        planning.put(t, obj.get(keys.get(t)).getAsString());
        t = "earliestStartDate";
        planning.put(t, obj.get(keys.get(t)).getAsString());
        t = "latestStartDate";
        planning.put(t, obj.get(keys.get(t)).getAsString());
        t = "estimatedtime";
        planning.put(t, obj.get(keys.get(t)).getAsString());
        t = "planning";
        map.put(t, planning);
        return map;
    }
}
