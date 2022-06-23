package com.gabrieltmjr.jdbcProject.controller.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class ManageImage {
    private final byte[] blobImage;

    public ManageImage(byte[] blobImage) {
        this.blobImage = blobImage;
    }

    public ImageIcon blobToImageIcon() {
        ByteArrayInputStream bis = new ByteArrayInputStream(blobImage);

        try {
            return new ImageIcon(ImageIO.read(bis));
        } catch (IOException i) {
            System.out.println(i.getMessage());
            return null;
        }
    }


}
