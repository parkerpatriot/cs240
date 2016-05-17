package edu.byu.cs.superasteroids.database;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.model.AsteroidType;
import edu.byu.cs.superasteroids.model.BackgroundObject;
import edu.byu.cs.superasteroids.model.Cannon;
import edu.byu.cs.superasteroids.model.Engine;
import edu.byu.cs.superasteroids.model.LeftWing;
import edu.byu.cs.superasteroids.model.LevelBackgroundObject;
import edu.byu.cs.superasteroids.model.LevelType;
import edu.byu.cs.superasteroids.model.MainBody;
import edu.byu.cs.superasteroids.model.PowerCore;

/**
 * <h>Data Access Object (DAO)</h>
 * The DAO is a singleton (the constructor is private). This design uses
 * only one DAO for accessing all tables in the SQLite database.
 *
 * @author James Parker
 * @version 1.0
 */
public class DAO {
    private static DAO ourInstance = new DAO();

    /**
     * Gets the single instance of the DAO
     * @return A reference to the DAO object
     */
    public static DAO getInstance() {
        return ourInstance;
    }

    private DAO() {
    }

    /**
     * Adds a background image to the database.
     * @param imagePath A String representing the path to the the image
     */
    public void addBackgroundObject(String imagePath) {}

    /**
     * Get a list of all background objects in the database
     * @return A list of BackgroundObject objects
     */
    public List<LevelBackgroundObject> getBackgroundObjects() {
        return null;
    }

    /**
     * Adds an asteroid type to the database.
     * @param type      Usually the name of the asteroid. Example: "octeroid"
     * @param image     The path to the asteroid image
     * @param width     The image width (pixels)
     * @param height    The image height (pixels)
     */
    public void addAstroid(String type, String image, int width, int height) {}

    /**
     * Adds a level to the database.
     * @param number    The level number (this will serve as the primary key)
     * @param title     The name (or title) of the level
     * @param hint      A hint corresponding to the level. Example: "Destroy a regular asteroid"
     * @param width     The image width (pixels)
     * @param height    The image height (pixels)
     * @param music     The path to the background music file for the level
     */
    public void addLevel(int number, String title, String hint, int width, int height, String music) {}

    /**
     * Gets a list of all levels in the database.
     * @return A list of LevelType objects
     */
    public List<LevelType> getLevels() {
        return null;
    }

    /**
     * Maps an asteroid type to a particular level in the database.
     * @param number    The number of asteroids with ID asteroidId on
     * @param astroidId The ID number of the asteroid. FK: asteroid(id)
     * @param levelId   The level number that the asteroid appears on. FK: levels(id)
     */
    public void addLevelAsteroid(int number, int astroidId, int levelId){}

    /**
     * Maps a background object to a particular level in the database.
     * @param position  The position of the object. Example: "150, 75"
     * @param objectId  The ID of the background object. FK: background_objects(id)
     * @param scale     The scale of the background object. Example: 2.0
     * @param level_id  The level number the object appears on. FK: levels(id)
     */
    public void addLevelBackgroundObject(String position, int objectId, int scale, int level_id) {}

    /**
     * Adds a cannon to the database.
     * @param attachPoint   The attachment point to the rest of the ship. Example: "150, 75"
     * @param emitPoint     The point at which the lasers are emitted. Example: "200, 75"
     * @param image         The path to the cannon image
     * @param width         The image width (pixels)
     * @param height        The image height (pixels)
     * @param attackImage   The path to the firing cannon image
     * @param attackWidth   The width of the firing cannon image
     * @param attackHeight  The height of the firing cannon image
     * @param attackSound   The path to the sound of the cannon firing
     * @param damage        The damage power of the cannon
     */
    public void addCannon(String attachPoint, String emitPoint, String image, int width, int height,
                                 String attackImage, int attackWidth, int attackHeight, String attackSound,
                                 int damage) {}

    /**
     * Gets a list of all cannons in the database.
     * @return A list of Cannon objects
     */
    public List<Cannon> getCannons() {
        return null;
    }

    /**
     * Adds an engine to the database.
     * @param baseSpeed     The default speed of the engine
     * @param baseTurnRate  The turn rate of engine
     * @param attachPoint   The attachment point to the rest of the ship. Example: "150, 75"
     * @param image         The path to the engine image
     * @param width         The width of the engine image (pixels)
     * @param height        The height of the engine image (pixels)
     */
    public void addEngine(int baseSpeed, int baseTurnRate, String attachPoint, String image, int width, int height) {}

    /**
     * Returns a list of all engines in the database.
     * @return A list of Engine objects
     */
    public List<Engine> getEngines() {
        return null;
    }

    /**
     * Adds a left wing (aka "extraPart" to the database.
     * @param image         The path to the wing image
     * @param attachPoint   The attchment point to the rest of the ship. Example: "150, 75"
     * @param width         The wing image width
     * @param height        The wing image height
     */
    public void addLeftWing(String image, String attachPoint, int width, int height) {}

    /**
     * Gets a list of all left wings in the database.
     * @return A list of LeftWing objects
     */
    public List<LeftWing> getLeftWings() {
        return null;
    }

    /**
     * Adds a mainBody to the database.
     * @param cannonAttach      The attachment point of the cannon. Example: "150, 75"
     * @param engineAttach      The attachment point of the engine. Example: "150, 75"
     * @param leftWingAttach    The attachment point of the left wing. Example: "150, 75"
     * @param image             The path to the main body image
     * @param width             The main body image width (pixels)
     * @param height            The main body image height (pixels)
     */
    public void addMainBody(String cannonAttach, String engineAttach, String leftWingAttach, String image, int width, int height) {}

    /**
     * Gets a list of all the main bodies in the database.
     * @return A list of MainBody objects
     */
    public List<MainBody> getMainBodies() {
        return null;
    }

    /**
     * Adds a power core to the database.
     * @param cannonBoost   Cannon boost power
     * @param engineBoost   Engine boost power
     * @param image         The path to the power core image
     */
    public void addPowerCore(int cannonBoost, int engineBoost, String image) {}

    /**
     * Gets a list of all power cores in the database.
     * @return A list of PowerCore objects
     */
    public List<PowerCore> getPowerCores() {
        return null;
    }
}
