package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class PowerCore {
    private int mCannonBoost;
    private int mEngineBoost;
    private String mImage;
    private int id;

    /**
     * Constructor.
     * @param cannonBoost   Cannon boost power
     * @param engineBoost   Engine boost power
     * @param image         The path to the power core image
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
