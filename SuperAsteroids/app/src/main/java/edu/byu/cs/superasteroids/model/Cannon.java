package edu.byu.cs.superasteroids.model;

/**
 * Created by jparker on 5/13/16.
 */
public class Cannon {
    private Coordinate mAttachPoint;
    private Coordinate mEmitPoint;
    private String mImage;
    private int mWidth;
    private int mHeight;
    private String mAttackImage;
    private int mAttackWidth;
    private int mAttackHeight;
    private String mAttackSound;
    private int mDamage;

    public Cannon(Coordinate attachPoint, Coordinate emitPoint, String image, int width, int height,
                  String attackImage, int attackWidth, int attackHeight, String attackSound,
                  int damage) {
        mAttachPoint = attachPoint;
        mEmitPoint = emitPoint;
        mImage = image;
        mWidth = width;
        mHeight = height;
        mAttackImage = attackImage;
        mAttackWidth = attackWidth;
        mAttackHeight = attackHeight;
        mAttackSound = attackSound;
        mDamage = damage;
    }

    public Coordinate getAttachPoint() {
        return mAttachPoint;
    }

    public Coordinate getEmitPoint() {
        return mEmitPoint;
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

    public String getAttackImage() {
        return mAttackImage;
    }

    public int getAttackWidth() {
        return mAttackWidth;
    }

    public int getAttackHeight() {
        return mAttackHeight;
    }

    public String getAttackSound() {
        return mAttackSound;
    }

    public int getDamage() {
        return mDamage;
    }
}
