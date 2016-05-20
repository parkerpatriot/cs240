package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class BackgroundObject implements Comparable<BackgroundObject> {
    private String mImage;
    private int id;

    /**
     * Constructor.
     * @param imagePath the path to the image location (required)
     */
    public BackgroundObject(String imagePath) {
        mImage = imagePath;
    }

    public String getImage() {
        return mImage;
    }

    /**
     * Standard comparable method. Compares image paths
     * @param backgroundImage
     * @return
     */
    public int compareTo(BackgroundObject backgroundImage) {
        return this.getImage().compareTo(backgroundImage.getImage());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
