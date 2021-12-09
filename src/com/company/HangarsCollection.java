package com.company;
import org.omg.CORBA.Environment;

import javax.swing.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Map;
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

    private String separator = ":";

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

    public boolean SaveFile(String filename) {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(filename);
        }
        catch (FileNotFoundException ex) { return false; }

        OutputStreamWriter sw = new OutputStreamWriter(fs);

        try {
            sw.write("HangarsCollection" + System.getProperty("line.separator"));
            for (Map.Entry<String, Hangar<Vehicle, Additionals_Draw>> level : hangarStages.entrySet()) {
                sw.write("Hangar" + separator + level.getKey() + System.getProperty("line.separator"));
                ITransport plane = null;
                for (int i = 0; (plane = level.getValue().getPlane(i)) != null; i++) {
                    if (plane != null) {
                        if (plane.getClass() == Plane.class) {
                            sw.write("Plane" + separator);
                        }
                        if (plane.getClass() == Plane_bomber.class) {
                            sw.write("Plane_bomber" + separator);
                        }
                        sw.write(plane + System.getProperty("line.separator"));
                    }
                }
            }
            sw.close();
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean SaveSingleHangarFile(String filename, String key) {
        if (!hangarStages.containsKey(key)) return false;

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(filename);
        }
        catch (FileNotFoundException ex) { return false; }

        OutputStreamWriter sw = new OutputStreamWriter(fs);

        try {
            sw.write("SingleHangar" + System.getProperty("line.separator"));

            Hangar<Vehicle, Additionals_Draw> level = hangarStages.get(key);
            sw.write("Hangar" + separator + key + System.getProperty("line.separator"));
            ITransport plane = null;
            for (int i = 0; (plane = level.getPlane(i)) != null; i++) {
                if (plane != null) {
                    if (plane.getClass() == Plane.class) {
                        sw.write("Plane" + separator);
                    }
                    if (plane.getClass() == Plane_bomber.class) {
                        sw.write("Plane_bomber" + separator);
                    }
                    sw.write(plane + System.getProperty("line.separator"));
                }
            }
            sw.close();
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean LoadData(String filename) {
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(filename);
        }
        catch (FileNotFoundException ex) { return false; }

        BufferedReader sr = new BufferedReader(new InputStreamReader(fs));
        try {
            String line = sr.readLine();
            if (line.contains("HangarsCollection")) {
                hangarStages.clear();
            }
            else if (line.contains("SingleHangar")) {
                return AddSingleHangar(sr);
            }
            else return false;

            Vehicle plane = null;
            String key = "";

            while ((line = sr.readLine()) != null) {
                if (line.contains("Hangar")) {
                    key = line.split(separator)[1];
                    AddHangar(key);
                    continue;
                }
                if (line == null || line.isEmpty()) {
                    continue;
                }
                if (line.split(separator)[0].equals("Plane")) {
                    plane = new Plane(line.split(separator + "")[1]);
                }
                else if (line.split(separator)[0].equals("Plane_bomber")) {
                    plane = new Plane_bomber(line.split(separator)[1]);
                }
                int result = hangarStages.get(key).Add(plane);
                if (result == -1) return false;
            }
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean AddSingleHangar(BufferedReader sr) throws IOException{
        String key = null;
        Vehicle plane = null;

        String line = sr.readLine();
        if (line.contains("Hangar")) {
            key = line.split(separator)[1];
            if (hangarStages.containsKey(key)) hangarStages.get(key).Clear();
            else AddHangar(key);
        }
        else return false;

        while ((line = sr.readLine()) != null) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            if (line.split(separator)[0].equals("Plane")) {
                plane = new Plane(line.split(separator + "")[1]);
            }
            else if (line.split(separator)[0].equals("Plane_bomber")) {
                plane = new Plane_bomber(line.split(separator)[1]);
            }
            int result = hangarStages.get(key).Add(plane);
            if (result == -1) return false;
        }
        return true;
    }
}
