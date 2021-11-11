package com.company;
import java.awt.*;
import java.lang.reflect.Array;

public class Hangar<TP extends ITransport, TA extends Additionals_Draw> {
    private final TP[] _places;

    private int pictureWidth;
    private int pictureHeight;

    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;


    public Hangar(Class<TP> klass, int picWidth, int picHeight) {
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        _places = (TP[])Array.newInstance(klass,
                (pictureWidth / _placeSizeWidth) * (pictureHeight / _placeSizeHeight));
    }

    public int Add(TP plane) {
        int i = 0;
        int j = 0;

        while (i < (pictureHeight / _placeSizeHeight))
        {
            j = 0;
            while (j < pictureWidth / _placeSizeWidth)
            {
                if (_places[i*(pictureWidth / _placeSizeWidth) + j] == null)
                {
                    plane.SetPosition(5 + j * _placeSizeWidth, 5 + i * _placeSizeHeight,
                            pictureWidth, pictureHeight);
                    _places[i * (pictureWidth / _placeSizeWidth) + j] = plane;
                    return (i * (pictureWidth / _placeSizeWidth) + j);
                }
                j++;
            }
            i++;
        }
        return -1;
    }

    public TP Remove(int index) {
        if (index >= _places.length || index < 0) return null;
        if (_places[index] != null)
        {
            TP ret_T = _places[index];
            _places[index] = null;
            return ret_T;
        }
        else return null;
    }

    public boolean equal(int count) {
        int k = 0;
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] != null) {
                k++;
            }
        }
        return (count == k);
    }

    public boolean unEqual(int count) {
        int k = 0;
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] != null) {
                k++;
            }
        }
        return (count != k);
    }

    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        DrawMarking(g2d);
        for (int i = 0; i < _places.length; i++)
        {
            if (_places[i] != null) {
                _places[i].DrawObject(g);
            }
        }
    }

    public void DrawMarking(Graphics2D g) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++)
        {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j)
            {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i *
                        _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth,
                    (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
}
