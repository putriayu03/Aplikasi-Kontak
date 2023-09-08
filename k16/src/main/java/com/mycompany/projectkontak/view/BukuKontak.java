/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.projectkontak.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.ConstUtil;

/**
 *
 * @author Putri Ayu
 */
public class BukuKontak extends javax.swing.JPanel {

    private static final String username = "root";
    private static final String password = "putri2003";
    private static final String dataConn = "jdbc:mysql://localhost:3306/k16";
    
    Connection sqlConn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int q, i , id;
    
    private final Menu utama;
    /**
     * Creates new form BukuKontak
     * @param _utama
     */
    public BukuKontak(Menu _utama) {
        this.utama = _utama;
        initComponents();
        updateTable("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 204, 51));
        setPreferredSize(new java.awt.Dimension(541, 496));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Buku Kontak");

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setText("Pencarian");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "No Telp", "Kategori", "Email", "Alamat"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(201, 201, 201))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        updateTable(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyTyped

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        updateTable(jTextField1.getText());
    }//GEN-LAST:event_formMouseEntered

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public void updateTable(String val){
        val = "%" + val + "%";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("select nama, no_telp, kategori, email, alamat from kontak WHERE user_id = ? AND (nama LIKE ? OR no_telp LIKE ?)");

            pst.setInt(1, ConstUtil.user_id);
            pst.setString(2, val);
            pst.setString(3, val);
            
            rs = pst.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
            RecordTable.setRowCount(0);
            int idx = 1;
            while(rs.next()){
                List<String> columnData = new ArrayList<String>();
                
                for(i = 1; i <= q; i++){
                    columnData.add( String.valueOf(idx));
                    columnData.add(String.valueOf(rs.getString("nama")));
                    columnData.add(String.valueOf(rs.getString("no_telp")));
                    columnData.add(String.valueOf(rs.getString("kategori")));
                    columnData.add(String.valueOf(rs.getString("email")));
                    columnData.add(String.valueOf(rs.getString("alamat")));
                }
                idx++;
                RecordTable.addRow(columnData.toArray());
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
