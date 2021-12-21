package com.company;
import org.omg.CORBA._PolicyStub;

import java.awt.*;
import java.util.*;

public class Hangar<TP extends ITransport, TA extends Additionals_Draw> implements Iterable<TP> {
    private final ArrayList<TP> _places;
    private int max_count;

    private int pictureWidth;
    private int pictureHeight;

    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;

    int currentIndex;

    public Hangar(int picWidth, int picHeight) {
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        max_count = (pictureWidth / _placeSizeWidth) * (pictureHeight / _placeSizeHeight);
        _places = new ArrayList<TP>();
        currentIndex = -1;
    }

    public int Add(TP plane) throws HangarOverflowException, HangarAlreadyHaveException {
        if (_places.size() >= max_count) throw new HangarOverflowException();
        if (_places.contains(plane)) throw new HangarAlreadyHaveException();

        int i = 0;
        int j = 0;

        while (i < (pictureHeight / _placeSizeHeight))
        {
            j = 0;
            while (j < (pictureWidth / _placeSizeWidth))
            {
                if (i * (pictureWidth / _placeSizeWidth) + j == _places.size() && _places.size() <= max_count)
                {
                    plane.SetPosition(5 + j * _placeSizeWidth, 5 + i * _placeSizeHeight, pictureWidth, pictureHeight);
                    _places.add(plane);
                    return (i * (pictureWidth / _placeSizeWidth) + j);
                }
                else
                if (i * (pictureWidth / _placeSizeWidth) + j < _places.size() &&
                        _places.get(i * (pictureWidth / _placeSizeWidth) + j) == null)
                {
                    plane.SetPosition(5 + j * _placeSizeWidth, 5 + i * _placeSizeHeight, pictureWidth, pictureHeight);
                    _places.set(i * (pictureWidth / _placeSizeWidth) + j, plane);
                    return (i * (pictureWidth / _placeSizeWidth) + j);
                }
                j++;
            }
            i++;
        }
        return -1;
    }

    public TP Remove(int index) throws HangarNotFoundException {
        if (index >= _places.size() || index < 0) throw new HangarNotFoundException(index);
        if (_places.get(index) != null)
        {
            TP ret_T = _places.get(index);
            _places.set(index, null);
            return ret_T;
        }
        else return null;
    }

    public boolean equal(int count) {
        int k = 0;
        for (int i = 0; i < _places.size(); i++) {
            if (_places.get(i) != null) {
                k++;
            }
        }
        return (count == k);
    }

    public boolean unEqual(int count) {
        int k = 0;
        for (int i = 0; i < _places.size(); i++) {
            if (_places.get(i) != null) {
                k++;
            }
        }
        return (count != k);
    }

    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        DrawMarking(g2d);

        for (int i = 0; i < _places.size(); i++)
        {
            _places.get(i).SetPosition(5 + (i % (pictureWidth / _placeSizeWidth)) * _placeSizeWidth,
                    5 + (i / (pictureWidth / _placeSizeWidth)) * _placeSizeHeight, pictureWidth, pictureHeight);
            _places.get(i).DrawObject(g);
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

    public TP getPlane(int ind) {
        if (ind > -1 && ind < _places.size()) return _places.get(ind);
        else return null;
    }

    public void Sort()  {
        Collections.sort(_places, (Comparator<? super TP>) new PlaneComparator());
    }

    public Iterator<TP> iterator() {
        return _places.iterator();
    }

    public void Print() {
        LinkedList<Plane> planes = new LinkedList<Plane>();
        planes.add(new Plane(1, 1, Color.GREEN, 10, 15));
        planes.add(new Plane(1, 1, Color.GREEN, 10, 15));
        planes.add(new Plane(1, 1, Color.GREEN, 10, 15));
        planes.add(new Plane(1, 1, Color.GREEN, 10, 15));
        planes.add(new Plane(1, 1, Color.GREEN, 10, 15));
        for (Plane plane: planes) {
                System.out.println(plane);
        }
    }
}
