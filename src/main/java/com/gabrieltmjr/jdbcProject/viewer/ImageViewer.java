package com.gabrieltmjr.jdbcProject.viewer;

import javax.swing.*;
import java.awt.*;

public class ImageViewer extends JFrame {

    public ImageViewer(ImageIcon image) {
        super("Image");
        BorderLayout b = new BorderLayout();
        Container cont = new Container();
        cont = getContentPane();
        cont.setLayout(b);
        cont.setBackground(Color.BLACK);
        JLabel lblProductImage = new JLabel();
        lblProductImage.setIcon(image);
        cont.add(lblProductImage,BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(image.getIconWidth(),image.getIconHeight()));
    }
}
