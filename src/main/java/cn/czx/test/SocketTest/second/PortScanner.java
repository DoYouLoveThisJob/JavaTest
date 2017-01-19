package cn.czx.test.SocketTest.second;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by czx on 2017/1/18.
 */
public class PortScanner {
    public void scan(String host){
        Socket socket=null;
        for(int port=1;port<1024;port++){

            try {
                //socket=new Socket(host,port);
                socket=new Socket();
                SocketAddress remoteAddr=new InetSocketAddress(host,port);
                socket.connect(remoteAddr,1);
                System.out.println("There is a server on port"+port);
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Can't connect to port"+port);
            }finally {
                try {
                    if(socket!=null){
                        socket.close();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void main(String  args[]){
        String host="localhost";
        if(args.length>0){
           host = args[0];
        }
        new PortScanner().scan(host);
    }
}
