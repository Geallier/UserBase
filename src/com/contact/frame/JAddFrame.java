package com.contact.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.contact.data.Insert_Record;

public class JAddFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // ���崰������
    private JTextField nameTextField = null;// �����ı���
    private JTextField phoneTextField = null;// �ֻ����ı���
    private JTextField emailTextField = null;// email�ı���
    private JTextField addressTextField = null;// ��ַ�ı���

    private JRadioButton maleButton = null; // �Ա�ѡ��ť
    private JRadioButton femaleButton = null;

    private JComboBox relationshipBox = null;// ��ϵ������

    private JTextArea ab = null;// ����һ���µ�����

    public JAddFrame() {
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        this.add(getAddPanel2());
        this.add(getAddPanel3());
        this.add(getAddPanel4());
        this.add(getAddPanel5());
        this.add(getAddPanel6());
    }

    private JPanel getAddPanel2() {
        JPanel addPanel2 = new JPanel();// �����½���ϵ�˵ڶ������

        addPanel2.add(new JLabel(" ������   "));// �������ӵ������
        nameTextField = new JTextField(30);
        addPanel2.add(nameTextField);

        return addPanel2;
    }

    private JPanel getAddPanel3() {
        JPanel addPanel2 = new JPanel();// �����½���ϵ�˵ڶ������

        addPanel2.add(new JLabel("�ֻ��ţ�"));
        phoneTextField = new JTextField(30);
        addPanel2.add(phoneTextField);

        return addPanel2;
    }

    private JPanel getAddPanel4() {
        JPanel addPanel3 = new JPanel();// �����½���ϵ�˵��������

        addPanel3.add(new JLabel("email��  "));// �������ӵ������
        emailTextField = new JTextField(30);
        addPanel3.add(emailTextField);

        return addPanel3;
    }

    private JPanel getAddPanel5() {
        JPanel addPanel3 = new JPanel();// �����½���ϵ�˵��������

        addPanel3.add(new JLabel(" ��ַ��   "));
        addressTextField = new JTextField(30);
        addPanel3.add(addressTextField);

        return addPanel3;
    }

    private JPanel getAddPanel6() {
        JPanel addPanel4 = new JPanel();// �����½���ϵ�˵��ĸ����

        addPanel4.add(new JLabel("�Ա�"));// �������ӵ������
        maleButton = new JRadioButton("��");
        femaleButton = new JRadioButton("Ů");
        ButtonGroup buttonGroup = new ButtonGroup();// ���Ա�ť��ӵ�ButtonGroup��
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        maleButton.setSelected(true);
        addPanel4.add(maleButton);
        addPanel4.add(femaleButton);

        addPanel4.add(new JLabel("��ϵ��"));
        String[] relationship = { "ͬ��", "ͬѧ", "����", "����" };
        relationshipBox = new JComboBox(relationship);
        addPanel4.add(relationshipBox);

        addPanel4.add(getAddButton());// ���������ϵ�˰�ť
        return addPanel4;
    }

    private String switchButton() {

        boolean isSelect = maleButton.isSelected();
        if (isSelect == true)
            return "��";
        else
            return "Ů";
    }

    private JButton getAddButton() {

        JButton button = new JButton("�½���ϵ��");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameTextField.getText();
                String phone = phoneTextField.getText();
                String email = emailTextField.getText();
                String address = addressTextField.getText();
                String sex = switchButton();
                String relation = relationshipBox.getSelectedItem().toString();

                nameTextField.setText("");
                phoneTextField.setText("");
                emailTextField.setText("");
                addressTextField.setText("");

                Insert_Record
                        .insert(name, phone, email, address, sex, relation);
            }
        });
        return button;
    }
}

