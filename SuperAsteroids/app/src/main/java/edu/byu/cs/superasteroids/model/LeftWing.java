package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class LeftWing {
    private String mImage;
    private Coordinate mAttachPoint;
    private int mWidth;
    private int mHeight;
    private int id;

    /**
     * Constructor.
     * @param image         The path to the wing image
     * @param attachPoint   The attchment point to the rest of the ship. Example: "150, 75"
     * @param width         The wing image width
     * @param height        The wing image height
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
