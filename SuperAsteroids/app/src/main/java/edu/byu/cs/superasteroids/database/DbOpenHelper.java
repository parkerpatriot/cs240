package edu.byu.cs.superasteroids.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jparker on 5/18/16.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "super_asteroids.sqlite";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
//        deleteAllTables(db); // unnecessary since it must have just created the database
        createAllTables(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }

    public static void createAllTables(SQLiteDatabase db) {
        db.execSQL(CREATE_BACKGROUND_OBJECTS_TABLE);
        db.execSQL(CREATE_ASTEROID_TYPES_TABLE);
        db.execSQL(CREATE_LEVELS_TABLE);
        db.execSQL(CREATE_LEVEL_BACKGROUND_OBJECTS_TABLE);
        db.execSQL(CREATE_LEVEL_ASTEROID_TYPES_TABLE);
        db.execSQL(CREATE_MAIN_BODIES_TABLE);
        db.execSQL(CREATE_CANNONS_TABLE);
        db.execSQL(CREATE_LEFT_WINGS_TABLE);
        db.execSQL(CREATE_ENGINES_TABLE);
        db.execSQL(CREATE_POWER_CORES_TABLE);
    }

    public static void deleteAllTables(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("DROP TABLE IF EXISTS background_objects;");
        db.execSQL("DROP TABLE IF EXISTS levels;");
        db.execSQL("DROP TABLE IF EXISTS level_background_objects;");
        db.execSQL("DROP TABLE IF EXISTS level_asteroid_types;");
        db.execSQL("DROP TABLE IF EXISTS asteroid_types;");
        db.execSQL("DROP TABLE IF EXISTS main_bodies;");
        db.execSQL("DROP TABLE IF EXISTS cannons;");
        db.execSQL("DROP TABLE IF EXISTS left_wings;");
        db.execSQL("DROP TABLE IF EXISTS engines;");
        db.execSQL("DROP TABLE IF EXISTS power_cores;");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public static final String CREATE_BACKGROUND_OBJECTS_TABLE =

            "CREATE TABLE background_objects" +
            "(" +
            "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "	image TEXT NOT NULL" +
            ");";


    public static final String CREATE_ASTEROID_TYPES_TABLE =

            "CREATE TABLE asteroid_types" +
            "(" +
            "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "	name TEXT NOT NULL," +
            "	image TEXT NOT NULL," +
            "	width INTEGER NOT NULL," +
            "	height INTEGER NOT NULL" +
            ");";

    public static final String CREATE_LEVELS_TABLE =

            "CREATE TABLE levels" +
            "(" +
            "	id INTEGER NOT NULL PRIMARY KEY," +
            "	title TEXT NOT NULL," +
            "	hint TEXT NOT NULL," +
            "	width INTEGER NOT NULL," +
            "	height INTEGER NOT NULL," +
            "	music TEXT NOT NULL" +
            ");";

    public static final String CREATE_LEVEL_BACKGROUND_OBJECTS_TABLE =

            "CREATE TABLE level_background_objects" +
            "(" +
            "	level_id INTEGER NOT NULL," +
            "	object_id INTEGER NOT NULL," +
            "   position TEXT NOT NULL," +
            "	scale REAL NOT NULL," +
            "   FOREIGN KEY(object_id) REFERENCES background_objects(id)," +
            "   FOREIGN KEY(level_id) REFERENCES levels(id)" +
            ");";

    public static final String CREATE_LEVEL_ASTEROID_TYPES_TABLE =

            "CREATE TABLE level_asteroid_types" +
            "(" +
            "	level_id INTEGER NOT NULL," +
            "	asteroid_id INTEGER NOT NULL," +
            "	count INTEGER NOT NULL," +
            "   FOREIGN KEY(level_id) REFERENCES levels(id)," +
            "   FOREIGN KEY(asteroid_id) REFERENCES asteroid_types(id)" +
            ");";

    public static final String CREATE_MAIN_BODIES_TABLE =

            "CREATE TABLE main_bodies" +
            "(" +
            "	id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	cannon_attach_point TEXT NOT NULL," +
            "	engine_attach_point TEXT NOT NULL," +
            "	left_wing_attach_point TEXT NOT NULL," +
            "	image TEXT NOT NULL," +
            "	width INT NOT NULL," +
            "	height INT NOT NULL" +
            ");";

    public static final String CREATE_CANNONS_TABLE =

            "CREATE TABLE cannons" +
            "(	" +
            "	id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	image TEXT NOT NULL," +
            "	attach_point TEXT NOT NULL," +
            "	emit_point TEXT NOT NULL," +
            "	width INTEGER NOT NULL," +
            "	height INTEGER NOT NULL," +
            "	image_attack TEXT NOT NULL," +
            "	width_attack INTEGER NOT NULL," +
            "	height_attack INTEGER NOT NULL," +
            "	sound_attack TEXT NOT NULL," +
            "	damage INTEGER NOT NULL" +
            ");";

    public static final String CREATE_LEFT_WINGS_TABLE =

            "CREATE TABLE left_wings" +
            "(" +
            "	id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	attach_point TEXT NOT NULL," +
            "	image TEXT NOT NULL," +
            "	width INTEGER NOT NULL," +
            "	height INTEGER NOT NULL" +
            ");";

    public static final String CREATE_ENGINES_TABLE =

            "CREATE TABLE engines" +
            "(" +
            "	id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	base_speed INTEGER NOT NULL," +
            "	base_turn_rate INTEGER NOT NULL," +
            "	attach_point TEXT NOT NULL," +
            "	image TEXT NOT NULL," +
            "	width INTEGER NOT NULL," +
            "	height INTEGER NOT NULL" +
            ");";

    public static final String CREATE_POWER_CORES_TABLE =

            "CREATE TABLE power_cores" +
            "(" +
            "	id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "	cannon_boost INTEGER NOT NULL," +
            "	engine_boost INTEGER NOT NULL," +
            "	image TEXT NOT NULL" +
            ");";
}
