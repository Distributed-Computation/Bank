package test.RPC;

import test.Interface.CheckLoginService;
import test.Interface.CheckLoginServiceImpl;

public class RPCServerTest {
    public static void main(String[] args) {
        //开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //服务中心
                Server server = new ServerCenter(9999);
                //将HelloService接口及实现类 注册到 服务中心
                server.register(CheckLoginService.class, CheckLoginServiceImpl.class);
                server.start();
            }
        }).start();//start()
    }
}
