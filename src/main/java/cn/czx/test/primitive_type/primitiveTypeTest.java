package cn.czx.test.primitive_type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czx on 2016/12/12.
 */
public class primitiveTypeTest {
    //public static void test(String\u005B\u005D args){}无法这样表示
    public static void main(String[] args){
        //0x
        int sixteen=0xCAFE;//十六进制
        int sixteen2=0xffff;//十六进制
        int eight=010;//8进制
        byte  two1=0b11;//jdk1.7才支持
        int underline=1_000_000;
        String twoStr= Integer.toBinaryString(8);
        System.out.println(eight);
        System.out.println(two1);
        System.out.println(twoStr);
        System.out.println(underline);
        System.out.println(sixteen2);
        int i=0; //4字节  1B（byte，字节）= 8 bit
        short s=0;//2字节
        long l=1L;//8字节
        byte b1= 1;//1字节
        double d=0.1;
        float  f=0.1f;
        boolean b=true;
        char c='c';
        /*
        * 在jdk5.0中,可以使用十六进制表示浮点数值。例如，0.125可以表示成0x1.0p-3。在十六进制表示法中，使用p表示指数，而不是e.注意，尾数采用十六进制，指数采用十进制。指数的基数是2，而不是10.
        * */
//        Float.POSITIVE_INFINITY,
//        Float.NEGATIVE_INFINITY,
//        Float.NaN,
//        Double.NaN,
//        Double.POSITIVE_INFINITY,
//        Double.NEGATIVE_INFINITY;

        if(Double.isNaN(0.1/0)){
            System.out.println("this result is not number");
        }else if(Double.isInfinite(0.1/0)){
            System.out.println("this result is infinity");
        }else {
            System.out.println("this result is finity");
        }
        System.out.println(2.0f-1.1f);//使用单精度没问题
        System.out.println(2.0d-1.1d);//使用双精度有问题
        String[] sixteenStr=new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
        List<String> unicodeStrList=new ArrayList<String>();
        for(String one:sixteenStr){
            for(String two:sixteenStr){
                for(String three:sixteenStr){
                    for(String four:sixteenStr){
                        String number="\\u"+one+two+three+four;
                        unicodeStrList.add(number);
                    }
                }
            }
        }
        System.out.println(unicodeStrList.size());
        System.out.println("\u005B\u005D");

       // Character.isJavaIdentifierPart();
        //Character.isJavaIdentifierStart();
        System.out.println(Character.isJavaIdentifierStart('a'));
        //命名是尽量避免使用$这符号，一般为java编译器或者其他工具生成的名字。
        //可以在一行中声明多个变量，但不提倡这种风格。
        int test1=1/5;
        System.out.println(test1);
        //System.out.println(1/0);
        System.out.println(1f/0);
        System.out.println(1d/0);
        int m=7;
        int n=7;
        int a1=2*++m;
        int b2=2*n++;
        System.out.println("a1:"+a1+" b2:"+b2);
        System.out.println(2<<3);//16
        System.out.println(2>>1);//1
        System.out.println(2>>>1);//1
        System.out.println(-2>>1);//-1
        System.out.println(-2>>>1);//2147483647
        System.out.println(20 >> 2);
        System.out.println(-20 >>> 2);
        System.out.println(Integer.toBinaryString(-20));
        System.out.println(Integer.toBinaryString(-20>>> 2));
        System.out.println(Integer.toBinaryString(-20>> 2));
        //>>>运算符将用0填充高位;>>运算符用符号位填充高位。对移位运算符右侧的参数需要进行模32的运算（除非左边的操作数是long类型，在这种情况下需对右侧操作数模64）。例如，1<<35与1<<3或8是相同的。

    }

    public static strictfp void testFunction(){
        //使用strictfp关键字标记的方法必须使用严格的浮计算来产生理想的结果。

    }
}
