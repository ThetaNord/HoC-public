package ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A class the instances of which are used to display a log of the game events.
 * 
 * @author Tuure Saloheimo
 *
 */

public class GameLog extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The test area where the game events are displayed.
	 */
	private JTextArea textArea;
	/**
	 * The text to be displayed in the textArea.
	 */
	private String text;
	
	/**
	 * A method that creates a new GameLog
	 */
	public GameLog() {
		this.setSize(400, 75);
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
		this.text = "";
		this.textArea = new JTextArea(this.text);
		this.textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(this.textArea);
		scrollPane.setPreferredSize(new Dimension(450, 65));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
		this.setVisible(true);
	}
	
	/**
	 * A method used to add events to the game log.
	 * @param text the event to be added
	 */
	public void addText(String text) {
		this.text += text;
		this.textArea.setText(this.text);
		this.repaint();
	}
	
	/**
	 * A method used for clearing the log at the beginning of a new game.
	 */
	public void clear() {
		this.text = ""/*"Player 1 moves first\n"*/;
		this.textArea.setText(this.text);
		this.repaint();
	}
	
}
