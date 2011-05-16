/*jadclipse*/// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   WebServer.java

package webserver;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import util.PropertyHolder;

// Referenced classes of package webserver:
//            ConnectionHandler

public class WebServer
    implements Runnable
{

    public WebServer()
    {
        port = 0;
        root = null;
        maint = null;
        address = "127.0.0.1";
        is_running = false;
        is_maint = false;
        server_socket = null;
        pool = Executors.newFixedThreadPool(10);
    }

    public boolean setRootPath(File path)
    {
        File f = new File(path, "index.html");
        File f2 = new File(path, "404.html");
        if(f.exists() && f2.exists())
        {
            root = path;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean setMaintPath(File path)
    {
        File f = new File(path, "maint.html");
        if(f.exists())
        {
            maint = path;
            return true;
        } else
        {
            return false;
        }
    }

    public static WebServer getInstance()
    {
        if(_instance == null)
            _instance = new WebServer();
        return _instance;
    }

    public boolean startServer(int port)
    {
        if(port > 0)
        {
            if(root == null)
            {
                System.out.println("Root path not set!");
                return false;
            }
            this.port = port;
            is_running = true;
            try
            {
                server_socket = new ServerSocket(this.port);
                (new Thread(this)).start();
            }
            catch(IOException ex)
            {
                System.err.println((new StringBuilder()).append("Could not listen on port ").append(this.port).toString());
                System.exit(1);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void stopServer()
    {
        is_running = false;
        Thread.currentThread().interrupt();
        _instance = null;
        is_maint = false;
    }

    public String getServerAddress()
    {
        return address;
    }

    public boolean setToMaintenanceMode(boolean b)
    {
        if(is_running && maint != null)
        {
            is_maint = b;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isInMaintenanceMode()
    {
        return is_maint;
    }

    public boolean isRunning()
    {
        return is_running;
    }

    public String getRootPath()
    {
        return root.getAbsolutePath();
    }

    public void run()
    {
        do
        {
            if(!is_running)
                break;
            try
            {
                java.net.Socket s = server_socket.accept();
                System.out.println("New connection accepted!");
                if(is_running)
                {
                    ConnectionHandler c;
                    if(!is_maint)
                        c = new ConnectionHandler(s, new PropertyHolder(root.getAbsolutePath(), "index.html", "404.html"));
                    else
                        c = new ConnectionHandler(s, new PropertyHolder(maint.getAbsolutePath(), "maint.html", "maint.html"));
                    pool.execute(c);
                }
            }
            catch(IOException ex)
            {
                System.err.println("Accept failed!");
                System.exit(1);
            }
        } while(true);
    }

    public static final String MANDATORY_ROOT_FILE = "index.html";
    public static final String MANDATORY_MAINT_FILE = "maint.html";
    public static final String NO_FILE_FOUND = "404.html";
    public static final int NR_THREADS = 10;
    private static WebServer _instance = null;
    private int port;
    private File root;
    private File maint;
    private String address;
    private boolean is_running;
    private boolean is_maint;
    private ServerSocket server_socket;
    private ExecutorService pool;

}

