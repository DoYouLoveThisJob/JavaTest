package cn.czx.test.SocketTest.third;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by czx on 2017/1/19.
 */
public class Server {
    private int port=8000;
    private ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket=new ServerSocket(port,3);
        System.out.println("serverSocket is start");
    }
    public void service(){
        while(true){
         Socket socket=null;

                try {
                    socket=serverSocket.accept();//
                    System.out.println("New connection accepted:"+socket.getInetAddress()+";"+socket.getPort());

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(socket!=null){
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        }


    }
    public  static void main(String[] args){
        try {
            Server server=new Server();
            //Thread.sleep(60000*10);
            server.service();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
