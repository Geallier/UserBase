package com.contact.data;

import java.sql.*;

public class Update_Record {
    public static String update(String sname, String sphone) {
        String Jdriver = "com.hxtt.sql.access.AccessDriver";
        String conURL = "jdbc:Access:///c:/contact_db.accdb";
        String[] name = { sname };
        String[] phone = { sphone };
        String output = "�޸Ĺ���ı�Ϊ��";
        try {
            Class.forName(Jdriver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("forname:" + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(conURL);
            // �޸����ݿ������ݱ������
            PreparedStatement ps = con.prepareStatement("UPDATE contact set phonenum=? where contactor=?");
            int i = 0, namelen = name.length;
            do {
                ps.setString(1, phone[i]);
                ps.setString(2, name[i]);
                ps.executeUpdate();
                ++i;
            } while (i < namelen);
            ps.close();
            // ��ѯ���ݿⲢ�����ݱ�������������Ļ��
            Statement s = con.createStatement();
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