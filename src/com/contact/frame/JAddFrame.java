package com.contact.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.contact.data.Insert_Record;

public class JAddFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // 定义窗口属性
    private JTextField nameTextField = null;// 姓名文本域
    private JTextField phoneTextField = null;// 手机号文本域
    private JTextField emailTextField = null;// email文本域
    private JTextField addressTextField = null;// 地址文本域

    private JRadioButton maleButton = null; // 性别单选按钮
    private JRadioButton femaleButton = null;

    private JComboBox relationshipBox = null;// 关系下拉框

    private JTextArea ab = null;// 增加一个新的区域

    public JAddFrame() {
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        this.add(getAddPanel2());
        this.add(getAddPanel3());
        this.add(getAddPanel4());
        this.add(getAddPanel5());
        this.add(getAddPanel6());
    }

    private JPanel getAddPanel2() {
        JPanel addPanel2 = new JPanel();// 创建新建联系人第二个面板

        addPanel2.add(new JLabel(" 姓名：   "));// 将组件添加到面板中
        nameTextField = new JTextField(30);
        addPanel2.add(nameTextField);

        return addPanel2;
    }

    private JPanel getAddPanel3() {
        JPanel addPanel2 = new JPanel();// 创建新建联系人第二个面板

        addPanel2.add(new JLabel("手机号："));
        phoneTextField = new JTextField(30);
        addPanel2.add(phoneTextField);

        return addPanel2;
    }

    private JPanel getAddPanel4() {
        JPanel addPanel3 = new JPanel();// 创建新建联系人第三个面板

        addPanel3.add(new JLabel("email：  "));// 将组件添加到面板中
        emailTextField = new JTextField(30);
        addPanel3.add(emailTextField);

        return addPanel3;
    }

    private JPanel getAddPanel5() {
        JPanel addPanel3 = new JPanel();// 创建新建联系人第三个面板

        addPanel3.add(new JLabel(" 地址：   "));
        addressTextField = new JTextField(30);
        addPanel3.add(addressTextField);

        return addPanel3;
    }

    private JPanel getAddPanel6() {
        JPanel addPanel4 = new JPanel();// 创建新建联系人第四个面板

        addPanel4.add(new JLabel("性别："));// 将组件添加到面板中
        maleButton = new JRadioButton("男");
        femaleButton = new JRadioButton("女");
        ButtonGroup buttonGroup = new ButtonGroup();// 将性别按钮添加到ButtonGroup中
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        maleButton.setSelected(true);
        addPanel4.add(maleButton);
        addPanel4.add(femaleButton);

        addPanel4.add(new JLabel("关系："));
        String[] relationship = { "同事", "同学", "亲戚", "朋友" };
        relationshipBox = new JComboBox(relationship);
        addPanel4.add(relationshipBox);

        addPanel4.add(getAddButton());// 添加新增联系人按钮
        return addPanel4;
    }

    private String switchButton() {

        boolean isSelect = maleButton.isSelected();
        if (isSelect == true)
            return "男";
        else
            return "女";
    }

    private JButton getAddButton() {

        JButton button = new JButton("新建联系人");
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

