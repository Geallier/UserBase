package com.contact.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.contact.frame.JAddFrame;
import com.contact.frame.JDeleteFrame;
import com.contact.frame.JQueryFrame;
import com.contact.frame.JUpdateFrame;

public class JMainMenu extends JMenuBar{


    private static final long serialVersionUID = 1L;

    private JMenuItem addMenu=new JMenuItem(Constant.TEXT_ADD+"A");
    private JMenuItem queryMenu=new JMenuItem(Constant.TEXT_QUERY+"Q");
    private JMenuItem deleteMenu=new JMenuItem(Constant.TEXT_DELETE+"D");
    private JMenuItem updateMenu=new JMenuItem(Constant.TEXT_UPDATE+"U");
    private JMenuItem exitMenu=new JMenuItem(Constant.TEXT_EXIT+"X");

    public JMainMenu(){
        this.add(getOpSubMenu());
    }

    private JMenu getOpSubMenu(){

        JMenu opMenu=new JMenu(Constant.TEXT_OPERATE);

        addMenu.setMnemonic(KeyEvent.VK_A);
        queryMenu.setMnemonic(KeyEvent.VK_Q);
        deleteMenu.setMnemonic(KeyEvent.VK_D);
        updateMenu.setMnemonic(KeyEvent.VK_U);
        exitMenu.setMnemonic(KeyEvent.VK_X);
        //点击 添加 后出现窗口
        addMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JAddFrame add = new JAddFrame();
                add.setVisible(true);
                add.setPreferredSize(new Dimension(500,200));
                add.pack();
            }

        });
        //点击 查找 后出现窗口
        queryMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JQueryFrame query=new JQueryFrame();
                query.setVisible(true);
                query.setPreferredSize(new Dimension(500,200));
                query.pack();
            }
        });
        //点击 删除 后出现窗口
        deleteMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JDeleteFrame delete=new JDeleteFrame();
                delete.setVisible(true);
                delete.setPreferredSize(new Dimension(500,200));
                delete.pack();
            }

        });
        //点击 修改 后出现窗口
        updateMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JUpdateFrame update=new JUpdateFrame();
                update.setVisible(true);
                update.setPreferredSize(new Dimension(500,200));
                update.pack();
            }

        });
        //点击 退出 后程序主界面关闭
        exitMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(JFrame.EXIT_ON_CLOSE);
            }
        });

        opMenu.add(addMenu);
        opMenu.add(queryMenu);
        opMenu.add(deleteMenu);
        opMenu.add(updateMenu);
        opMenu.addSeparator();
        opMenu.add(exitMenu);
        return opMenu;
    }

}
