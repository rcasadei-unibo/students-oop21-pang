package stage.utils;

import javax.swing.JLabel;

import player.Player;
import stage.components.HUDPanel;
import java.awt.Font;
import java.awt.Color;

public class P1ScoreSetter extends Thread {

    private Player player;
    private HUDPanel hud;
    private int actualScore = 0;
    private MainFontLoader fontLoader = new MainFontLoader();
	private Font gameFont = fontLoader.load();
    private JLabel scoreLabel = new JLabel();
    private JLabel playerNameLabel = new JLabel();


    public P1ScoreSetter(Player player, HUDPanel hud) {
        this.player = player;
        this.hud = hud;
        this.scoreLabel.setForeground(Color.WHITE);
        this.scoreLabel.setFont(gameFont);
        this.playerNameLabel.setForeground(Color.WHITE);
        this.playerNameLabel.setFont(gameFont);
        this.playerNameLabel.setText(player.getNickname());
    }
    
    @Override
    public void run() {
        this.hud.getHUDPanel()[0][0].add(playerNameLabel);
        while (true) {
            try {
                if (this.actualScore != this.player.getScore().getScore()) {
                    this.actualScore = this.player.getScore().getScore();
                    this.scoreLabel.setText("" + this.actualScore);
                    this.scoreLabel.setForeground(Color.green);
                    this.hud.getHUDPanel()[1][1].removeAll();
                    this.hud.getHUDPanel()[1][1].add(scoreLabel);
                    this.hud.validate();
                    this.hud.repaint();
                    Thread.sleep(150);
                }else{
                    this.scoreLabel.setForeground(Color.WHITE);
                    this.scoreLabel.setText("" + this.actualScore);
                    this.hud.getHUDPanel()[1][1].removeAll();
                    this.hud.getHUDPanel()[1][1].add(scoreLabel);
                    this.hud.validate();
                    this.hud.repaint();
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
