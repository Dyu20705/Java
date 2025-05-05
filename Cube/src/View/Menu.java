package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.Game;

public class Menu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Game game;
	private int score;
	private JLabel scoreLabel;
	private final JButton pauseBtn;
	private final JButton newGameBtn;
	private final JButton saveBtn;
	private final JButton loadBtn;
	private final JButton achvBtn;
	
	public Menu(Game game) {
		this.game = game;
		
		// Create buttons with icons
		pauseBtn = createIconButton("rsc/img/pause.png", "Resume/Pause");
		newGameBtn = createIconButton("rsc/img/new.png", "New Game");
		saveBtn = createIconButton("rsc/img/save.png", "Save");
		loadBtn = createIconButton("rsc/img/load.png", "Load");
		achvBtn = createIconButton("rsc/img/Achievements.png", "Achievements");
		
		// Add action listeners to buttons
		pauseBtn.addActionListener(e -> game.eventHandler(0));
		newGameBtn.addActionListener(e -> game.eventHandler(1));
		saveBtn.addActionListener(e -> game.eventHandler(2));
		loadBtn.addActionListener(e -> game.eventHandler(3));
		achvBtn.addActionListener(e -> game.eventHandler(4));
		
		// Set up the panel
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		setBackground(new Color(40, 40, 40));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(0, 60));
		
		// Add buttons to the panel
		add(pauseBtn);
		add(newGameBtn);
		add(saveBtn);
		add(loadBtn);
		add(achvBtn);
		
		// Add a horizontal glue to push the score label to the right
		score = game.getEnemy().getMaxHp() - game.getEnemy().getHp();
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		// Add the score label to the panel
		add(scoreLabel);
		add(Box.createHorizontalGlue());
		add(Box.createRigidArea(new Dimension(0, 0)));
		add(Box.createHorizontalStrut(10));
	}
	
	// Method to create a button with an icon and tooltip
	private JButton createIconButton(String iconPath, String tooltip) {
		// Load the icon and scale it
		ImageIcon orig = new ImageIcon(iconPath);
		Image img = orig.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JButton b = new JButton(new ImageIcon(img));
		b.setToolTipText(tooltip);
		b.setPreferredSize(new Dimension(50, 50));
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
		b.setFocusable(false);

		// Set the button to be transparent
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
				b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setBorder(BorderFactory.createEmptyBorder());
			}
		});
		return b;
	}
	
	// Method to update the score label
	public void updateScore() {
		score = game.getEnemy().getMaxHp() - game.getEnemy().getHp() + 50;
		scoreLabel.setText("Score: " + score);
	}
	
	// Method to set the score 
	public void setScore(int score) {
		this.score = score;
		scoreLabel.setText("Score: " + score);
	}
	
	public int getScore() {
		return score;
	}
	
	public Game getGame() {
		return game;
	}

}