package edu.byu.cs.superasteroids.importer;

import android.graphics.AvoidXfermode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.database.DAO;
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
import edu.byu.cs.superasteroids.model.Model;
import edu.byu.cs.superasteroids.model.PowerCore;

/**
 * Created by jparker on 5/18/16.
 */
public class GameDataImporter implements IGameDataImporter {

    public boolean importData(InputStreamReader dataInputReader) {
        boolean check = true;
        try {
            DAO.SINGLETON.emptyDatabase();
            JSONObject jRoot = new JSONObject(makeString(dataInputReader));
            JSONObject rootObject = jRoot.getJSONObject("asteroidsGame");
  
            check = check && addBackgroundObjects(rootObject);
            check = check && addAsteroids(rootObject);
            check = check && addLevels(rootObject);
            check = check && addMainBodies(rootObject);
            check = check && addCannons(rootObject);
            check = check && addLeftWings(rootObject);
            check = check && addEngines(rootObject);
            check = check && addPowerCores(rootObject);

            Model.SINGLETON.initialize();

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return check;
    }

    private static String makeString(Reader reader) throws IOException {

        StringBuilder sb = new StringBuilder();
        char[] buf = new char[512];

        int n = 0;
        while ((n = reader.read(buf)) > 0) {
            sb.append(buf, 0, n);
        }

        return sb.toString();
    }

    private boolean addBackgroundObjects(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("objects");
        for(int i=0; i<jsonArray.length(); i++) {
            String pathTo = jsonArray.getString(i);
            BackgroundObject backgroundObject = new BackgroundObject(pathTo);
            if(!DAO.SINGLETON.addBackgroundObject(backgroundObject)){
                return false;
            }
        }
        return true;
    }

    private boolean addAsteroids(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("asteroids");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonAsteroid = jsonArray.getJSONObject(i);
            String type = jsonAsteroid.getString("name");
            String image = jsonAsteroid.getString("image");
            int width = jsonAsteroid.getInt("imageWidth");
            int height = jsonAsteroid.getInt("imageHeight");
            AsteroidType asteroidType = new AsteroidType(type, image, width, height);
            if(!DAO.SINGLETON.addAsteroidType(asteroidType)){
                return false;
            }
        }
        return true;
    }

