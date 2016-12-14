package cn.czx.test.primitive_type;

/**
 * Created by czx on 2016/12/12.
 */
public class primitiveTypeTest {
    public static void main(String[] args){
        //0x
        int sixteen=0xCAFE;//十六进制
        int eight=010;//8进制
        byte  two=0b11;//jdk1.7才支持
        int underline=1_000_000;
        String twoStr= Integer.toBinaryString(8);
        System.out.println(eight);
        System.out.println(two);
        System.out.println(twoStr);
        System.out.println(underline);
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


    }
}
