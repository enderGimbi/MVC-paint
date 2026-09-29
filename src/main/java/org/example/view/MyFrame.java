/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private MyPanel panel;

    public MyFrame() {
        setTitle("pain");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
        setJMenuBar(panel.getMenuBar());
        add(panel);
    }


} 
