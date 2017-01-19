package cn.czx.test.SocketTest.second;

import java.io.*;
import java.net.Socket;

/**
 * Created by czx on 2017/1/18.
 */
public class HttpClient {
    String host="www.baidu.com";
    int port=80;//443是https端口，80是http
    Socket socket;
    public void createSocket() throws IOException {
        socket =new Socket(host,port);
    }
    public void communicate() throws IOException {
        StringBuffer sb=new StringBuffer("GET /search/jubao.html HTTP/1.1\r\n");
        sb.append("Host:www.baidu.com\r\n");
        sb.append("Accept:*/*\r\n");
        sb.append("Accept-Language:zh-cn\r\n");
        sb.append("User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36\r\n");
        sb.append("Connection:Keep-Alive\r\n\r\n");
        //发出http请求
        OutputStream socketOut=socket.getOutputStream();
        socketOut.write(sb.toString().getBytes());
        socket.shutdownOutput();
        //接收响应结果
        InputStream socketIn=socket.getInputStream();
//        ByteArrayOutputStream buffer=new ByteArrayOutputStream();
//        byte[] buff=new byte[1024];
//        int len=-1;
//        while ((len=socketIn.read(buff))!=-1) {
//            buffer.write(buff,0,len);
//        }
//        System.out.println(new String(buffer.toByteArray()));
        BufferedReader br=new BufferedReader(new InputStreamReader(socketIn));
        String data;
        while ((data=br.readLine())!=null){
             System.out.println(data);
        }
        socket.close();
        }
    public static void main(String[] args){
        HttpClient client=new HttpClient();
        try {
            client.createSocket();
            client.communicate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
