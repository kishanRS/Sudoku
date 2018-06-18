/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author soft
 */
public class BasicSudoku {
    
    public static boolean solvePuzzle=true;
    public static boolean  easy=true;
    public static boolean medium=false;
    public static boolean hard=false;
    
    
    public static void setOriginalColor(int row, int col,JTextField l)
     {
        if( (row<3 && (col<3 || col>5)) || ((row>=3&&row<=5) &&(col>=3&&col<=5)) || (row>5 && (col<3 || col>5)))
        {
            l.setBackground(new Color(255,0,153));
        }
        else
            l.setBackground(new Color(255,0,255));
    }
    
    public static void setMessage(int choice, JLabel messageL,String message)
    {
        if(choice==0)
        {
            messageL.setText("");
            messageL.setOpaque(false);
            messageL.setForeground(Color.orange);
        }
        else if(choice==1)
        {
            messageL.setText(message);
            messageL.setOpaque(true);
            messageL.setForeground(Color.red);
            messageL.setBackground(Color.orange);
        }
        else if(choice==2)
        {
            messageL.setText(message);
            messageL.setOpaque(true);
            messageL.setForeground(Color.white);
            messageL.setBackground(Color.green);
        }
            
    }
    
    public static boolean Verify(int sud[][], JTextField l[][], JLabel messageL) 
    {
        boolean verified=true;
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(sud[i][j]>0&&(! l[i][j].getBackground().equals(Color.pink)))
                {
                    int num=sud[i][j];
                    sud[i][j]=-1;
                    if(Solution.isSafe(sud,i,j,num))
                        sud[i][j]=num;
                    else
                    {
                        BasicSudoku.setMessage(1, messageL,"2 or more same enteries in same row or column or box !!!");
                        verified=false;
                        l[i][j].setBackground(Color.orange);
                        break;
                    }
                }
                
            }
            if(!verified)
                break;
        }
        return verified;
    }
    
}
