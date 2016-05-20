package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class AsteroidType implements Comparable<AsteroidType> {

    private String mType;
    private String mImage;
    private int mWidth;
    private int mHeight;
    private int id;
    
    /**
     * Constructor.
     * @param type      Usually the name of the asteroid. Example: "octeroid"
     * @param image     The path to the asteroid image
     * @param width     The image width (pixels)
     * @param height    The image height (pixels)
     */
    public AsteroidType(String type, String image, int width, int height) {
        mType = type;
        mImage = image;
        mWidth = width;
        mHeight = height;
    }

    public String getType() {
        return mType;
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

    public int compareTo(AsteroidType asteroid) {
        return this.getType().compareTo(asteroid.getType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsteroidType that = (AsteroidType) o;

        if (mWidth != that.mWidth) return false;
        if (mHeight != that.mHeight) return false;
        if (mType != null ? !mType.equals(that.mType) : that.mType != null) return false;
        return mImage != null ? mImage.equals(that.mImage) : that.mImage == null;

    }

    @Override
    public int hashCode() {
        int result = mType != null ? mType.hashCode() : 0;
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        result = 31 * result + mWidth;
        result = 31 * result + mHeight;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
