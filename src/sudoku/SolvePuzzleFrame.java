/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author soft
 */
public class SolvePuzzleFrame extends javax.swing.JFrame {

    /**
     * Creates new form SolvePuzzleFrame
     */
    JTextField l[][]=new JTextField[9][9];
    JLabel cl[][]=new JLabel[2][9];
   
    int pno;
    char ptype;
    char prob[]=new char[83];
    char saved[]=new char[83];
    int curSudo[][]=new int[9][9];
    File f;
    public SolvePuzzleFrame(int pno, char pType)
    {
        this();
        this.pno=pno;
        this.ptype=pType;
        assign();
        if(BasicSudoku.easy)
            f=new File(Paths.get("").toAbsolutePath()+"/puzzle/easy.txt");
        else if(BasicSudoku.medium)
            f=new File(Paths.get("").toAbsolutePath()+"/puzzle/medium.txt");
        else
            f=new File(Paths.get("").toAbsolutePath()+"/puzzle/hard.txt");
        
        if(ptype=='S')
        {
            submitL.setEnabled(false);
            saveStateL.setEnabled(false);
            solutionL.setEnabled(false);
            BasicSudoku.setMessage(2, messageL, "Already Solved, to solve again click Clear All !!!");
        }
        getProblem();
    }
        
        
    public SolvePuzzleFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }
    
        private void assign()
    {
        l[0][0]=s00;         l[0][1]=s01;         l[0][2]=s02;         l[0][3]=s03;         l[0][4]=s04;         l[0][5]=s05;         l[0][6]=s06;         l[0][7]=s07;         l[0][8]=s08; 
        l[1][0]=s10;         l[1][1]=s11;         l[1][2]=s12;         l[1][3]=s13;         l[1][4]=s14;         l[1][5]=s15;         l[1][6]=s16;         l[1][7]=s17;         l[1][8]=s18; 
        l[2][0]=s20;         l[2][1]=s21;         l[2][2]=s22;         l[2][3]=s23;         l[2][4]=s24;         l[2][5]=s25;         l[2][6]=s26;         l[2][7]=s27;         l[2][8]=s28; 
        l[3][0]=s30;         l[3][1]=s31;         l[3][2]=s32;         l[3][3]=s33;         l[3][4]=s34;         l[3][5]=s35;         l[3][6]=s36;         l[3][7]=s37;         l[3][8]=s38; 
        l[4][0]=s40;         l[4][1]=s41;         l[4][2]=s42;         l[4][3]=s43;         l[4][4]=s44;         l[4][5]=s45;         l[4][6]=s46;         l[4][7]=s47;         l[4][8]=s48; 
        l[5][0]=s50;         l[5][1]=s51;         l[5][2]=s52;         l[5][3]=s53;         l[5][4]=s54;         l[5][5]=s55;         l[5][6]=s56;         l[5][7]=s57;         l[5][8]=s58; 
        l[6][0]=s60;         l[6][1]=s61;         l[6][2]=s62;         l[6][3]=s63;         l[6][4]=s64;         l[6][5]=s65;         l[6][6]=s66;         l[6][7]=s67;         l[6][8]=s68; 
        l[7][0]=s70;         l[7][1]=s71;         l[7][2]=s72;         l[7][3]=s73;         l[7][4]=s74;         l[7][5]=s75;         l[7][6]=s76;         l[7][7]=s77;         l[7][8]=s78; 
        l[8][0]=s80;         l[8][1]=s81;         l[8][2]=s82;         l[8][3]=s83;         l[8][4]=s84;         l[8][5]=s85;         l[8][6]=s86;         l[8][7]=s87;         l[8][8]=s88; 
        cl[0][0]=cr0;        cl[0][1]=cr1;        cl[0][2]=cr2;        cl[0][3]=cr3;        cl[0][4]=cr4;        cl[0][5]=cr5;        cl[0][6]=cr6;         cl[0][7]=cr7;         cl[0][8]=cr8; 
        cl[1][0]=cc0;        cl[1][1]=cc1;        cl[1][2]=cc2;        cl[1][3]=cc3;        cl[1][4]=cc4;        cl[1][5]=cc5;        cl[1][6]=cc6;         cl[1][7]=cc7;         cl[1][8]=cc8;
        
        for(int i=0;i<2;i++)
            for(int j=0;j<9;j++)
            {
                final int iSelection =i, jSelection=j;
                cl[iSelection][jSelection].addMouseListener(new MouseEventHandler(iSelection,jSelection,l,messageL));
            }
    }

    private void getProblem()
    {
        int end=0;
        int pno=0;
        
        try
        {
            FileReader fr=new FileReader (f);
            
            do
            {
                if(pno!=0)
                {
                    end=fr.read();
                    end=fr.read();
                }
               // else
                {
                  end=fr.read(prob);
                  pno++;
                  if(prob[82]=='S'||prob[82]=='C')
                    end=fr.read(saved);
                  if(pno==this.pno)
                      break;
                }
                    
            }while(end!=-1);
            project();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("Exception in solvePuzzleFrame.getProblem(): "+fnfe.getMessage());   
        }
        catch(IOException ioe)
        {
            System.out.println("Exception in solvePuzzleFrame.getProblem(): "+ioe.getMessage()+"");
        }

    }
        
    private void project()
    {
        int probSudo[][]=new int[9][9];
        int savedSudo[][]=new int[9][9];
        
        for(int i=0;i<81;i++)
        {
            probSudo[i/9][i%9]=(int)prob[i]-48;
            savedSudo[i/9][i%9]=0;
        }
        if(ptype=='C'||ptype=='S')
        {
            for(int i=0;i<81;i++)
            {
                savedSudo[i/9][i%9]=(int)saved[i]-48;
            }
        }
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(probSudo[i][j]!=0)
                {
                    l[i][j].setText(probSudo[i][j]+"");
                    l[i][j].setBackground(Color.pink);
                    l[i][j].setEditable(false);
                }
                else
                {
                    if(savedSudo[i][j]!=0)
                        l[i][j].setText(savedSudo[i][j]+"");
                    else
                        l[i][j].setText("");
                    
                    final int iSelection =i, jSelection=j;
                    l[iSelection][jSelection].addActionListener(new ActionFocusEventHandler(iSelection,jSelection,messageL,l[iSelection][jSelection]));
                    l[iSelection][jSelection].addFocusListener(new ActionFocusEventHandler(iSelection,jSelection,messageL,l[iSelection][jSelection]));    
                }
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cc0 = new javax.swing.JLabel();
        cc1 = new javax.swing.JLabel();
        cc2 = new javax.swing.JLabel();
        cc5 = new javax.swing.JLabel();
        cc4 = new javax.swing.JLabel();
        cc3 = new javax.swing.JLabel();
        cc8 = new javax.swing.JLabel();
        cc7 = new javax.swing.JLabel();
        cc6 = new javax.swing.JLabel();
        cr0 = new javax.swing.JLabel();
        cr1 = new javax.swing.JLabel();
        cr2 = new javax.swing.JLabel();
        cr3 = new javax.swing.JLabel();
        cr4 = new javax.swing.JLabel();
        cr5 = new javax.swing.JLabel();
        cr6 = new javax.swing.JLabel();
        cr7 = new javax.swing.JLabel();
        cr8 = new javax.swing.JLabel();
        s01 = new javax.swing.JTextField();
        s00 = new javax.swing.JTextField();
        s02 = new javax.swing.JTextField();
        s12 = new javax.swing.JTextField();
        s11 = new javax.swing.JTextField();
        s10 = new javax.swing.JTextField();
        s22 = new javax.swing.JTextField();
        s21 = new javax.swing.JTextField();
        s20 = new javax.swing.JTextField();
        s52 = new javax.swing.JTextField();
        s42 = new javax.swing.JTextField();
        s32 = new javax.swing.JTextField();
        s31 = new javax.swing.JTextField();
        s30 = new javax.swing.JTextField();
        s40 = new javax.swing.JTextField();
        s50 = new javax.swing.JTextField();
        s51 = new javax.swing.JTextField();
        s41 = new javax.swing.JTextField();
        s82 = new javax.swing.JTextField();
        s81 = new javax.swing.JTextField();
        s80 = new javax.swing.JTextField();
        s70 = new javax.swing.JTextField();
        s60 = new javax.swing.JTextField();
        s61 = new javax.swing.JTextField();
        s62 = new javax.swing.JTextField();
        s71 = new javax.swing.JTextField();
        s72 = new javax.swing.JTextField();
        s06 = new javax.swing.JTextField();
        s16 = new javax.swing.JTextField();
        s26 = new javax.swing.JTextField();
        s36 = new javax.swing.JTextField();
        s46 = new javax.swing.JTextField();
        s56 = new javax.swing.JTextField();
        s66 = new javax.swing.JTextField();
        s76 = new javax.swing.JTextField();
        s86 = new javax.swing.JTextField();
        s87 = new javax.swing.JTextField();
        s77 = new javax.swing.JTextField();
        s67 = new javax.swing.JTextField();
        s57 = new javax.swing.JTextField();
        s47 = new javax.swing.JTextField();
        s37 = new javax.swing.JTextField();
        s27 = new javax.swing.JTextField();
        s17 = new javax.swing.JTextField();
        s07 = new javax.swing.JTextField();
        s08 = new javax.swing.JTextField();
        s18 = new javax.swing.JTextField();
        s28 = new javax.swing.JTextField();
        s38 = new javax.swing.JTextField();
        s48 = new javax.swing.JTextField();
        s58 = new javax.swing.JTextField();
        s68 = new javax.swing.JTextField();
        s78 = new javax.swing.JTextField();
        s88 = new javax.swing.JTextField();
        s03 = new javax.swing.JTextField();
        s13 = new javax.swing.JTextField();
        s23 = new javax.swing.JTextField();
        s33 = new javax.swing.JTextField();
        s43 = new javax.swing.JTextField();
        s53 = new javax.swing.JTextField();
        s63 = new javax.swing.JTextField();
        s73 = new javax.swing.JTextField();
        s83 = new javax.swing.JTextField();
        s84 = new javax.swing.JTextField();
        s74 = new javax.swing.JTextField();
        s64 = new javax.swing.JTextField();
        s54 = new javax.swing.JTextField();
        s44 = new javax.swing.JTextField();
        s34 = new javax.swing.JTextField();
        s24 = new javax.swing.JTextField();
        s14 = new javax.swing.JTextField();
        s04 = new javax.swing.JTextField();
        s05 = new javax.swing.JTextField();
        s15 = new javax.swing.JTextField();
        s25 = new javax.swing.JTextField();
        s35 = new javax.swing.JTextField();
        s45 = new javax.swing.JTextField();
        s55 = new javax.swing.JTextField();
        s65 = new javax.swing.JTextField();
        s75 = new javax.swing.JTextField();
        s85 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        submitL = new javax.swing.JLabel();
        clearAllL = new javax.swing.JLabel();
        saveStateL = new javax.swing.JLabel();
        solutionL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        messageL = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 0, 153));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setOpaque(false);

        cc0.setBackground(new java.awt.Color(255, 153, 255));
        cc0.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc0.setForeground(new java.awt.Color(255, 0, 204));
        cc0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc0.setText("C");
        cc0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc0.setOpaque(true);

        cc1.setBackground(new java.awt.Color(255, 153, 255));
        cc1.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc1.setForeground(new java.awt.Color(255, 0, 204));
        cc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc1.setText("C");
        cc1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc1.setOpaque(true);

        cc2.setBackground(new java.awt.Color(255, 153, 255));
        cc2.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc2.setForeground(new java.awt.Color(255, 0, 204));
        cc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc2.setText("C");
        cc2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc2.setOpaque(true);

        cc5.setBackground(new java.awt.Color(255, 153, 255));
        cc5.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc5.setForeground(new java.awt.Color(255, 0, 204));
        cc5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc5.setText("C");
        cc5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc5.setOpaque(true);

        cc4.setBackground(new java.awt.Color(255, 153, 255));
        cc4.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc4.setForeground(new java.awt.Color(255, 0, 204));
        cc4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc4.setText("C");
        cc4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc4.setOpaque(true);

        cc3.setBackground(new java.awt.Color(255, 153, 255));
        cc3.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc3.setForeground(new java.awt.Color(255, 0, 204));
        cc3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc3.setText("C");
        cc3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc3.setOpaque(true);

        cc8.setBackground(new java.awt.Color(255, 153, 255));
        cc8.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc8.setForeground(new java.awt.Color(255, 0, 204));
        cc8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc8.setText("C");
        cc8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc8.setOpaque(true);
        cc8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cc8FocusLost(evt);
            }
        });

        cc7.setBackground(new java.awt.Color(255, 153, 255));
        cc7.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc7.setForeground(new java.awt.Color(255, 0, 204));
        cc7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc7.setText("C");
        cc7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc7.setOpaque(true);

        cc6.setBackground(new java.awt.Color(255, 153, 255));
        cc6.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cc6.setForeground(new java.awt.Color(255, 0, 204));
        cc6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cc6.setText("C");
        cc6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cc6.setOpaque(true);

        cr0.setBackground(new java.awt.Color(255, 153, 255));
        cr0.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr0.setForeground(new java.awt.Color(255, 0, 204));
        cr0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr0.setText("C");
        cr0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr0.setOpaque(true);

        cr1.setBackground(new java.awt.Color(255, 153, 255));
        cr1.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr1.setForeground(new java.awt.Color(255, 0, 204));
        cr1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr1.setText("C");
        cr1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr1.setOpaque(true);

        cr2.setBackground(new java.awt.Color(255, 153, 255));
        cr2.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr2.setForeground(new java.awt.Color(255, 0, 204));
        cr2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr2.setText("C");
        cr2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr2.setOpaque(true);

        cr3.setBackground(new java.awt.Color(255, 153, 255));
        cr3.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr3.setForeground(new java.awt.Color(255, 0, 204));
        cr3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr3.setText("C");
        cr3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr3.setOpaque(true);

        cr4.setBackground(new java.awt.Color(255, 153, 255));
        cr4.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr4.setForeground(new java.awt.Color(255, 0, 204));
        cr4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr4.setText("C");
        cr4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr4.setOpaque(true);

        cr5.setBackground(new java.awt.Color(255, 153, 255));
        cr5.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr5.setForeground(new java.awt.Color(255, 0, 204));
        cr5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr5.setText("C");
        cr5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr5.setOpaque(true);

        cr6.setBackground(new java.awt.Color(255, 153, 255));
        cr6.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr6.setForeground(new java.awt.Color(255, 0, 204));
        cr6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr6.setText("C");
        cr6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr6.setOpaque(true);

        cr7.setBackground(new java.awt.Color(255, 153, 255));
        cr7.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr7.setForeground(new java.awt.Color(255, 0, 204));
        cr7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr7.setText("C");
        cr7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr7.setOpaque(true);

        cr8.setBackground(new java.awt.Color(255, 153, 255));
        cr8.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        cr8.setForeground(new java.awt.Color(255, 0, 204));
        cr8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cr8.setText("C");
        cr8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cr8.setOpaque(true);

        s01.setBackground(new java.awt.Color(255, 0, 153));
        s01.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s01.setForeground(new java.awt.Color(255, 255, 255));
        s01.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s01.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s01.setSelectionColor(new java.awt.Color(255, 102, 204));

        s00.setBackground(new java.awt.Color(255, 0, 153));
        s00.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s00.setForeground(new java.awt.Color(255, 255, 255));
        s00.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s00.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s00.setSelectionColor(new java.awt.Color(255, 102, 204));

        s02.setBackground(new java.awt.Color(255, 0, 153));
        s02.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s02.setForeground(new java.awt.Color(255, 255, 255));
        s02.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s02.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s02.setSelectionColor(new java.awt.Color(255, 102, 204));

        s12.setBackground(new java.awt.Color(255, 0, 153));
        s12.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s12.setForeground(new java.awt.Color(255, 255, 255));
        s12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s12.setSelectionColor(new java.awt.Color(255, 102, 204));

        s11.setBackground(new java.awt.Color(255, 0, 153));
        s11.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s11.setForeground(new java.awt.Color(255, 255, 255));
        s11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s11.setSelectionColor(new java.awt.Color(255, 102, 204));

        s10.setBackground(new java.awt.Color(255, 0, 153));
        s10.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s10.setForeground(new java.awt.Color(255, 255, 255));
        s10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s10.setSelectionColor(new java.awt.Color(255, 102, 204));

        s22.setBackground(new java.awt.Color(255, 0, 153));
        s22.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s22.setForeground(new java.awt.Color(255, 255, 255));
        s22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s22.setSelectionColor(new java.awt.Color(255, 102, 204));

        s21.setBackground(new java.awt.Color(255, 0, 153));
        s21.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s21.setForeground(new java.awt.Color(255, 255, 255));
        s21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s21.setSelectionColor(new java.awt.Color(255, 102, 204));

        s20.setBackground(new java.awt.Color(255, 0, 153));
        s20.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s20.setForeground(new java.awt.Color(255, 255, 255));
        s20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s20.setSelectionColor(new java.awt.Color(255, 102, 204));

        s52.setBackground(new java.awt.Color(255, 0, 255));
        s52.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s52.setForeground(new java.awt.Color(255, 255, 255));
        s52.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s52.setSelectionColor(new java.awt.Color(255, 102, 204));

        s42.setBackground(new java.awt.Color(255, 0, 255));
        s42.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s42.setForeground(new java.awt.Color(255, 255, 255));
        s42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s42.setSelectionColor(new java.awt.Color(255, 102, 204));

        s32.setBackground(new java.awt.Color(255, 0, 255));
        s32.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s32.setForeground(new java.awt.Color(255, 255, 255));
        s32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s32.setSelectionColor(new java.awt.Color(255, 102, 204));

        s31.setBackground(new java.awt.Color(255, 0, 255));
        s31.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s31.setForeground(new java.awt.Color(255, 255, 255));
        s31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s31.setSelectionColor(new java.awt.Color(255, 102, 204));

        s30.setBackground(new java.awt.Color(255, 0, 255));
        s30.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s30.setForeground(new java.awt.Color(255, 255, 255));
        s30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s30.setSelectionColor(new java.awt.Color(255, 102, 204));

        s40.setBackground(new java.awt.Color(255, 0, 255));
        s40.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s40.setForeground(new java.awt.Color(255, 255, 255));
        s40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s40.setSelectionColor(new java.awt.Color(255, 102, 204));

        s50.setBackground(new java.awt.Color(255, 0, 255));
        s50.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s50.setForeground(new java.awt.Color(255, 255, 255));
        s50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s50.setSelectionColor(new java.awt.Color(255, 102, 204));

        s51.setBackground(new java.awt.Color(255, 0, 255));
        s51.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s51.setForeground(new java.awt.Color(255, 255, 255));
        s51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s51.setSelectionColor(new java.awt.Color(255, 102, 204));

        s41.setBackground(new java.awt.Color(255, 0, 255));
        s41.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s41.setForeground(new java.awt.Color(255, 255, 255));
        s41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s41.setSelectionColor(new java.awt.Color(255, 102, 204));

        s82.setBackground(new java.awt.Color(255, 0, 153));
        s82.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s82.setForeground(new java.awt.Color(255, 255, 255));
        s82.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s82.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s82.setSelectionColor(new java.awt.Color(255, 102, 204));

        s81.setBackground(new java.awt.Color(255, 0, 153));
        s81.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s81.setForeground(new java.awt.Color(255, 255, 255));
        s81.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s81.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s81.setSelectionColor(new java.awt.Color(255, 102, 204));

        s80.setBackground(new java.awt.Color(255, 0, 153));
        s80.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s80.setForeground(new java.awt.Color(255, 255, 255));
        s80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s80.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s80.setSelectionColor(new java.awt.Color(255, 102, 204));

        s70.setBackground(new java.awt.Color(255, 0, 153));
        s70.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s70.setForeground(new java.awt.Color(255, 255, 255));
        s70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s70.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s70.setSelectionColor(new java.awt.Color(255, 102, 204));

        s60.setBackground(new java.awt.Color(255, 0, 153));
        s60.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s60.setForeground(new java.awt.Color(255, 255, 255));
        s60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s60.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s60.setSelectionColor(new java.awt.Color(255, 102, 204));

        s61.setBackground(new java.awt.Color(255, 0, 153));
        s61.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s61.setForeground(new java.awt.Color(255, 255, 255));
        s61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s61.setSelectionColor(new java.awt.Color(255, 102, 204));

        s62.setBackground(new java.awt.Color(255, 0, 153));
        s62.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s62.setForeground(new java.awt.Color(255, 255, 255));
        s62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s62.setSelectionColor(new java.awt.Color(255, 102, 204));

        s71.setBackground(new java.awt.Color(255, 0, 153));
        s71.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s71.setForeground(new java.awt.Color(255, 255, 255));
        s71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s71.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s71.setSelectionColor(new java.awt.Color(255, 102, 204));

        s72.setBackground(new java.awt.Color(255, 0, 153));
        s72.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s72.setForeground(new java.awt.Color(255, 255, 255));
        s72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s72.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s72.setSelectionColor(new java.awt.Color(255, 102, 204));

        s06.setBackground(new java.awt.Color(255, 0, 153));
        s06.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s06.setForeground(new java.awt.Color(255, 255, 255));
        s06.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s06.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s06.setSelectionColor(new java.awt.Color(255, 102, 204));

        s16.setBackground(new java.awt.Color(255, 0, 153));
        s16.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s16.setForeground(new java.awt.Color(255, 255, 255));
        s16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s16.setSelectionColor(new java.awt.Color(255, 102, 204));

        s26.setBackground(new java.awt.Color(255, 0, 153));
        s26.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s26.setForeground(new java.awt.Color(255, 255, 255));
        s26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s26.setSelectionColor(new java.awt.Color(255, 102, 204));

        s36.setBackground(new java.awt.Color(255, 0, 255));
        s36.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s36.setForeground(new java.awt.Color(255, 255, 255));
        s36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s36.setSelectionColor(new java.awt.Color(255, 102, 204));

        s46.setBackground(new java.awt.Color(255, 0, 255));
        s46.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s46.setForeground(new java.awt.Color(255, 255, 255));
        s46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s46.setSelectionColor(new java.awt.Color(255, 102, 204));

        s56.setBackground(new java.awt.Color(255, 0, 255));
        s56.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s56.setForeground(new java.awt.Color(255, 255, 255));
        s56.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s56.setSelectionColor(new java.awt.Color(255, 102, 204));

        s66.setBackground(new java.awt.Color(255, 0, 153));
        s66.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s66.setForeground(new java.awt.Color(255, 255, 255));
        s66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s66.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s66.setSelectionColor(new java.awt.Color(255, 102, 204));

        s76.setBackground(new java.awt.Color(255, 0, 153));
        s76.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s76.setForeground(new java.awt.Color(255, 255, 255));
        s76.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s76.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s76.setSelectionColor(new java.awt.Color(255, 102, 204));

        s86.setBackground(new java.awt.Color(255, 0, 153));
        s86.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s86.setForeground(new java.awt.Color(255, 255, 255));
        s86.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s86.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s86.setSelectionColor(new java.awt.Color(255, 102, 204));

        s87.setBackground(new java.awt.Color(255, 0, 153));
        s87.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s87.setForeground(new java.awt.Color(255, 255, 255));
        s87.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s87.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s87.setSelectionColor(new java.awt.Color(255, 102, 204));

        s77.setBackground(new java.awt.Color(255, 0, 153));
        s77.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s77.setForeground(new java.awt.Color(255, 255, 255));
        s77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s77.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s77.setSelectionColor(new java.awt.Color(255, 102, 204));

        s67.setBackground(new java.awt.Color(255, 0, 153));
        s67.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s67.setForeground(new java.awt.Color(255, 255, 255));
        s67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s67.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s67.setSelectionColor(new java.awt.Color(255, 102, 204));

        s57.setBackground(new java.awt.Color(255, 0, 255));
        s57.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s57.setForeground(new java.awt.Color(255, 255, 255));
        s57.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s57.setSelectionColor(new java.awt.Color(255, 102, 204));

        s47.setBackground(new java.awt.Color(255, 0, 255));
        s47.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s47.setForeground(new java.awt.Color(255, 255, 255));
        s47.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s47.setSelectionColor(new java.awt.Color(255, 102, 204));

        s37.setBackground(new java.awt.Color(255, 0, 255));
        s37.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s37.setForeground(new java.awt.Color(255, 255, 255));
        s37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s37.setSelectionColor(new java.awt.Color(255, 102, 204));

        s27.setBackground(new java.awt.Color(255, 0, 153));
        s27.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s27.setForeground(new java.awt.Color(255, 255, 255));
        s27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s27.setSelectionColor(new java.awt.Color(255, 102, 204));

        s17.setBackground(new java.awt.Color(255, 0, 153));
        s17.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s17.setForeground(new java.awt.Color(255, 255, 255));
        s17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s17.setSelectionColor(new java.awt.Color(255, 102, 204));

        s07.setBackground(new java.awt.Color(255, 0, 153));
        s07.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s07.setForeground(new java.awt.Color(255, 255, 255));
        s07.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s07.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s07.setSelectionColor(new java.awt.Color(255, 102, 204));

        s08.setBackground(new java.awt.Color(255, 0, 153));
        s08.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s08.setForeground(new java.awt.Color(255, 255, 255));
        s08.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s08.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s08.setSelectionColor(new java.awt.Color(255, 102, 204));

        s18.setBackground(new java.awt.Color(255, 0, 153));
        s18.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s18.setForeground(new java.awt.Color(255, 255, 255));
        s18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s18.setSelectionColor(new java.awt.Color(255, 102, 204));

        s28.setBackground(new java.awt.Color(255, 0, 153));
        s28.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s28.setForeground(new java.awt.Color(255, 255, 255));
        s28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s28.setSelectionColor(new java.awt.Color(255, 102, 204));

        s38.setBackground(new java.awt.Color(255, 0, 255));
        s38.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s38.setForeground(new java.awt.Color(255, 255, 255));
        s38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s38.setSelectionColor(new java.awt.Color(255, 102, 204));

        s48.setBackground(new java.awt.Color(255, 0, 255));
        s48.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s48.setForeground(new java.awt.Color(255, 255, 255));
        s48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s48.setSelectionColor(new java.awt.Color(255, 102, 204));

        s58.setBackground(new java.awt.Color(255, 0, 255));
        s58.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s58.setForeground(new java.awt.Color(255, 255, 255));
        s58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s58.setSelectionColor(new java.awt.Color(255, 102, 204));

        s68.setBackground(new java.awt.Color(255, 0, 153));
        s68.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s68.setForeground(new java.awt.Color(255, 255, 255));
        s68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s68.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s68.setSelectionColor(new java.awt.Color(255, 102, 204));

        s78.setBackground(new java.awt.Color(255, 0, 153));
        s78.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s78.setForeground(new java.awt.Color(255, 255, 255));
        s78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s78.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s78.setSelectionColor(new java.awt.Color(255, 102, 204));

        s88.setBackground(new java.awt.Color(255, 0, 153));
        s88.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s88.setForeground(new java.awt.Color(255, 255, 255));
        s88.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s88.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s88.setSelectionColor(new java.awt.Color(255, 102, 204));

        s03.setBackground(new java.awt.Color(255, 0, 255));
        s03.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s03.setForeground(new java.awt.Color(255, 255, 255));
        s03.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s03.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s03.setSelectionColor(new java.awt.Color(255, 102, 204));

        s13.setBackground(new java.awt.Color(255, 0, 255));
        s13.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s13.setForeground(new java.awt.Color(255, 255, 255));
        s13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s13.setSelectionColor(new java.awt.Color(255, 102, 204));

        s23.setBackground(new java.awt.Color(255, 0, 255));
        s23.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s23.setForeground(new java.awt.Color(255, 255, 255));
        s23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s23.setSelectionColor(new java.awt.Color(255, 102, 204));

        s33.setBackground(new java.awt.Color(255, 0, 153));
        s33.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s33.setForeground(new java.awt.Color(255, 255, 255));
        s33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s33.setSelectionColor(new java.awt.Color(255, 102, 204));

        s43.setBackground(new java.awt.Color(255, 0, 153));
        s43.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s43.setForeground(new java.awt.Color(255, 255, 255));
        s43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s43.setSelectionColor(new java.awt.Color(255, 102, 204));

        s53.setBackground(new java.awt.Color(255, 0, 153));
        s53.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s53.setForeground(new java.awt.Color(255, 255, 255));
        s53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s53.setSelectionColor(new java.awt.Color(255, 102, 204));

        s63.setBackground(new java.awt.Color(255, 0, 255));
        s63.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s63.setForeground(new java.awt.Color(255, 255, 255));
        s63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s63.setSelectionColor(new java.awt.Color(255, 102, 204));

        s73.setBackground(new java.awt.Color(255, 0, 255));
        s73.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s73.setForeground(new java.awt.Color(255, 255, 255));
        s73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s73.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s73.setSelectionColor(new java.awt.Color(255, 102, 204));

        s83.setBackground(new java.awt.Color(255, 0, 255));
        s83.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s83.setForeground(new java.awt.Color(255, 255, 255));
        s83.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s83.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s83.setSelectionColor(new java.awt.Color(255, 102, 204));

        s84.setBackground(new java.awt.Color(255, 0, 255));
        s84.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s84.setForeground(new java.awt.Color(255, 255, 255));
        s84.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s84.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s84.setSelectionColor(new java.awt.Color(255, 102, 204));

        s74.setBackground(new java.awt.Color(255, 0, 255));
        s74.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s74.setForeground(new java.awt.Color(255, 255, 255));
        s74.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s74.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s74.setSelectionColor(new java.awt.Color(255, 102, 204));

        s64.setBackground(new java.awt.Color(255, 0, 255));
        s64.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s64.setForeground(new java.awt.Color(255, 255, 255));
        s64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s64.setSelectionColor(new java.awt.Color(255, 102, 204));

        s54.setBackground(new java.awt.Color(255, 0, 153));
        s54.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s54.setForeground(new java.awt.Color(255, 255, 255));
        s54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s54.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s54.setSelectionColor(new java.awt.Color(255, 102, 204));

        s44.setBackground(new java.awt.Color(255, 0, 153));
        s44.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s44.setForeground(new java.awt.Color(255, 255, 255));
        s44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s44.setSelectionColor(new java.awt.Color(255, 102, 204));

        s34.setBackground(new java.awt.Color(255, 0, 153));
        s34.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s34.setForeground(new java.awt.Color(255, 255, 255));
        s34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s34.setSelectionColor(new java.awt.Color(255, 102, 204));

        s24.setBackground(new java.awt.Color(255, 0, 255));
        s24.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s24.setForeground(new java.awt.Color(255, 255, 255));
        s24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s24.setSelectionColor(new java.awt.Color(255, 102, 204));

        s14.setBackground(new java.awt.Color(255, 0, 255));
        s14.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s14.setForeground(new java.awt.Color(255, 255, 255));
        s14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s14.setSelectionColor(new java.awt.Color(255, 102, 204));

        s04.setBackground(new java.awt.Color(255, 0, 255));
        s04.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s04.setForeground(new java.awt.Color(255, 255, 255));
        s04.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s04.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s04.setSelectionColor(new java.awt.Color(255, 102, 204));

        s05.setBackground(new java.awt.Color(255, 0, 255));
        s05.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s05.setForeground(new java.awt.Color(255, 255, 255));
        s05.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s05.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s05.setSelectionColor(new java.awt.Color(255, 102, 204));

        s15.setBackground(new java.awt.Color(255, 0, 255));
        s15.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s15.setForeground(new java.awt.Color(255, 255, 255));
        s15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s15.setSelectionColor(new java.awt.Color(255, 102, 204));

        s25.setBackground(new java.awt.Color(255, 0, 255));
        s25.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s25.setForeground(new java.awt.Color(255, 255, 255));
        s25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s25.setSelectionColor(new java.awt.Color(255, 102, 204));

        s35.setBackground(new java.awt.Color(255, 0, 153));
        s35.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s35.setForeground(new java.awt.Color(255, 255, 255));
        s35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s35.setSelectionColor(new java.awt.Color(255, 102, 204));

        s45.setBackground(new java.awt.Color(255, 0, 153));
        s45.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s45.setForeground(new java.awt.Color(255, 255, 255));
        s45.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s45.setSelectionColor(new java.awt.Color(255, 102, 204));

        s55.setBackground(new java.awt.Color(255, 0, 153));
        s55.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s55.setForeground(new java.awt.Color(255, 255, 255));
        s55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s55.setSelectionColor(new java.awt.Color(255, 102, 204));

        s65.setBackground(new java.awt.Color(255, 0, 255));
        s65.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s65.setForeground(new java.awt.Color(255, 255, 255));
        s65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s65.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s65.setSelectionColor(new java.awt.Color(255, 102, 204));

        s75.setBackground(new java.awt.Color(255, 0, 255));
        s75.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s75.setForeground(new java.awt.Color(255, 255, 255));
        s75.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s75.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s75.setSelectionColor(new java.awt.Color(255, 102, 204));

        s85.setBackground(new java.awt.Color(255, 0, 255));
        s85.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        s85.setForeground(new java.awt.Color(255, 255, 255));
        s85.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        s85.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        s85.setSelectionColor(new java.awt.Color(255, 102, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(cc0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cc1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cc2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(s00, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s50, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s51, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s52, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s80, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s81, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s82, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s70, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s71, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s72, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(s60, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s61, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s62, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cc3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cc4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cc5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cc6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cc7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cc8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(s53, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s54, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s55, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(s43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s44, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s45, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(s33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s83, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s84, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s85, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s73, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s74, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s75, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s63, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s64, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s65, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(s03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(s56, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s57, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s58, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(s46, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s47, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s48, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(s36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s86, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s87, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s88, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s76, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s77, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s78, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s66, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s67, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s68, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(s06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(s26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cr0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cr1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cr2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cr6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cr7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cr8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cr3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cr4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cr5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cc0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cc8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s00, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s51, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s50, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s52, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s61, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s60, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s62, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s71, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s70, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s72, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s81, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s80, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s82, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s05, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s44, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s45, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s54, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s53, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s55, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s64, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s63, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s65, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s74, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s73, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s75, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s84, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s83, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(s85, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s07, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s06, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s08, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cr0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cr1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cr2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cr3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cr4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cr5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s47, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s46, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s48, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s57, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s56, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s58, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s67, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s66, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s68, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s77, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s76, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s78, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(s87, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s86, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(s88, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cr6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cr7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cr8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setOpaque(false);

        submitL.setBackground(new java.awt.Color(255, 51, 204));
        submitL.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        submitL.setForeground(new java.awt.Color(255, 255, 255));
        submitL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        submitL.setText("Submit");
        submitL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitL.setOpaque(true);
        submitL.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                submitLFocusLost(evt);
            }
        });
        submitL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitLMouseClicked(evt);
            }
        });

        clearAllL.setBackground(new java.awt.Color(255, 102, 255));
        clearAllL.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        clearAllL.setForeground(new java.awt.Color(255, 255, 255));
        clearAllL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearAllL.setText("Clear All");
        clearAllL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearAllL.setOpaque(true);
        clearAllL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearAllLMouseClicked(evt);
            }
        });

        saveStateL.setBackground(new java.awt.Color(255, 102, 255));
        saveStateL.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        saveStateL.setForeground(new java.awt.Color(255, 255, 255));
        saveStateL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveStateL.setText("Save State");
        saveStateL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveStateL.setOpaque(true);
        saveStateL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveStateLMouseClicked(evt);
            }
        });

        solutionL.setBackground(new java.awt.Color(255, 102, 255));
        solutionL.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        solutionL.setForeground(new java.awt.Color(255, 255, 255));
        solutionL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        solutionL.setText("Solution");
        solutionL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        solutionL.setOpaque(true);
        solutionL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solutionLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveStateL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearAllL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(solutionL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(clearAllL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(saveStateL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(solutionL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sudoku Puzzle");

        messageL.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        messageL.setForeground(new java.awt.Color(255, 204, 0));
        messageL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageLMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("back");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(messageL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cc8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cc8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cc8FocusLost

    private void submitLFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_submitLFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_submitLFocusLost

    private void submitLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitLMouseClicked
        if(submitL.isEnabled())
        {
            if(verify())
            {
                saveState('S');
                
                BasicSudoku.setMessage(2, messageL, "Well Done");
            }
        }

    }//GEN-LAST:event_submitLMouseClicked

    private void clearAllLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllLMouseClicked
        submitL.setEnabled(true);
        saveStateL.setEnabled(true);
        solutionL.setEnabled(true);
        
        for(int i=0;i<9;i++)
        for(int j=0;j<9;j++)
        {
            if(!l[i][j].getBackground().equals(Color.pink))
            {
                BasicSudoku.setOriginalColor(i,j,l[i][j]);
                l[i][j].setText("");
            }        
        }

        BasicSudoku.setMessage(0,messageL," ");
    }//GEN-LAST:event_clearAllLMouseClicked

    private void saveStateLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveStateLMouseClicked
    if(saveStateL.isEnabled())
        saveState('C');
    }//GEN-LAST:event_saveStateLMouseClicked

    private void saveState(char ch)
    {
        try
    {
        File temp=new File(Paths.get("").toAbsolutePath()+"/puzzle/temp.txt");
        temp.createNewFile();
        FileWriter fw=new FileWriter(temp);
        FileReader fr=new FileReader(f);
        int pno=0,end=0;
        do
        {
            if(pno!=0)
            {
                end=fr.read();
                end=fr.read();
                if(end==-1)
                    break;
                fw.write("\r\n");
                
            }
            //else
            {
                pno++;
                char buf[]=new char[83];
                
                end= fr.read(buf);
                if(ch=='C')
                {
                    if(buf[82]=='U'&&pno==(this.pno))
                        buf[82]='C';                     
                }
                else if(ch=='S')
                {
                    if((buf[82]=='U'||buf[82]=='C')&&pno==(this.pno))
                    buf[82]='S';
                }
                
                
                fw.write(buf);
                if(pno==this.pno)
                {
                   
                    fw.write(getCurrentState(ch));
                }
                if((this.ptype=='C'||this.ptype=='S')&&pno==this.pno)
                {
                    end=fr.read(buf);
                    // fw.write(buf);
                }
                else if((buf[82]=='C'||buf[82]=='S')&&pno!=this.pno)
                {
                    end=fr.read(buf);
                    fw.write(buf);
                }   
            }
                
        }while(end!=-1);
        fr.close();
        fw.close();
        f.delete();
        f=new File(Paths.get("").toAbsolutePath()+"/puzzle/temp.txt");
        if(BasicSudoku.easy)
            temp=new File(Paths.get("").toAbsolutePath()+"/puzzle/easy.txt");
        else if(BasicSudoku.medium)
            temp=new File(Paths.get("").toAbsolutePath()+"/puzzle/medium.txt");
        else
            temp=new File(Paths.get("").toAbsolutePath()+"/puzzle/hard.txt");
        
        f.renameTo(temp);
    }
    catch(IOException ioe)
    {
        System.out.println("Exception in solvePuzzleFrame.saveState: "+ioe.getMessage());
    }
        
    
    }
    private char[] getCurrentState(char ch)
    {
        String sudo="";
        for(int i=0;i<9;i++)
        for(int j=0;j<9;j++)
        {
            if(l[i][j].getText().trim().isEmpty())
                  sudo=sudo+"0";
            else
                  sudo=sudo+l[i][j].getText();
        }
        if(ch=='C')
            sudo=sudo+" "+'C';
        else if(ch=='S')
             sudo=sudo+" "+'S';   
            
        else
            sudo=sudo+" "+this.ptype;
        char c[]=sudo.toCharArray();
        return c;   
    }
    private void messageLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_messageLMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        SelectionFrame selectionFrame=new SelectionFrame();
        selectionFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void solutionLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solutionLMouseClicked
      submitL.setEnabled(false);
      saveStateL.setEnabled(false);
      
      int probSudo[][]=new int[9][9];  
      for(int i=0;i<81;i++)
      {
        probSudo[i/9][i%9]=(int)prob[i]-48;
      }
      Solution.solveSudoku(probSudo);
      
      for(int i=0;i<9;i++)
          for(int j=0;j<9;j++)
              l[i][j].setText(Solution.solution[i][j]+"");
    }//GEN-LAST:event_solutionLMouseClicked

    private boolean verify()
    {
         BasicSudoku.setMessage(0,messageL," ");
    
    
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(!l[i][j].getText().trim().isEmpty())
                {
                    curSudo[i][j]=Integer.parseInt(l[i][j].getText());
                }
                else
                {
                    BasicSudoku.setMessage(1, messageL, "Incomplete Solution, TRY AGAIN!!!");
                return false;
                }
            }
        }
    
    if(BasicSudoku.Verify(curSudo,l,messageL))
    {
       return true;
    }
    return false;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SolvePuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SolvePuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SolvePuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SolvePuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolvePuzzleFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cc0;
    private javax.swing.JLabel cc1;
    private javax.swing.JLabel cc2;
    private javax.swing.JLabel cc3;
    private javax.swing.JLabel cc4;
    private javax.swing.JLabel cc5;
    private javax.swing.JLabel cc6;
    private javax.swing.JLabel cc7;
    private javax.swing.JLabel cc8;
    private javax.swing.JLabel clearAllL;
    private javax.swing.JLabel cr0;
    private javax.swing.JLabel cr1;
    private javax.swing.JLabel cr2;
    private javax.swing.JLabel cr3;
    private javax.swing.JLabel cr4;
    private javax.swing.JLabel cr5;
    private javax.swing.JLabel cr6;
    private javax.swing.JLabel cr7;
    private javax.swing.JLabel cr8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel messageL;
    private javax.swing.JTextField s00;
    private javax.swing.JTextField s01;
    private javax.swing.JTextField s02;
    private javax.swing.JTextField s03;
    private javax.swing.JTextField s04;
    private javax.swing.JTextField s05;
    private javax.swing.JTextField s06;
    private javax.swing.JTextField s07;
    private javax.swing.JTextField s08;
    private javax.swing.JTextField s10;
    private javax.swing.JTextField s11;
    private javax.swing.JTextField s12;
    private javax.swing.JTextField s13;
    private javax.swing.JTextField s14;
    private javax.swing.JTextField s15;
    private javax.swing.JTextField s16;
    private javax.swing.JTextField s17;
    private javax.swing.JTextField s18;
    private javax.swing.JTextField s20;
    private javax.swing.JTextField s21;
    private javax.swing.JTextField s22;
    private javax.swing.JTextField s23;
    private javax.swing.JTextField s24;
    private javax.swing.JTextField s25;
    private javax.swing.JTextField s26;
    private javax.swing.JTextField s27;
    private javax.swing.JTextField s28;
    private javax.swing.JTextField s30;
    private javax.swing.JTextField s31;
    private javax.swing.JTextField s32;
    private javax.swing.JTextField s33;
    private javax.swing.JTextField s34;
    private javax.swing.JTextField s35;
    private javax.swing.JTextField s36;
    private javax.swing.JTextField s37;
    private javax.swing.JTextField s38;
    private javax.swing.JTextField s40;
    private javax.swing.JTextField s41;
    private javax.swing.JTextField s42;
    private javax.swing.JTextField s43;
    private javax.swing.JTextField s44;
    private javax.swing.JTextField s45;
    private javax.swing.JTextField s46;
    private javax.swing.JTextField s47;
    private javax.swing.JTextField s48;
    private javax.swing.JTextField s50;
    private javax.swing.JTextField s51;
    private javax.swing.JTextField s52;
    private javax.swing.JTextField s53;
    private javax.swing.JTextField s54;
    private javax.swing.JTextField s55;
    private javax.swing.JTextField s56;
    private javax.swing.JTextField s57;
    private javax.swing.JTextField s58;
    private javax.swing.JTextField s60;
    private javax.swing.JTextField s61;
    private javax.swing.JTextField s62;
    private javax.swing.JTextField s63;
    private javax.swing.JTextField s64;
    private javax.swing.JTextField s65;
    private javax.swing.JTextField s66;
    private javax.swing.JTextField s67;
    private javax.swing.JTextField s68;
    private javax.swing.JTextField s70;
    private javax.swing.JTextField s71;
    private javax.swing.JTextField s72;
    private javax.swing.JTextField s73;
    private javax.swing.JTextField s74;
    private javax.swing.JTextField s75;
    private javax.swing.JTextField s76;
    private javax.swing.JTextField s77;
    private javax.swing.JTextField s78;
    private javax.swing.JTextField s80;
    private javax.swing.JTextField s81;
    private javax.swing.JTextField s82;
    private javax.swing.JTextField s83;
    private javax.swing.JTextField s84;
    private javax.swing.JTextField s85;
    private javax.swing.JTextField s86;
    private javax.swing.JTextField s87;
    private javax.swing.JTextField s88;
    private javax.swing.JLabel saveStateL;
    private javax.swing.JLabel solutionL;
    private javax.swing.JLabel submitL;
    // End of variables declaration//GEN-END:variables
}
