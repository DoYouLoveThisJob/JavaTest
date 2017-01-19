package cn.czx.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by czx on 2016/12/29.
 */
public class forClass {
    public static void main(String[] args){
        //一个循环对应一个标签
        break_sign:
        for(int i=0;i<100;i++){
             if(i==1){
                 System.out.println(i);
                break break_sign;
             }
        }
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String x = it.next();
            for(int i=0;i<list.size();i++){
            if(x.equals("a")){
                it.remove();
            }
            }
        }


        Iterator<String> it3 = list.iterator();
        while(it3.hasNext()){
            String x = it.next();
                if(x.equals("a")){
                    it3.remove();
                }
        }
        Iterator<String> it2 = list.iterator();
        while(it2.hasNext()){

            String x = it2.next();
            System.out.print(x);
        }
    }
}
