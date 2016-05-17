package edu.byu.cs.superasteroids.model;

import java.util.Scanner;

/**
 * Created by jparker on 5/14/16.
 */
public class Coordinate {
    private String mStrCoordinates;
    private int x;
    private int y;

    public Coordinate(String strCoordinates) {
        mStrCoordinates = strCoordinates;
        Scanner scanner = new Scanner(strCoordinates);
        x = scanner.nextInt();
        y = scanner.nextInt();
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
