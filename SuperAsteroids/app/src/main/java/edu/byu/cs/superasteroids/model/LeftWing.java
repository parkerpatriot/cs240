package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class LeftWing {
    private String mImage;
    private Coordinate mAttachPoint;
    private int mWidth;
    private int mHeight;

    public LeftWing(String image, Coordinate attachPoint, int width, int height) {
        mImage = image;
        mAttachPoint = attachPoint;
        mWidth = width;
        mHeight = height;
    }

    public String getImage() {
        return mImage;
    }

    public Coordinate getAttachPoint() {
        return mAttachPoint;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
