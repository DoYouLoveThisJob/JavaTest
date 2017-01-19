package cn.czx.test.SocketTest.first;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by czx on 2017/1/12.
 */
public class EchoServer {
    private int port=8000;
    private ServerSocket serverSocket;
    public EchoServer() throws IOException {
        this.serverSocket=new ServerSocket(8000);//监听8000端口的服务器端socket
        System.out.println("服务器启动");
    }
    public String echo(String msg){
        return "echo:"+msg;
    }


    public void service(){
        while (true)
        {
            Socket socket=null;
            try{
                socket=serverSocket.accept();//
                System.out.println("New connection accepted:"+socket.getInetAddress()+";"+socket.getPort());
                if(socket!=null){
                    new Thread(new RunHander(socket)).start();
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public class RunHander implements Runnable {
        private PrintWriter getWriter(Socket socket) throws IOException {
            OutputStream socketOut=socket.getOutputStream();
            return new PrintWriter(socketOut,true);
        }
        private BufferedReader getReader(Socket socket) throws IOException {
            InputStream socketIn=socket.getInputStream();
            return new BufferedReader(new InputStreamReader(socketIn));
        }
        private Socket socket;

        public RunHander(Socket socket){
            this.socket=socket;
        }
        @Override
        public void run() {
            try {
                BufferedReader br = getReader(socket);
                PrintWriter pw=getWriter(socket);
                String msg=null;
                while ((msg=br.readLine())!=null){
                    System.out.println(msg);
                    pw.println(echo(msg));
                    if(msg.equals("bye")){
                        break;
                    }
                }
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
   public static void main(String[] args){
        try {
            new EchoServer().service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
