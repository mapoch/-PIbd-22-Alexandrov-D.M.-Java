package com.company;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

public class HangarsCollection {
    HashMap<String, Hangar<Vehicle, Additionals_Draw>> hangarStages;

    //public ArrayList<String> Keys = () -> hangarStages.keySet();

    private int pictureWidth;
    private int pictureHeight;

    public HangarsCollection(int pictureWidth, int pictureHeight)
    {
        hangarStages = new HashMap<String, Hangar<Vehicle, Additionals_Draw>>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public void AddParking(String name)
    {
        if (hangarStages.containsKey(name)) return;
        hangarStages.put(name, new Hangar<Vehicle, Additionals_Draw>(pictureWidth, pictureHeight));
    }

    public void DelParking(String name)
    {
        if (hangarStages.containsKey(name)) hangarStages.remove(name);
    }

    public Hangar<Vehicle, Additionals_Draw> getValue(String ind) {
        if (hangarStages.containsKey(ind)) return hangarStages.get(ind);
        else return null;
    }
}
