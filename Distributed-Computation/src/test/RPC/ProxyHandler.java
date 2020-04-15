package test.RPC;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    Object obj;
    
    public ProxyHandler(Object v){this.obj=v;}
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret=method.invoke(obj,args);//obj是要代理的真实目标对象
        return ret;
    }

}
