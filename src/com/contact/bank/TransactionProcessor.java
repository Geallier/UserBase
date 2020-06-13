package com.contact.bank;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.*;

import com.contact.bank.*;
import com.contact.view.JMainMenu;

public class TransactionProcessor extends JFrame {

    private BankUI userInterface;
    private JMenuItem newItem, updateItem, deleteItem, openItem, exitItem;
    private JTextField fields[];
    private JTextField accountField, transactionField;
    private JButton actionButton, cancelButton;
    private FileEditor dataFile;
    private RandomAccessAccountRecord record;

    public TransactionProcessor()
    {
        super( "第十组面向对象" );

        // set up desktop, menu bar and File menu
        userInterface = new BankUI( 5 );
        getContentPane().add( userInterface );
        userInterface.setVisible( false );

        // set up the action button
        actionButton = userInterface.getDoTask1Button();
        actionButton.setText( "保存" );
        actionButton.setEnabled( false );

        // register action button listener
        actionButton.addActionListener(

                new ActionListener() { // anonymous inner class

                    public void actionPerformed( ActionEvent event )
                    {
                        String action = event.getActionCommand();
                        performAction( action );

                    } // end method actionPerformed

                } // end anonymous inner class

        ); // end call to addActionListener

        // set up the cancel button
        cancelButton = userInterface.getDoTask2Button();
        cancelButton.setText( "取消" );
        cancelButton.setEnabled( false );

        // register cancel button listener
        cancelButton.addActionListener(

                new ActionListener() { // anonymous inner class

                    // clear the fields
                    public void actionPerformed( ActionEvent event )
                    {
                        userInterface.clearFields();
                    }

                } // end anonymous inner class

        ); // end call to addActionListener

        // set up the listener for the account field
        fields = userInterface.getFields();
        accountField = fields[ BankUI.ACCOUNT ];
        accountField.addActionListener(

                new ActionListener() { // anonymous inner class

                    public void actionPerformed( ActionEvent event )
                    {
                        displayRecord( "0" );
                    }

                } // end anonymous inner class

        ); // end call to addActionListener

        // create reference to the transaction field
        transactionField = fields[ BankUI.TRANSACTION ];

        // register transaction field listener
        transactionField.addActionListener(

                new ActionListener() { // anonymous inner class

                    // update the GUI fields
                    public void actionPerformed( ActionEvent event )
                    {
                        displayRecord( transactionField.getText() );
                    }

                } // end anonymous inner class

        ); // end call to addActionListener

        JMenuBar menuBar = new JMenuBar(); // set up the menu
        setJMenuBar( menuBar );

        JMenu fileMenu = new JMenu( "银行系统" );
        menuBar.add( fileMenu );

        JMainMenu opMenu = new JMainMenu();
        menuBar.add( opMenu );

        // set up menu item for adding a record
        newItem = new JMenuItem( "添加记录" );
        newItem.setEnabled( false );

        // register new item listener
        newItem.addActionListener(

                new ActionListener() { // anonymous inner class

                    public void actionPerformed( ActionEvent event )
                    {

                        // set up the GUI fields for editing
                        fields[ BankUI.ACCOUNT ].setEnabled( true );
                        fields[ BankUI.FIRSTNAME ].setEnabled( true );
                        fields[ BankUI.LASTNAME ].setEnabled( true );
                        fields[ BankUI.BALANCE ].setEnabled( true );
                        fields[ BankUI.TRANSACTION ].setEnabled( false );

                        actionButton.setEnabled( true );
                        actionButton.setText( "创建记录" );
                        cancelButton.setEnabled( true );

                        userInterface.clearFields(); // reset the textfields

                    } // end method actionPerformed

                } // end anonymous inner class

        ); // end call to addActionListener

        // set up menu item for updating a record
        updateItem = new JMenuItem( "更改记录" );
        updateItem.setEnabled( false );

        // register update item listener
        updateItem.addActionListener(

                new ActionListener() { // anonymous inner class

                    public void actionPerformed( ActionEvent event )
                    {
                        // set up the GUI fields for editing
                        fields[ BankUI.ACCOUNT ].setEnabled( true );
                        fields[ BankUI.FIRSTNAME ].setEnabled( false );
                        fields[ BankUI.LASTNAME ].setEnabled( false );
                        fields[ BankUI.BALANCE ].setEnabled( false );
                        fields[ BankUI.TRANSACTION ].setEnabled( true );

                        actionButton.setEnabled( true );
                        actionButton.setText( "确认更改" );
                        cancelButton.setEnabled( true );

                        userInterface.clearFields(); // reset the textfields

                    } // end method actionPerformed

                } // end anonymous inner class

        ); // end call to addActionListener

        // set up menu item for deleting a record
        deleteItem = new JMenuItem( "删除记录" );
        deleteItem.setEnabled( false );

        // register delete item listener
        deleteItem.addActionListener(

                new ActionListener() { // anonymous inner class

                    public void actionPerformed( ActionEvent event )
                    {
                        // set up the GUI fields for editing
                        fields[ BankUI.ACCOUNT ].setEnabled( true );
                        fields[ BankUI.FIRSTNAME ].setEnabled( false );
                        fields[ BankUI.LASTNAME ].setEnabled( false );
                        fields[ BankUI.BALANCE ].setEnabled( false );
                        fields[ BankUI.TRANSACTION ].setEnabled( false );

                        actionButton.setEnabled( true );
                        actionButton.setText( "删除" );
                        cancelButton.setEnabled( true );

                        userInterface.clearFields(); // reset the textfields

                    } // end method actionPerformed

                } // end anonymous inner class

        ); // end call to addActionListener

        // set up menu item for opening file
        openItem = new JMenuItem( "打开/新建文件" );

        // register open item listener
        openItem.addActionListener(

                new ActionListener() { // anonymous inner class

                    public void actionPerformed( ActionEvent event )
                    {
                        // try to open the file
                        if ( !openFile() )
                            return;

                        // set up the menu items
                        newItem.setEnabled( true );
                        updateItem.setEnabled( true );
                        deleteItem.setEnabled( true );
                        openItem.setEnabled( false );

                        // set the interface
                        userInterface.setVisible( true );
                        fields[ BankUI.ACCOUNT ].setEnabled( false );
                        fields[ BankUI.FIRSTNAME ].setEnabled( false );
                        fields[ BankUI.LASTNAME ].setEnabled( false );
                        fields[ BankUI.BALANCE ].setEnabled( false );
                        fields[ BankUI.TRANSACTION ].setEnabled( false );

                    } // end method actionPerformed

                } // end anonymous inner class

        ); // end call to addActionListener

        // set up menu item for exiting program
        exitItem = new JMenuItem( "退出" );

        // register exit item listener
        exitItem.addActionListener(

                new ActionListener() { // anonyomus inner class

                    public void actionPerformed( ActionEvent event )
                    {
                        try {
                            dataFile.closeFile(); // close the file
                        }

                        catch ( IOException ioException ) {
                            JOptionPane.showMessageDialog(
                                    TransactionProcessor.this, "关闭文件时出错",
                                    "IO Error", JOptionPane.ERROR_MESSAGE );
                        }

                        finally {
                            System.exit( 0 ); // exit the program
                        }

                    } // end method actionPerformed

                } // end anonymous inner class

        ); // end call to addActionListener

        // attach menu items to File menu
        fileMenu.add( openItem );
        fileMenu.add( newItem );
        fileMenu.add( updateItem );
        fileMenu.add( deleteItem );
        fileMenu.addSeparator();
        fileMenu.add( exitItem );

        setSize( 400, 250  );
        setVisible( true );

    } // end constructor

