package com.company;
import java.awt.*;

public class Inner_bombs implements Additionals_Draw{
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

    public void Draw(Graphics2D g, Color color, boolean Back_state, boolean Bombs_state, int X, int Y) {

        g.setColor(color);

        if (Back_state)
        {
            g.drawOval(X + 90, Y + 30, 10, 10);
            g.drawLine(X + 100, Y + 35,X + 105, Y + 35);
        }

        if (Bombs_state)
        {
            Polygon bomb_plumage;
            X += 20;
            Y += 31;
            switch (Amount)
            {
                case Two:

                    for (int i = 0; i < 2; i++) {
                        bomb_plumage = new Polygon();
                        bomb_plumage.addPoint(X + 20, Y + 4);
                        bomb_plumage.addPoint(X + 25, Y);
                        bomb_plumage.addPoint(X + 25, Y + 8);

                        g.fillOval(X, Y, 20, 8);

                        g.fillPolygon(bomb_plumage);

                        X += 30;
                    }
                    break;
                case Four:
                    X -= 3;
                    Y++;

                    for (int i = 0; i < 4; i++) {
                        bomb_plumage = new Polygon();
                        bomb_plumage.addPoint(X + 10, Y + 3);
                        bomb_plumage.addPoint(X + 15, Y - 2);
                        bomb_plumage.addPoint(X + 15, Y + 8);

                        g.fillOval(X, Y, 10, 6);

                        g.fillPolygon(bomb_plumage);

                        X += 18;
                    }
                    break;
                case Six:
                    X -= 3;
                    Y++;

                    for (int i = 0; i < 6; i++) {
                        bomb_plumage = new Polygon();
                        bomb_plumage.addPoint(X + 5, Y + 3);
                        bomb_plumage.addPoint(X + 10, Y - 1);
                        bomb_plumage.addPoint(X + 10, Y + 7);

                        g.fillOval(X, Y, 5, 6);

                        g.fillPolygon(bomb_plumage);

                        X += 12;
                    }
                    break;
            }
        }
    }
}
