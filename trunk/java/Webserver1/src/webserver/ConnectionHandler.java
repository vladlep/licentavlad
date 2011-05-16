package webserver;

import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import util.PropertyHolder;

// Referenced classes of package webserver:
//            WebServer

public class ConnectionHandler
    implements Runnable
{

    public ConnectionHandler(Socket client_socket, PropertyHolder ph)
    {
        this.client_socket = client_socket;
        this.ph = ph;
    }

    public void run()
    {
        System.out.println("New Communication Thread Started");
        try
        {
            String name = "";
            String msg = "";
            String ext = "";
            PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
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
                File f = new File((new StringBuilder()).append(ph.getPath()).append("/").append(name).toString());
                if(f.exists())
                {
                    if(ext.equals("gif") || ext.equals("jpg") || ext.equals("png"))
                        sendImage((new StringBuilder()).append(ph.getPath()).append("\\").append(name).toString(), ext);
                    else
                    if(ph.getPath().equals(WebServer.getInstance().getRootPath()))
                        sendFileContent((new StringBuilder()).append(ph.getPath()).append("\\").append(name).toString(), out);
                    else
                        sendFileContent((new StringBuilder()).append(ph.getPath()).append("\\").append(ph.getDefaultFile()).toString(), out);
                } else
                {
                    sendFileContent((new StringBuilder()).append(ph.getPath()).append("\\").append(ph.getNotFoundFile()).toString(), out);
                }
            }
            in.close();
            out.close();
        }
        catch(IOException e)
        {
            System.err.println("Problem with Communication Server");
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
        ImageIO.write(img, format, client_socket.getOutputStream());
    }

    private PropertyHolder ph;
    private Socket client_socket;
}

