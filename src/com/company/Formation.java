package com.company;
import java.awt.*;
import java.lang.reflect.Array;

public class Formation<T extends ITransport> {
    private final T[] _places;

    private int pictureWidth;
    private int pictureHeight;

    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;

    private int width;
    private int height;

    public Formation(Class<T> klass, int picWidth, int picHeight) {
        width = picWidth / _placeSizeWidth;
        height = picHeight / _placeSizeHeight;
        _places = (T[])Array.newInstance(klass, width * height);
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }


}
