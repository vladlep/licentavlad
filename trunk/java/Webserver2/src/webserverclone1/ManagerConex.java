/*jadclipse*/// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.

package webserverclone1;

import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;

// Referenced classes of package webserverclone1:
//            ProprietateConex, AServer

public class ManagerConex extends Thread
{

    public ManagerConex(Socket s, ProprietateConex pc)
    {
        this.s = s;
        this.pc = pc;
    }

    public void run()
    {
        try
        {
            System.out.println("Hellllo!!!!");
            String name = "";
            String msg = "";
            String ext = "";
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null && !inputLine.trim().equals("")) 
                msg = (new StringBuilder()).append(msg).append(inputLine).toString();
            if(!msg.equals(""))
            {
                int ind = msg.indexOf("/") + 1;
                name = msg.substring(ind, msg.indexOf(" ", ind));
                if(name.equals(""))
                    name = "index.html";
                ext = name.substring(name.indexOf(".") + 1);
                File f = new File((new StringBuilder()).append(pc.getPath()).append("/").append(name).toString());
                if(f.exists())
                {
                    if(ext.equals("gif") || ext.equals("jpg") || ext.equals("png"))
                        sendImage((new StringBuilder()).append(pc.getPath()).append("\\").append(name).toString(), ext);
                    else
                    if(pc.getPath().equals(AServer.instanta().getRoot()))
                        sendFileContent((new StringBuilder()).append(pc.getPath()).append("\\").append(name).toString(), out);
                    else
                        sendFileContent((new StringBuilder()).append(pc.getPath()).append("\\").append(pc.getFile()).toString(), out);
                } else
                {
                    sendFileContent((new StringBuilder()).append(pc.getPath()).append("\\").append(pc.getErr()).toString(), out);
                }
            }
            in.close();
            out.close();
        }
        catch(IOException e)
        {
            System.exit(1);
        }
    }

    private void sendFileContent(String name, PrintWriter out)
        throws IOException
    {
        BufferedReader file_reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
        String inputLine;
        while((inputLine = file_reader.readLine()) != null) 
            out.println(inputLine);
        file_reader.close();
    }

    private void sendImage(String name, String format)
        throws IOException
    {
        javax.imageio.stream.ImageInputStream fis = ImageIO.createImageInputStream(new File(name));
        java.awt.image.BufferedImage img = ImageIO.read(fis);
        ImageIO.write(img, format, s.getOutputStream());
    }

    private ProprietateConex pc;
    private Socket s;
}


/*
	DECOMPILATION REPORT

	Decompiled from: F:\serios\faculta\licenta\WebServer\WebServerClone1.jar
	Total time: 129 ms
	Jad reported messages/errors:
The class file version is 49.0 (only 45.3, 46.0 and 47.0 are supported)
	Exit status: 0
	Caught exceptions:
*/