package com.company;
import java.awt.*;
import javax.swing.*;

public class cImage extends JComponent {
    public Graphics2D g2d;
    Image b_img;
    public cImage() { super(); }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;

        g2d.drawImage(b_img, 0, 0, this);
    }
}
