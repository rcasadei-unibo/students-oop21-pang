package pangGuy.gui;

import pangGuy.utilities.Directions;
import pangGuy.utilities.Pos2D;
import pangGuy.modularGun.Status;

import java.awt.Color;

import javax.swing.JPanel;

public class ArpionComponent extends JPanel{
    
    private static final int WIDTH = 10;
    private static final int HEIGHT = 2;

    private Shape s;
    private Directions direction;
    private Status status;

    public ArpionComponent(Color color, Pos2D startpos){
        this.s = new Shape(startpos, WIDTH, HEIGHT);
        this.direction = Directions.LEFT;
        this.status = Status.IDLE;

        super.setBackground(color);
        super.setBounds(this.s.getRectangle());
        super.setOpaque(true);

    }

    public void changeLocation(Pos2D pos){
        super.setLocation(pos.x, pos.y);
        this.s = new Shape(pos, WIDTH, HEIGHT);
    }

    public Shape getShape(){
        return this.s;
    }


}
