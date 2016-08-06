package Testes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class popupmenu {
  public static JFrame createUI(){
    JFrame testFrame = new JFrame(  );

    testFrame.add( createLabelWithPopupMenu() );

    testFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    testFrame.pack();
    return testFrame;
  }

  private static JLabel createLabelWithPopupMenu(){
    JLabel result = new JLabel( "Right-click me" );
    result.setComponentPopupMenu( createPopupMenu() );
    return result;
  }



  private static JPopupMenu createPopupMenu(){
    JPopupMenu popupMenu = new JPopupMenu(  );
    popupMenu.add( createAction() );
    return popupMenu;
  }

  private static Action createAction(){
    AbstractAction result = new AbstractAction() {
      @Override
      public void actionPerformed( ActionEvent e ) {
        System.out.println( "MnemonicTest.actionPerformed" );
      }
    };
    result.putValue( Action.MNEMONIC_KEY, KeyEvent.VK_A );
    result.putValue( Action.NAME, "Action" );
    return result;
  }

  public static void main( String[] args ) {
    EventQueue.invokeLater( new Runnable() {
      @Override
      public void run() {
        createUI().setVisible( true );
      }
    } );
  }
}