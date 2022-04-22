package stage.utils;

import pangGuy.character.Hero;
import stage.components.HUDPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LifeSetter extends Thread{
    
    private final Hero hero;
    private final HUDPanel hud;
    private final Font font = new MainFontLoader().load();
    private final JLabel label = new JLabel();
    private int actualLifes;

    public LifeSetter(HUDPanel hud, Hero hero){
        this.hud = hud;
        this.hero = hero;
        this.actualLifes = hero.getLifes();
    }

    @Override
    public void run(){
        label.setFont(this.font);
        while (true) {
            try {
                if (this.hero.getLifes() == this.actualLifes){
                    label.setForeground(Color.white);
                    this.label.setText("LIFES: " + this.hero.getLifes());
                    this.hud.getHUDPanel()[3][3].removeAll();
                    this.hud.getHUDPanel()[3][3].add(label);
                    this.hud.validate();
                    this.hud.repaint();
                }else {
                    this.label.setText("LIFES: " + this.hero.getLifes());
                    this.label.setForeground(Color.red);
                    this.hud.getHUDPanel()[3][3].removeAll();
                    this.hud.getHUDPanel()[3][3].add(label);
                    this.hud.validate();
                    this.hud.repaint();
                    Thread.sleep(150);
                    this.actualLifes = this.hero.getLifes();
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep() Exception: " + e.getMessage());
            }
        }
    }
}


/*

                Thread.sleep(500);


 */
