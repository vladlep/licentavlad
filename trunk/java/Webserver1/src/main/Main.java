/*jadclipse*/// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   Main.java

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


/*
	DECOMPILATION REPORT

	Decompiled from: F:\serios\faculta\licenta\WebServer\WebServer.jar
	Total time: 433 ms
	Jad reported messages/errors:
The class file version is 49.0 (only 45.3, 46.0 and 47.0 are supported)
	Exit status: 0
	Caught exceptions:
*/