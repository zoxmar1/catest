package com.ca.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mnael on 1/13/2017.
 */
public class Installer
{
    public static void depend(String item, String ... dependencies)
    {
        try
        {
            //System.out.println("depend:" + item +  " " + Arrays.toString(dependencies));
            ItemManager.SINGLETON.createItem(item, dependencies);
        }
        catch(Exception e)
        {

        }
    }

    public static void install(String item)
    {
        try
        {
            InstallationManager.SINGLETON.install(item);
            System.out.println("\tInstalling " + item);
        }
        catch(Exception e)
        {
            System.out.println("\t" + e.getMessage());
        }
    }

    public static void list()
    {
        System.out.println(InstallationManager.SINGLETON.list());
    }

    public static void remove(String item)
    {
        try
        {
            InstallationManager.SINGLETON.remove(item);
            System.out.println("\tRemoving " + item);
        }
        catch(Exception e)
        {
            System.out.println("\t" + e.getMessage());
        }
    }

    public static void main(String ...args) throws IOException {
        String commands[] =
                {
                 "DEPEND TELNET TCPIP NETCARD",
                 "DEPEND​ TCPIP NETCARD", "DEPEND DNS TCPIP NETCARD","DEPEND BROWSER HTML TCPIP","INSTALL NETCARD", "INSTALL TELNET", "INSTALL foo",
                        "REMOVE NETCARD", "INSTALL BROWSER", "INSTALL DNS", "LIST", "REMOVE TELNET", "REMOVE NETCARD", "REMOVE DNS", "REMOVE NETCARD", "INSTALL NETCARD",
                        "REMOVE TCPIP", "REMOVE BROWSER", "REMOVE TCPIP", "LIST"

                };


        if (args.length == 1)
        {
            BufferedReader fr = new BufferedReader(new FileReader(args[0]));
            String line = null;
            ArrayList<String> allFile = new ArrayList<String>();
            do
            {
                line = fr.readLine();
                if (line != null)
                {
                    allFile.add(line);
                }
            }while(line != null);
            fr.close();
            commands = allFile.toArray(new String[0]);
        }

        for (String command : commands)
        {
            String parsedCommand[] = command.split(" ");
            int index = 0;
            String action = parsedCommand[index++];
            System.out.println(command);
            switch(action)
            {
                case "DEPEND":
                    String item = parsedCommand[index++];
                    List<String> items = new ArrayList<String>();
                    for (; index < parsedCommand.length; index++)
                    {
                        items.add(parsedCommand[index]);
                    }
                    depend(item, items.toArray(new String[0]));
                    break;
                case "INSTALL":
                    install(parsedCommand[index++]);
                    break;
                case "LIST":
                    list();
                    break;
                case "REMOVE":
                    remove(parsedCommand[index++]);
                    break;
            }

        }


    }
}
