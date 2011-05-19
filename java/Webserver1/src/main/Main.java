package main;

import java.io.File;
import webserver.WebServer;

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        WebServer ws = WebServer.getInstance();
        ws.setRootPath(new File("C:\\Users\\oana\\Documents\\NetBeansProjects\\WebServer\\root"));
        ws.setMaintPath(new File("C:\\Users\\oana\\Documents\\NetBeansProjects\\WebServer\\maint"));
        ws.startServer(9000);
    }
}
