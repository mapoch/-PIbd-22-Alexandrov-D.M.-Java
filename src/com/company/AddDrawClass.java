package com.company;

import com.sun.javafx.scene.control.behavior.TwoLevelFocusBehavior;

import java.awt.*;

public class AddDrawClass {
    Add_amount Amount;

    public void setAmount(int k) {
        if (k <= 2) {
            Amount = Add_amount.Two;
            return;
        }
        if (k <= 4) {
            Amount = Add_amount.Four;
            return;
        }
        if (k <= 6) {
            Amount = Add_amount.Six;
            return;
        }
    }
    public Add_amount getAmount() {
        return this.Amount;
    }

    public void Draw(Graphics2D g, Color color, int X, int Y) {

        g.setColor(color);

        Polygon[] bombs_plumages;
        X += 20;
        Y += 31;
        switch (Amount)
        {
            case Two:
                bombs_plumages = new Polygon[2];

                for (int i = 0; i < 2; i++) {
                    bombs_plumages[i] = new Polygon();
                    bombs_plumages[i].addPoint(X + 20, Y + 4);
                    bombs_plumages[i].addPoint(X + 25, Y);
                    bombs_plumages[i].addPoint(X + 25, Y + 8);

                    g.fillOval(X, Y, 20, 8);

                    g.fillPolygon(bombs_plumages[i]);

                    X += 30;
                }
                break;
            case Four:
                bombs_plumages = new Polygon[4];
                X -= 3;
                Y++;

                for (int i = 0; i < 4; i++) {
                    bombs_plumages[i] = new Polygon();
                    bombs_plumages[i].addPoint(X + 10, Y + 3);
                    bombs_plumages[i].addPoint(X + 15, Y - 2);
                    bombs_plumages[i].addPoint(X + 15, Y + 8);

                    g.fillOval(X, Y, 10, 6);

                    g.fillPolygon(bombs_plumages[i]);

                    X += 18;
                }
                break;
            case Six:
                bombs_plumages = new Polygon[6];
                X -= 3;
                Y++;

                for (int i = 0; i < 6; i++) {
                    bombs_plumages[i] = new Polygon();
                    bombs_plumages[i].addPoint(X + 5, Y + 3);
                    bombs_plumages[i].addPoint(X + 10, Y - 1);
                    bombs_plumages[i].addPoint(X + 10, Y + 7);

                    g.fillOval(X, Y, 5, 6);

                    g.fillPolygon(bombs_plumages[i]);

                    X += 12;
                }
                break;
        }
    }
}
