package mergeballs.control;

import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

import ball.controller.BallBoundChecker;
import ball.controller.IntersectionChecker;
import pangGuy.character.Hero;
import pangGuy.character.HitHandler;
import pangGuy.modularGun.GunSet;
import pangGuy.modularGun.Status;
import pangGuy.utilities.StepsApplier;
import ball.controller.Runner;
import bird.controller.BirdHandler;
import pangGuy.character.HeroStatus;

public class EntityHandler extends Thread {
    private final BallBoundChecker checker;
    private final UpdateableVisual frame;
    private final Runner ballRunner;
    private final GunSet gSet;
    private final StepsApplier stepsConv;
    private final Hero hero;
    private final BirdHandler bird;

    
    public EntityHandler(UpdateableVisual frame, Hero hero) {
        this.frame = frame;
        this.checker = new BallBoundChecker(this.frame.getBounds().getX(),
                                            this.frame.getBounds().getY());
        this.ballRunner = new Runner(2, this.checker);
        this.hero = hero;
        this.gSet = hero.getGset();
        this.stepsConv = new StepsApplier(this.frame.getStartPos());
        this.bird = new BirdHandler();
    }

    @Override
    public void run() {
        this.ballRunner.start();
        this.bird.start();
        while(true) {
            try {
                ballRunner.getBalls()
                .forEach(t -> {
                    for (final var arp : frame.getArpions()) {
                        if (arp.getStatus().equals(Status.RISING)) {
                            if (this.checker.checkEnemyCollision(arp.getShape(), t)) {
                                this.gSet.getBulletFromSteps(this.stepsConv.fromPixeltoStep(arp.getShape().getPos().getY())).get().hit();
                                this.ballRunner.duplication(t);
                            } 
                        }
                    }

                    if (this.checker.checkEnemyCollision(this.frame.getHero().getShape(), t)) {
                        this.setHeroStatus();
                    }

                    this.frame.updatePosition(this.ballRunner
                                .getBalls()
                                .stream()
                                .map(s -> s.getBallPosition())
                                .collect(Collectors.toList()), this.hero.getDirection(), this.bird.getShape());
                });

                if (!this.bird.getActor().isEmpty()) {
                    for (final var arp : frame.getArpions()) {
                        if (arp.getStatus().equals(Status.RISING)) {
                            if (IntersectionChecker.checkShapeCollsion(arp.getShape(), this.bird.getShape().get())) {
                                this.gSet.getBulletFromSteps(this.stepsConv.fromPixeltoStep(arp.getShape().getPos().getY())).get().hit();
                                this.bird.setBirdDead();
                            }
                        }
                    }

                    if (IntersectionChecker.checkShapeCollsion(this.bird.getShape().get(), this.frame.getHero().getShape())) {
                        this.setHeroStatus();
                    }
                }
                
                Thread.sleep(10);
            } catch (Exception e) {
                //Catching Thread Exceptions
            }
        }
    }

    private void setHeroStatus() {
        if(this.hero.getStatus() == HeroStatus.NEUTRAL){
            Timer timer = new Timer();
            timer.schedule(new HitHandler(hero), 0);
        }
    }
    
    public synchronized List<Pausable> getPausable() {
        return List.of(this.ballRunner, this.bird);
    }
}
