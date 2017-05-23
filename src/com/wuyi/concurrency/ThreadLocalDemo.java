package com.wuyi.concurrency;

import java.sql.*;

/**
 * Created by LENOVO on 2017/5/18.
 */
public class ThreadLocalDemo {
    private final static String driverName="oracle.jdbc.driver.OracleDriver";
    private final static String url="jdbc:oracle:thin:@localhost:1521:orcl";
    private final static String username="WUYI";
    private final static String password="Wuyi1482675166";

    private static ThreadLocal<Connection> connectionHolder=new ThreadLocal<Connection>(){
        public Connection initialValue() {
            try {
                return DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };
    public static Connection getConnectin(){
        return connectionHolder.get();

    }
    public static void main(String[] args) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("USERID")+" "+resultSet.getString("username"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