    private boolean addLevels(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("levels");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonLevel = jsonArray.getJSONObject(i);
            int number = jsonLevel.getInt("number");
            String title = jsonLevel.getString("title");
            String hint = jsonLevel.getString("hint");
            int width = jsonLevel.getInt("width");
            int height = jsonLevel.getInt("height");
            String music = jsonLevel.getString("music");
            List<LevelBackgroundObject> levelBackgroundObjects = makeLevelBackgroundObjects(jsonLevel.getJSONArray("levelObjects"), number);
            List<LevelAsteroid> levelAsteroids = makeLevelAstroids(jsonLevel.getJSONArray("levelAsteroids"), number);
            LevelType levelType = new LevelType(number, title, hint, width, height, music, levelBackgroundObjects, levelAsteroids);
            if(!DAO.SINGLETON.addLevel(levelType)){
                return false;
            }
        }
        return true;

    }

    private List<LevelBackgroundObject> makeLevelBackgroundObjects(JSONArray levelBackgroundObjects, int levelId) throws JSONException {
        ArrayList<LevelBackgroundObject> levelBackgroundObjectsList = new ArrayList<>();
        for(int i=0; i<levelBackgroundObjects.length(); i++) {
            JSONObject jsonBackGroundObject = levelBackgroundObjects.getJSONObject(i);
            Coordinate position = new Coordinate(jsonBackGroundObject.getString("position"));
            int objectId = jsonBackGroundObject.getInt("objectId");
            float scale = (float) jsonBackGroundObject.getDouble("scale");
            LevelBackgroundObject levelBackgroundObject = new LevelBackgroundObject(position, objectId, scale, levelId);
            levelBackgroundObjectsList.add(levelBackgroundObject);
        }
        return levelBackgroundObjectsList;
    }

    private List<LevelAsteroid> makeLevelAstroids(JSONArray levelAsteroids, int levelId) throws JSONException {
        ArrayList<LevelAsteroid> levelAsteroidsList = new ArrayList<>();
        for(int i=0; i<levelAsteroids.length(); i++) {
            JSONObject jsonLevelAsteroid = levelAsteroids.getJSONObject(i);
            int number = jsonLevelAsteroid.getInt("number");
            int asteroidId = jsonLevelAsteroid.getInt("asteroidId");
            LevelAsteroid levelAsteroid = new LevelAsteroid(number, asteroidId, levelId);
            levelAsteroidsList.add(levelAsteroid);
        }
        return levelAsteroidsList;
    }

    private boolean addMainBodies(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("mainBodies");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonMainBody = jsonArray.getJSONObject(i);
            Coordinate cannonAttach = new Coordinate(jsonMainBody.getString("cannonAttach"));
            Coordinate engineAttach = new Coordinate(jsonMainBody.getString("engineAttach"));
            Coordinate leftWingAttach = new Coordinate(jsonMainBody.getString("extraAttach"));
            String image = jsonMainBody.getString("image");
            int width = jsonMainBody.getInt("imageWidth");
            int height = jsonMainBody.getInt("imageHeight");
            MainBody mainBody = new MainBody(cannonAttach, engineAttach, leftWingAttach, image, width, height);
            if(!DAO.SINGLETON.addMainBody(mainBody)){
                return false;
            }
        }
        return true;
    }

    private boolean addCannons(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("cannons");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Coordinate attachPoint = new Coordinate(jsonObject.getString("attachPoint"));
            Coordinate emitPoint = new Coordinate(jsonObject.getString("emitPoint"));
            String image = jsonObject.getString("image");
            int width = jsonObject.getInt("imageWidth");
            int height = jsonObject.getInt("imageHeight");
            String attackImage = jsonObject.getString("attackImage");
            int attackWidth = jsonObject.getInt("attackImageWidth");
            int attackHeight = jsonObject.getInt("attackImageHeight");
            String attackSound = jsonObject.getString("attackSound");
            int damage = jsonObject.getInt("damage");
            Cannon cannon = new Cannon(attachPoint, emitPoint, image, width, height, attackImage,
                    attackWidth, attackHeight, attackSound, damage);
            if(!DAO.SINGLETON.addCannon(cannon)){
                return false;
            }
        }
        return true;
    }

    private boolean addLeftWings(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("extraParts");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonLeftWing = jsonArray.getJSONObject(i);
            Coordinate attachPoint = new Coordinate(jsonLeftWing.getString("attachPoint"));
            String image = jsonLeftWing.getString("image");
            int width = jsonLeftWing.getInt("imageWidth");
            int height = jsonLeftWing.getInt("imageHeight");
            LeftWing leftWing = new LeftWing(image, attachPoint, width,height);
            if(!DAO.SINGLETON.addLeftWing(leftWing)){
                return false;
            }
        }
        return true;
    }

    private boolean addEngines(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("engines");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonEngine = jsonArray.getJSONObject(i);
            int baseSpeed = jsonEngine.getInt("baseSpeed");
            int baseTurnRate = jsonEngine.getInt("baseTurnRate");
            Coordinate attachPoint = new Coordinate(jsonEngine.getString("attachPoint"));
            String image = jsonEngine.getString("image");
            int width = jsonEngine.getInt("imageWidth");
            int height = jsonEngine.getInt("imageHeight");
            Engine engine = new Engine(baseSpeed, baseTurnRate, attachPoint, image, width, height);
            if(!DAO.SINGLETON.addEngine(engine)){
                return false;
            }
        }
        return true;
    }

    private boolean addPowerCores(JSONObject rootObject) throws JSONException {
        JSONArray jsonArray = rootObject.getJSONArray("powerCores");
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonPowerCore = jsonArray.getJSONObject(i);
            int cannonBoost = jsonPowerCore.getInt("cannonBoost");
            int engineBoost = jsonPowerCore.getInt("engineBoost");
            String image = jsonPowerCore.getString("image");
            PowerCore powerCore = new PowerCore(cannonBoost, engineBoost, image);
            if(!DAO.SINGLETON.addPowerCore(powerCore)){
                return false;
            }
        }
        return true;
    }

}
