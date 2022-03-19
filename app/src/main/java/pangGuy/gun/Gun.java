package pangGuy.gun;

import pangGuy.gui.Shape;
import pangGuy.utilities.Pos2D;

import java.awt.Color;

import javax.swing.JPanel;

import pangGuy.gui.Actor;

public class Gun extends JPanel{

    private final Actor a;
    private Shape s;
    private boolean isMovable = true;

    public Gun(Actor actor){
        this.a = actor;
        this.s = new Shape(this.a.getShape().getPos(), 10, 2);

        super.setBounds(this.s.getRectangle());
        super.setBackground(Color.green);
        super.setForeground(Color.green);
        super.setOpaque(true);
        
    }

    public void changeLocation(Pos2D pos){
        if(this.isMovable){
            super.setLocation(pos.x, pos.y);
            this.s = new Shape(pos, s.getDimensions().getX(), s.getDimensions().getY());
        }
    }

    public void raise(){
        this.isMovable = false;
        while(this.s.getPos().y > 0 ){
            Pos2D newPos = new Pos2D(this.s.getPos().x , this.s.getPos().y -1);
            this.s = new Shape(newPos, this.s.getDimensions().getX(), this.s.getDimensions().getY() + 1);
            super.setBounds(this.s.getRectangle());
            System.out.println(this.s.getPos().toString());
        }
        

    }



}
