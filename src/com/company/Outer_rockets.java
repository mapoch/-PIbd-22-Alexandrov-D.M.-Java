package com.company;
import java.awt.*;

public class Outer_rockets implements Additionals_Draw{
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

        Polygon rocket;
        X += 40;
        switch (Amount) {
            case Two:
                Y += 15;
                for (int i = 0; i < 2; i++) {
                    rocket = new Polygon();
                    rocket.addPoint(X - 5, Y + 5);
                    rocket.addPoint(X, Y + 3);
                    rocket.addPoint(X + 12, Y + 3);
                    rocket.addPoint(X + 14, Y + 1);
                    rocket.addPoint(X + 20, Y + 1);
                    rocket.addPoint(X + 20, Y + 9);
                    rocket.addPoint(X + 14, Y + 9);
                    rocket.addPoint(X + 12, Y + 7);
                    rocket.addPoint(X, Y + 7);

                    g.fillPolygon(rocket);

                    Y += 30;
                }
                break;
            case Four:
                Y += 10;
                for (int i = 0; i < 4; i++) {
                    rocket = new Polygon();
                    rocket.addPoint(X - 3, Y + 3);
                    rocket.addPoint(X, Y + 2);
                    rocket.addPoint(X + 12, Y + 2);
                    rocket.addPoint(X + 14, Y);
                    rocket.addPoint(X + 18, Y);
                    rocket.addPoint(X + 18, Y + 6);
                    rocket.addPoint(X + 14, Y + 6);
                    rocket.addPoint(X + 12, Y + 4);
                    rocket.addPoint(X, Y + 4);

                    g.fillPolygon(rocket);

                    if (i == 1) Y += 25;
                    else Y += 10;
                }
                break;
            case Six:
                Y += 10;
                for (int i = 0; i < 6; i++) {
                    rocket = new Polygon();
                    rocket.addPoint(X - 3, Y + 2);
                    rocket.addPoint(X, Y + 1);
                    rocket.addPoint(X + 12, Y + 1);
                    rocket.addPoint(X + 14, Y);
                    rocket.addPoint(X + 18, Y);
                    rocket.addPoint(X + 18, Y + 4);
                    rocket.addPoint(X + 14, Y + 4);
                    rocket.addPoint(X + 12, Y + 3);
                    rocket.addPoint(X, Y + 3);

                    g.fillPolygon(rocket);

                    if (i == 2) Y += 20;
                    else Y += 7;
                }
                break;
        }
    }
}
