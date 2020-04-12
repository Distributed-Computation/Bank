package test.RPC_example;

import java.net.InetSocketAddress;


public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        HelloService service = Client.getRemoteProxyObj(
                Class.forName("test.RPC_example.HelloService") , 
                new InetSocketAddress("127.0.0.1", 9999)) ;
        System.out.println( service.sayHi("zs")  ) ;

        HelloService service2 = Client.getRemoteProxyObj(
                Class.forName("test.RPC_example.HelloService") ,
                new InetSocketAddress("127.0.0.1", 9999)) ;
        System.out.println( service2.sayHi("eeee")  ) ;
        
        ProductService productService = Client.getRemoteProxyObj(
                Class.forName("test.RPC_example.ProductService") ,
                new InetSocketAddress("127.0.0.1", 9999)) ;
        System.out.println( productService.sayHi("ww")  ) ;
    }
}
