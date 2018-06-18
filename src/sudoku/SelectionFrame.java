/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author soft
 */
public class SelectionFrame extends javax.swing.JFrame {

    /**
     * Creates new form Selection
     */
    
    public SelectionFrame() {
        initComponents();
        
        imageL.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/images/logo1.png")),imageL));
        this.setLocationRelativeTo(null);
        
        typeBG.add(solvePuzzleRB);
        typeBG.add(addPuzzleRB);
        
        difficultyBG.add(easyRB);
        difficultyBG.add(mediumRB);
        difficultyBG.add(hardRB);
        
        solvePuzzleRB.setSelected(true);
        easyRB.setSelected(true);
        BasicSudoku.easy=true;
        BasicSudoku.solvePuzzle=true;
        
        String path=Paths.get("").toAbsolutePath().toString();
     
        File f=new File(path+"/puzzle");
        f.mkdir();
        
        path+="/puzzle";
        
        try
        {
            f=new File(path+"\\easy.txt");
            f.createNewFile();
            f=new File(path+"\\medium.txt");
            f.createNewFile();
            f=new File(path+"\\hard.txt");
            f.createNewFile();
            
        }
        catch(IOException ioe)
        {
            System.out.println("Exception in SelectionFrame ctor: "+ioe.getMessage());
        }
        
    }
    
    public static ImageIcon resizeIcon(ImageIcon imageIcon, JLabel label)
    {
        Image image=imageIcon.getImage();
        image=image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon=new ImageIcon(image);
        
        return scaledIcon;
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeBG = new javax.swing.ButtonGroup();
        difficultyBG = new javax.swing.ButtonGroup();
        mainP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        findSolutionL = new javax.swing.JLabel();
        puzzleL = new javax.swing.JLabel();
        imageL = new javax.swing.JLabel();
        optionsP = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        easyRB = new javax.swing.JRadioButton();
        mediumRB = new javax.swing.JRadioButton();
        hardRB = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        addPuzzleRB = new javax.swing.JRadioButton();
        solvePuzzleRB = new javax.swing.JRadioButton();
        proceedL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        mainP.setBackground(new java.awt.Color(102, 0, 102));

        jPanel2.setBackground(new java.awt.Color(153, 0, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        findSolutionL.setBackground(new java.awt.Color(255, 51, 255));
        findSolutionL.setFont(new java.awt.Font("Comic Sans MS", 0, 25)); // NOI18N
        findSolutionL.setForeground(new java.awt.Color(255, 255, 255));
        findSolutionL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        findSolutionL.setText("Find Solution");
        findSolutionL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        findSolutionL.setOpaque(true);
        findSolutionL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findSolutionLMouseClicked(evt);
            }
        });

        puzzleL.setBackground(new java.awt.Color(255, 51, 255));
        puzzleL.setFont(new java.awt.Font("Comic Sans MS", 0, 25)); // NOI18N
        puzzleL.setForeground(new java.awt.Color(255, 255, 255));
        puzzleL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puzzleL.setText("Puzzle");
        puzzleL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        puzzleL.setOpaque(true);
        puzzleL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                puzzleLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(puzzleL, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(findSolutionL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findSolutionL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puzzleL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        imageL.setFont(new java.awt.Font("Comic Sans MS", 0, 25)); // NOI18N
        imageL.setForeground(new java.awt.Color(255, 255, 255));
        imageL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout mainPLayout = new javax.swing.GroupLayout(mainP);
        mainP.setLayout(mainPLayout);
        mainPLayout.setHorizontalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageL, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPLayout.setVerticalGroup(
            mainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageL, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        getContentPane().add(mainP, "card2");

        optionsP.setBackground(new java.awt.Color(102, 0, 102));
        optionsP.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setOpaque(false);

        easyRB.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        easyRB.setForeground(new java.awt.Color(255, 255, 255));
        easyRB.setText("Easy");
        easyRB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        easyRB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        easyRB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        easyRB.setOpaque(false);
        easyRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyRBActionPerformed(evt);
            }
        });

