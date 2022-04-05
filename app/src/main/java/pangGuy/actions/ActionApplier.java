package pangGuy.actions;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import pangGuy.gui.BoundChecker;

import pangGuy.character.Hero;
import pangGuy.gui.Visual;

import pangGuy.utilities.Pair;

public class ActionApplier{

    private final ActionFactory f = new ActionFactory();

    public ActionApplier(Visual v, Hero h){

        BoundChecker bc = new BoundChecker(new Pair<Integer, Integer>(0, v.getBounds().getX()), new Pair<Integer, Integer>(0, v.getBounds().getY()));
        JPanel character = v.getHeroComponent();


        character.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        character.getActionMap().put("rightAction", f.getRightAction(v, h, bc));
        
        /*
        character.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        character.getActionMap().put("leftAction", f.getLeftAction(a, g, bc));
        */
        /*
        character.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shootAction");
        character.getActionMap().put("shootAction", f.getShootAction(g));
        */
    }


}

       