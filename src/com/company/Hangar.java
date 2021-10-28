package com.company;
import java.awt.*;
import java.util.ArrayList;

public class Hangar<TP extends ITransport, TA extends Additionals_Draw> {
    private final ArrayList<TP> _places;
    private int max_count;

    private int pictureWidth;
    private int pictureHeight;

    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;

    private int width;
    private int height;

    public Hangar(int picWidth, int picHeight) {
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        max_count = width * height;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        _places = new ArrayList<TP>();
    }

    public int Add(TP plane) {
        int i = 0;
        int j = 0;

        while (i < height)
        {
            j = 0;
            while (j < width)
            {
                if (i * width + j == _places.size() && _places.size() <= max_count)
                {
                    plane.SetPosition(5 + j * _placeSizeWidth, 5 + i * _placeSizeHeight, pictureWidth, pictureHeight);
                    _places.add(plane);
                    return (i * width + j);
                }
                else
                if (i * width + j < _places.size() && _places.get(i * width + j) == null)
                {
                    plane.SetPosition(5 + j * _placeSizeWidth, 5 + i * _placeSizeHeight, pictureWidth, pictureHeight);
                    _places.set(i * width + j, plane);
                    return (i * width + j);
                }
                j++;
            }
            i++;
        }
        return -1;
    }

    public TP Remove(int index) {
        if (index >= _places.size() || index < 0) return null;
        if (_places.get(index) != null)
        {
            TP ret_T = _places.get(index);
            _places.set(index, null);
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
        for (int i = 0; i < _places.size(); i++)
        {
            if (_places.get(i) != null) {
                _places.get(i).DrawObject(g);
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
