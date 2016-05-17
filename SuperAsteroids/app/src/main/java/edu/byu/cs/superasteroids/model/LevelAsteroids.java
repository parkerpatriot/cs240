package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/14/16.
 */
public class LevelAsteroids {
    private int mNumber;
    private int mAsteroidId;

    public LevelAsteroids(int number, int astroidId) {
        mNumber = number;
        mAsteroidId = astroidId;
    }

    public int getNumber() {
        return mNumber;
    }

    public int getAsteroidId() {
        return mAsteroidId;
    }
}
