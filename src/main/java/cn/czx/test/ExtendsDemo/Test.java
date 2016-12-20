package cn.czx.test.ExtendsDemo;

/**
 * Created by 0003 on 2016/8/1.
 */
public class Test {
    //因为存储类型跟数组存储不同，引发ArrayStoreException异常.
    public static void   main(String[] args){
        Manager[] managers=new Manager[10];
        Employee[] staff=managers;
        staff[0]=new Employee();
        System.out.println(staff[0].getAge());
    }
    public  static void testFunction(String a,Object ... objects){
     System.out.println("the first parameter a is "+a);
        for(Object obj:objects){
         System.out.println("the next parameter is"+obj);
        }

    }

}
