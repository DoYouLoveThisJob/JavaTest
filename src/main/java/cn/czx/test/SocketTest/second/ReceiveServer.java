package cn.czx.test.SocketTest.second;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by czx on 2017/1/18.
 */
public class ReceiveServer {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket s=serverSocket.accept();
        s.setSoTimeout(20000);
        InputStream in=s.getInputStream();
        ByteArrayOutputStream buffer=new ByteArrayOutputStream();
        byte[] buff=new byte[1024];
        int len=-1;
        do{
            try{
                len= in.read(buff);
                if(len!=-1){
                    buffer.write(buff,0,len);
                }
            }catch (SocketTimeoutException ex){
                System.out.println("wait for timeout");
                len=0;
            }

        }while(len!=-1);
        System.out.println(new String(buffer.toByteArray()));


    }
}
