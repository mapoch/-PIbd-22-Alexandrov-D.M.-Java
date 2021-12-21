package com.company;

public class HangarAlreadyHaveException extends Exception{
    public HangarAlreadyHaveException() {
        super("Hangar already have this model of plane");
    }
}
