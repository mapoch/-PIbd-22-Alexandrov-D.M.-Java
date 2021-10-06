package com.company;
import java.awt.*;
import javax.swing.*;

public class cImage extends JComponent {
    public Graphics2D g2d;

    public cImage() { super(); }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, getWidth(), getHeight());
    }
}
