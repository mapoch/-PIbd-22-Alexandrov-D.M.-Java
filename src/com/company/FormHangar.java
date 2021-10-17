package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class FormHangar {
    private Hangar<Plane, Additionals_Draw> hangar;
    JComponent img;
    Graphics gf;

    public FormHangar() {
        JFrame w = new JFrame("Formation");
        w.setLayout(null);
        w.setSize(1101, 648);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        img = new cImage();
        img.setSize(900, 601);
        img.setBackground(Color.WHITE);
        img.setLocation(0, 0);
        img.setVisible(true);

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
                        img.update(gf);
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
                        img.update(gf);
                        Plane_bomber plane = new Plane_bomber(100, 1000, mColor, aColor,
                        true, true, rnd.nextInt(6), rnd.nextInt(3));
                        int num = hangar.Add(plane);
                        if (num != -1) {
                            img.update(gf);
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
                    img.update(gf);
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
        w.add(img);

        w.setVisible(true);

        hangar = new Hangar<>(Plane.class, img.getWidth(), img.getHeight());

        gf = img.getGraphics();
        Draw();
    }

    private void Draw() {
        hangar.Draw(gf);
    }
}
