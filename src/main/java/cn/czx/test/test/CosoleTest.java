package cn.czx.test.test;

import java.io.Console;

/**
 * Created by czx on 2016/12/22.
 */
public class CosoleTest {

    public static void main(String[] args){
        Console cons=System.console();
        String username=cons.readLine("User name: ");
        char[] passwd =cons.readPassword("Password: ");
        //System.out.println(username);
        //System.out.println(passwd.toString());

    }
}
