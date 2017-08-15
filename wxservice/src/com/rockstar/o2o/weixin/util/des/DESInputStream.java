
package com.rockstar.o2o.weixin.util.des;

import java.io.*;


public class DESInputStream extends InputStream
{

    public DESInputStream(InputStream inSet, DES desSet)
    {
        des = null;
        p = new byte[8];
        loc = 9;
        changMeaning = false;
        inBlock = false;
        des = desSet;
        in = inSet;
    }

    public void close()
        throws IOException
    {
        super.close();
        in.close();
    }

    public int read()
        throws IOException
    {
        do
        {
            if(loc >= 8)
            {
                int len = in.read(p);
                if(len == -1)
                    throw new EOFException("\u6587\u4EF6\u672B\u5C3E");
                for(; len < 8; len++)
                    p[len] = (byte)in.read();

                long s = des.bytes2long(p);
                long l = des.decrypt(s);
                des.long2bytes(l, p);
                loc = 0;
            }
            if(changMeaning)
            {
                changMeaning = false;
                if(p[loc] != 0)
                    break;
                loc = 8;
                if(inBlock)
                    throw new IOException("END OF THIS BLOCK");
                continue;
            }
            if(p[loc] != 100)
                break;
            changMeaning = true;
            loc++;
        } while(true);
        byte pb = p[loc++];
        int pi = pb & 255;
        return pi;
    }

    public int read(byte b[])
        throws IOException
    {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        if(len <= 0)
            return 0;
        int c = read();
        if(c == -1)
            return -1;
        inBlock = true;
        b[off] = (byte)c;
        int i = 1;
        do
        {
            try
            {
                if(i >= len)
                    break;
                c = read();
                if(c == -1)
                    break;
                if(b != null)
                    b[off + i] = (byte)c;
                i++;
                continue;
            }
            catch(IOException ee) { }
            break;
        } while(true);
        inBlock = false;
        return i;
    }

    private DES des;
    private byte p[];
    private InputStream in;
    private int loc;
    public static final byte ENDEDCODE = 100;
    private boolean changMeaning;
    private boolean inBlock;
}
