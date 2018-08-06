package base;

import java.sql.*;

public class DataProvider {
    public static Connection connData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.31.100:3306/z_plan_test?" +
                    "user=root&password=HkilDiYoPRwjh");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        return conn;
    }
    public static String getParameters(String method, String testType) {
        Connection conn = connData();
        Statement stmt = null;
        ResultSet rs = null;
        String r = null;
        try {
            if (conn != null) {
                System.out.println("数据库连接正常！");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM bbd_testcases WHERE Method = "+"'"+method+"'"+"AND testType = "+"'"+testType+"'");

                while (rs.next()) {
                    r = rs.getString("Parameters");
                    System.out.println(r);
                }
            } else {
                System.out.println("数据库连接异常");
            }

        } catch (SQLException ex) {
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
        return r;
    }
    public static void main(String[] args) {
        getParameters("archiveQueryCharts", "important");
    }
}
