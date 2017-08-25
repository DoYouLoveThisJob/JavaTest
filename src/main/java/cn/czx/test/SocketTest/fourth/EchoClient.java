package cn.czx.test.SocketTest.fourth;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * Created by czx on 2017/8/25.
 */
public class EchoClient {
    private SocketChannel socketChannel = null;
    public EchoClient() throws IOException {
        socketChannel = SocketChannel.open();
        InetAddress inetAddress = InetAddress.getLocalHost();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress,8000);
        socketChannel.connect(inetSocketAddress);
        System.out.println("与服务器建立连接成功");
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
            BufferedReader br=getReader(socketChannel.socket());
            PrintWriter pw=getWriter(socketChannel.socket());
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
                socketChannel.close();
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

