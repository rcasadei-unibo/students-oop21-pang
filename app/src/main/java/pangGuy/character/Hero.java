package pangGuy.character;

import java.util.Timer;
import java.util.TimerTask;

import mergeballs.utilities.EntityPos2D;
import pangGuy.modularGun.GunSet;

import pangGuy.utilities.Directions;

/**
 * Models the model of the main character.
 */

public class Hero {
    
    private static final int HIT_WAIT = 1;

    private EntityPos2D pos;
    private Directions direction;
    private GunSet gSet;
    private heroStatus status;
    private int lives;

    /**
     * Creates the main character giving default values.
     */
    public Hero(){
        this.pos = new EntityPos2D(0, 0);
        this.direction = Directions.LEFT;
        this.gSet = new GunSet();
        this.status = heroStatus.NEUTRAL;
        this.lives = 3;
    }

    /**
     * Updates the hero direction.
     * @param dir the given direction.
     */
    private void updateDirection(Directions dir){
        this.direction = dir;
        this.gSet.setDirections(dir);
    }

    /**
     * Getter for the actual Direction.
     * @return the actual direction.
     */
    public Directions getDirection(){
        return this.direction;
    }

    /**
     * Getter for the actual absolute position.
     * @return the absolute position.
     */
    public EntityPos2D getPosition(){
        return this.pos;
    }

    /**
     * Giving a Directions, it change the absolute coords making a step.
     * @param dir the direction where it has to step.
     */
    public void move(Directions dir){
        switch (dir) {
            case RIGHT:
                this.pos = new EntityPos2D(pos.x + 1, pos.y);
                break;
            case LEFT:
                this.pos = new EntityPos2D(pos.x - 1, pos.y);
                break;
        }
        this.updateDirection(dir);
    }
    
    /**
     * Getter for the GunSet.
     * @return the GunSet in use.
     */
    public GunSet getGset(){
        return this.gSet;
    }

    /**
     * The method is called whenever a ball hits the main character, it cut off one life.
     */
    public void hit(){
        if(this.status == heroStatus.NEUTRAL){
            this.status = heroStatus.HIT;
            System.out.println(this.status);
            Timer timer = new Timer();
            TimerTask hitCond = new hitCondition(this.status);
            timer.schedule(hitCond, HIT_WAIT * 1000);
            this.lives--;
            System.out.println(this.lives);
        }
    }

}
