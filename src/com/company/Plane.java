package com.company;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Plane extends Vehicle implements Comparable<Plane>, Iterable<String> {
    protected int planeWidth = 95;
    protected int planeHeight = 70;

    protected String separator = ";";

    ArrayList<String> props = new ArrayList<String>();

    public Plane(int maxSpeed, int load_Weight, Color mainColor) {
        Max_Speed = maxSpeed;
        Load_Weight = load_Weight;
        MainColor = mainColor;
    }

    protected Plane(int maxSpeed, int load_Weight, Color mainColor, int plane_Width, int plane_Height) {
        Max_Speed = maxSpeed;
        Load_Weight = load_Weight;
        MainColor = mainColor;
        this.planeWidth = plane_Width;
        this.planeHeight = plane_Height;
    }

    protected Plane(String info) {
        String[] strs = info.split(separator);
        if (strs.length == 3) {
            Max_Speed = Integer.parseInt(strs[0]);
            Load_Weight = Integer.parseInt(strs[1]);
            MainColor = Color.decode(strs[2]);
        }
    }

    @Override
    public void MoveObject(Direction dir) {
        int step = Max_Speed * 100 / Load_Weight;
        switch (dir) {
            // вправо
            case Right:
                if (_startX + step < _picWidth - planeWidth) {
                    _startX += step;
                }
                break;
            //влево
            case Left:
                if (_startX - step > 0) {
                    _startX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startY - step > 0) {
                    _startY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startY + step < _picHeight - planeHeight) {
                    _startY += step;
                }
                break;
        }
    }

    @Override
    public void DrawObject(Graphics gg) {
        Graphics2D g = (Graphics2D) gg;

        g.setColor(MainColor);

        Polygon cabin = new Polygon();
        cabin.addPoint(_startX, _startY + 35);
        cabin.addPoint(_startX + 15, _startY + 30);
        cabin.addPoint(_startX + 15, _startY + 40);
        g.fillPolygon(cabin);

        Polygon wings = new Polygon();
        wings.addPoint(_startX + 40, _startY);
        wings.addPoint(_startX + 45, _startY);
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

        Polygon body = new Polygon();
        body.addPoint(_startX + 15, _startY + 30);
        body.addPoint(_startX + 15, _startY + 40);
        body.addPoint(_startX + 95, _startY + 40);
        body.addPoint(_startX + 95, _startY + 30);
        g.drawPolygon(body);
    }

    @Override
    public String toString() {
        return "" + Max_Speed + separator + Load_Weight + separator + MainColor.getRGB();
    }

    public boolean equals(Plane other) {
        if (other == null) {
            return false;
        }
        if (!this.getClass().equals(other.getClass())) {
            return false;
        }
        if (Max_Speed != other.Max_Speed) {
            return false;
        }
        if (Load_Weight != other.Load_Weight) {
            return false;
        }
        if (!MainColor.equals(other.MainColor)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Plane)) {
            return false;
        } else {
            return equals((Plane) obj);
        }
    }

    public int compareTo(Plane other) {
        if (other instanceof Plane_bomber) return 1;

        if (Max_Speed != other.Max_Speed) {
            return Integer.compare(Max_Speed, other.Max_Speed);
        }
        if (Load_Weight != other.Load_Weight) {
            return Integer.compare(Load_Weight, other.Load_Weight);
        }
        if (!MainColor.equals(other.MainColor)) {
            return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
        }
        return 0;
    }

    private void GetProps() {
        props.clear();
        props.add(this.getClass().toString());
        props.add(this.getMax_Speed() + "");
        props.add(this.getLoad_Weight() + "");
        props.add(this.getMainColor().getRGB() + "");
    }

    public Iterator<String> iterator() {
        GetProps();
        return props.iterator();
    }
}
