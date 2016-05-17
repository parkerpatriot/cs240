package edu.byu.cs.superasteroids.model;

import java.util.Scanner;

/**
 * Created by jparker on 5/13/16.
 */
public class MainBody {
    private Coordinate mCannonAttach;
    private Coordinate mEngineAttach;
    private Coordinate mLeftWingAttach;
    private String mImage;
    private int mWidth;
    private int mHeight;

    /**
     * Constructor
     * @param cannonAttach (required)
     * @param engineAttach (required)
     * @param leftWingAttach (required)
     * @param image path to image (required)
     * @param width image width (required)
     * @param height image height (required)
     */
    public MainBody(Coordinate cannonAttach, Coordinate engineAttach, Coordinate leftWingAttach, String image, int width, int height) {
        mCannonAttach = cannonAttach;
        mEngineAttach = engineAttach;
        mLeftWingAttach = leftWingAttach;
        mImage = image;
        mWidth = width;
        mHeight = height;
    }

    /**
     * Getter for cannot attach point
     * @return cannon attach coordinate
     */
    public Coordinate getCannonAttach() {
        return mCannonAttach;
    }

    /**
     * Getter for engine attach point
     * @return engine attach coordinate
     */
    public Coordinate getEngineAttach() {
        return mEngineAttach;
    }

    /**
     * Getter for left wing attach point
     * @return left wing attach coordinate
     */
    public Coordinate getLeftWingAttach() {
        return mLeftWingAttach;
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
}
