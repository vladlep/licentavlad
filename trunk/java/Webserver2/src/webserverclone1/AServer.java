/*jadclipse*/// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   AServer.java

package webserverclone1;

import java.io.*;
import java.net.ServerSocket;

// Referenced classes of package webserverclone1:
//            ProprietateConex, ManagerConex

public class AServer extends Thread
{
	private boolean flag_r;
    private int p;
    private File m;
    private String ip;
    private boolean flag_m;
    private File r;
    private ServerSocket s;
    private static AServer instance = null;

    private AServer()
    {
        ip = "127.0.0.1";
        m = null;
        r = null;
        flag_m = false;
        flag_r = false;
        System.out.println("Serverul este setat...");
    }

    public static AServer instanta()
    {
        if(instance == null)
            instance = new AServer();
        return instance;
    }

    public boolean setMentenanta(File m)
    {
        File f = new File(m, "maint.html");
        if(f.exists())
        {
            this.m = m;
            return true;
        } else
        {
            return false;
        }
    }

    public String getRoot()
    {
        return r.getAbsolutePath();
    }

    public boolean porneste(int port)
    {
        if(port > 0)
        {
            if(r == null)
            {
                System.out.println("Root path not set!");
                return false;
            }
            flag_r = true;
            try
            {
                s = new ServerSocket(port);
                p = port;
                System.out.println("Serverul este pornit....");
                start();
            }
            catch(IOException ex)
            {
                System.exit(1);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isMentenanta()
    {
        return flag_m;
    }

    public String getIp()
    {
        return ip;
    }

    public boolean mentenanta(boolean b)
    {
        if(flag_r && m != null)
        {
            flag_m = b;
            System.out.println("Serverul este in mentenanta...");
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isPornit()
    {
        return flag_r;
    }

    public boolean setRoot(File r)
    {
        File f = new File(r, "index.html");
        if(f.exists())
        {
            this.r = r;
            return true;
        } else
        {
            return false;
        }
    }

    public void run()
    {
        do
        {
            if(!flag_r)
                break;
            try
            {
                java.net.Socket soc = s.accept();
                if(flag_r)
                {
                    ManagerConex c;
                    if(flag_m)
                    {
                        ProprietateConex pc = new ProprietateConex(m.getAbsolutePath(), "maint.html", "maint.html");
                        c = new ManagerConex(soc, pc);
                    } else
                    {
                        ProprietateConex pc = new ProprietateConex(r.getAbsolutePath(), "index.html", "404.html");
                        c = new ManagerConex(soc, pc);
                    }
                    c.start();
                }
            }
            catch(IOException ex)
            {
                System.exit(1);
            }
        } while(true);
    }

    public void opreste()
    {
        flag_r = false;
        Thread.currentThread().interrupt();
        flag_m = false;
    }

    
}


/*
	DECOMPILATION REPORT

	Decompiled from: F:\serios\faculta\licenta\WebServer\WebServerClone1.jar
	Total time: 146 ms
	Jad reported messages/errors:
The class file version is 49.0 (only 45.3, 46.0 and 47.0 are supported)
	Exit status: 0
	Caught exceptions:
*/