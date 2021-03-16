package ui;

import game.Faction;
import game.LoadException;
import game.SaveException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The class that initializes the game and the window.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Game extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * The UIBoard object handling most of the actual game.
	 */
	private UIBoard uiBoard;

	/**
	 * Creates a new Game-object.
	 */

	public Game() {

		this.setTitle("Heroes of Chess");
		this.setLayout(new BorderLayout());

		this.uiBoard = new UIBoard();
		this.add(this.uiBoard, BorderLayout.CENTER);

		JPanel downpanel = new JPanel();
		downpanel.setLayout(new BorderLayout());

		JPanel controlpanel = new JPanel();
		controlpanel.setLayout(new GridLayout(2,2));
		controlpanel.setSize(100,75);
		JButton newGame = new JButton("New Game");
		newGame.setActionCommand("newgame");
		newGame.addActionListener(this);
		controlpanel.add(newGame);
		JButton saveGame = new JButton("Save Game");
		saveGame.setActionCommand("save");
		saveGame.addActionListener(this);
		controlpanel.add(saveGame);
		JButton loadGame = new JButton("Load Game");
		loadGame.setActionCommand("load");
		loadGame.addActionListener(this);
		controlpanel.add(loadGame);
		JButton quitGame = new JButton("Quit");
		quitGame.setActionCommand("quit");
		quitGame.addActionListener(this);
		controlpanel.add(quitGame);
		downpanel.add(controlpanel, BorderLayout.EAST);

		downpanel.add(this.uiBoard.getBoard().getGameLog(), 
				BorderLayout.CENTER);
		this.add(downpanel, BorderLayout.SOUTH);

		JPanel sidepanel = new JPanel();
		sidepanel.setLayout(new BorderLayout());
		sidepanel.add(this.uiBoard.getInfoPanel(), BorderLayout.NORTH);
		this.add(sidepanel, BorderLayout.EAST);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("newgame")) {
			this.newGame();
		}
		if (ae.getActionCommand().equals("save")) {
			try {
				this.uiBoard.getBoard().saveGame();
			}
			catch (SaveException se) {
				JOptionPane.showMessageDialog(this, se.getMessage(), "Error", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (ae.getActionCommand().equals("load")) {
			try {
				this.uiBoard.getBoard().loadGame();
				this.uiBoard.setActivePiece(null);
			} catch (LoadException le) {
				JOptionPane.showMessageDialog(this, le.getMessage(), "Error", 
						JOptionPane.ERROR_MESSAGE);
			}
			finally {
				this.repaint();
			}
		}
		if (ae.getActionCommand().equals("quit")) {
			System.exit(0);
		}
	}

	/**
	 * A method that asks the required information from the Players to
	 * start a new game.
	 */
	private void newGame() {
		//Initializes the list of Factions available.
		List<Faction> abstractFactions = Arrays.asList(Faction.values());
		ArrayList<Faction> factions = new ArrayList<Faction>();
		for (int i = 0; i < abstractFactions.size(); i++) {
			factions.add(abstractFactions.get(i));
		}
		//Puts the Faction names into a String array
		String[] factionNames = new String[factions.size()];
		for (int i = 0; i < factions.size(); i++) {
			factionNames[i] = factions.get(i).toString();
		}
		//Initializes the Players' Factions
		Faction faction1 = null;
		Faction faction2 = null;
		String factionName1 = null;

		/*Asks for Player 1 to choose a Faction, if none is chosen
		 *a new game will not be begun.*/
		factionName1 = (String)JOptionPane.showInputDialog(null, 
				"Choose a faction for Player 1.", "New Game", 
				JOptionPane.PLAIN_MESSAGE, null, factionNames, factionNames[0]);
		if (factionName1 == null) return;
		//Removes the faction Player 1 chose from the list
		for (int i = 0; i < factions.size(); i++) {
			if (factions.get(i).toString().equals(factionName1)) {
				faction1 = factions.get(i);
				factions.remove(i);
			}
		}
		//If the chosen faction was NONE, also Player 2's Faction is NONE
		if (faction1 == Faction.NONE) {
			faction2 = Faction.NONE;
		}
		/*Otherwise removes NONE from the list as it is not allowed to
		 *be used against other factions than NONE.*/
		else {
			factions.remove(Faction.NONE);
		}
		if (faction2 == null) {
			//Updates the Faction names.
			factionNames = new String[factions.size()];
			for (int i = 0; i < factions.size(); i++) {
				factionNames[i] = factions.get(i).toString();
			}
			String factionName2 = null;
			//Asks for Player 2 to choose a Faction
			factionName2 = (String)JOptionPane.showInputDialog(null, 
					"Choose a faction for Player 2.", "New Game", 
					JOptionPane.PLAIN_MESSAGE, null, factionNames, 
					factionNames[0]);
			if (factionName2 == null) return;
			//Sets Player 2's Faction
			for (int i = 0; i < factions.size(); i++) {
				if (factions.get(i).toString().equals(factionName2)) {
					faction2 = factions.get(i);
				}
			}
		}
		//Starts a new game with the chosen factions
		this.uiBoard.getBoard().newGame(faction1, faction2);
		this.uiBoard.setActivePiece(null);
		this.repaint();

	}

	public static void main(String[] args) {
		new Game();
	}

}
