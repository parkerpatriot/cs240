package edu.byu.cs.superasteroids.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.byu.cs.superasteroids.model.AsteroidType;
import edu.byu.cs.superasteroids.model.BackgroundObject;
import edu.byu.cs.superasteroids.model.Cannon;
import edu.byu.cs.superasteroids.model.Coordinate;
import edu.byu.cs.superasteroids.model.Engine;
import edu.byu.cs.superasteroids.model.LeftWing;
import edu.byu.cs.superasteroids.model.LevelAsteroid;
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
    public static final DAO SINGLETON = new DAO();
    private SQLiteDatabase db;

    /**
     * Gets the single instance of the DAO
     * @return A reference to the DAO object
     */
    private DAO() {db = null;}

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * Adds a background object to the database.
     * @param backgroundObject A BackgroundObject object
     */
    public boolean addBackgroundObject(BackgroundObject backgroundObject) {
        ContentValues values = new ContentValues();
        values.put("image", backgroundObject.getImage());

        long id = db.insert("background_objects", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Get a list of all background objects in the database
     * @return A list of BackgroundObject objects
     */
    public List<BackgroundObject> getAllBackgroundObjects() {
        ArrayList<BackgroundObject> backgroundObjects = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_BACKGROUND_OBJECT_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String image = cursor.getString(1);
                BackgroundObject backgroundObject = new BackgroundObject(image);
                // backgroundObject.setId(id);
                backgroundObjects.add(backgroundObject);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return backgroundObjects;
    }

    /**
     * Adds and astroid to the database.
      * @param asteroidType An AsteroidType object
     */
    public boolean addAsteroidType(AsteroidType asteroidType) {
        ContentValues values = new ContentValues();
        values.put("name", asteroidType.getType());
        values.put("image", asteroidType.getImage());
        values.put("width", asteroidType.getWidth());
        values.put("height", asteroidType.getHeight());

        long id = db.insert("asteroid_types", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    public List<AsteroidType> getAllAsteroidTypes() {
        ArrayList<AsteroidType> asteroidTypes = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_ASTEROID_TYPE_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String image = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                AsteroidType asteroid = new AsteroidType(name, image, width, height);
                // asteroid.setId(id);
                asteroidTypes.add(asteroid);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return asteroidTypes;
    }

    /**
     * Adds a level to the database.
     * @param levelType A LevelType object
     */
    public boolean addLevel(LevelType levelType) {
        ContentValues values = new ContentValues();
        values.put("id", levelType.getNumber());
        values.put("title", levelType.getTitle());
        values.put("hint", levelType.getHint());
        values.put("width", levelType.getWidth());
        values.put("height", levelType.getHeight());
        values.put("music", levelType.getMusic());
        long id = db.insert("levels", null, values);
        addLevelBackgroundObjects(levelType.getLevelBackgroundObjects());
        addLevelAsteroids(levelType.getLevelAsteroids());

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Gets a list of all levels in the database.
     * @return A list of LevelType objects
     */
    public List<LevelType> getAllLevels() {
        ArrayList<LevelType> levelTypes = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_LEVEL_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int number = cursor.getInt(0);
                String title = cursor.getString(1);
                String hint = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                String music = cursor.getString(5);
                List<LevelBackgroundObject> levelBackgroundObjects = getLevelBackgroundObjects(number);
                List<LevelAsteroid> levelAsteroids = getLevelAsteroids(number);
                LevelType levelType = new LevelType(number, title, hint, width, height, music, levelBackgroundObjects, levelAsteroids);
                levelTypes.add(levelType);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return levelTypes;
    }

    public boolean addLevelAsteroid(LevelAsteroid levelAsteroid){
        ContentValues values = new ContentValues();
        values.put("level_id", levelAsteroid.getLevelId());
        values.put("asteroid_id", levelAsteroid.getAsteroidId());
        values.put("count", levelAsteroid.getNumber());

        long id = db.insert("level_asteroid_types", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }
        return result;
    }
    public boolean addLevelBackgroundObject(LevelBackgroundObject levelBackgroundObject) {
        ContentValues values = new ContentValues();
        values.put("level_id", levelBackgroundObject.getLevelId());
        values.put("object_id", levelBackgroundObject.getObjectId());
        values.put("position", levelBackgroundObject.getPosition().toString());
        values.put("scale", levelBackgroundObject.getScale());

        long id = db.insert("level_background_objects", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }
        return result;
    }

    private boolean addLevelAsteroids(List<LevelAsteroid> levelAsteroids){
        for(LevelAsteroid levelAsteroid: levelAsteroids) {
            if(!addLevelAsteroid(levelAsteroid)){
                return false;
            }
        }
        return true;
    }
    private List<LevelAsteroid> getLevelAsteroids(int level_id) {
        List<LevelAsteroid> levelAsteroids = new ArrayList<>();
        Cursor cursor = db.rawQuery(String.format(Locale.ENGLISH,
                "select * from level_asteroid_types where level_id = %d", level_id),
                EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int levelId = cursor.getInt(0);
                int asteroidId = cursor.getInt(1);
                int count = cursor.getInt(2);
                LevelAsteroid levelAsteroid = new LevelAsteroid(count, asteroidId, levelId);
                levelAsteroids.add(levelAsteroid);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return levelAsteroids;
    }
    private List<LevelBackgroundObject> getLevelBackgroundObjects(int level_id) {
        ArrayList<LevelBackgroundObject> backgroundObjects = new ArrayList<>();

        Cursor cursor = db.rawQuery(String.format(Locale.ENGLISH,
                "select * from level_background_objects where level_id = %d", level_id),
                EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int levelId = cursor.getInt(0);
                int objectId = cursor.getInt(1);
                Coordinate position = new Coordinate(cursor.getString(2));
                float scale = cursor.getFloat(3);
                LevelBackgroundObject levelBackgroundObject = new LevelBackgroundObject(position, objectId, scale, levelId);
                backgroundObjects.add(levelBackgroundObject);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return backgroundObjects;
    }
    private boolean addLevelBackgroundObjects(List<LevelBackgroundObject> levelBackgroundObjects) {
        for(LevelBackgroundObject levelBackgroundObject: levelBackgroundObjects) {
            if(!addLevelBackgroundObject(levelBackgroundObject)){
                return false;
            }
        }
        return true;
    }


    /**
     * Adds a cannon to the database.
     * @param cannon A Cannon object
     */
    public boolean addCannon(Cannon cannon) {
        ContentValues values = new ContentValues();
        values.put("image", cannon.getImage());
        values.put("attach_point", cannon.getAttachPoint().toString());
        values.put("emit_point", cannon.getEmitPoint().toString());
        values.put("width", cannon.getWidth());
        values.put("height", cannon.getHeight());
        values.put("image_attack", cannon.getAttackImage());
        values.put("width_attack", cannon.getAttackWidth());
        values.put("height_attack", cannon.getAttackHeight());
        values.put("image_attack", cannon.getAttackImage());
        values.put("sound_attack", cannon.getAttackSound());
        values.put("damage", cannon.getDamage());
        long id = db.insert("cannons", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Gets a list of all cannons in the database.
     * @return A list of Cannon objects
     */
    public List<Cannon> getAllCannons() {
        ArrayList<Cannon> cannons = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_CANNON_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String image = cursor.getString(1);
                Coordinate attachPoint = new Coordinate(cursor.getString(2));
                Coordinate emitPoint = new Coordinate(cursor.getString(3));
                int width = cursor.getInt(4);
                int height = cursor.getInt(5);
                String attackImage = cursor.getString(6);
                int attackWidth = cursor.getInt(7);
                int attackHeight = cursor.getInt(8);
                String attackSound = cursor.getString(9);
                int damage = cursor.getInt(10);
                Cannon cannon = new Cannon(attachPoint, emitPoint, image, width, height,
                        attackImage, attackWidth, attackHeight, attackSound, damage);
                // cannon.setId(id);
                cannons.add(cannon);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return cannons;    }

    /**
     * Adds an engine to the database.
     * @param engine An Engine object
     */
    public boolean addEngine(Engine engine) {
        ContentValues values = new ContentValues();
        values.put("image", engine.getImage());
        values.put("attach_point", engine.getAttachPoint().toString());
        values.put("base_speed", engine.getBaseSpeed());
        values.put("base_turn_rate", engine.getBaseTurnRate());
        values.put("width", engine.getWidth());
        values.put("height", engine.getHeight());
        long id = db.insert("engines", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Returns a list of all engines in the database.
     * @return A list of Engine objects
     */
    public List<Engine> getAllEngines() {
        ArrayList<Engine> engines = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_ENGINE_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                int baseSpeed = cursor.getInt(1);
                int baseTurnRate = cursor.getInt(2);
                Coordinate attachPoint = new Coordinate(cursor.getString(3));
                String image = cursor.getString(4);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                Engine engine = new Engine(baseSpeed, baseTurnRate, attachPoint, image, width, height);
                // engine.setId(id);
                engines.add(engine);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return engines;
    }

    /**
     * Adds a left wing (aka "extraPart" to the database.
     * @param leftWing A LeftWing object
     */
    public boolean addLeftWing(LeftWing leftWing) {
        ContentValues values = new ContentValues();
        values.put("attach_point", leftWing.getAttachPoint().toString());
        values.put("image", leftWing.getImage());
        values.put("width", leftWing.getWidth());
        values.put("height", leftWing.getHeight());
        long id = db.insert("left_wings", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Gets a list of all left wings in the database.
     * @return A list of LeftWing objects
     */
    public List<LeftWing> getAllLeftWings() {
        ArrayList<LeftWing> leftWings = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_LEFT_WING_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                Coordinate attachPoint = new Coordinate(cursor.getString(1));
                String image = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                LeftWing leftWing = new LeftWing(image, attachPoint, width, height);
                // leftWing.setId(id);
                leftWings.add(leftWing);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return leftWings;
    }

    /**
     * Adds a mainBody to the database.
     * @param mainBody A MainBody object
     */
    public boolean addMainBody(MainBody mainBody) {
        ContentValues values = new ContentValues();
        values.put("image", mainBody.getImage());
        values.put("cannon_attach_point", mainBody.getCannonAttachPoint().toString());
        values.put("engine_attach_point", mainBody.getEngineAttachPoint().toString());
        values.put("left_wing_attach_point", mainBody.getLeftWingAttachPoint().toString());
        values.put("width", mainBody.getWidth());
        values.put("height", mainBody.getHeight());
        long id = db.insert("main_bodies", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Gets a list of all the main bodies in the database.
     * @return A list of MainBody objects
     */
    public List<MainBody> getAllMainBodies() {
        ArrayList<MainBody> mainBodies = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_MAIN_BODY_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                Coordinate cannonAttachPoint = new Coordinate(cursor.getString(1));
                Coordinate engineAttachPoint = new Coordinate(cursor.getString(2));
                Coordinate leftWingAttachPoint = new Coordinate(cursor.getString(3));
                String image = cursor.getString(2);
                int width = cursor.getInt(3);
                int height = cursor.getInt(4);
                MainBody mainBody = new MainBody(cannonAttachPoint, engineAttachPoint, leftWingAttachPoint, image, width, height);
                // mainBody.setId(id);
                mainBodies.add(mainBody);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return mainBodies;
    }

    /**
     * Adds a power core to the database.
     * @param powerCore A PowerCore object
     */
    public boolean addPowerCore(PowerCore powerCore) {
        ContentValues values = new ContentValues();
        values.put("image", powerCore.getImage());
        values.put("cannon_boost", powerCore.getCannonBoost());
        values.put("engine_boost", powerCore.getEngineBoost());
        long id = db.insert("power_cores", null, values);

        boolean result = false;
        if (id >= 0) {
//            object.setId(id);
            result = true;
        }

        return result;
    }

    /**
     * Gets a list of all power cores in the database.
     * @return A list of PowerCore objects
     */
    public List<PowerCore> getAllPowerCores() {
        ArrayList<PowerCore> powerCores = new ArrayList<>();

        Cursor cursor = db.rawQuery(SELECT_ALL_POWER_CORE_INFO, EMPTY_ARRAY_OF_STRINGS);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                int cannonBoost = cursor.getInt(1);
                int engineBoost = cursor.getInt(2);
                String image = cursor.getString(3);
                PowerCore powerCore = new PowerCore(cannonBoost,engineBoost, image);
                // powerCore.setId(id);
                powerCores.add(powerCore);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return powerCores;
    }

    public void emptyDatabase() {
        DbOpenHelper.deleteAllTables(db);
        DbOpenHelper.createAllTables(db);
    }

    private static final String[] EMPTY_ARRAY_OF_STRINGS = {};
    private static final String SELECT_ALL_BACKGROUND_OBJECT_INFO= "select * from background_objects";
    private static final String SELECT_ALL_ASTEROID_TYPE_INFO= "select * from asteroid_types";
    private static final String SELECT_ALL_LEVEL_INFO = "select * from levels";
    private static final String SELECT_ALL_LEVEL_BACKGROUND_OBJECTS_INFO= "select * from level_background_objects";
    private static final String SELECT_ALL_LEVEL_ASTROID_INFO= "select * from level_asteroid_types";
    private static final String SELECT_ALL_MAIN_BODY_INFO= "select * from main_bodies";
    private static final String SELECT_ALL_CANNON_INFO= "select * from cannons";
    private static final String SELECT_ALL_LEFT_WING_INFO= "select * from left_wings";
    private static final String SELECT_ALL_ENGINE_INFO= "select * from engines";
    private static final String SELECT_ALL_POWER_CORE_INFO= "select * from power_cores";
}