    public static void main( String args[] )
    {
        new TransactionProcessor();
    }

    // get the file name and open the file
    private boolean openFile()
    {
        // display dialog so user can select file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );

        int result = fileChooser.showOpenDialog( this );

        // if user clicked Cancel button on dialog, return
        if ( result == JFileChooser.CANCEL_OPTION )
            return false;

        // obtain selected file
        File fileName = fileChooser.getSelectedFile();

        // display error if file name invalid
        if ( fileName == null || fileName.getName().equals( "" ) ) {
            JOptionPane.showMessageDialog( this, "无效的文件名！",
                    "Bad File Name", JOptionPane.ERROR_MESSAGE );
            return false;
        }

        try {
            // call the helper method to open the file
            dataFile = new FileEditor( fileName );
        }

        catch( IOException ioException ) {
            JOptionPane.showMessageDialog( this, "打开文件时出错",
                    "IO Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }

        return true;

    } // end method openFile

    // create, update or delete the record
    private void performAction( String action )
    {
        try {

            // get the textfield values
            String[] values = userInterface.getFieldValues();

            int accountNumber = Integer.parseInt( values[ BankUI.ACCOUNT ] );
            String firstName = values[ BankUI.FIRSTNAME ];
            String lastName = values[ BankUI.LASTNAME ];
            double balance = Double.parseDouble( values[ BankUI.BALANCE ] );

            if ( action.equals( "创建" ) )
                dataFile.newRecord( accountNumber, // create a new record
                        firstName, lastName, balance );

            else if ( action.equals( "更新" ) )
                dataFile.updateRecord( accountNumber, // update record
                        firstName, lastName, balance );

            else if ( action.equals( "删除" ) )
                dataFile.deleteRecord( accountNumber ); // delete record

            else
                JOptionPane.showMessageDialog( this, "无效的操作",
                        "Error executing action", JOptionPane.ERROR_MESSAGE );

        } // end try

        catch( NumberFormatException format ) {
            JOptionPane.showMessageDialog( this, "输入格式错误",
                    "Number Format Error", JOptionPane.ERROR_MESSAGE );
        }

        catch( IllegalArgumentException badAccount ) {
            JOptionPane.showMessageDialog( this, badAccount.getMessage(),
                    "Bad Account Number", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException ioException ) {
            JOptionPane.showMessageDialog( this, "写入文件时错误",
                    "IO Error", JOptionPane.ERROR_MESSAGE );
        }

    } // end method performAction

    //  input a record in the textfields and update the balance
    private void displayRecord( String transaction )
    {
        try {
            // get the account number
            int accountNumber = Integer.parseInt(
                    userInterface.getFieldValues()[ BankUI.ACCOUNT ] );

            // get the associated record
            RandomAccessAccountRecord record =
                    dataFile.getRecord( accountNumber );

            if ( record.getAccount() == 0 )
                JOptionPane.showMessageDialog( this, "记录不存在",
                        "Bad Account Number",  JOptionPane.ERROR_MESSAGE );

            // get the transaction
            double change = Double.parseDouble( transaction );

            // create a string array to send to the textfields
            String[] values = { String.valueOf( record.getAccount() ),
                    record.getFirstName(), record.getLastName(),
                    String.valueOf( record.getBalance() + change ),
                    "收款(+)/付款(-)" };

            userInterface.setFieldValues( values );

        } // end try

        catch( NumberFormatException format ) {
            JOptionPane.showMessageDialog( this, "Bad Input",
                    "Number Format Error", JOptionPane.ERROR_MESSAGE );
        }

        catch ( IllegalArgumentException badAccount ) {
            JOptionPane.showMessageDialog( this, badAccount.getMessage(),
                    "Bad Account Number", JOptionPane.ERROR_MESSAGE );
        }

        catch( IOException ioException ) {
            JOptionPane.showMessageDialog( this, "读取文件时错误",
                    "IO Error", JOptionPane.ERROR_MESSAGE );
        }

    } // end method displayRecord

} // end class TransactionProcessor

