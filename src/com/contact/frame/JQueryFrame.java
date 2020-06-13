package com.contact.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.contact.data.DbQuery;

public class JQueryFrame extends JFrame{

    private JLabel tip=new JLabel("输入要查询的联系人");
    private JTextField keywordField=new JTextField(12);
    private JTextArea resultArea=new JTextArea();

    private JButton getQueryButton() {
        JButton button=new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword=keywordField.getText();
                String result=DbQuery.query(keyword);
                keywordField.setText("");
                resultArea.setText(result);
            }
        });
        return button;
    }

    public JQueryFrame(){
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        this.add(getqueryPanel());
    }

    private JPanel getqueryPanel(){
        JPanel queryPanel=new JPanel();

        queryPanel.add(tip);
        queryPanel.add(keywordField);
        queryPanel.add(getQueryButton());
        queryPanel.add(resultArea,BorderLayout.SOUTH);

        return queryPanel;
    }

}
