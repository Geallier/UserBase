package com.contact.data;

import java.sql.Connection;//�������ݿ�
import java.sql.DriverManager;//�������ݿ�������
import java.sql.ResultSet;//��ѯ�������ļ�¼��
import java.sql.Statement;//���ݿ���䣬����ִ��sql���

public class DbQuery {

    public static String query(String keyword) {
        String sql = "select * from contact where contactor like '%" + keyword + "%'";
        String output = "��ѯ���Ϊ��";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
            String url = "jdbc:Access:///c:/contact_db.accdb";
            Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                output = output + "\n" + "  ������" + rs.getString(2) + "  ���䣺" + rs.getString(3)
                        + "  ��ϵ��ʽ��" + rs.getString(4) + "  ���䣺" + rs.getString(5) + "  סַ��" + rs.getString(6) + "  ��ϵ��"
                        + rs.getString(7);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return output;
    }

}