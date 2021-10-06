package com.company;
import java.awt.*;

public class Plane {
    private AddDrawClass addDraw;

    private int _startX;
    private int _startY;

    private int _picWidth;
    private int _picHeight;

    private int planeWidth = 105;
    private int planeHeight = 70;

    private int Load_Weight;
    public int getLoad_Weight() {
        return Load_Weight;
    }

    private int Max_Speed;
    public int getMax_Speed() {
        return Max_Speed;
    }

    private Color MainColor;
    public Color getMainColor() {
        return MainColor;
    }
    private Color AddColor;
    public Color getAddColor() {
        return AddColor;
    }

    private boolean Bombs_state;
    public boolean getBombs_state() {
        return Bombs_state;
    }
    private boolean Back_state;
    public boolean getBack_state() {
        return Back_state;
    }

    public Plane(int max_Speed, int load_Weight, Color mainColor, Color addColor,
                     boolean bombs_state, boolean back_state, int bombs_amount)
    {
        addDraw = new AddDrawClass();
        addDraw.setAmount(bombs_amount);

        Max_Speed = max_Speed;
        Load_Weight = load_Weight;
        MainColor = mainColor;
        AddColor = addColor;
        Bombs_state = bombs_state;
        Back_state = back_state;
    }

    public void SetPosition(int x, int y, int width, int height)
    {
        _startX = x;
        _startY = y;

        _picHeight = height;
        _picWidth = width;
    }

    public void MoveObject(Direction dir)
    {
        int step = Max_Speed * 100 / Load_Weight;
        switch (dir)
        {
            // вправо
            case Right:
                if (_startX + step < _picWidth - planeWidth)
                {
                    _startX += step;
                }
                break;
            //влево
            case Left:
                if (_startX - step > 0)
                {
                    _startX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startY - step > 0)
                {
                    _startY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startY + step < _picHeight - planeHeight)
                {
                    _startY += step;
                }
                break;
        }
    }

    public void DrawObject(Graphics gg)
    {
        Graphics2D g = (Graphics2D)gg;

        g.setColor(MainColor);

        Polygon cabin = new Polygon();
        cabin.addPoint(_startX, _startY + 35);
        cabin.addPoint(_startX + 15, _startY + 30);
        cabin.addPoint(_startX + 15, _startY + 40);
        g.fillPolygon(cabin);

        Polygon wings = new Polygon();
        wings.addPoint(_startX + 40, _startY + 0);
        wings.addPoint(_startX + 45, _startY + 0);
        wings.addPoint(_startX + 55, _startY + 25);
        wings.addPoint(_startX + 55, _startY + 45);
        wings.addPoint(_startX + 45, _startY + 70);
        wings.addPoint(_startX + 40, _startY + 70);
        g.drawPolygon(wings);

        Polygon tail = new Polygon();
        tail.addPoint(_startX + 90, _startY + 20);
        tail.addPoint(_startX + 95, _startY + 15);
        tail.addPoint(_startX + 95, _startY + 55);
        tail.addPoint(_startX + 90, _startY + 50);
        g.drawPolygon(tail);

        Polygon body =  new Polygon();
        body.addPoint(_startX + 15, _startY + 30);
        body.addPoint(_startX + 15, _startY + 40);
        body.addPoint(_startX + 95, _startY + 40);
        body.addPoint(_startX + 95, _startY + 30);
        g.drawPolygon(body);

        addDraw.Draw(g, AddColor, Back_state, Bombs_state, _startX, _startY);
    }
}
