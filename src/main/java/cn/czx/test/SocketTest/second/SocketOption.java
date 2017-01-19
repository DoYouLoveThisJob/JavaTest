package cn.czx.test.SocketTest.second;

import java.net.Socket;
import java.net.SocketException;

/**
 * Created by czx on 2017/1/18.
 */
public class SocketOption {
    private Socket socket;
    public SocketOption(){
        socket=new Socket();
    }
    public  void socketOptionSet() throws Exception {
        socket.setTcpNoDelay(true);
        socket.getTcpNoDelay();

    }

}
