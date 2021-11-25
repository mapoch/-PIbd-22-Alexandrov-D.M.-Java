package com.company;

import javax.swing.*;

public class addJLabel extends JLabel {
    public addJLabel(String str, int state) {
        super(str);

        switch (state) {
            case 0:
                add = new Inner_bombs();
                break;
            case 1:
                add = new Outer_bombs();
                break;
            case 2:
                add = new Outer_rockets();
                break;
        }
        add.setAmount(2);
    }

    Additionals_Draw add;
    public Additionals_Draw getAdd() { return add; }
}
