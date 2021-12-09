package com.company;

public class HangarNotFoundException extends Exception{
    public HangarNotFoundException(int ind) {
        super("Plane is not found on the place " + ind);
    }
}
