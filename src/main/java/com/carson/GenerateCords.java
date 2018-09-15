package com.carson;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class GenerateCords {

    public static void main(String[] args) throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Get X and Y coordinates");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        frame.getContentPane().setLayout(new FlowLayout());
        BufferedImage im = ImageIO.read(new File("img/map.png"));
        frame.setSize(im.getWidth(),im.getHeight());
        frame.getContentPane().add(new JLabel(new ImageIcon(im)));


        final JTextField text = new JTextField();;
        frame.add(text,BorderLayout.SOUTH);

        frame.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                System.out.println(x + "," + y);
            }
        });

        frame.setVisible(true);
    }
}
