package ball.gui;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.List;

import ball.physics.Pos2D;
import ball.physics.Dimensions;
import ball.physics.Entity;
import ball.testing.Enemy;

public class Visual extends JFrame {
	private static final long serialVersionUID = 1L;

	private VisualPanel panel;
	private ImageLoader iLoader;
	
	private static final int WINDOW_SIZE_X = 800;
	private static final int WINDOW_SIZE_Y = 600;

	
	public Visual() {
		setTitle("Bouncing Balls");
		this.iLoader = new ImageLoader();
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(false);
		panel = new VisualPanel();
		this.add(panel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void updatePosition(List<Pos2D> pos) {
		panel.updatePositions(pos);
	}
    
    public class VisualPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private List<Pos2D> positions;

		private Enemy enemy;
	    
		public VisualPanel() {
			setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
			this.enemy = new Enemy(WINDOW_SIZE_X / 2, WINDOW_SIZE_Y / 2, 60);
		}

	    public void paint(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        //g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

	        g2.clearRect(0, 0, WINDOW_SIZE_X, WINDOW_SIZE_Y);

	        synchronized (this) {
	            if (positions != null) {
	                for(final var position : positions) {
	                    int x = (int)( position.x * WINDOW_SIZE_X );
	                    int y = (int)( position.y * WINDOW_SIZE_Y - 80 );
						g2.drawImage(iLoader.getBallImage(position.getDimension()), x, y, this);
	                }
	            }
	        }
			g2.fillOval((int)this.enemy.getPosition().x, (int)this.enemy.getPosition().y
						, this.enemy.getSize(), this.enemy.getSize());
			g2.dispose();
			Toolkit.getDefaultToolkit().sync();
	    }

	    public void updatePositions(List<Pos2D> pos) {
	        positions = pos;
	        repaint();
	    }

		public Entity getGuy() {
			return this.enemy;
		}
    }

	public int getBallImageDiameter(Dimensions dim) {
		return this.iLoader.getBallImage(dim).getHeight();
	}

    public Entity getGuy() {
        return panel.getGuy();
    }
}

