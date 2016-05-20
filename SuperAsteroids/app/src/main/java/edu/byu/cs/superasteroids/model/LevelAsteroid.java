package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/14/16.
 */
public class LevelAsteroid {
    private int mNumber;
    private int mAsteroidId;
    private int mLevelId;

    /**
     * Constructor.
     * @param number    The number of asteroids with ID asteroidId which appear on the level
     * @param astroidId The ID number of the asteroid
     * @param levelId   The level number on which the asteroid appears
     */
    public LevelAsteroid(int number, int astroidId, int levelId) {
        mNumber = number;
        mAsteroidId = astroidId;
        mLevelId = levelId;
    }

    public int getNumber() {
        return mNumber;
    }

    public int getAsteroidId() {
        return mAsteroidId;
    }

    public int getLevelId() {
        return mLevelId;
    }
}
