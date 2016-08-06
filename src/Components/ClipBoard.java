/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author toze
 */
public class ClipBoard 
{
    public static void main(String[] args)
        throws UnsupportedFlavorException, IOException
    {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData;

        //  Add some test data

        if (args.length > 0)
            testData = new StringSelection( args[0] );
        else
            testData = new StringSelection( "Test Data" );

        c.setContents(testData, testData);

        //  Get clipboard contents, as a String

        Transferable t = c.getContents( null );

        if ( t.isDataFlavorSupported(DataFlavor.stringFlavor) )
        {
            Object o = t.getTransferData( DataFlavor.stringFlavor );
            String data = (String)t.getTransferData( DataFlavor.stringFlavor );
            System.out.println( "Clipboard contents: " + data );
        }

        System.exit(0);
    }
}