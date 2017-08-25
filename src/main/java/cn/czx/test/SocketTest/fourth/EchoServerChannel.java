package cn.czx.test.SocketTest.fourth;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by czx on 2017/8/25.
 */
public class EchoServerChannel {
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("GBK");
    public EchoServerChannel() throws IOException {
        selector = Selector.open();
        serverSocketChannel =ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("非阻塞服务启动");
    }
    public void service() throws IOException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(selector.select()>0){
            Set readKeys = selector.selectedKeys();
            Iterator it = readKeys.iterator();
            while(it.hasNext()){
                SelectionKey key =  null;
                try{
                    key = (SelectionKey) it.next();
                    it.remove();

                    if(key.isAcceptable()){
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = (SocketChannel) ssc.accept();
                        System.out.println("接收到客户里连接，来自："+socketChannel.socket().getInetAddress()+":"+socketChannel.socket().getPort());
                        socketChannel.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE,buffer);
                    }
                    if(key.isReadable()){
                        receive(key);
                    }
                    if(key.isWritable()){
                       send(key);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    try{
                        if(key!=null){
                            key.channel();
                            key.channel().close();
                        }
                    }catch(Exception ep){
                           ep.printStackTrace();
                    }
                }
            }
        }
    }
    public void send(SelectionKey key) throws IOException {
        ByteBuffer buffer  = (ByteBuffer)key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.flip();//把极限设置为0，把位置也设置为0
        String data = decode(buffer);
        if(data.indexOf("\r\n")==-1){
            return;
        }
        String outputData = data.substring(0,data.indexOf("\n")+1);
        System.out.print(outputData);
        ByteBuffer outputBuffer = encode("echo:"+outputData);
        while(outputBuffer.hasRemaining()){
            socketChannel.write(outputBuffer);
            ByteBuffer temp = encode(outputData);
            buffer.position(temp.limit());
            buffer.compact();
            if(outputData.equals("bye\r\n")){
               key.channel();
                socketChannel.close();
                System.out.println("关闭与客户的连接");
            }
        }
    }
    public void receive(SelectionKey key) throws IOException {
        ByteBuffer buffer  = (ByteBuffer)key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        socketChannel.read(readBuff);
        readBuff.flip();
        buffer.limit(buffer.capacity());
        buffer.put(readBuff);
    }
    public String  decode(ByteBuffer buffer){
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }
    public ByteBuffer encode(String str){
      return charset.encode(str);
    }
    public static void main(String args[])throws Exception{
        EchoServerChannel echoServerChannel = new EchoServerChannel();
        echoServerChannel.service();
    }

}
