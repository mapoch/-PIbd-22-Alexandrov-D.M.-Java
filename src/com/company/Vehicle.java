package com.company;
import java.awt.*;

public abstract class Vehicle implements ITransport{
    protected int _startX;
    protected int _startY;

    protected int _picWidth;
    protected int _picHeight;

    public int Load_Weight;
    public int getLoad_Weight() {
        return Load_Weight;
    }

    public int Max_Speed;
    public int getMax_Speed() {
        return Max_Speed;
    }

    public Color MainColor;
    public Color getMainColor() {
        return MainColor;
    }

    public void SetPosition(int x, int y, int width, int height)
    {
        _startX = x;
        _startY = y;

        _picHeight = height;
        _picWidth = width;
    }
    public abstract void DrawObject(Graphics g);
    public abstract void MoveObject(Direction direction);

    public void SetMainColor(Color color) {
        MainColor = color;
    }

    public void SetLoad(int i) { Load_Weight = i; }
    public void SetSpeed(int i) { Max_Speed = i; }
}
