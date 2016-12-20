package cn.czx.test.primitive_type;

/**
 * Created by czx on 2016/12/20.
 */
public class StringType {
    public static void main(String[] args)
    {
        String greeting="hello中国人名解放军";
        int n=greeting.length();
        int cpCount=greeting.codePointCount(0,greeting.length());

        System.out.println("n:"+n);
        System.out.println("cpCount:"+cpCount);

        StringBuffer sf=new StringBuffer();
        sf.append("abc");
        System.out.println(sf.toString());

        StringBuilder sb=new StringBuilder();
        sb.append("abc");
        System.out.println(sb.toString());
    }
}
