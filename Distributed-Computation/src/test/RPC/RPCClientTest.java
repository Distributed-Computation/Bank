package test.RPC;


import test.Interface.CheckLoginService;

import java.net.InetSocketAddress;
import java.util.Scanner;

//启动完RPCClientServer之后启动
public class RPCClientTest {
    public static void main(String[] args) throws Exception {
        int loginkey=0;
        String name=null;
        String psw=null;
        Scanner in=new Scanner(System.in);
        
        while(loginkey==0){
            System.out.println("输入用户名:");
            name=in.nextLine();
            System.out.println("输入密码");
            psw=in.nextLine();
            System.out.println("输入结束");
            System.out.println("验证中.....");
            
            //把上面输入注释，把下面注释删除，偷懒0V0
            /*name="gcw";
            psw="gcw";*/
            
            //反射
            CheckLoginService service= Client.getRemoteProxyObj(
                    Class.forName("test.Interface.CheckLoginService") ,
                    new InetSocketAddress("127.0.0.1", 9999)) ;
            loginkey=service.check_login(name,psw) ;
            if(loginkey==0) System.out.println("登入失败");
        }
        //in.close();
        if(loginkey==1) {
            System.out.println("登入成功");
            ClientOperation operation=new ClientOperation();
            operation.operation(name);
        }
        if(loginkey==-1) System.out.println("已被冻结");
    }
}