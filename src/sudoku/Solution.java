/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javax.swing.JTextField;

/**
 *
 * @author soft
 */
public class Solution {
    
    public static int solution[][]=null;
    public static boolean solveSudoku(int sud[][])
    {
        int row=0,col=0;
        boolean unAssigned=false;
        for (row =0; row < 9; row++)
        {
            for (col = 0; col < 9; col++)
            {
                if (sud[row][col] == 0)
                {
                    unAssigned=true;
                    break;
                }
            }
            if(unAssigned)
                break;
        }
        
        if(!unAssigned)  //Solution Found
        {
            solution=sud;
            
            return true;
        }
        
        for (int num = 1; num <= 9; num++)
        {
            if (isSafe(sud, row, col, num))
            {
                sud[row][col] = num;
                if (solveSudoku(sud))
                    return true;
                sud[row][col] = 0;
            }
        }
        
        return false;
}

/* Returns whether any assigned entry n the specified row matches 
   the given number. */
public static boolean usedInRow(int sud[][], int row, int num)
{
    for (int col = 0; col < 9; col++)
        if (sud[row][col] == num)
            return true;
    return false;
}
 
/* Returns whether any assigned entry in the specified column matches 
   the given number. */
public static boolean usedInCol(int sud[][], int col, int num)
{
    for (int row = 0; row < 9; row++)
        if (sud[row][col] == num)
            return true;
    return false;
}
 
/* Returns whether any assigned entry within the specified 3x3 box matches 
   the given number. */
public static boolean usedInBox(int sud[][], int boxStartRow, int boxStartCol, int num)
{
    for (int row = 0; row < 3; row++)
        for (int col = 0; col < 3; col++)
            if (sud[row+boxStartRow][col+boxStartCol] == num)
                return true;
    return false;
}
 
/* Returns whether it will be legal to assign num to the given row,col location. 
 */
public static boolean isSafe(int sud[][], int row, int col, int num)
{
    return !usedInRow(sud, row, num) && !usedInCol(sud, col, num) &&
           !usedInBox(sud, row - row % 3 , col - col % 3, num);
}

}
