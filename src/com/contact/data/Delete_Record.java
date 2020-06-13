package com.contact.data;

import java.sql.*;

public class Delete_Record {
    public static String delete(String sname) {
        String Jdriver = "com.hxtt.sql.access.AccessDriver";
        String conURL = "jdbc:Access:///c:/contact_db.accdb";
        String output = "删除过后的表为：";
        try {
            Class.forName(Jdriver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("forname:" + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(conURL);
            Statement s = con.createStatement();
            // 删除第二条记录
            PreparedStatement ps = con.prepareStatement("delete from contact where contactor=?");
            ps.setString(1, sname);
            ps.executeUpdate();
            ps.close();
            // 查询数据库并把数据表的内容输出到屏幕上
            ResultSet rs = s.executeQuery("select * from contact");
            while (rs.next()) {
                output = output + "\n" + "序号：" + rs.getString(1) + "  姓名：" + rs.getString(2) + "  手机号："
                        + rs.getString(3) + "  邮箱：" + rs.getString(4) + "  地址：" + rs.getString(5) + "  性别："
                        + rs.getString(6) + "  关系：" + rs.getString(7);

            }
            s.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + e.getMessage());
        }
        return output;
    }
}

