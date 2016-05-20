package edu.byu.cs.superasteroids.model;

import java.util.List;

import edu.byu.cs.superasteroids.database.DAO;

/**
 * Created by jparker on 5/13/16.
 */
public class Model {
    public static final Model SINGLETON = new Model();
    private boolean _initialized;
    private Ship mShip = Ship.SINGLETON;
    private List<LevelType> mLevels;
    private List<AsteroidType> mAstroidTypes;
    private List<BackgroundObject> mBackgroundObjects;
    // ship parts
    private List<MainBody> mMainBodies;
    private List<Cannon> mCannons;
    private List<LeftWing> mLeftWings;
    private List<Engine> mEngines;
    private List<PowerCore> mPowerCores;

    private Model() {
        _initialized = false;
    }

    public void initialize() {
        mBackgroundObjects = DAO.SINGLETON.getAllBackgroundObjects();
        mAstroidTypes = DAO.SINGLETON.getAllAsteroidTypes();
        mLevels = DAO.SINGLETON.getAllLevels();
        mMainBodies = DAO.SINGLETON.getAllMainBodies();
        mCannons = DAO.SINGLETON.getAllCannons();
        mLeftWings = DAO.SINGLETON.getAllLeftWings();
        mEngines = DAO.SINGLETON.getAllEngines();
        mPowerCores = DAO.SINGLETON.getAllPowerCores();
    }

    public Ship getShip() {
        return mShip;
    }

    public List<LevelType> getLevels() {
        return mLevels;
    }

    public List<AsteroidType> getAstroidTypes() {
        return mAstroidTypes;
    }

    public List<BackgroundObject> getBackgroundObjects() {
        return mBackgroundObjects;
    }

    public List<MainBody> getMainBodies() {
        return mMainBodies;
    }

    public List<Cannon> getCannons() {
        return mCannons;
    }

    public List<LeftWing> getLeftWings() {
        return mLeftWings;
    }

    public List<Engine> getEngines() {
        return mEngines;
    }

    public List<PowerCore> getPowerCores() {
        return mPowerCores;
    }
}