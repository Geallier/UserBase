package com.contact.data;

import java.sql.*;

public class Delete_Record {
    public static String delete(String sname) {
        String Jdriver = "com.hxtt.sql.access.AccessDriver";
        String conURL = "jdbc:Access:///c:/contact_db.accdb";
        String output = "ɾ������ı�Ϊ��";
        try {
            Class.forName(Jdriver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("forname:" + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(conURL);
            Statement s = con.createStatement();
            // ɾ���ڶ�����¼
            PreparedStatement ps = con.prepareStatement("delete from contact where contactor=?");
            ps.setString(1, sname);
            ps.executeUpdate();
            ps.close();
            // ��ѯ���ݿⲢ�����ݱ�������������Ļ��
            ResultSet rs = s.executeQuery("select * from contact");
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