        mediumRB.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        mediumRB.setForeground(new java.awt.Color(255, 255, 255));
        mediumRB.setText("Medium");
        mediumRB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mediumRB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mediumRB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        mediumRB.setOpaque(false);
        mediumRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediumRBActionPerformed(evt);
            }
        });

        hardRB.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        hardRB.setForeground(new java.awt.Color(255, 255, 255));
        hardRB.setText("Hard");
        hardRB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hardRB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hardRB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        hardRB.setOpaque(false);
        hardRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardRBActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose Difficulty");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hardRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mediumRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(easyRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(easyRB, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(mediumRB, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(hardRB, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setOpaque(false);

        addPuzzleRB.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        addPuzzleRB.setForeground(new java.awt.Color(255, 255, 255));
        addPuzzleRB.setText("Add a Puzzle");
        addPuzzleRB.setOpaque(false);
        addPuzzleRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPuzzleRBActionPerformed(evt);
            }
        });

        solvePuzzleRB.setFont(new java.awt.Font("Comic Sans MS", 0, 22)); // NOI18N
        solvePuzzleRB.setForeground(new java.awt.Color(255, 255, 255));
        solvePuzzleRB.setText("Solve a Puzzle");
        solvePuzzleRB.setOpaque(false);
        solvePuzzleRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvePuzzleRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(solvePuzzleRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(addPuzzleRB, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPuzzleRB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solvePuzzleRB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        proceedL.setFont(new java.awt.Font("Comic Sans MS", 1, 72)); // NOI18N
        proceedL.setForeground(new java.awt.Color(255, 255, 255));
        proceedL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proceedL.setText(">");
        proceedL.setToolTipText("Proceed");
        proceedL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        proceedL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proceedLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout optionsPLayout = new javax.swing.GroupLayout(optionsP);
        optionsP.setLayout(optionsPLayout);
        optionsPLayout.setHorizontalGroup(
            optionsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(optionsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(proceedL, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPLayout.setVerticalGroup(
            optionsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(optionsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(proceedL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(optionsPLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        getContentPane().add(optionsP, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findSolutionLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findSolutionLMouseClicked
        Solver solver=new Solver();
        solver.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_findSolutionLMouseClicked

    private void proceedLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedLMouseClicked
       File f;
        if(BasicSudoku.solvePuzzle)
       {
          this.setVisible(false);
          SolvingOptionsFrame sof=new SolvingOptionsFrame();
          sof.setVisible(true);
       }
       else
       {
           InsertPuzzle ip=new InsertPuzzle();
           ip.setVisible(true);
           this.setVisible(false);
       }
       
    }//GEN-LAST:event_proceedLMouseClicked

    private void addPuzzleRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPuzzleRBActionPerformed
        BasicSudoku.solvePuzzle=false;
    }//GEN-LAST:event_addPuzzleRBActionPerformed

    private void solvePuzzleRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvePuzzleRBActionPerformed
        BasicSudoku.solvePuzzle=true;
    }//GEN-LAST:event_solvePuzzleRBActionPerformed

    private void easyRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyRBActionPerformed
        BasicSudoku.easy=true;
        BasicSudoku.medium=false;
        BasicSudoku.hard=false;
    }//GEN-LAST:event_easyRBActionPerformed

    private void mediumRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumRBActionPerformed
        BasicSudoku.easy=false;
        BasicSudoku.medium=true;
        BasicSudoku.hard=false;
    }//GEN-LAST:event_mediumRBActionPerformed

    private void hardRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardRBActionPerformed
        BasicSudoku.easy=false;
        BasicSudoku.medium=false;
        BasicSudoku.hard=true;
    }//GEN-LAST:event_hardRBActionPerformed

    private void puzzleLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puzzleLMouseClicked
      optionsP.setVisible(true);
      mainP.setVisible(false);
    }//GEN-LAST:event_puzzleLMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException{
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
            java.util.logging.Logger.getLogger(SelectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectionFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton addPuzzleRB;
    private javax.swing.ButtonGroup difficultyBG;
    private javax.swing.JRadioButton easyRB;
    private javax.swing.JLabel findSolutionL;
    private javax.swing.JRadioButton hardRB;
    private javax.swing.JLabel imageL;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel mainP;
    private javax.swing.JRadioButton mediumRB;
    private javax.swing.JPanel optionsP;
    private javax.swing.JLabel proceedL;
    private javax.swing.JLabel puzzleL;
    private javax.swing.JRadioButton solvePuzzleRB;
    private javax.swing.ButtonGroup typeBG;
    // End of variables declaration//GEN-END:variables
}
