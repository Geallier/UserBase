package com.contact.frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.contact.data.*;

public class JDeleteFrame extends JFrame{
    private JTextField keywordField=new JTextField(20);
    private JLabel tip=new JLabel("要删除的联系人");
    private JTextArea resultArea=new JTextArea();

    private JButton getDeleteButton(){
        JButton button=new JButton("删除");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String keyword=keywordField.getText();
                String result="";
                String result1="";
                keywordField.setText("");
                if(DbQuery.query(keyword)!=result1){
                    result=Delete_Record.delete(keyword);
                    resultArea.setText(result);
                }
                else
                    JOptionPane.showMessageDialog(null, "联系人不存在！");
            }

        });
        return button;
    }

    public JDeleteFrame(){
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        this.add(getDeletePanel());
    }

    private JPanel getDeletePanel() {
        JPanel deletePanel=new JPanel();

        deletePanel.add(tip);
        deletePanel.add(keywordField);
        deletePanel.add(getDeleteButton());
        deletePanel.add(resultArea);

        return deletePanel;
    }

}

