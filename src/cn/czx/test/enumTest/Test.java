package cn.czx.test.enumTest;

/**
 * Created by 0003 on 2016/6/12.
 */
public class Test {
    public static void main(String[] args){
        for(EnumTest e:EnumTest.values()){
            System.out.println(e.toString());
        }
        EnumTest test = EnumTest.TUE;
        switch (test) {
            case MON:
                System.out.println("今天是星期一");
                break;
            case TUE:
                System.out.println("今天是星期二");
                break;
            // ... ...
            default:
                System.out.println(test);
                break;
        }

    }

}
