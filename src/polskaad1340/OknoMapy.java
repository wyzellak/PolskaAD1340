/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polskaad1340;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author Kuba
 */
public class OknoMapy extends javax.swing.JFrame {

    public ArrayList<ArrayList<JLabel>> foregroundTileGrid, backgroundTileGrid;
    public int tileSize;

    public ArrayList<ArrayList<JLabel>> createTileGrid(int size, int defaultTileNo) {
        ArrayList<ArrayList<JLabel>> tileGrid = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {

            ArrayList<JLabel> tileGridRow = new ArrayList<>(size);

            for (int j = 0; j < size; j++) {
                //int num = i * size + j; //wszystkie obrazy po kolei
                int num=defaultTileNo;
                String path = "/images/" + num + ".png";

                ImageIcon ii = new ImageIcon(getClass().getResource(path));

                JLabel jl = new JLabel(ii);
                jl.setSize(tileSize, tileSize);
                tileGridRow.add(jl);
            }
            tileGrid.add(tileGridRow);
        }
        return tileGrid;
    }

    public void addTileGridToWindow(ArrayList<ArrayList<JLabel>> tileGrid, JPanel targetPanel) {
        int size = tileGrid.size();

        GridLayout gl = (GridLayout) targetPanel.getLayout();
        if (tileGrid.size() > 0) {
            gl.setRows(tileGrid.size());
            gl.setColumns(tileGrid.size());
            targetPanel.setSize(size * tileSize, size * tileSize);
        }

        for (int i = 0; i < tileGrid.size(); i++) {
            ArrayList<JLabel> arrayList = tileGrid.get(i);
            for (int j = 0; j < arrayList.size(); j++) {
                JLabel jLabel = arrayList.get(j);

                jLabel.setBounds(j * this.tileSize, i * this.tileSize, this.tileSize, this.tileSize);
                targetPanel.add(jLabel);
            }
        }
    }

    /**
     * Creates new form OknoMapy
     */
    public OknoMapy() {
        initComponents();
        this.tileSize = 32;
        foregroundTileGrid = createTileGrid(25,1793);
        backgroundTileGrid = createTileGrid(25,56);
        //addTileGridToWindow(foregroundTileGrid,foregroundPanel);
        System.out.print("done");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        foregroundPanel = new javax.swing.JPanel();
        backgroundPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foregroundPanel.setBackground(new java.awt.Color(102, 102, 255));
        foregroundPanel.setOpaque(false);
        foregroundPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(foregroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 100));

        backgroundPanel.setBackground(new java.awt.Color(0, 204, 204));
        backgroundPanel.setForeground(new java.awt.Color(240, 240, 240));
        backgroundPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        foregroundPanel.removeAll();
        backgroundPanel.removeAll();
        
        if (foregroundTileGrid == null || backgroundTileGrid == null) {
            return;
        }

        addTileGridToWindow(foregroundTileGrid, foregroundPanel);
        addTileGridToWindow(backgroundTileGrid, backgroundPanel);
    }//GEN-LAST:event_formComponentResized

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
            java.util.logging.Logger.getLogger(OknoMapy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OknoMapy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OknoMapy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OknoMapy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OknoMapy().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel foregroundPanel;
    // End of variables declaration//GEN-END:variables
}
