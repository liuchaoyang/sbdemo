package com.example.sbdemo.db.mysql;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;

public class MysqlConnector {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            PrintWriter printWriter = new PrintWriter("error2.txt");
            DriverManager.setLogWriter(printWriter);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itskls?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8", "fabu", "");
            String sql = "INSERT INTO `luck_draw_app_mapping` (appid, draw_id, creator) " +
                    "VALUES ('4', '4', '4'),('5', '5', '5'); INSERT INTO `luck_draw_app_mapping` (appid, draw_id, creator) VALUES ('6', '6', '6');";
            Statement statement = connection.createStatement();
            int update = statement.executeUpdate(sql);
            System.out.println(update);
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from luck_draw_app_mapping where appid = ?", new String[] {"appid"});
//            preparedStatement.setString(1, "5");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                System.out.println(id);
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
