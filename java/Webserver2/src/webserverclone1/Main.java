package webserverclone1;

import java.io.File;

// Referenced classes of package webserverclone1:
//            AServer

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        AServer s = AServer.instanta();
        File r = new File("C:\\Users\\oana\\Documents\\NetBeansProjects\\WebServer\\root");
        s.setRoot(r);
        File m = new File("C:\\Users\\oana\\Documents\\NetBeansProjects\\WebServer\\maint");
        s.setMentenanta(m);
        s.porneste(8888);
    }
}


