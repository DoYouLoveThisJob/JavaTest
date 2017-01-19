package cn.czx.test.SocketTest.second;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by czx on 2017/1/18.
 */
public class SendClient {
    public static void main(String args[]) throws Exception {
     Socket s=new Socket("localhost",8000);
        OutputStream out=s.getOutputStream();
        out.write("hello".getBytes());
        out.write(" world".getBytes());
        Thread.sleep(60000);
        s.close();
    }
}
