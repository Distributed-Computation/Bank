package test._Fake_;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean {
    private Object target;
    private CheckLoginService advice;
    public Object getTarget() { return target; }
    public void setTarget(Object target) { this.target = target; }
    public CheckLoginService getAdvice() { return advice; }
    public void setAdvice(CheckLoginService advice) { this.advice = advice; }
    public Object getProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        Object retVal = null;
                        try {
                            advice.check_login(target, method, args);
                            retVal = method.invoke(target, args);
                            //advice.doAfter(target, method, args, retVal);
                        } catch (Exception e) {
                            //advice.doThrow(target, method, args, e);
                        } finally {
                            //advice.doFinally(target, method, args);
                        }
                        return retVal;
                    }}
        );
    }
}
