package edu.byu.cs.superasteroids.model;

import java.util.Scanner;

/**
 * Created by jparker on 5/14/16.
 */
public class LevelBackgroundObject {
    private Coordinate mPosition;
    public int mObjectId;
    public int mScale;

    public LevelBackgroundObject(Coordinate position, int objectId, int scale) {
        mPosition = position;
        mObjectId = objectId;
        mScale = scale;
    }

    /**
     * Getter for the position coordinates
     * @return a comma delineated String of the x,y coordinates
     */
    public Coordinate getPosition() {
        return mPosition;
    }

    public int getObjectId() {
        return mObjectId;
    }

    public int getScale() {
        return mScale;
    }
}
