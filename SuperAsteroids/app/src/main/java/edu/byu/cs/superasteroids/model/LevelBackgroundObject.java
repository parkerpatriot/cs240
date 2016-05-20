package edu.byu.cs.superasteroids.model;

import java.util.Scanner;

/**
 * Created by jparker on 5/14/16.
 */
public class LevelBackgroundObject {
    private Coordinate mPosition;
    private int mObjectId;
    private float mScale;
    private int mLevelId;


    /**
     * Constructor.
     * @param position  The position of the object. Example: "150, 75"
     * @param objectId  The ID of the background object. FK: background_objects(id)
     * @param scale     The scale of the background object. Example: 2.0
     * @param levelId  The level number the object appears on. FK: levels(id)
     */
    public LevelBackgroundObject(Coordinate position, int objectId, float scale, int levelId) {
        mPosition = position;
        mObjectId = objectId;
        mScale = scale;
        mLevelId = levelId;
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

    public float getScale() {
        return mScale;
    }

    public int getLevelId() {
        return mLevelId;
    }
}
