package cn.czx.test.SocketTest.second;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by czx on 2017/1/18.
 */
public class Sender {
    private String host="localhost";
    private int  port=8000;
    private Socket socket;
    private static int stopWay=1;
    private final  int NATURAL_STOP=1;
    private final  int SUDDEN_STOP=2;
    private final  int SOCKET_STOP=3;
    private final  int OUTPUT_STOP=4;
    public Sender() throws IOException {
        socket = new Socket(host,port);
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }

    public void send() throws Exception{
        PrintWriter pw=getWriter(socket);
        for(int i=0;i<20;i++){
         String msg="hello_"+i;
            pw.println(msg);
            System.out.println("send:"+msg);
            Thread.sleep(500);
            if(i==2){
                if(stopWay==SUDDEN_STOP){
                    System.out.println("突然终止程序");
                    System.exit(0);
                }else if(stopWay==SOCKET_STOP){
                    System.out.println("关闭Socket终止程序");
                    socket.close();
                    break;
                }else  if(stopWay==OUTPUT_STOP){
                    System.out.println("关闭输出流并终止程序");
                    socket.shutdownOutput();
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
        stopWay=1;
        try {
            new Sender().send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
