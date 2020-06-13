package com.contact.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class JToolCollect extends JToolBar {

    private JButton jbAdd = null;
    private JButton jbQuery = null;

    public JToolCollect() {
        jbAdd = new AddButton(Constant.TEXT_ADD, Constant.ICON_ADD);
        jbQuery = new QueryButton(Constant.TEXT_QUERY, Constant.ICON_QUERY);
        this.add(jbAdd);
        this.add(jbQuery);

        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 3));
        iconPanel.add(new JLabel(new ImageIcon(Constant.ICON_LOGO)));
        this.add(iconPanel);
    }

    class ToolButton extends JButton {

        private static final long serialVersionUID = 1L;

        public ToolButton(String tiptext, String imagepath) {
            this.setToolTipText(tiptext);// 设置提示文本
            this.setIcon(new ImageIcon(imagepath));// 设置图片
            this.setHideActionText(true);
        }
    }

    class AddButton extends ToolButton {
        private static final long serialVersionUID = 1L;

        public AddButton(String tiptext, String imagepath) {
            super(tiptext, imagepath);
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }

    class QueryButton extends ToolButton {
        private static final long serialVersionUID = 1L;

        public QueryButton(String tiptext, String imagepath) {
            super(tiptext, imagepath);
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }
}

