package test._Fake_;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Bean工厂，负责产生代理类或目标类的实例
 */
public class BeanFactory<T> {

    private Properties props = new Properties();

    public BeanFactory(InputStream inStream) {
        try {
            props.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public T getBean(String beanName) throws ClassNotFoundException {
        String className = props.getProperty(beanName);
        Class clazz = Class.forName(className);
        T bean = null;
        try {
            bean = (T)clazz.newInstance();
            if (bean instanceof ProxyFactoryBean) {
                ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
                CheckLoginService advice = (CheckLoginService) Class.forName(props.getProperty(beanName + ".advice")).newInstance();
                Object target = Class.forName(props.getProperty(beanName + ".target")).newInstance();
                proxyFactoryBean.setAdvice(advice);
                proxyFactoryBean.setTarget(target);
                bean = (T)proxyFactoryBean.getProxy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}