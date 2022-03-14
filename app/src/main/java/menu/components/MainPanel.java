package menu.components;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.GridLayout;

public class MainPanel extends JPanel{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3036609648280707540L;

	public MainPanel() {
		JPanel MainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints container = new GridBagConstraints();
		MainPanel.setLayout(new GridBagLayout());
		
		container.fill = GridBagConstraints.HORIZONTAL ;
		container.ipady = 40;
		container.gridx = 0;
		container.gridy = 0;
		container.gridwidth = 3;
		MainPanel.add(new TitlePanel(), container);
		
		container.fill = GridBagConstraints.HORIZONTAL ;
		container.gridx = 0;
		container.gridy = 1;
		container.gridwidth = 10;
		MainPanel.add(new SubtitlePanel(), container);
		
		container.fill = GridBagConstraints.HORIZONTAL ;
		container.gridx = 0;
		container.gridy = 3;
		container.gridwidth = 5;
		container.gridheight = 2;
		MainPanel.add(new NavigationPanel(), container);
		
		

		this.setBackground(Color.BLACK);
		MainPanel.setBackground(Color.BLACK);
		this.add(MainPanel);
		
	}
		
		
		
		

}
