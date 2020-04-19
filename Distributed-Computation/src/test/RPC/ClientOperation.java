package test.RPC;

import test.Interface.UserService;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientOperation {
    public void operation(String username) throws Exception{
        UserService service= Client.getRemoteProxyObj(
                Class.forName("test.Interface.UserService") ,
                new InetSocketAddress("127.0.0.1", 9999)) ;
        String opt = "";//宁的操作
        double num;
        double result;
        while(!opt.equals("退出")){
            Scanner in=new Scanner(System.in);
            System.out.println("输入操作： 查询 | 取钱 | 存钱 | 退出");
            opt=in.nextLine();
            //给阿姨倒一杯卡布奇诺
            switch (opt){
                case "查询":
                    //开始你的炸弹秀
                    break;
                case "取钱":
                    System.out.println("输入取款金额：");
                    num = in.nextDouble();
                    result = service.withdraw(username,num);
                    if(result==-1) System.out.println("取钱超过数额！");
                    else System.out.println("剩余："+result+"元");
                    break;
                case "存钱":
                    //炸他炸他
                    System.out.println("输入存款金额：");
                    num = in.nextDouble();
                    result = service.save(username,num);
                    if(result==-1) System.out.println("存款金额应为非负数！");
                    else System.out.println("剩余："+result+"元");
                    break;
                case "退出":
                    System.out.println("退出成功...");
                    break;
                default:
                    System.out.println("输入错误！");
                    break;    
            }
        }
    }
}
