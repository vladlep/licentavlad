/*jadclipse*/// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   PropertyHolder.java

package util;


public class PropertyHolder
{

    public PropertyHolder(String path, String default_file, String not_found_file)
    {
        this.path = path;
        this.default_file = default_file;
        this.not_found_file = not_found_file;
    }

    public String getPath()
    {
        return path;
    }

    public String getDefaultFile()
    {
        return default_file;
    }

    public String getNotFoundFile()
    {
        return not_found_file;
    }

    private String path;
    private String default_file;
    private String not_found_file;
}


/*
	DECOMPILATION REPORT

	Decompiled from: F:\serios\faculta\licenta\WebServer\WebServer.jar
	Total time: 165 ms
	Jad reported messages/errors:
The class file version is 49.0 (only 45.3, 46.0 and 47.0 are supported)
	Exit status: 0
	Caught exceptions:
*/