package mergeballs.control;

import java.util.stream.Collectors;

import ball.controller.ConstraintCheck;
import mergeballs.gui.VisualTest;
import pangGuy.character.Hero;
import pangGuy.modularGun.GunSet;
import pangGuy.modularGun.Status;
import pangGuy.utilities.StepsApplier;
import ball.controller.Runner;


public class EntityHandler extends Thread {
    private final ConstraintCheck checker;
    private final UpdateableVisual frame;
    private final Runner ballRunner;
    private final GunSet gSet;
    private final StepsApplier stepsConv;
    private final Hero hero;
    
    public EntityHandler(VisualTest frame, GunSet gSet, Hero hero) {
        this.frame = frame;
        this.checker = new ConstraintCheck(this.frame.getBounds().getX(),
                                            this.frame.getBounds().getY());
        this.ballRunner = new Runner(5, this.checker);
        this.hero = hero;
        this.gSet = gSet;
        this.stepsConv = new StepsApplier(this.frame.getStartPos());
    }

    @Override
    public void run() {
        this.ballRunner.start();
        while(true) {
            try {
                ballRunner.getBalls()
                .forEach(t -> {
                    for (final var arp : frame.getArpions()) {
                        if (arp.getStatus().equals(Status.RISING)) {
                            if (this.checker.checkEnemyCollision(arp.getShape(), t)) {
                                this.gSet.getBulletFromSteps(this.stepsConv.fromPixeltoStep(arp.getShape().getPos().y)).get().hit();
                                this.ballRunner.duplication(t);
                            } 
                        }
                    }

                    if (this.checker.checkEnemyCollision(this.frame.getHero().getShape(), t)) {
                        System.out.println("HERO HIT!!! YOU LOSE!!!!!!!!");
                    }

                    this.frame.updatePosition(this.ballRunner
                                .getBalls()
                                .stream()
                                .map(s -> s.getBallPosition())
                                .collect(Collectors.toList()), this.hero.getDirection());
                });
                
                Thread.sleep(10);
            } catch (Exception e) {
                //Catching Thread Exceptions
            }
        }
    }
}
