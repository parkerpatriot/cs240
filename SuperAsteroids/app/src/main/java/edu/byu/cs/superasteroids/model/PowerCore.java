package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class PowerCore {
    private int mCannonBoost;
    private int mEngineBoost;
    private String mImage;

    public PowerCore(int cannonBoost, int engineBoost, String image) {
        mCannonBoost = cannonBoost;
        mEngineBoost = engineBoost;
        mImage = image;
    }

    public int getCannonBoost() {
        return mCannonBoost;
    }

    public int getEngineBoost() {
        return mEngineBoost;
    }

    public String getImage() {
        return mImage;
    }
}
