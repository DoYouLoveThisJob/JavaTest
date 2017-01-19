package cn.czx.test.SocketTest.first;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by czx on 2017/1/12.
 */
public class EchoClient {
    private String host="localhost";
    private int port=8000;
    private Socket socket;
    public EchoClient() throws IOException {
        //this.socket=new Socket(host,port);
        Socket socket=new Socket();
        SocketAddress remoteAddr=new InetSocketAddress(host,port);
        socket.connect(remoteAddr,1000);//等待建立连接的超时时间为一分钟。Socket类的connect（SocketAddress endPoint,int timeout）,timeout设置为0的时候，表示永远不会超时。
        this.socket=socket;
    }
    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    public void talk(){
        try {
            BufferedReader br=getReader(socket);
            PrintWriter pw=getWriter(socket);
            BufferedReader localReader=new BufferedReader(new InputStreamReader(System.in));
            String msg=null;
            while((msg=localReader.readLine())!=null){
                pw.println(msg);
                System.out.println(br.readLine());
                if(msg.equals("bye")){
                   break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args){
        try {
            new EchoClient().talk();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
