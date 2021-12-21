package com.company;

import com.sun.deploy.uitoolkit.DragListener;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.dnd.DropTarget;
import java.awt.datatransfer.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;
import javax.swing.border.*;
import javax.swing.TransferHandler;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.datatransfer.Transferable;

public class FormPlaneConfig extends JFrame implements DragGestureListener {
    private ITransport plane;

    public ITransport GetPlane() { return plane; }

    public JFrame w;
    cImage image_box;
    BufferedImage buff_img;
    Graphics g;

    JLabel mainColorButton;
    JLabel addColorButton;

    private WindowAdapter eventAddPlane;

    private void Draw() {
        g = buff_img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, image_box.getWidth(), image_box.getHeight());
        if (plane != null) {
            plane.DrawObject(g);
        }
        image_box.b_img = buff_img;
        image_box.repaint();
    }

    public void updatePlaneBombs(boolean state) {
        if (plane == null) return;
        if (plane.getClass().equals(Plane_bomber.class)) ((Plane_bomber)plane).SetBombs(state);
    }

    public void updatePlaneCabin(boolean state) {
        if (plane == null) return;
        if (plane.getClass().equals(Plane_bomber.class)) ((Plane_bomber)plane).SetCabin(state);
    }

    public void ChangeLoad(int k) {
        if (plane == null) return;
        ((Vehicle)plane).SetLoad(k);
    }

    public void ChangeSpeed(int k) {
        if (plane == null) return;
        ((Vehicle)plane).SetSpeed(k);
    }

    public void ChangeAmm(int k) {
        if (plane == null) return;
        if (plane.getClass().equals(Plane_bomber.class)) ((Plane_bomber)plane).SetAmm(k);
    }

    public void AddEvent(WindowAdapter ev) {
        if (eventAddPlane == null)
        {
            eventAddPlane = ev;
        }
        else
        {
            eventAddPlane = ev;
        }
    }

    public FormPlaneConfig() {
        super("Plane configs");
        this.setLayout(null);
        this.setSize(827, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        w = this;

        image_box = new cImage();
        image_box.setSize(279, 195);
        image_box.setBackground(Color.WHITE);
        image_box.setLocation(234, 29);
        image_box.setVisible(true);

        new imDropTarget(image_box);

        buff_img = new BufferedImage(image_box.getWidth(), image_box.getHeight(), BufferedImage.TYPE_INT_RGB);

        JPanel panelParameters = new JPanel();
        panelParameters.setLocation(12, 270);
        panelParameters.setSize(501,147);
        panelParameters.setLayout(null);
        panelParameters.setVisible(true);

        JCheckBox checkBoxBombs = new JCheckBox("bombs");
        checkBoxBombs.setLocation(313, 77);
        checkBoxBombs.setSize(120, 21);
        checkBoxBombs.setSelected(true);
        checkBoxBombs.setVisible(true);

        checkBoxBombs.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                updatePlaneBombs(checkBoxBombs.isSelected());
                Draw();
            }
        });

        JCheckBox checkBoxCabin = new JCheckBox("cabin");
        checkBoxCabin.setLocation(313, 50);
        checkBoxCabin.setSize(120, 21);
        checkBoxCabin.setSelected(true);
        checkBoxCabin.setVisible(true);

        checkBoxCabin.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updatePlaneCabin(checkBoxCabin.isSelected());
                Draw();
            }
        });

        SpinnerNumberModel spinnerSModel = new SpinnerNumberModel(100, 100, 1000, 1);
        SpinnerNumberModel spinnerLModel = new SpinnerNumberModel(100, 100, 1000, 1);

        JSpinner spinnerSpeed = new JSpinner(spinnerSModel);
        spinnerSpeed.setLocation(131, 49);
        spinnerSpeed.setSize(120, 22);
        spinnerSpeed.setVisible(true);

        spinnerSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ChangeSpeed((int)spinnerSpeed.getValue());
            }
        });

        JLabel labelSpeed = new JLabel("Speed:");
        labelSpeed.setLocation(13, 49);
        labelSpeed.setSize(112, 17);
        labelSpeed.setVisible(true);

        JSpinner spinnerLoad = new JSpinner(spinnerLModel);
        spinnerLoad.setLocation(131, 75);
        spinnerLoad.setSize(120, 22);
        spinnerLoad.setVisible(true);

        spinnerLoad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ChangeLoad((int)spinnerLoad.getValue());
            }
        });

        JLabel labelLoad = new JLabel("Load weight:");
        labelLoad.setLocation(13, 75);
        labelLoad.setSize(112, 17);
        labelLoad.setVisible(true);

        JSpinner spinnerAmm = new JSpinner(new SpinnerNumberModel(2, 2, 6, 2));
        spinnerAmm.setLocation(131, 100);
        spinnerAmm.setSize(120, 22);
        spinnerAmm.setVisible(true);

        spinnerAmm.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ChangeAmm((int)spinnerAmm.getValue());
                Draw();
            }
        });

        JLabel labelAmm = new JLabel("Ammo amount:");
        labelAmm.setLocation(13, 100);
        labelAmm.setSize(112, 17);

        panelParameters.add(checkBoxBombs);
        panelParameters.add(checkBoxCabin);
        panelParameters.add(spinnerLoad);
        panelParameters.add(labelLoad);
        panelParameters.add(spinnerSpeed);
        panelParameters.add(labelSpeed);
        panelParameters.add(spinnerAmm);
        panelParameters.add(labelAmm);

        addJLabel ibButton = new addJLabel("Inner bombs", 0);
        ibButton.setLocation(13, 225);
        ibButton.setSize(95, 25);
        ibButton.setBorder(new LineBorder(Color.BLACK));
        ibButton.setVisible(true);

        addJLabel obButton = new addJLabel("Outer bombs", 1);
        obButton.setLocation(113, 225);
        obButton.setSize(95, 25);
        obButton.setBorder(new LineBorder(Color.BLACK));
        obButton.setVisible(true);

        addJLabel orButton = new addJLabel("Outer rockets", 2);
        orButton.setLocation(213, 225);
        orButton.setSize(95, 25);
        orButton.setBorder(new LineBorder(Color.BLACK));
        orButton.setVisible(true);

        DragSource drsa = new DragSource();
        drsa.createDefaultDragGestureRecognizer(ibButton, DnDConstants.ACTION_COPY, this);
        drsa.createDefaultDragGestureRecognizer(obButton, DnDConstants.ACTION_COPY, this);
        drsa.createDefaultDragGestureRecognizer(orButton, DnDConstants.ACTION_COPY, this);

        JLabel planeButton = new JLabel(" Military plane");
        planeButton.setLocation(18,71);
        planeButton.setSize(188, 61);
        planeButton.setBorder(new LineBorder(Color.BLACK));
        planeButton.setVisible(true);

        JLabel bomberButton = new JLabel(" Bomber plane");
        bomberButton.setLocation(18, 148);
        bomberButton.setSize(188, 61);
        bomberButton.setBorder(new LineBorder(Color.BLACK));
        bomberButton.setVisible(true);

        new addDropTarget(bomberButton);

        DragSource drs = new DragSource();
        drs.createDefaultDragGestureRecognizer(planeButton, DnDConstants.ACTION_COPY, this);
        drs.createDefaultDragGestureRecognizer(bomberButton, DnDConstants.ACTION_COPY, this);

        JPanel colorsPanel = new JPanel();
        colorsPanel.setLocation(540, 29);
        colorsPanel.setSize(257, 222);
        colorsPanel.setLayout(null);
        colorsPanel.setVisible(true);

        mainColorButton = new JLabel("Main color");
        mainColorButton.setLocation(8, 27);
        mainColorButton.setSize(116, 50);
        mainColorButton.setVisible(true);

        addColorButton = new JLabel("Add. color");
        addColorButton.setLocation(130, 27);
        addColorButton.setSize(116, 50);
        addColorButton.setVisible(true);

        new colDropTarget(mainColorButton);
        new colDropTarget(addColorButton);

        JLabel redButton = new JLabel("RED");
        redButton.setLocation(8, 100);
        redButton.setSize(55,55);
        redButton.setBorder(new LineBorder(Color.RED));
        redButton.setForeground(Color.RED);
        redButton.setVisible(true);

        JLabel greenButton = new JLabel("GREEN");
        greenButton.setLocation(69, 100);
        greenButton.setSize(55,55);
        greenButton.setBorder(new LineBorder(Color.GREEN));
        greenButton.setForeground(Color.GREEN);
        greenButton.setVisible(true);

        JLabel blueButton = new JLabel("BLUE");
        blueButton.setLocation(130, 100);
        blueButton.setSize(55,55);
        blueButton.setBorder(new LineBorder(Color.BLUE));
        blueButton.setForeground(Color.BLUE);
        blueButton.setVisible(true);

        JLabel yellowButton = new JLabel("YELLOW");
        yellowButton.setLocation(191, 100);
        yellowButton.setSize(55,55);
        yellowButton.setBorder(new LineBorder(Color.YELLOW));
        yellowButton.setForeground(Color.YELLOW);
        yellowButton.setVisible(true);

        JLabel orangeButton = new JLabel("ORANGE");
        orangeButton.setLocation(191, 161);
        orangeButton.setSize(55,55);
        orangeButton.setBorder(new LineBorder(Color.ORANGE));
        orangeButton.setForeground(Color.ORANGE);
        orangeButton.setVisible(true);

        JLabel grayButton = new JLabel("GRAY");
        grayButton.setLocation(130, 161);
        grayButton.setSize(55,55);
        grayButton.setBorder(new LineBorder(Color.GRAY));
        grayButton.setForeground(Color.GRAY);
        grayButton.setVisible(true);

        JLabel blackButton = new JLabel("BLACK");
        blackButton.setLocation(69, 161);
        blackButton.setSize(55,55);
        blackButton.setBorder(new LineBorder(Color.BLACK));
        blackButton.setForeground(Color.BLACK);
        blackButton.setVisible(true);

        JLabel magentaButton = new JLabel("MAGENTA");
        magentaButton.setLocation(8, 161);
        magentaButton.setSize(55,55);
        magentaButton.setBorder(new LineBorder(Color.MAGENTA));
        magentaButton.setForeground(Color.MAGENTA);
        magentaButton.setVisible(true);

        DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(redButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(blueButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(greenButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(yellowButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(orangeButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(blackButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(grayButton, DnDConstants.ACTION_COPY, this);
        ds.createDefaultDragGestureRecognizer(magentaButton, DnDConstants.ACTION_COPY, this);

        colorsPanel.add(mainColorButton);
        colorsPanel.add(addColorButton);
        colorsPanel.add(redButton);
        colorsPanel.add(greenButton);
        colorsPanel.add(blueButton);
        colorsPanel.add(yellowButton);
        colorsPanel.add(orangeButton);
        colorsPanel.add(grayButton);
        colorsPanel.add(blackButton);
        colorsPanel.add(magentaButton);

        JButton buttonEnter = new JButton("Enter");
        buttonEnter.setLocation(540, 270);
        buttonEnter.setSize(257,52);
        buttonEnter.setVisible(true);

        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventAddPlane.windowClosing(new WindowEvent(w, 	WindowEvent.WINDOW_CLOSING));
                w.dispose();
            }
        });

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setLocation(540, 339);
        buttonCancel.setSize(257,52);
        buttonCancel.setVisible(true);

        buttonCancel.addActionListener( (ActionEvent e) -> { w.dispose(); });

        this.add(panelParameters);
        this.add(planeButton);
        this.add(bomberButton);
        this.add(image_box);
        this.add(colorsPanel);
        this.add(buttonEnter);
        this.add(buttonCancel);
        this.add(ibButton);
        this.add(obButton);
        this.add(orButton);

        this.setVisible(true);
        Draw();
    }

    public void dragGestureRecognized(DragGestureEvent event) {
        Cursor cursor = null;
        JLabel panel = (JLabel) event.getComponent();

        String str = panel.getText();
        if (str.equals(" Military plane") || str.equals(" Bomber plane")) {
            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }

            event.startDrag(cursor, new TransferableText(str));
        }
        else if (str.equals("Inner bombs") || str.equals("Outer bombs") || str.equals("Outer rockets")) {
            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }
            event.startDrag(cursor, new TransferableAdd(((addJLabel)panel).getAdd()));
        }
        else {
            Color color = panel.getForeground();
            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }

            event.startDrag(cursor, new TransferableColor(color));
        }
    }

    class colDropTarget extends DropTargetAdapter {
        private DropTarget dropTarget;
        private JLabel panel;
        public colDropTarget(JLabel lab) {
            this.panel = lab;
            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
        }
        public void drop(DropTargetDropEvent event) {
            try {
                Transferable tr = event.getTransferable();
                Color color = (Color) tr.getTransferData(TransferableColor.colorFlavor);
                if (event.isDataFlavorSupported(TransferableColor.colorFlavor)) {
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    if (plane == null) return;
                    DropTarget ndt = (DropTarget) event.getSource();
                    System.out.println(ndt.getComponent());
                    System.out.println(mainColorButton);
                    if (ndt.getComponent().equals(mainColorButton)) {
                        System.out.println("ok");
                        plane.SetMainColor(color);
                    }
                    else if (ndt.getComponent().equals(addColorButton)) {
                        if (plane.getClass() == Plane_bomber.class) ((Plane_bomber) plane).SetAddColor(color);
                    }
                    Draw();

                    event.dropComplete(true);
                    return;
                }
                event.rejectDrop();
            } catch (Exception e) {
                e.printStackTrace();
                event.rejectDrop();
            }
        }
    }

    class imDropTarget extends DropTargetAdapter {
        private DropTarget dropTarget;
        private cImage panel;
        public imDropTarget(cImage lab) {
            this.panel = lab;
            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
        }
        public void drop(DropTargetDropEvent event) {
            try {
                Transferable tr = event.getTransferable();
                DataFlavor[] dfs = tr.getTransferDataFlavors();
                String str = (String) tr.getTransferData(DataFlavor.stringFlavor);
                if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    if (str.equals(" Military plane")) {
                        plane = new Plane(100, 500, Color.BLACK);
                    }
                    else if (str.equals(" Bomber plane")) {
                        plane = new Plane_bomber(100, 500, Color.BLACK, Color.RED,
                                true, true, 2, 1);
                    }
                    Draw();

                    event.dropComplete(true);
                    return;
                }
                event.rejectDrop();
            } catch (Exception e) {
                e.printStackTrace();
                event.rejectDrop();
            }
        }
    }

    class addDropTarget extends DropTargetAdapter {
        private DropTarget dropTarget;
        private JLabel panel;
        public addDropTarget(JLabel lab) {
            this.panel = lab;
            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
        }
        public void drop(DropTargetDropEvent event) {
            try {
                Transferable tr = event.getTransferable();
                DataFlavor[] dfs = tr.getTransferDataFlavors();
                Additionals_Draw add = (Additionals_Draw) tr.getTransferData(TransferableAdd.addFlavor);
                if (event.isDataFlavorSupported(TransferableAdd.addFlavor)) {
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    if (plane == null) return;
                    ((Plane_bomber)plane).SetAdd(add);
                    Draw();

                    event.dropComplete(true);
                    return;
                }
                event.rejectDrop();
            } catch (Exception e) {
                e.printStackTrace();
                event.rejectDrop();
            }
        }
    }
}
