package com.contact.data;

import java.sql.*;

public class Insert_Record {

    public static String insert(String sname, String sphone, String semail, String saddress, String sex,
                                String srelation) {
        String JDriver = "com.hxtt.sql.access.AccessDriver";
        String conURL = "jdbc:Access:///c:/contact_db.accdb";
        String output = "���֮���ͨѶ¼Ϊ��";

        try {
            Class.forName(JDriver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("forname:" + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(conURL);
            Statement s = con.createStatement();
            String r1 = "insert into contact values(+ id" + ",'" + sname + "','" + sphone + "','" + semail + "','"
                    + saddress + "','" + sex + "','" + srelation + "')";

            s.executeUpdate(r1);

            String sql = "select * from contact";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                output = output + "\n" + "��ţ�" + rs.getString(1) + "  ������" + rs.getString(2) + "  �ֻ��ţ�"
                        + rs.getString(3) + "  ���䣺" + rs.getString(4) + "  ��ַ��" + rs.getString(5) + "  �Ա�"
                        + rs.getString(6) + "  ��ϵ��" + rs.getString(7);
            }

            s.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + e.getMessage());
        }
        return output;
    }

}
