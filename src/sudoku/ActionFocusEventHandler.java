/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author soft
 */
public class ActionFocusEventHandler extends MouseAdapter implements ActionListener, FocusListener 
{
    int iSelection,jSelection;
    JLabel messageL;
    JTextField l;
    ActionFocusEventHandler(int iSelection, int jSelection, JLabel messageL, JTextField l)
    {
        this.iSelection=iSelection;
        this.jSelection=jSelection;
        this.messageL=messageL;
        this.l=l;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent evt) 
    {
        BasicSudoku.setOriginalColor(iSelection,jSelection,l);
        lActionPerformed();
    }
    
    public void focusLost(java.awt.event.FocusEvent evt) 
    {
        BasicSudoku.setOriginalColor(iSelection,jSelection,l);
        lActionPerformed();
    }
    public void focusGained(java.awt.event.FocusEvent evt) 
    {
       
    }
    
        
    public void lActionPerformed()
    {
        
       if( ! l.getText().isEmpty())
       {
           int val=-1;
           try
           {
              val=Integer.parseInt(l.getText());
               
              if(val<1||val>9)
              {   
                   messageL.setText("Enter Digits between 1-9, TRY AGAIN");
                   l.setText("");
                   l.setBackground(Color.orange);
              }    
              
           }
           catch(NumberFormatException nfe)
           {
              messageL.setText("Enter only Digits: 1-9, TRY AGAIN");
              l.setText("");
              l.setBackground(Color.orange );
           }   
       }  
    }
}
