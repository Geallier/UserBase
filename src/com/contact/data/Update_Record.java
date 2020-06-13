package com.contact.data;

import java.sql.*;

public class Update_Record {
    public static String update(String sname, String sphone) {
        String Jdriver = "com.hxtt.sql.access.AccessDriver";
        String conURL = "jdbc:Access:///c:/contact_db.accdb";
        String[] name = { sname };
        String[] phone = { sphone };
        String output = "修改过后的表为：";
        try {
            Class.forName(Jdriver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("forname:" + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(conURL);
            // 修改数据库中数据表的内容
            PreparedStatement ps = con.prepareStatement("UPDATE contact set phonenum=? where contactor=?");
            int i = 0, namelen = name.length;
            do {
                ps.setString(1, phone[i]);
                ps.setString(2, name[i]);
                ps.executeUpdate();
                ++i;
            } while (i < namelen);
            ps.close();
            // 查询数据库并把数据表的内容输出到屏幕上
            Statement s = con.createStatement();
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