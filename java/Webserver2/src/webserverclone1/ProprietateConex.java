
package webserverclone1;


public class ProprietateConex
{
	private String p;
    private String f;
    private String err_f;
    
    public String getPath()
    {
        return p;
    }

    public String getErr()
    {
        return err_f;
    }

    public ProprietateConex(String p, String f, String err)
    {
        this.p = p;
        this.f = f;
        err_f = err;
    }

    public String getFile()
    {
        return f;
    }

    
}

