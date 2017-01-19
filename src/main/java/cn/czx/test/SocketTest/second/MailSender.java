package cn.czx.test.SocketTest.second;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by czx on 2017/1/18.
 */
public class MailSender {
    private String smtpServer="smtp.mydomain.com";
    private int port=25;
    public static void main(String[] args){

    }

    public void sendMail(Message message) throws IOException {
        Socket socket=null;
        socket=new Socket(smtpServer,port);
    }
    class Message{
        String from;
        String to;
        String  subject;
        String content;
        String data;
        public Message(String from,String to, String subject,String content){
            this.from=from;
            this.to=to;
            this.subject=subject;
            this.content=content;
            this.data="Subject:"+subject+"\r\n"+content;

        }
    }

}
