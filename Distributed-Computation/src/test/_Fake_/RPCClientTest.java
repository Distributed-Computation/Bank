package test._Fake_;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        int loginkey=0;
        Scanner in=new Scanner(System.in);

        InputStream inStream = RPCClientTest.class.getResourceAsStream("config.properties");

        BeanFactory<ArrayList> beanFactory = new BeanFactory<ArrayList>(inStream);
        List list = beanFactory.getBean("ArrayList");
        
        list.add("gcw");
        list.add("gcw");
        
        /*while(loginkey==0){
            System.out.println("输入用户名:");
            String name=in.nextLine();
            System.out.println("输入密码");
            String psw=in.nextLine();
            System.out.println("输入结束");
            System.out.println("验证中.....");
            
            CheckLoginService service= Client.getRemoteProxyObj(
                    Class.forName("test.Interface.CheckLoginService") ,
                    new InetSocketAddress("127.0.0.1", 9999)) ;
            loginkey=service.check_login(name,psw) ;
            if(loginkey==0) System.out.println("登入失败");
        }
        in.close();
        if(loginkey==1) {
            System.out.println("登入成功");

        }
        if(loginkey==-1) System.out.println("已被冻结");*/
    }
}
