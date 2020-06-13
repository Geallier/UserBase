package com.contact.frame;

import java.awt.*;

import javax.swing.*;

import com.contact.data.DbQuery;
import com.contact.view.Constant;
import com.contact.view.JMainMenu;
import com.contact.view.JToolCollect;

public class JMainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JMainMenu mainMenu = null;
    private JToolCollect collect = null;
    static DbQuery a = new DbQuery();

    public JMainFrame(String name) {
        init(name);
        this.setJMenuBar(getMainMenu());
        this.setLayout(new BorderLayout(0, 5));
        this.add(getToolCollect(), BorderLayout.NORTH);
        this.add(getContent(),BorderLayout.SOUTH);
    }

    private JMainMenu getMainMenu() {
        if (this.mainMenu == null) {
            mainMenu = new JMainMenu();
        }
        return mainMenu;
    }

    private JToolCollect getToolCollect() {
        if (this.collect == null) {
            collect = new JToolCollect();
        }

        return collect;
    }

    private JTextArea getContent(){
        JTextArea contentArea =new JTextArea();

        String content=DbQuery.query("*");
        contentArea.setText(content);

        return contentArea;
    }
    private void init(String name) {
        this.setTitle(name);
        this.setSize(new Dimension(600, 520));
        this.setIconImage(new ImageIcon(Constant.TITLE_ICON_PATH).getImage());
        this.setResizable(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int Screen_width = dim.width;
        int Screen_height = dim.height;
        int width = this.getWidth();
        int height = this.getHeight();
        this.setBounds((Screen_width - width) / 2, (Screen_height - height) / 2, width, height);
    }

    public static void main(String args[]) {
        JMainFrame frame = new JMainFrame(Constant.SYSTEM_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

