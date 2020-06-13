package com.contact.data;

import java.sql.Connection;//链接数据库
import java.sql.DriverManager;//管理数据库驱动的
import java.sql.ResultSet;//查询满足结果的记录集
import java.sql.Statement;//数据库语句，用来执行sql语句

public class DbQuery {

    public static String query(String keyword) {
        String sql = "select * from contact where contactor like '%" + keyword + "%'";
        String output = "查询结果为：";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
            String url = "jdbc:Access:///c:/contact_db.accdb";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                output = output + "\n" + "  姓名：" + rs.getString(2) + "  年龄：" + rs.getString(3)
                        + "  联系方式：" + rs.getString(4) + "  邮箱：" + rs.getString(5) + "  住址：" + rs.getString(6) + "  关系："
                        + rs.getString(7);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return output;
    }

}