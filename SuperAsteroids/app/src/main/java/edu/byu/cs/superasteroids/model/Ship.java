package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class Ship {
    public static final Ship SINGLETON = new Ship();
    private Cannon mCannon;
    private Engine mEngine;
    private LeftWing mLeftWing;
    private MainBody mMainBody;
    private PowerCore mPowerCore;

    private Ship() {
    }

    public Cannon getCannon() {
        return mCannon;
    }

    public void setCannon(Cannon cannon) {
        mCannon = cannon;
    }

    public Engine getEngine() {
        return mEngine;
    }

    public void setEngine(Engine engine) {
        mEngine = engine;
    }

    public LeftWing getLeftWing() {
        return mLeftWing;
    }

    public void setLeftWing(LeftWing leftWing) {
        mLeftWing = leftWing;
    }

    public MainBody getMainBody() {
        return mMainBody;
    }

    public void setMainBody(MainBody mainBody) {
        mMainBody = mainBody;
    }

    public PowerCore getPowerCore() {
        return mPowerCore;
    }

    public void setPowerCore(PowerCore powerCore) {
        mPowerCore = powerCore;
    }


}
