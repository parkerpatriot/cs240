package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class MainBody {
    private Coordinate mCannonAttachPoint;
    private Coordinate mEngineAttachPoint;
    private Coordinate mLeftWingAttachPoint;
    private String mImage;
    private int mWidth;
    private int mHeight;
    private int id;

    /**
     * Constructor
     * @param cannonAttachPoint (required)
     * @param engineAttachPoint (required)
     * @param leftWingAttachPoint (required)
     * @param image path to image (required)
     * @param width image width (required)
     * @param height image height (required)
     */
    public MainBody(Coordinate cannonAttachPoint, Coordinate engineAttachPoint,
                    Coordinate leftWingAttachPoint, String image, int width, int height) {
        mCannonAttachPoint = cannonAttachPoint;
        mEngineAttachPoint = engineAttachPoint;
        mLeftWingAttachPoint = leftWingAttachPoint;
        mImage = image;
        mWidth = width;
        mHeight = height;
    }

    /**
     * Getter for cannot attach point
     * @return cannon attach coordinate
     */
    public Coordinate getCannonAttachPoint() {
        return mCannonAttachPoint;
    }

    /**
     * Getter for engine attach point
     * @return engine attach coordinate
     */
    public Coordinate getEngineAttachPoint() {
        return mEngineAttachPoint;
    }

    /**
     * Getter for left wing attach point
     * @return left wing attach coordinate
     */
    public Coordinate getLeftWingAttachPoint() {
        return mLeftWingAttachPoint;
    }

    /**
     * Getter for body image path
     * @return image path (String)
     */
    public String getImage() {
        return mImage;
    }

    /**
     * Getter for body image width
     * @return width
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * Getter for body image height
     * @return height
     */
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
