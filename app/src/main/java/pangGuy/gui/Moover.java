package pangGuy.gui;

import pangGuy.gun.*;

import pangGuy.utilities.*;

public class Moover {

    private final Actor actor;
    private final Gun gun;
    private final BoundChecker bc;

    public Moover(Actor actor, Gun gun, BoundChecker bc){
        this.actor = actor;
        this.gun = gun;
        this.bc = bc;
    }

    public void move(Pos2D pos, Directions dir){
        if(bc.isInside(pos, this.actor.getShape().getDimensions().getX(),
        this.actor.getShape().getDimensions().getY())){
            this.actor.changeLocation(pos);
            this.gun.changeLocation(pos);
            this.gun.changeDir(dir);
        }
    }

    public Pos2D getCurrentPos(){
        return this.actor.getShape().getPos();
    }
    
}
