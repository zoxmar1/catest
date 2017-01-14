package com.ca.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mnael on 1/13/2017.
 */
public class ItemManager
{
    public static final ItemManager SINGLETON = new ItemManager();

    private Map<String, Item> items = new HashMap<String, Item>();

    private ItemManager()
    {

    }

    public synchronized Item createItem(String name, String ... dependencies)
    {
        Item item = null;
        if (name != null)
        {
            item = items.get(name);

            if (item == null) {
                item = new Item(name);
                items.put(item.getName(), item);
            }

            if (dependencies != null ) {
                for (String dep : dependencies) {
                    if (dep != null) {
                        Item toAdd = new Item(dep);
                        item.getDependencies().add(toAdd);
                        if (lookupItem(dep) == null)
                        {
                            createItem(dep);
                        }
                    }
                }
            }

        }
        return item;
    }

    public String toString()
    {
       Item all[] =  items.values().toArray(new Item[0]);
       StringBuilder sb = new StringBuilder();
       for(Item item : all)
       {
           if (sb.length() > 0)
           {
               sb.append('\n');
           }
           sb.append(item.getName());
           sb.append(':');
           Item dependencies[] = item.getDependencies().toArray(new Item[0]);
           if (dependencies.length > 0)
           {
               for(int i = 0; i < dependencies.length; i++)
               {
                   if (i > 0)
                   {
                       sb.append(", ");
                   }
                   sb.append(dependencies[i].getName());
               }
           }
       }

       return sb.toString();
    }


    public Item lookupItem(String itemName)
    {
        return items.get(itemName);
    }

}
