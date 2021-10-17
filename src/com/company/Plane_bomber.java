package com.company;
import java.awt.*;

public class Plane_bomber extends Plane{
    private Additionals_Draw addDraw;

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

    public Plane_bomber(int max_Speed, int load_Weight, Color mainColor, Color addColor,
                     boolean bombs_state, boolean back_state, int bombs_amount, int add_state)
    {
        super(max_Speed, load_Weight, mainColor, 105, 70);

        switch (add_state) {
            case 0:
                addDraw = new Inner_bombs();
                break;
            case 1:
                addDraw = new Outer_bombs();
                break;
            case 2:
                addDraw = new Outer_rockets();
                break;
        }
        addDraw.setAmount(bombs_amount);

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

    @Override
    public void DrawObject(Graphics gg)
    {
        super.DrawObject(gg);

        Graphics2D g = (Graphics2D)gg;

        if (Back_state) {
            g.drawOval(_startX + 90, _startY + 30, 10, 10);
            g.drawLine(_startX + 100, _startY + 35,_startX + 105, _startY + 35);
        }

        if (Bombs_state) {
            addDraw.Draw(g, AddColor, _startX, _startY);
        }
    }
}
