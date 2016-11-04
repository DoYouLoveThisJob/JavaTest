package cn.czx.test.forDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0003 on 2016/6/22.
 */
public class MyFor {
    static public  class Test{
        private int data;
        private int index;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
    public static void main(String[] age){
        List<Test> testList=new ArrayList<Test>();
        Test test=new Test();
        test.setData(1);
        test.setIndex(1);
        testList.add(test);
        for(Test i:testList){
            i.setData(0);
            i.setIndex(0);
        }
        System.out.println("testList index:"+testList.get(0).getIndex()+" data:"+testList.get(0).getData());


        List<Integer> numList=new ArrayList<Integer>();
        numList.add(1);
        for(Integer i:numList){
           i=0;
        }
        System.out.println("numList data:"+numList.get(0));
        int[] ints=new int[]{1};
        for(int i:ints){
            i=0;
        }
        System.out.println("ints data:"+ints[0]);


    }
}
