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
    private int id;

    /**
     * Constructor.
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
