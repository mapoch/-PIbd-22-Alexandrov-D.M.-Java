package com.company;
import java.awt.*;

public class Outer_bombs implements Additionals_Draw{
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

        Polygon bomb_plumage;
        X += 40;
        switch (Amount) {
            case Two:
                Y += 18;
                for (int i = 0; i < 2; i++) {
                    bomb_plumage = new Polygon();
                    bomb_plumage.addPoint(X + 15, Y + 2);
                    bomb_plumage.addPoint(X + 20, Y - 2);
                    bomb_plumage.addPoint(X + 20, Y + 8);

                    g.fillOval(X, Y, 15, 5);

                    g.fillPolygon(bomb_plumage);

                    Y += 30;
                }
                break;
            case Four:
                Y += 10;
                for (int i = 0; i < 4; i++) {
                    bomb_plumage = new Polygon();
                    bomb_plumage.addPoint(X + 8, Y + 3);
                    bomb_plumage.addPoint(X + 12, Y - 2);
                    bomb_plumage.addPoint(X + 12, Y + 7);

                    g.fillOval(X, Y, 8, 6);

                    g.fillPolygon(bomb_plumage);

                    if (i == 1) Y += 25;
                    else Y += 10;
                }
                break;
            case Six:
                Y += 8;
                for (int i = 0; i < 6; i++) {
                    bomb_plumage = new Polygon();
                    bomb_plumage.addPoint(X + 5, Y + 2);
                    bomb_plumage.addPoint(X + 8, Y - 1);
                    bomb_plumage.addPoint(X + 8, Y + 6);

                    g.fillOval(X, Y, 5, 4);

                    g.fillPolygon(bomb_plumage);

                    if (i == 2) Y += 22;
                    else Y += 7;
                }
                break;
        }
    }

    @Override
    public String toString() {
        int amount = 2;
        switch (Amount) {
            case Two:
                amount = 2;
                break;
            case Four:
                amount = 4;
                break;
            case Six:
                amount = 6;
                break;
        }
        return 1 + ";" + amount;
    }
}
