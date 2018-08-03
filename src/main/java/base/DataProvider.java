package base;

import java.sql.*;

public class DataProvider {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }catch (Exception ex){

        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn =
                    DriverManager.getConnection("jdbc:mysql://192.168.31.100:3306/z_plan_test?"+
                                                 "user=root&password=HkilDiYoPRwjh");
            if(conn != null){
                System.out.println("数据库连接正常！");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM bbd_testcases ");
                while(rs.next()){
                    System.out.println(rs.getString("id"));
                    System.out.println(rs.getString("servicename"));
                }
            }
            else{
                System.out.println("数据库连接异常");
            }

        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally {
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException sqlEx){}
                rs = null;
            }
            if(stmt !=null){
                try{
                    stmt.close();
                }catch (SQLException sqlEx){}
                stmt = null;
            }
        }
    }
}
