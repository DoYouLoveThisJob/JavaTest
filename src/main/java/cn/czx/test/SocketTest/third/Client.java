package cn.czx.test.SocketTest.third;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by czx on 2017/1/19.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        final  int length=100;
        String host="localhost";
        int port=8000;
        Socket[] sockets=new  Socket[length];
        for(int  i=0; i<length;i++){
            sockets[i]=new Socket(host,port);
            System.out.println("第"+(i+1)+"次连接成功");
        }
        Thread.sleep(3000);
        for(int  i=0; i<length;i++){
            sockets[i].close();
        }

    }
}
