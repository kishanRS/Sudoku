/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author soft
 */
public class MouseEventHandler extends MouseAdapter
{
    JTextField l[][];
    int iSelection, jSelection;
    JLabel messageL;
    public MouseEventHandler( int iSelection, int jSelection, JTextField l[][],JLabel messageL)
    {
        this.l=l;
        this.iSelection=iSelection;
        this.jSelection=jSelection;
        this.messageL=messageL;
    }
    
    public void mouseClicked(java.awt.event.MouseEvent evt)
    {
        clLMouseClicked(iSelection, jSelection);
    }
    
    private void clLMouseClicked(int iSelection,int jSelection)
    {
        BasicSudoku.setMessage(0,messageL," ");
        
        if(iSelection==0)
        {
            for(int i=0;i<9;i++)
            {
                if(!l[jSelection][i].getBackground().equals(Color.pink))
                {
                    l[jSelection][i].setText("");
                    BasicSudoku.setOriginalColor(jSelection,i,l[jSelection][i]);
                }
            }
        }
        else
            for(int i=0;i<9;i++)
            {
                if(!l[i][jSelection].getBackground().equals(Color.pink))
                {
                    l[i][jSelection].setText("");
                    BasicSudoku.setOriginalColor(i,jSelection,l[i][jSelection]);
                }
            }
    }
}
