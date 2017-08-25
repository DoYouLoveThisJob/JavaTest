package cn.czx.test.SocketTest.fourth;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by czx on 2017/2/14.
 * 阻塞模式
 */
public class EchoServer {
private int port = 8000;
    private ServerSocketChannel serverSocketChannel=null;
    private ExecutorService executorService;//线程池
    private static final int POOL_MULTIPLE=4;//线程池中工作线程的数目
    public EchoServer() throws IOException {
        //创建一个线程池
        this.executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_MULTIPLE);
        //创建一个ServerSocketChannel对象
        this.serverSocketChannel=ServerSocketChannel.open();
        //使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定相同的端口
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("多线程服务启动!");
    }
    public  void service(){
        while(true){
            SocketChannel socketChannel=null;
            try {
                socketChannel=serverSocketChannel.accept();
                executorService.execute(new Handler(socketChannel));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        try {
            new EchoServer().service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class Handler implements Runnable{
       private SocketChannel socketChannel;
        public Handler(SocketChannel socketChannel){
            this.socketChannel=socketChannel;
        }
        @Override
        public void run() {

        }
        private PrintWriter getWriter(Socket socket) throws IOException {
            OutputStream socketOut=socket.getOutputStream();
            return new PrintWriter(socketOut,true);
        }
        private BufferedReader getReader(Socket socket) throws IOException {
            InputStream socketIn=socket.getInputStream();
            return new BufferedReader(new InputStreamReader(socketIn));
        }
        public void handle(SocketChannel socketChannel){

            try {
                Socket socket=socketChannel.socket();
                System.out.println("接收到客户连接，来自"+socket.getInetAddress()+":"+socket.getPort());
                BufferedReader br=getReader(socket);
                PrintWriter pw = getWriter(socket);
                String msg=null;
                while ((msg= br.readLine())!=null){
                    System.out.println(msg);
                    pw.println(echo(msg));
                    if(msg.equals("bye")){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try{
                    if(socketChannel!=null){
                        socketChannel.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        public String echo(String msg){
            return "echo:"+msg;
        }
        public String readLine(SocketChannel socketChannel) throws IOException {
            //存放所有读到的数据，假定一行字符串对应的字节序列的长度小于1024
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            //存放一次读到的数据，一次只读一个字节
            ByteBuffer tempBuffer=ByteBuffer.allocate(1);
            boolean isLine=false;//表示是否读到了一行字符串
            boolean isEnd=false;//表示是否到达输入流末尾
            String data=null;
            while(!isLine && !isEnd){
                tempBuffer.clear();
                //在阻塞模式下，只有等读到了一个字节或者读到输入流 末尾才返回
                //在非阻塞模式下，有可能返回零
                int n= socketChannel.read(tempBuffer);
                if(n==-1){
                    isEnd = true;
                    break;
                }
                if(n==0){
                    continue;
                }
                tempBuffer.flip();//把极限设置为位置，把位置设置为0
                buffer.put(tempBuffer);//把tempBuffer中的数据复制到buffer中
                Charset charset=Charset.forName("GBK");
                CharBuffer charBuffer=charset.decode(buffer);
                data=charBuffer.toString();
                if(data.indexOf("\r\n")!=-1)
                {
                    isLine =true;
                    data=data.substring(0,data.indexOf("\r\n"));
                    break;
                }
                buffer.position(buffer.limit());//把位置设置为极限，为下次读取数据作准备
                buffer.limit(buffer.capacity());//把极限设置为容量，为下次读取数据作准备

            }
            return data;
        }
    }

}
