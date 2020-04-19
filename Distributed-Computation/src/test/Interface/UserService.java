package test.Interface;

public interface UserService {
    double withdraw(String name,double num) throws Exception;//取钱
    double save(String name,double num) throws Exception;
    boolean update(String name,double money) throws Exception;//更新余款
}
