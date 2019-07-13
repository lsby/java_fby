package com.hby;

import javax.swing.*;

public class Main extends javax.swing.JFrame {
    public Main() {
        initComponents();
    }

    private void initComponents() {
        JScrollPane 面板 = new JScrollPane();
        提示区类 提示区 = new 提示区类();

        getContentPane().add(面板, java.awt.BorderLayout.CENTER);
        面板.setViewportView(提示区);

        提示区.addKeyListener(提示区);
        提示区.setRows(20);
        提示区.setColumns(40);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
