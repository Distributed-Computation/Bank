package test._Fake_;

import java.lang.reflect.Method;

public interface CheckLoginService {
    public void check_login(Object target, Method method, Object[] args) ;//检查登入
    //int check_login(String name, String password) throws Exception;//检查登入
    int check_fail(String name) throws Exception;//查看错误次数
    void clear_fail(String name) throws Exception;//登入成功，清空错误次数
    void addition_fail(String name, int fail) throws Exception;//错误次数+1
    
}
