package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class FormHangar {
    private HangarsCollection hangarsCollection;
    private LinkedList<Vehicle> deleted;
    cImage img_box;
    BufferedImage buffered_img;
    Graphics gh;
    JList list_box;

    void ReloadLevels() {
        int index = list_box.getSelectedIndex();
        DefaultListModel list = new DefaultListModel();
        for (int i = 0; i < hangarsCollection.getKeys().size(); i++) {
            list.addElement(hangarsCollection.getKeys().get(i));
        }
        list_box.setModel(list);

        if (list_box.getVisibleRowCount() > 0 && (index == -1 || index >= list_box.getVisibleRowCount())) {
            list_box.setSelectedIndex(0);
        }
        else if (list_box.getVisibleRowCount() > 0 && index > -1 && index < list_box.getVisibleRowCount()) {
            list_box.setSelectedIndex(index);
        }
        Draw();
    }

    private void Draw() {
        if (list_box.getSelectedIndex() > -1) {
            gh = buffered_img.createGraphics();
            gh.setColor(Color.WHITE);
            gh.fillRect(0, 0, img_box.getWidth(), img_box.getHeight());
            hangarsCollection.getValue((String) list_box.getSelectedValue()).Draw(gh);
            img_box.b_img = buffered_img;
            img_box.repaint();
        }
        else {
            gh = buffered_img.createGraphics();
            gh.setColor(Color.WHITE);
            gh.fillRect(0, 0, img_box.getWidth(), img_box.getHeight());
            img_box.b_img = buffered_img;
            img_box.repaint();
        }
    }

    public FormHangar() {
        JFrame w = new JFrame("Hangar");
        w.setLayout(null);
        w.setSize(1101, 648);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        img_box = new cImage();
        img_box.setSize(900, 601);
        img_box.setLocation(0, 0);
        img_box.setVisible(true);

        buffered_img = new BufferedImage(img_box.getWidth(), img_box.getHeight(), BufferedImage.TYPE_INT_RGB);

        JLabel labelHangar = new JLabel("Hangar:");
        labelHangar.setBounds(912, 3, 53, 17);
        labelHangar.setVisible(true);

        TextField textFieldHangar = new TextField();
        textFieldHangar.setLocation(971, 1);
        textFieldHangar.setSize(94,22);
        textFieldHangar.setVisible(true);

        JButton buttonAddHangar = new JButton("Add hangar");
        buttonAddHangar.setLocation(906, 25);
        buttonAddHangar.setSize(165, 45);
        buttonAddHangar.setVisible(true);

        buttonAddHangar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldHangar.getText().equals("")) {
                    JOptionPane.showMessageDialog(w, "The name is not printed");
                    return;
                }
                hangarsCollection.AddHangar(textFieldHangar.getText());
                ReloadLevels();
            }
        });

        list_box = new JList();
        list_box.setLocation(906, 75);
        list_box.setSize(165,100);
        list_box.setVisible(true);

        list_box.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Draw();
            }
        });

        JButton buttonDeleteHangar = new JButton("Delete hangar");
        buttonDeleteHangar.setLocation(906, 180);
        buttonDeleteHangar.setSize(165, 45);
        buttonDeleteHangar.setVisible(true);

        buttonDeleteHangar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list_box.getSelectedIndex() > -1) {
                    if (JOptionPane.showConfirmDialog(w,
                            "Are you want to delete hangar" + list_box.getSelectedValue().toString() + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                        hangarsCollection.DelHangar(textFieldHangar.getText());
                        ReloadLevels();
                    }
                }
            }
        });

        Button buttonSetP = new Button("Add plane");
        buttonSetP.setLocation(906, 230);
        buttonSetP.setSize(165, 45);
        buttonSetP.setVisible(true);

        buttonSetP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list_box.getSelectedIndex() > -1) {
                    Color mColor = JColorChooser.showDialog(w, "Selection", Color.green);
                    if (mColor != null) {
                        Plane plane = new Plane(100, 1000, mColor);
                        int num = hangarsCollection.getValue(list_box.getSelectedValue().toString()).Add(plane);
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

        Button buttonSetB = new Button("Add bomber plane");
        buttonSetB.setLocation(906, 280);
        buttonSetB.setSize(165, 45);
        buttonSetB.setVisible(true);

        buttonSetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list_box.getSelectedIndex() > -1) {
                    Color mColor = JColorChooser.showDialog(w, "Selection", Color.green);
                    Color aColor = JColorChooser.showDialog(w, "Selection", Color.red);
                    Random rnd = new Random();
                    if (mColor != null) {
                        if (aColor != null) {

                            Plane_bomber plane = new Plane_bomber(100, 1000, mColor, aColor,
                                    true, true, rnd.nextInt(6), rnd.nextInt(3));
                            int num = hangarsCollection.getValue(list_box.getSelectedValue().toString()).Add(plane);
                            if (num != -1) {
                                JOptionPane.showMessageDialog(w, "Place " + num + " taken");
                                Draw();
                            } else {
                                JOptionPane.showMessageDialog(w, "The whole hangar is filled");
                            }
                        }
                    }
                }
            }
        });

        JLabel labelUnset = new JLabel("Plane removing");
        labelUnset.setBounds(912, 330, 100, 17);
        labelUnset.setVisible(true);

        JLabel labelPlace = new JLabel("Place:");
        labelPlace.setBounds(912, 350, 53, 17);
        labelPlace.setVisible(true);

        TextField textFieldPlace = new TextField();
        textFieldPlace.setLocation(971, 350);
        textFieldPlace.setSize(94,22);
        textFieldPlace.setVisible(true);

        Button buttonUnset = new Button("Remove");
        buttonUnset.setLocation(915, 375);
        buttonUnset.setSize(150, 33);
        buttonUnset.setVisible(true);

        buttonUnset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list_box.getSelectedIndex() > -1 && textFieldPlace.getText() != "") {
                    int place;
                    try {
                        place = Integer.parseInt(textFieldPlace.getText());
                    }
                    catch (NumberFormatException ex) {
                        return;
                    }

                    Vehicle plane = hangarsCollection.getValue(list_box.getSelectedValue().toString()).Remove(place);
                    if (plane != null) deleted.add(plane);
                    Draw();
                }
            }
        });

        JLabel labelDeleted = new JLabel("Place in deleted list:");
        labelDeleted.setBounds(912, 410, 120, 17);
        labelDeleted.setVisible(true);

        TextField textFieldDeleted = new TextField();
        textFieldDeleted.setLocation(971, 430);
        textFieldDeleted.setSize(94,22);
        textFieldDeleted.setVisible(true);

        Button buttonMove = new Button("Move to plane form");
        buttonMove.setLocation(915, 455);
        buttonMove.setSize(150, 33);
        buttonMove.setVisible(true);

        buttonMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldPlace.getText() != "") {
                    int ind;
                    try {
                        ind = Integer.parseInt(textFieldDeleted.getText());
                    }
                    catch (NumberFormatException ex) {
                        return;
                    }

                    if (deleted.get(ind) != null) {
                        FormPlane form = new FormPlane();
                        form.SetPlane(deleted.get(ind));
                        deleted.remove(ind);
                    }
                    Draw();
                }
            }
        });

        w.add(labelHangar);
        w.add(textFieldHangar);
        w.add(buttonAddHangar);
        w.add(buttonDeleteHangar);
        w.add(list_box);
        w.add(buttonSetP);
        w.add(buttonSetB);
        w.add(labelUnset);
        w.add(labelPlace);
        w.add(textFieldPlace);
        w.add(buttonUnset);
        w.add(img_box);
        w.add(labelDeleted);
        w.add(textFieldDeleted);
        w.add(buttonMove);

        w.setVisible(true);

        hangarsCollection = new HangarsCollection(img_box.getWidth(), img_box.getHeight());
        deleted = new LinkedList<Vehicle>();

        Draw();
    }
}
