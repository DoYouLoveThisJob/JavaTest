package cn.czx.test.myextends;

/**
 * Created by 0003 on 2016/6/24.
 */
public class OtherChild extends Parent{

    public  static  void main(String[] args){
        Parent c1=new ChildOne();
        c1.run();
        Parent c2=new OtherChild();
        c2.run();
    }

}
