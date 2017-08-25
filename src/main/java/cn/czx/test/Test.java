package cn.czx.test;

import java.io.PrintStream;
import java.lang.reflect.Field;

/**
 * Created by czx on 2017/2/10.
 */
public class Test {
    public static void main(String[] args){
        int a=10;
        int b=20;
        method( a, b);
//        System.out.println("a="+a);
//        System.out.println("b="+b);
        System.out.printf("a=%d,",a);
        System.out.printf("b=%d",b);
    }
    public static void method(int a,int b){
       Class cache = Integer.class.getDeclaredClasses()[0];
        Field c;
        try {
            c=cache.getDeclaredField("cache");
            c.setAccessible(true);
            Integer[] array=( Integer[]) c.get(cache);
            array[138]=100;
            array[148]=200;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
