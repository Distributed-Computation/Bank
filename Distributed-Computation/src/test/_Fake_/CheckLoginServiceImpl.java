package test._Fake_;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static test.DB.BaseDao.getConnection;

public class CheckLoginServiceImpl implements CheckLoginService {
    @Override
    //检查登入
    public void check_login(Object target, Method method, Object[] args) {
        /*String s = "";
        for (int i =0;i<args.length;i++){
            s+=args[i];
        }
        System.out.println(s);*/
        //String name=s[0],password=s[1];
        //System.out.println(name+password);
        /*System.out.println(target.getClass().getSimpleName() +
                "." + method.getName() + "方法被调用，参数值：" + Arrays.toString(args));*/
    }
    /*public int check_login(String name,String password) throws Exception {
        CheckLoginServiceImpl checkLoginService=new CheckLoginServiceImpl();
        InvocationHandler handler=new ProxyHandler(new CheckLoginServiceImpl());
        CheckLoginService i=(CheckLoginService) Proxy.newProxyInstance(CheckLoginServiceImpl.class.getClassLoader(), CheckLoginServiceImpl.class.getInterfaces(), handler);
        
        String sql="select * from infor where name=? and password=?";
        try (Connection conn=getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs1 = stmt.executeQuery();
            int fail=i.check_fail(name);
            if(fail>=3){ return -1; }
            if(rs1.next()&&fail<3) {
                checkLoginService.clear_fail(name);
                return 1;
            }
            else{
                checkLoginService.addition_fail(name,fail);
                return 0;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }*/
   

    @Override
    //查看错误次数
    public int check_fail(String name) throws Exception {
        int fail = 0;
        String sql="select failure from infor where name=? ";
        try (Connection conn=getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                fail=rs.getInt("failure");
            }
            //BaseDao.closeAll(conn, stmt, rs);
           return fail;
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    //登入成功，清空错误次数
    public void clear_fail(String name) throws Exception {
        String sql="UPDATE infor set failure=0 WHERE name=?";
        try (Connection conn=getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            return;
        }catch (SQLException e){
            e.printStackTrace();
            return ;
        }
    }
    @Override
    //错误次数+1
    public void addition_fail(String name,int fail) throws Exception {
        String sql="UPDATE infor set failure=? WHERE name=?";
        try (Connection conn=getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setInt(1, fail+1);
            stmt.setString(2, name);
            stmt.executeUpdate();
            return;
        }catch (SQLException e){
            e.printStackTrace();
            return ;
        }
    }


}
