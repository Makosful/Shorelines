package com.github.makosful.shoreline.dal.Json;

import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Axl
 */
public class JsonReader implements IReader
{

    private JsonElement element;

    @Override
    public boolean loadFile(String file) throws ReaderException
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
            Gson gson = new Gson();
            GsonBuilder builder = gson.newBuilder();
            Object obj = builder.create().fromJson(sb.toString(), Object.class);
            element = gson.toJsonTree(obj);

            return true;
        }
        catch (IOException ex)
        {
            throw new ReaderException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<String> getHeaders() throws ReaderException
    {
        if (element == null)
        {
            String s = "Call loadJson on this object before using this method";
            throw new ReaderException(s);
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
            throw new ReaderException(s);
        }
    }

    @Override
    public List<Map> getValues(Map<String, String> keys) throws ReaderException
    {
        if (element == null)
        {
            String s = "Call loadJson on this object before using this method";
            throw new ReaderException(s);
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
            throw new ReaderException(s);
        }
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
