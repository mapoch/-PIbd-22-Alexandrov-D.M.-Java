package com.company;

public class HangarOverflowException extends Exception{
    public HangarOverflowException() {
        super("Hangar is filled");
    }
}
