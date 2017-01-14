package com.ca.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Created by mnael on 1/13/2017.
 */
public class InstallationManager
{
    public static final InstallationManager SINGLETON = new InstallationManager();

    private Map<Item, Set<Item>> installedItems = new HashMap<Item, Set<Item>>();


    public synchronized void install(String itemName)
            throws IllegalArgumentException
    {

        Item toInstall = ItemManager.SINGLETON.lookupItem(itemName);
        if (toInstall ==  null)
        {
            // create the item
            toInstall = ItemManager.SINGLETON.createItem(itemName);
        }


        if (installedItems.get(toInstall) == null)
        {
            for (Item dep : toInstall.getDependencies())
            {
               if (installedItems.get(dep) == null)
                    install(dep.getName());
            }

            installedItems.put(toInstall, new HashSet<Item>());
            for (Item dep : toInstall.getDependencies())
            {

                Set<Item> set = installedItems.get(dep);
                set.add(toInstall);
            }

        }
        else
        {
            throw new IllegalArgumentException(itemName + " is already installed");
        }
    }

    public synchronized void remove(String itemName)
            throws IllegalArgumentException
    {
        Item toRemove = ItemManager.SINGLETON.lookupItem(itemName);
        if (toRemove == null)
            throw new IllegalArgumentException(itemName + " not found");

        Set<Item> dependencies = installedItems.get(toRemove);
        if (dependencies == null)
            throw new IllegalArgumentException(toRemove.getName() + " is not installed");

        if (dependencies.size() != 0 )
            throw new IllegalArgumentException(toRemove.getName() + " is still needed");
        for(Set<Item> dep : installedItems.values())
        {
            dep.remove(toRemove);
        }
        installedItems.remove(toRemove);

    }


    public String listWithDependencies()
    {
        StringBuilder sb = new StringBuilder();


        Item installed[] = installedItems.keySet().toArray(new Item[0]);
        for(Item item : installed)
        {
            if (sb.length() > 0 )
            {
                sb.append('\n');
            }
            sb.append(item.getName());
            Set<Item> deps = installedItems.get(item);
            sb.append(":");
            for (Item depItem : deps)
            {
                sb.append(depItem.getName());
                sb.append(' ');
            }
        }

        return sb.toString();
    }

    public String list()
    {
        StringBuilder sb = new StringBuilder();


        Item installed[] = installedItems.keySet().toArray(new Item[0]);
        for(Item item : installed)
        {
            if (sb.length() > 0 )
            {
                sb.append('\n');
            }
            sb.append('\t');
            sb.append(item.getName());
        }

        return sb.toString();
    }

 }
