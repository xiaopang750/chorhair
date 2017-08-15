
package com.rockstar.o2o.weixin.util.des;
import java.io.IOException;
import java.io.OutputStream;

public class DESOutputStream extends OutputStream
{

    public DESOutputStream(OutputStream outSet, DES desSet)
    {
        des = null;
        p = new byte[8];
        loc = 0;
        des = desSet;
        out = outSet;
    }

    public void close()
        throws IOException
    {
        writeOUT();
        out.close();
    }

    public void flush()
        throws IOException
    {
        p[loc++] = 100;
        if(loc == 8)
            writeOUT();
        p[loc++] = 0;
        writeOUT();
    }

    public void write(int b)
        throws IOException
    {
        if(b == 100)
        {
            p[loc++] = (byte)(b & 255);
            if(loc == 8)
                writeOUT();
            p[loc++] = (byte)(b & 255);
        } else
        {
            p[loc++] = (byte)(b & 255);
        }
        if(loc == 8)
        {
            writeOUT();
            return;
        } else
        {
            return;
        }
    }

    public void writeOUT()
        throws IOException
    {
        if(loc < 8)
            p[loc++] = 100;
        long l = des.encrypt(des.bytes2long(p));
        des.long2bytes(l, p);
        out.write(p);
        loc = 0;
        out.flush();
    }

    private DES des;
    private OutputStream out;
    private byte p[];
    private int loc;
}


