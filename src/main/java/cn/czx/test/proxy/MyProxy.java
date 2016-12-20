package cn.czx.test.proxy;

import java.lang.reflect.*;

/**
 * Created by 0003 on 2016/6/22.
 */
public class MyProxy {
    public interface IHello{
        void sayHello();
    }
    static class Hello implements IHello{
        public void sayHello(){
            System.out.println("hello world");
        }
    }
    static class HWInvocationHandler implements InvocationHandler{
        private Object target;
        public HWInvocationHandler(Object target){
            this.target= target;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("------插入前置通知代码-------------");
            //执行相应的目标方法
            Object rs = method.invoke(target,args);
            System.out.println("------插入后置处理代码-------------");
            return rs;
        }
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(),IHello.class);
        //获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IHello iHello = (IHello) constructor.newInstance(new HWInvocationHandler(new Hello()));
        //通过代理对象调用目标方法
        iHello.sayHello();

        //生成$Proxy0的class文件
       // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello  ihell1 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
                new Class[]{IHello.class},      //一组接口
                new HWInvocationHandler(new Hello())); //自定义的InvocationHandler
        ihell1.sayHello();
    }

}
