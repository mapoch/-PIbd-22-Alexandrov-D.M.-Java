package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FormHangar {
    private Hangar<Plane, Additionals_Draw> hangar;
    cImage img_box;
    BufferedImage buffered_img;
    Graphics gh;

    private void Draw() {
        gh = buffered_img.createGraphics();
        gh.setColor(Color.WHITE);
        gh.fillRect(0,0, img_box.getWidth(), img_box.getHeight());
        hangar.Draw(gh);
        img_box.b_img = buffered_img;
        img_box.repaint();
    }

    public FormHangar() {
        JFrame w = new JFrame("Hangar");
        w.setLayout(null);
        w.setSize(1101, 648);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        img_box = new cImage();
        img_box.setSize(900, 601);
        img_box.setLocation(0, 0);

        buffered_img = new BufferedImage(img_box.getWidth(), img_box.getHeight(), BufferedImage.TYPE_INT_RGB);

        Button buttonSetP = new Button("Add plane");
        buttonSetP.setLocation(906, 12);
        buttonSetP.setSize(165, 47);
        buttonSetP.setVisible(true);

        buttonSetP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mColor = JColorChooser.showDialog(w, "Selection", Color.green);
                if (mColor != null) {
                    Plane plane = new Plane(100, 1000, mColor);
                    int num = hangar.Add(plane);
                    if (num != -1) {
                        JOptionPane.showMessageDialog(w, "Place " + num + " taken");
                        Draw();
                    } else {
                        JOptionPane.showMessageDialog(w, "The whole hangar is filled");
                    }
                }
            }
        });

        Button buttonSetB = new Button("Add bomber plane");
        buttonSetB.setLocation(906, 81);
        buttonSetB.setSize(165, 47);
        buttonSetB.setVisible(true);

        buttonSetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mColor = JColorChooser.showDialog(w, "Selection", Color.green);
                Color aColor = JColorChooser.showDialog(w, "Selection", Color.red);
                Random rnd = new Random();
                if (mColor != null) {
                    if (aColor != null) {

                        Plane_bomber plane = new Plane_bomber(100, 1000, mColor, aColor,
                        true, true, rnd.nextInt(6), rnd.nextInt(3));
                        int num = hangar.Add(plane);
                        if (num != -1) {
                            JOptionPane.showMessageDialog(w, "Place " + num + " taken");
                            Draw();
                        } else {
                            JOptionPane.showMessageDialog(w, "The whole hangar is filled");
                        }
                    }
                }
            }
        });

        JLabel labelUnset = new JLabel("Plane removing");
        labelUnset.setBounds(912, 171, 100, 17);
        labelUnset.setVisible(true);

        JLabel labelPlace = new JLabel("Place:");
        labelPlace.setBounds(912, 207, 53, 17);
        labelPlace.setVisible(true);

        TextField textFieldPlace = new TextField();
        textFieldPlace.setLocation(971, 204);
        textFieldPlace.setSize(94,22);
        textFieldPlace.setVisible(true);

        Button buttonUnset = new Button("Remove");
        buttonUnset.setLocation(915, 242);
        buttonUnset.setSize(150, 33);
        buttonUnset.setVisible(true);

        buttonUnset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldPlace.getText() != "") {
                    int place;
                    try {
                        place = Integer.parseInt(textFieldPlace.getText());
                    }
                    catch (NumberFormatException ex) {
                        return;
                    }

                    ITransport plane = hangar.Remove(place);
                    if (plane != null) {
                        FormPlane form = new FormPlane();
                        form.SetPlane(plane);
                    }
                    Draw();
                }
            }
        });

        w.add(buttonSetP);
        w.add(buttonSetB);
        w.add(labelUnset);
        w.add(labelPlace);
        w.add(textFieldPlace);
        w.add(buttonUnset);
        w.add(img_box);

        w.setVisible(true);

        hangar = new Hangar<>(Plane.class, img_box.getWidth(), img_box.getHeight());

        Draw();
    }
}
