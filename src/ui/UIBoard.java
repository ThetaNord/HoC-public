package ui;

import game.Board;
import game.Piece;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * A class udes to visualize the Board object and the current game situation
 * 
 * @author Tuure Saloheimo
 *
 */
public class UIBoard extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * A List of UISquare objects used to visualize the Board
	 */
	private List<UISquare> squares;
	/**
	 * The Board this UIBoard displays
	 */
	private Board board;
	/**
	 * The currently active Piece, i.e. the one the move possibilities of
	 * which are to be displayed.
	 */
	private Piece activePiece;
	
	/**
	 * The InfoPanel displaying the infromation of the active Piece.
	 */
	private InfoPanel infoPanel;
	
	/**
	 * The boolean value which tells whether to priorize special ability
	 * targets visibility over other options or not
	 */
	private boolean priorizeSpecial;
	
	/**
	 * Creates a new UIBoard object
	 */
	public UIBoard() {
		
		this.board = new Board();
		this.activePiece = null;
		
		this.infoPanel = new InfoPanel(this);
		
		this.priorizeSpecial = false;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.squares = new LinkedList<UISquare>();
		UISquare square;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				square = new UISquare(j,i,this);
				this.squares.add(square);
				gbc.gridx = i;
				gbc.gridy = 7-j;
				this.add(square, gbc);
			}
		}
		
	}
	
	/**
	 * A method that returns the Board object this UIBoard visualizes
	 * @return the Board object this UIBoard visualizes
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * A method that returns the InfoPanel object this UIBoard uses to display
	 * info on the active Piece.
	 * @return the InfoPanel associated with this UIBoard
	 */
	InfoPanel getInfoPanel() {
		return this.infoPanel;
	}
	
	/**
	 * A method that returns the currently active Piece.
	 * @return the currently active Piece
	 */
	Piece getActivePiece() {
		return this.activePiece;
	}
	
	/**
	 * A method that tells whether to priorize special ability targets' 
	 * visibility over other options or not
	 * @return true if the visibility should be priorized, false otherwise
	 */
	boolean specialPriorized() {
		return this.priorizeSpecial;
	}
	
	/**
	 * A method used to set the currently active Piece.
	 * @param piece the Piece object to be made the active piece.
	 */
	void setActivePiece(Piece piece) {
		this.activePiece = piece;
		this.priorizeSpecial = false;
		this.infoPanel.updateInfo();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("special")) {
			this.priorizeSpecial = !this.priorizeSpecial;
			this.repaint();
		}	
	}

}
