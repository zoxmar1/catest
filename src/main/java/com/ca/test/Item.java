package com.ca.test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mnael on 1/13/2017.
 * Note: item was choosen instead of package, because package is a reserved java word
 */
public class Item
{
    private String name;
    private Set<Item> dependencies = new HashSet<Item>();


    public Item()
    {
    }

    public Item(String name)
    {
        setName(name);
    }



    public Set<Item> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<Item> dependencies) {
        this.dependencies = dependencies;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was overridden to make the item behave list a string when stored in set or map
     * @return
     */
    public int hashCode()
    {
        return name != null ? name.hashCode() : "".hashCode();
    }

    /**
     * This method was overridden to make the item behave list a string when stored in set or map
     * @param obj
     * @return
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else if (obj != null && obj instanceof Item)
        {
            return this.hashCode() == obj.hashCode();
        }

        return false;
    }
}
