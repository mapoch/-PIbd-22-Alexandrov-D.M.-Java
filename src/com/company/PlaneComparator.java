package com.company;

import java.util.Comparator;

public class PlaneComparator implements Comparator<Vehicle>{
    public int compare(Vehicle x, Vehicle y) {
        if (!x.getClass().equals(y.getClass()))
        {
            if ((x instanceof Plane) && (y instanceof Plane_bomber)) return -1;
                else return 1;
        }

        if (x.getClass().equals("Plane")) return ComparerPlane((Plane)x, (Plane)y);
        if (x.getClass().equals(Plane_bomber.class)) return ComparerBomberPlane((Plane_bomber) x, (Plane_bomber) y);

        return 0;
    }

    private int ComparerPlane(Plane x, Plane y) {
        if (x.Max_Speed != y.Max_Speed)
        {
            return Integer.compare(x.Max_Speed, y.Max_Speed);
        }
        if (x.Load_Weight != y.Load_Weight)
        {
            return Integer.compare(x.Load_Weight, y.Load_Weight);
        }
        if (!x.MainColor.equals(y.MainColor))
        {
            return Integer.compare(x.MainColor.getRGB(), y.MainColor.getRGB());
        }
        return 0;
    }

    private int ComparerBomberPlane(Plane_bomber x, Plane_bomber y) {
        int res = ComparerPlane(x, y);
        if (res != 0)
        {
            return res;
        }
        if (!x.getAddColor().equals(y.getAddColor()))
        {
            return Integer.compare(x.getAddColor().getRGB(), y.getAddColor().getRGB());
        }
        if (x.getBack_state() != y.getBack_state())
        {
            return Boolean.compare(x.getBack_state(), y.getBack_state());
        }
        if (x.getBombs_state() != y.getBombs_state())
        {
            return Boolean.compare(x.getBombs_state(), y.getBombs_state());
        }
        if (x.getAddDrawState() != y.getAddDrawState()) {
            return Integer.compare(x.getAddDrawState(), y.getAddDrawState());
        }
        return 0;
    }
}
