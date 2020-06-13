package com.contact.bank;

import java.io.*;

import com.contact.bank.RandomAccessAccountRecord;

public class FileEditor {

    RandomAccessFile file; // reference to the file

    // open the file
    public FileEditor( File fileName ) throws IOException
    {
        file = new RandomAccessFile( fileName, "rw" );
    }

    // close the file
    public void closeFile() throws IOException
    {
        if ( file != null )
            file.close();
    }

    // get a record from the file
    public RandomAccessAccountRecord getRecord( int accountNumber )
            throws IllegalArgumentException, NumberFormatException, IOException
    {
        RandomAccessAccountRecord record = new RandomAccessAccountRecord();

        if ( accountNumber < 1 || accountNumber > 100 )
            throw new IllegalArgumentException( "Out of range" );

        // seek appropriate record in file
        file.seek( ( accountNumber - 1 ) * RandomAccessAccountRecord.SIZE );

        record.read( file );

        return record;

    } // end method getRecord

    // update record in file
    public void updateRecord( int accountNumber, String firstName,
                              String lastName, double balance )
            throws IllegalArgumentException, IOException
    {
        RandomAccessAccountRecord record = getRecord( accountNumber );
        if ( accountNumber == 0 )
            throw new IllegalArgumentException( "Account does not exist" );

        // seek appropriate record in file
        file.seek( ( accountNumber - 1 ) * RandomAccessAccountRecord.SIZE );

        record = new RandomAccessAccountRecord( accountNumber,
                firstName, lastName, balance );

        record.write( file ); // write updated record to file

    } // end method updateRecord

    // add record to file
    public void newRecord( int accountNumber, String firstName,
                           String lastName, double balance )
            throws IllegalArgumentException, IOException
    {
        RandomAccessAccountRecord record = getRecord( accountNumber );

        if ( record.getAccount() != 0 )
            throw new IllegalArgumentException( "Account already exists" );

        // seek appropriate record in file
        file.seek( ( accountNumber - 1 ) * RandomAccessAccountRecord.SIZE );

        record = new RandomAccessAccountRecord( accountNumber,
                firstName, lastName, balance );

        record.write( file ); // write record to file

    } // end method newRecord

    // delete record from file
    public void deleteRecord( int accountNumber )
            throws IllegalArgumentException, IOException
    {
        RandomAccessAccountRecord record = getRecord( accountNumber );

        if ( record.getAccount() == 0 )
            throw new IllegalArgumentException( "Account does not exist" );

        // seek appropriate record in file
        file.seek( ( accountNumber - 1 ) * RandomAccessAccountRecord.SIZE );

        // create a blank record to write to the file
        record = new RandomAccessAccountRecord();
        record.write( file );

    } // end method deleteRecord

} // end class EditFile
