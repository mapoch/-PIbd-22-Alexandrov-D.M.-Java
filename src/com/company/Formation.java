package com.company;
import java.awt.*;
import java.lang.reflect.Array;

public class Formation<TP extends ITransport, TA extends Additionals_Draw> {
    private final TP[] _places;

    private int pictureWidth;
    private int pictureHeight;

    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;

    private int width;
    private int height;

    public Formation(Class<TP> klass, int picWidth, int picHeight) {
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        _places = (TP[])Array.newInstance(klass, width * height);
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public int Add(TP plane) {
        int i = 0;
        int j = 0;

        while (i < height)
        {
            j = 0;
            while (j < width)
            {
                if (_places[i*width + j] == null)
                {
                    plane.SetPosition(5 + j * _placeSizeWidth, 5 + i * _placeSizeHeight,
                            pictureWidth, pictureHeight);
                    _places[i * width + j] = plane;
                    return (i * width + j);
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

    public boolean equal(int places) {
        if (width*height == places) return true;
        else return false;
    }

    public boolean non_equal(int places) {
        if (width*height != places) return true;
        else return false;
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
