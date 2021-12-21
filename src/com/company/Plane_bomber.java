package com.company;
import java.awt.*;
import java.lang.reflect.Field;

public class Plane_bomber extends Plane implements Comparable<Plane>{
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

    public Plane_bomber(String info) {
        super(info);
        String[] strs = info.split(separator);
        if (strs.length == 8) {
            Max_Speed = Integer.parseInt(strs[0]);
            Load_Weight = Integer.parseInt(strs[1]);
            MainColor = Color.decode(strs[2]);
            AddColor = Color.decode(strs[3]);
            Back_state = Boolean.parseBoolean(strs[4]);
            Bombs_state = Boolean.parseBoolean(strs[5]);
            switch (Integer.parseInt(strs[6])) {
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
            addDraw.setAmount(Integer.parseInt(strs[7]));
        }
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

        g.setColor(getAddColor());

        if (Back_state) {
            g.drawOval(_startX + 90, _startY + 30, 10, 10);
            g.drawLine(_startX + 100, _startY + 35,_startX + 105, _startY + 35);
        }

        if (Bombs_state) {
            addDraw.Draw(g, AddColor, _startX, _startY);
        }
    }

    public void SetAddColor(Color color) {
        AddColor = color;
    }
    public void SetCabin(boolean state) { Back_state = state; System.out.println(Back_state);}
    public void SetBombs(boolean state) { Bombs_state = state; }
    public void SetAmm(int i) { addDraw.setAmount(i); }
    public void SetAdd(Additionals_Draw add) {
        addDraw = add;
    }

    @Override
    public String toString() {
        return "" + Max_Speed + separator + Load_Weight + separator + MainColor.getRGB() + separator + AddColor.getRGB() +
                separator + Back_state + separator + Bombs_state + separator + addDraw.toString();
    }

    public boolean equals(Plane_bomber other) {
        if (other == null)
        {
            return false;
        }
        if (!this.getClass().equals(other.getClass()))
        {
            return false;
        }
        if (Max_Speed != other.Max_Speed)
        {
            return false;
        }
        if (Load_Weight != other.Load_Weight)
        {
            return false;
        }
        if (MainColor != other.MainColor)
        {
            return false;
        }
        if (AddColor != other.AddColor)
        {
            return false;
        }
        if (Back_state != other.Back_state)
        {
            return false;
        }
        if (Bombs_state != other.Bombs_state)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Plane_bomber))
        {
            return false;
        }
            else
        {
            return equals((Plane_bomber) obj);
        }
    }

    public int getAddDrawState() {
        if (!Bombs_state) return 0;
        if (addDraw instanceof Inner_bombs) return 1;
        if (addDraw instanceof Outer_bombs) return 2;
        if (addDraw instanceof Outer_rockets) return 3;
        return 0;
    }

    public int compareTo(Plane plane) {
        if (plane instanceof Plane) return -1;
        Plane_bomber other = (Plane_bomber) plane;

        if (Max_Speed != other.Max_Speed)
        {
            return Integer.compare(Max_Speed, other.Max_Speed);
        }
        if (Load_Weight != other.Load_Weight)
        {
            return Integer.compare(Load_Weight, other.Load_Weight);
        }
        if (!MainColor.equals(other.MainColor))
        {
            return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
        }
        if (!getAddColor().equals(other.getAddColor()))
        {
            return Integer.compare(getAddColor().getRGB(), other.getAddColor().getRGB());
        }
        if (getBack_state() != other.getBack_state())
        {
            return Boolean.compare(getBack_state(), other.getBack_state());
        }
        if (getBombs_state() != other.getBombs_state())
        {
            return Boolean.compare(getBombs_state(), other.getBombs_state());
        }
        if (getAddDrawState() != other.getAddDrawState()) {
            return Integer.compare(getAddDrawState(), other.getAddDrawState());
        }
        return 0;
    }
}
