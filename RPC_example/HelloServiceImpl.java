package test.RPC_example;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String name) {
        return "hi,"+name ;
    }

}
