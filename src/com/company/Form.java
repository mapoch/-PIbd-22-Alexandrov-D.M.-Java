package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Random;

public class Form {

    Plane plane;
    JComponent image;
    Graphics g;

    private void Draw() {
        plane.DrawObject(g);
    }

    public Form() {
        JFrame w = new JFrame("Plane");
        w.setLayout(null);
        w.setSize(1100, 647);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        image = new cImage();
        image.setSize(675, 488);
        image.setBackground(Color.WHITE);
        image.setLocation(0, 0);
        image.setVisible(true);

        Button buttonCreateP = new Button("Create plane");
        buttonCreateP.setLocation(680, 19);
        buttonCreateP.setSize(123, 24);
        buttonCreateP.setVisible(true);

        buttonCreateP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g = image.getGraphics();
                image.update(g);
                Random rnd = new Random();
                plane = new Plane(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                        Color.GREEN, Color.RED, true, true, rnd.nextInt(6));
                plane.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10,
                        image.getWidth(), image.getHeight());

                Draw();
            }
        });

        JButton buttonMoveUp = new JButton();
        buttonMoveUp.setLocation(700, 319);
        buttonMoveUp.setSize(85, 41);
        buttonMoveUp.setBackground(Color.YELLOW);
        buttonMoveUp.setVisible(true);
        buttonMoveUp.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-top.png")
                .getImage().getScaledInstance(85, 41, Image.SCALE_AREA_AVERAGING)));
        buttonMoveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.update(g);
                plane.MoveObject(Direction.Up);
                Draw();
            }
        });

        JButton buttonMoveDown = new JButton();
        buttonMoveDown.setLocation(700, 447);
        buttonMoveDown.setSize(85, 41);
        buttonMoveDown.setBackground(Color.YELLOW);
        buttonMoveDown.setVisible(true);
        buttonMoveDown.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-bottom.png").
                getImage().getScaledInstance(85, 41, Image.SCALE_AREA_AVERAGING)));
        buttonMoveDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.update(g);
                plane.MoveObject(Direction.Down);
                Draw();
            }
        });

        JButton buttonMoveRight = new JButton();
        buttonMoveRight.setLocation(761, 365);
        buttonMoveRight.setSize(41, 77);
        buttonMoveRight.setBackground(Color.YELLOW);
        buttonMoveRight.setVisible(true);
        buttonMoveRight.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-right.png").
                getImage().getScaledInstance(41, 77, Image.SCALE_AREA_AVERAGING)));
        buttonMoveRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.update(g);
                plane.MoveObject(Direction.Right);
                Draw();
            }
        });

        JButton buttonMoveLeft = new JButton();
        buttonMoveLeft.setLocation(680, 365);
        buttonMoveLeft.setSize(41, 77);
        buttonMoveLeft.setBackground(Color.YELLOW);
        buttonMoveLeft.setVisible(true);
        buttonMoveLeft.setIcon(new ImageIcon(new ImageIcon("src/com/company/materials/arrow-pointing-left.png").
                getImage().getScaledInstance(41, 77, Image.SCALE_AREA_AVERAGING)));
        buttonMoveLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image.update(g);
                plane.MoveObject(Direction.Left);
                Draw();
            }
        });

        w.add(buttonCreateP);
        w.add(buttonMoveUp);
        w.add(buttonMoveDown);
        w.add(buttonMoveRight);
        w.add(buttonMoveLeft);
        w.add(image);

        w.setVisible(true);
    }
}
