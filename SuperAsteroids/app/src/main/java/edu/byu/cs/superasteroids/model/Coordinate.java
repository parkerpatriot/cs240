package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import java.util.Scanner;

/**
 * Created by jparker on 5/14/16.
 */
public class Coordinate extends PointF {
    public static final Creator<PointF> CREATOR = null;
    private String mStrCoordinates;

    public Coordinate(String strCoordinates) {
        super();
        mStrCoordinates = strCoordinates;
        String[] coords = mStrCoordinates.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        set((float)x, (float)y);
    }

    @Override
    public String toString() {
        return mStrCoordinates;
    }
}
