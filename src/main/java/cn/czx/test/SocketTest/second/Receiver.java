package cn.czx.test.SocketTest.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by czx on 2017/1/18.
 */
public class Receiver {
    private String host="localhost";
    private int  port=8000;
    private ServerSocket serverSocket;
    private static int stopWay=1;
    private final  int NATURAL_STOP=1;
    private final  int SUDDEN_STOP=2;
    private final  int SOCKET_STOP=3;
    private final  int INPUT_STOP=4;
    private final int SEVERSOCKET_STOP=5;
    public Receiver() throws IOException {
        serverSocket=new ServerSocket(port);
        System.out.println("服务器已经启动");
    }
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    public  void receive() throws Exception {
        Socket socket=null;
        socket=serverSocket.accept();
        BufferedReader br=getReader(socket);
        for(int i=0;i<20;i++){
            String msg=br.readLine();

            System.out.println("receive:"+msg);
            Thread.sleep(1000);
            if(i==2){
                if(stopWay==SUDDEN_STOP){
                    System.out.println("突然终止程序");
                    System.exit(0);
                }else if(stopWay==SOCKET_STOP){
                    System.out.println("关闭Socket终止程序");
                    socket.close();
                    break;
                }else  if(stopWay==INPUT_STOP){
                    System.out.println("关闭输入流并终止程序");
                    socket.shutdownInput();
                    break;
                }else if(stopWay==SEVERSOCKET_STOP){
                    System.out.println("关闭ServerSocket并终止程序");
                    serverSocket.close();
                    break;
                }
            }
        }
        if(stopWay==NATURAL_STOP){
            System.out.println("自然终止程序");
            socket.close();
        }
    }
    public static void main(String args[]){
        stopWay=2;
        try {
            new Receiver().receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
