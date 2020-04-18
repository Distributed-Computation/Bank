package test.Interface;

import test.Log4J.vincent_player_framt;
import test.RPC.ProxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static test.DB.BaseDao.getConnection;

public class UserServiceImpl implements UserService {
    
    //取钱
    @Override
    public double withdraw(String name, double num) throws Exception {
        UserServiceImpl userService=new UserServiceImpl();
        InvocationHandler handler=new ProxyHandler(new UserServiceImpl());
        UserService i=(UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), UserServiceImpl.class.getInterfaces(), handler);
        String sql="select money from infor where name=? ";
        try (Connection conn=getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs1 = stmt.executeQuery();
            double FindMoney=0.0;
            double remain;
            while (rs1.next()){
                FindMoney=rs1.getDouble("money");
            }
            if(num<=FindMoney) {//取钱合法
                remain=FindMoney-num;
                i.update(name,remain);
                System.out.println("该用户取钱合法，取钱"+num+"元，剩余"+remain+"元");
                vincent_player_framt vincentPlayerFramt=new vincent_player_framt();
                vincentPlayerFramt.inform(name,FindMoney,num);
                return remain;
            }
            else{//取钱不合法
                System.out.println("该用户取钱不合法");
                return -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    //更新余款
    @Override
    public boolean update(String name, double money) throws Exception {
        String sql="update infor set money=? where name=? ";
        try (Connection conn=getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setDouble(1,money);
            stmt.setString(2,name);
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
