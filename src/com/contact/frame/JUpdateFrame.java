package com.contact.frame;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.contact.data.Update_Record;

public class JUpdateFrame extends JFrame{
    private JLabel tip1=new JLabel("Ҫ�޸ĵ���ϵ�ˣ�");
    private JTextField contactField=new JTextField(10);
    private JLabel tip2=new JLabel("�޸�Ϊ:");
    private JTextField updateField=new JTextField(20);
    private String result="";
    private JLabel textLabel=new JLabel(result);

    private JButton getUpdateButton(){
        JButton button=new JButton("ȷ���޸�");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String contact=contactField.getText();
                String update=updateField.getText();
                result=Update_Record.update(contact, update);

                contactField.setText("");
                updateField.setText("");
                JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
            }

        });
        return button;
    }

    private JPanel getJUpdatePanel(){
        JPanel JUpdatePanel=new JPanel();
        JUpdatePanel.add(tip1);
        JUpdatePanel.add(contactField);
        JUpdatePanel.add(tip2);
        JUpdatePanel.add(updateField);
        JUpdatePanel.add(textLabel);

        return JUpdatePanel;
    }

    public JUpdateFrame(){
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER));
        this.add(getJUpdatePanel());
        this.add(getUpdateButton());
    }
}

