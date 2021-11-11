package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Random;
import java.awt.image.*;

public class FormPlane {

    ITransport plane;
    cImage image_box;
    BufferedImage buff_img;
    Graphics g;

    private void Draw() {
        g = buff_img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, image_box.getWidth(), image_box.getHeight());
        plane.DrawObject(g);
        image_box.b_img = buff_img;
        image_box.repaint();
    }

    public void SetPlane(ITransport new_plane) {
        this.plane = new_plane;
        Draw();
    }

    public FormPlane() {
        JFrame w = new JFrame("Plane");
        w.setLayout(null);
        w.setSize(1101, 648);
        w.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        image_box = new cImage();
        image_box.setSize(900, 601);
        image_box.setBackground(Color.WHITE);
        image_box.setLocation(0, 0);
        image_box.setVisible(true);

        buff_img = new BufferedImage(image_box.getWidth(), image_box.getHeight(), BufferedImage.TYPE_INT_RGB);

        Button buttonCreateP = new Button("Create plane");
        buttonCreateP.setLocation(907, 23);
        buttonCreateP.setSize(164, 30);
        buttonCreateP.setVisible(true);

        buttonCreateP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                plane = new Plane(rnd.nextInt(200) + 100,
                        rnd.nextInt(1000) + 1000, Color.GREEN);
                plane.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10,
                        image_box.getWidth(), image_box.getHeight());

                Draw();
            }
        });

        Button buttonCreateB = new Button("Create bomber");
        buttonCreateB.setLocation(907, 58);
        buttonCreateB.setSize(164, 30);
        buttonCreateB.setVisible(true);

        buttonCreateB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                plane = new Plane_bomber(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                        Color.GREEN, Color.RED, true, true,
                        rnd.nextInt(6), rnd.nextInt(3));
                plane.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10,
                        image_box.getWidth(), image_box.getHeight());

                Draw();
            }
        });

        JButton buttonMoveUp = new JButton();
        buttonMoveUp.setLocation(933, 393);
        buttonMoveUp.setSize(113, 50);
        buttonMoveUp.setBackground(Color.YELLOW);
        buttonMoveUp.setVisible(true);
        buttonMoveUp.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-top.png")
                .getImage().getScaledInstance(85, 41, Image.SCALE_AREA_AVERAGING)));
        buttonMoveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plane.MoveObject(Direction.Up);
                Draw();
            }
        });

        JButton buttonMoveDown = new JButton();
        buttonMoveDown.setLocation(933, 550);
        buttonMoveDown.setSize(113, 50);
        buttonMoveDown.setBackground(Color.YELLOW);
        buttonMoveDown.setVisible(true);
        buttonMoveDown.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-bottom.png").
                getImage().getScaledInstance(85, 41, Image.SCALE_AREA_AVERAGING)));
        buttonMoveDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plane.MoveObject(Direction.Down);
                Draw();
            }
        });

        JButton buttonMoveRight = new JButton();
        buttonMoveRight.setLocation(1015, 449);
        buttonMoveRight.setSize(55, 95);
        buttonMoveRight.setBackground(Color.YELLOW);
        buttonMoveRight.setVisible(true);
        buttonMoveRight.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-right.png").
                getImage().getScaledInstance(41, 77, Image.SCALE_AREA_AVERAGING)));
        buttonMoveRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plane.MoveObject(Direction.Right);
                Draw();
            }
        });

        JButton buttonMoveLeft = new JButton();
        buttonMoveLeft.setLocation(907, 449);
        buttonMoveLeft.setSize(55, 95);
        buttonMoveLeft.setBackground(Color.YELLOW);
        buttonMoveLeft.setVisible(true);
        buttonMoveLeft.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-left.png").
                getImage().getScaledInstance(41, 77, Image.SCALE_AREA_AVERAGING)));
        buttonMoveLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plane.MoveObject(Direction.Left);
                Draw();
            }
        });

        w.add(buttonCreateP);
        w.add(buttonCreateB);
        w.add(buttonMoveUp);
        w.add(buttonMoveDown);
        w.add(buttonMoveRight);
        w.add(buttonMoveLeft);
        w.add(image_box);

        w.setVisible(true);
    }
}
