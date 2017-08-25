package cn.czx.test.SocketTest.third;

import java.util.LinkedList;

/**
 * Created by czx on 2017/2/3.
 */
public class ThreadPool extends  ThreadGroup{
    private boolean isClosed=false;//线程池是否关闭
    private LinkedList<Runnable> workQueue;//表示工作队列
    private static int threadPoolID;//表示线程池ID
    private int threadID; //表示工作线程ID
    public ThreadPool(int poolSize){//poolSize指定线程池中的工作线程数目
        super("ThreadPool-"+(threadPoolID++));
        setDaemon(true);
        workQueue=new LinkedList<Runnable>();
        for(int i=0;i<poolSize;i++){
           new WorkThread().start();//创建并启动工作线程
        }
    }
    /**向工作队列中加入一个新任务，由工作线程去执行任务*/
    public synchronized  void execute(Runnable task){
     if(isClosed){//线程池被关闭则抛出IllegalStateException异常
       throw new IllegalStateException();
     }
     if(task!=null){
         workQueue.add(task);
         notify();//唤醒正在getTask（）方法中等待任务的工作线程
     }
    }
    /**从工作队列中取出一个任务，工作线程会调用此方法*/
    protected synchronized  Runnable getTask() throws InterruptedException {
        while (workQueue.size()==0){
          if(isClosed) return null;
            wait();
        }
        return workQueue.removeFirst();
    }
    /**关闭线程池*/
    public synchronized  void close(){
        if(!isClosed){
           isClosed=true;
            workQueue.clear();
            interrupt();
        }
    }
    /**等待工作线程把所有任务执行完*/
    public void join(){
        synchronized (this){
            isClosed = true;
            notifyAll();//唤醒还在getTask（）方法中等待任务的工作线程
        }
        Thread[] threads = new Thread[activeCount()];
        int count =enumerate(threads);
        for(int i=0;i<count;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    /**内部类：工作线程*/
    private class WorkThread extends Thread{
        public WorkThread(){
            //加入到当前ThreadPool线程组中
            super(ThreadPool.this,"WorkThead-"+(threadID++));
        }
        public void run(){
            while(!isInterrupted()){//isInterrupted()方法继承自Thread类，判断线程是否被中断
                Runnable task = null;
                try{
                    task = getTask();
                }catch (InterruptedException ex){

                }
                //如果getTask()返回null或者线程执行getTask()时被中断，则结束此线程
                if(task == null) return ;
                try{
                   task.run();
                }catch (Throwable t){
                    t.printStackTrace();
                }

            }
        }

    }
}
