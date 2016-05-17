package edu.byu.cs.superasteroids.model;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jparker on 5/13/16.
 */
public class Engine {
    private int mBaseSpeed;
    private int mBaseTurnRate;
    private Coordinate mAttachPoint;
    private String mImage;
    private int mWidth;
    private int mHeight;

    public Engine(int baseSpeed, int baseTurnRate, Coordinate attachPoint, String image, int width, int height) {
        mBaseSpeed = baseSpeed;
        mBaseTurnRate = baseTurnRate;
        mAttachPoint = attachPoint;
        mImage = image;
        mWidth = width;
        mHeight = height;
    }

    public int getBaseSpeed() {
        return mBaseSpeed;
    }

    public int getBaseTurnRate() {
        return mBaseTurnRate;
    }

    public Coordinate getAttachPoint() {
        return mAttachPoint;
    }

    public String getImage() {
        return mImage;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
