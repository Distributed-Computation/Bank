package test.RPC_example;

public class ProductServiceImpl implements ProductService{
    @Override
    public String sayHi(String name) {
        return "product,"+name ;
    }
}
