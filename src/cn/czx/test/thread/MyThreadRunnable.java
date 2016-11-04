package cn.czx.test.thread;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * Created by 0003 on 2016/6/8.
 */
public class MyThreadRunnable implements  Runnable {
    private int  count=15;
    @Override
    public void run(){
       for(int i=0;i<5;i++){
           System.out.println(Thread.currentThread().getName()+"运行 count="+count-- );
       }

      try {
           Thread.sleep((int) Math.random()*10);
      } catch (InterruptedException e) {
           e.printStackTrace();
      }
    }
    public static void main(String[] args){
        MyThreadRunnable myThreadRunnable=new MyThreadRunnable();
        new Thread(myThreadRunnable,"A").start();
        new Thread(myThreadRunnable,"B").start();
        new Thread(myThreadRunnable,"C").start();
    }
}
