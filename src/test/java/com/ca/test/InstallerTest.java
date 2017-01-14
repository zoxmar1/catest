package com.ca.test;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mnael on 1/13/2017.
 */
public class InstallerTest {

    @Before
    public void init() throws Exception
    {
        ItemManager.SINGLETON.createItem("a", "x", "y", "z");
        ItemManager.SINGLETON.createItem("b", "x");
        ItemManager.SINGLETON.createItem("c", "b");
        ItemManager.SINGLETON.createItem("d", "m", "n");
        ItemManager.SINGLETON.createItem("e", "a", "o");
    }

    @Test
    public void installItems() throws Exception {


        System.out.println("Items:\n" + ItemManager.SINGLETON);
        System.out.println(InstallationManager.SINGLETON.listWithDependencies());
        InstallationManager.SINGLETON.install("e");
        InstallationManager.SINGLETON.install("f");
        InstallationManager.SINGLETON.install("b");
        InstallationManager.SINGLETON.install("c");
        System.out.println("List with dependencies:\n" + InstallationManager.SINGLETON.listWithDependencies());

    }

    @Test
    public void removeItem() throws Exception {
        InstallationManager.SINGLETON.remove("e");
        System.out.println("List with dependencies:\n" + InstallationManager.SINGLETON.listWithDependencies());
        InstallationManager.SINGLETON.remove("x");
    }

}
