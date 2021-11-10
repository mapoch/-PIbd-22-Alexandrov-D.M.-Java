package com.company;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

public class HangarsCollection {
    HashMap<String, Hangar<Vehicle, Additionals_Draw>> hangarStages;

    private ArrayList<String> Keys;
    private void setKeys() {
        Keys = new ArrayList<String>(hangarStages.keySet());
    }
    public ArrayList<String> getKeys() {
        return Keys;
    }

    private int pictureWidth;
    private int pictureHeight;

    public HangarsCollection(int pictureWidth, int pictureHeight)
    {
        hangarStages = new HashMap<String, Hangar<Vehicle, Additionals_Draw>>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public void AddHangar(String name)
    {
        if (hangarStages.containsKey(name)) return;
        hangarStages.put(name, new Hangar<Vehicle, Additionals_Draw>(pictureWidth, pictureHeight));
        setKeys();
    }

    public void DelHangar(String name)
    {
        if (hangarStages.containsKey(name)) hangarStages.remove(name);
        setKeys();
    }

    public Hangar<Vehicle, Additionals_Draw> getValue(String ind) {
        if (hangarStages.containsKey(ind)) return hangarStages.get(ind);
        else return null;
    }

    public Vehicle getValue(String indH, int indP) {
        Hangar<Vehicle, Additionals_Draw> hangar;
        if (hangarStages.containsKey(indH)) hangar = hangarStages.get(indH);
        else return null;
        return hangar.getPlane(indP);
    }
}
