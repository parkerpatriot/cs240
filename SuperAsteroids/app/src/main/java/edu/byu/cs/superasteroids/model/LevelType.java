package edu.byu.cs.superasteroids.model;

import java.util.List;

/**
 * Created by jparker on 5/13/16.
 */
public class LevelType {

    private int mNumber;
    private String mTitle;
    private String mHint;
    private int mWidth;
    private int mHeight;
    private String mMusic;
    private List<LevelBackgroundObject> mLevelBackgroundObjects;
    private List<LevelAsteroid> mLevelAsteroids;

    /**
     * Constructor.
     * @param number            The level number (this will serve as the primary key)
     * @param title             The name (or title) of the level
     * @param hint              A hint corresponding to the level. Example: "Destroy a regular asteroid"
     * @param width             The image width (pixels)
     * @param height            The image height (pixels)
     * @param music             The path to the background music file for the level
     * @param levelBackgroundObjects      A list of background objects which appear on the level
     * @param levelAsteroids    A list of asteroid types that appear on the level
     */
    public LevelType(int number, String title, String hint, int width, int height, String music,
                     List<LevelBackgroundObject> levelBackgroundObjects, List<LevelAsteroid> levelAsteroids) {
        mNumber = number;
        mTitle = title;
        mHint = hint;
        mWidth = width;

        mHeight = height;
        mMusic = music;
        mLevelBackgroundObjects = levelBackgroundObjects;
        mLevelAsteroids = levelAsteroids;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getHint() {
        return mHint;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getMusic() {
        return mMusic;
    }

    public List<LevelBackgroundObject> getLevelBackgroundObjects() {
        return mLevelBackgroundObjects;
    }

    public List<LevelAsteroid> getLevelAsteroids() {
        return mLevelAsteroids;
    }

    public void addLevelBackgroundObject(LevelBackgroundObject levelObject) {
        mLevelBackgroundObjects.add(levelObject);
    }

    public void setLevelBackgroundObjects(List<LevelBackgroundObject> levelBackgroundObjects) {
        mLevelBackgroundObjects = levelBackgroundObjects;
    }
}
