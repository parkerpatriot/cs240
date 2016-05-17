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
    private List<LevelBackgroundObject> mLevelObjects;

    public LevelType(int number, String title, String hint, int width, int height, String music, List<LevelBackgroundObject> levelObjects) {
        mNumber = number;
        mTitle = title;
        mHint = hint;
        mWidth = width;
        mHeight = height;
        mMusic = music;
        mLevelObjects = levelObjects;
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

    public List<LevelBackgroundObject> getLevelObjects() {
        return mLevelObjects;
    }

    public void addLevelObject(LevelBackgroundObject levelObject) {
        mLevelObjects.add(levelObject);
    }

    public void setLevelObjects(List<LevelBackgroundObject> levelObjects) {
        mLevelObjects = levelObjects;
    }
}
