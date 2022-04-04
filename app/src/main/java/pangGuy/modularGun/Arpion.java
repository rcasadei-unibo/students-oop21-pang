package pangGuy.modularGun;

import pangGuy.utilities.Directions;

public class Arpion implements Bullet {

    /*
    private static final int WIDTH = 10;
    private static final int HEIGHT = 2;
    private static final int SPEED = 4;
    */
    
    private Status status;
    private Directions direction;

    public Arpion(){
        this.status = Status.IDLE;
        this.direction = Directions.LEFT;
    }

    @Override
    public void restore() {
        this.status = Status.IDLE;
    }

    @Override
    public void raise() {
        this.status = Status.RISING;
    }
    
    @Override
    public void changeDir(Directions dir){
        this.direction = dir;
    }
    
    @Override
    public Directions getDirection(){
        return this.direction;
    }

    @Override 
    public Status getStatus(){
        return this.status;
    }

    @Override 
    public String toString(){
        return "Status:" + this.status + "Direction: " + this.direction;
    }


}
