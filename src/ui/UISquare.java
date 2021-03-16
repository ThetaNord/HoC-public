package ui;

import game.Board;
import game.GraphicConstants;
import game.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * A class defining the attributes and functionalities of UISquares
 * which are used to visualize single squares of the game board.
 * 
 * @author Tuure Saloheimo
 *
 */
public class UISquare extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The UIBoard object the UISquare is associated with
	 */
	private UIBoard uiBoard;
	/**
	 * The row of the squares location
	 */
	private int row;
	/**
	 * The column of the squares location
	 */
	private int column;

	/**
	 * The background Image of white squares
	 */
	private static Image whiteSquare;
	/**
	 * The background Image of black squares
	 */
	private static Image blackSquare;

	//The colors used to color the squares accordingly when the active piece 
	//Can move to the square
	private static Color transGreen = new Color(0, 255, 0, 150);
	//Can attack the square
	private static Color transRed = new Color(255, 0, 0, 150);
	//Can use it's special ability on the square
	private static Color transBlue = new Color(0, 0, 255, 150);
	//When none of the former are possible
	private static Color transparent = new Color(0,0,0,0);

	static {
		try {
			whiteSquare = 
				ImageIO.read(new File("graphics/squares/whitesquare.png"));
			blackSquare = 
				ImageIO.read(new File("graphics/squares/blacksquare.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A method that creates a new UISquare object accordingly to the given
	 * parametres.
	 * 
	 * @param row the row of the square the object visualizes
	 * @param column the column of the square the object visualizes
	 * @param uiBoard the UIBoard the object is associated with
	 */
	public UISquare(int row, int column, UIBoard uiBoard) {

		this.uiBoard = uiBoard;
		this.row = row;
		this.column = column;

		this.setBackground(UISquare.transparent);

		this.addActionListener(this);

		this.setSize(new Dimension(64,64));
		this.setPreferredSize(this.getSize());

	}

	@Override
	public void paintComponent(Graphics g) {
		//Draws the correct background Image
		if (Math.abs(this.row-this.column)%2 == 0) {
			g.drawImage(UISquare.blackSquare, 0, 0, 64, 64, null, null);
		}
		else {
			g.drawImage(UISquare.whiteSquare, 0, 0, 64, 64, null, null);
		}

		Piece piece = this.uiBoard.getActivePiece();
		//Determines the color to be used for coloring the square
		setColor: {
			g.setColor(UISquare.transparent);
			if (piece != null) {
				if (this.uiBoard.specialPriorized()) {
					if (piece.hasSpecial()) {
						if (piece.canUseSpecialTo(this.row, this.column)) {
							g.setColor(UISquare.transBlue);
							break setColor;
						}
					}
				}
				if (piece.canMoveTo(this.row, this.column)) {
					g.setColor(UISquare.transGreen);
				}
				else if (piece.canAttackTo(this.row, this.column)) {
					g.setColor(UISquare.transRed);
				}
				else if (!this.uiBoard.specialPriorized()) {
					if (piece.hasSpecial()) {
						if (piece.getRow() != this.row || 
								piece.getColumn() != this.column) {
							if (piece.canUseSpecialTo(this.row, this.column)) {
								g.setColor(UISquare.transBlue);
							}
						}
					}
				}
			}
		}
		//Colors the square with the determined Color
		g.fillRect(0, 0, 64, 64);
		super.paintComponent(g);
		piece = this.uiBoard.getBoard().getPiece(this.row, this.column); 
		if (piece != null) {
			/*Draws the Image of the Piece located in the square this UISquare
			 *visualizes*/
			g.drawImage(
				GraphicConstants.getImage(piece), 0, 0, 64, 64, null, null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Piece piece = this.uiBoard.getActivePiece();
		//Checks whether a piece is active and could theoretically take action
		if (piece != null && piece.getTeam() == 
				this.uiBoard.getBoard().whoseTurn() && 
				this.uiBoard.getBoard().isPlayable()) {
			boolean acted = false;
			if (this.uiBoard.specialPriorized()) {
				if (piece.canUseSpecialTo(this.row, this.column)) {
					if (piece.useSpecialTo(this.row, this.column)) {
						acted = true;
					}
				}
			}
			if (piece.canMoveTo(this.row, this.column ) && !acted) {
				this.uiBoard.getBoard().inform(piece.getName() + 
					" moved from " + Board.COLUMNS[piece.getColumn()] + 
					(piece.getRow()+1) + " to " + Board.COLUMNS[this.column] + 
					(this.row+1) + ".\n");
				this.uiBoard.getBoard().addMove(piece.toString() + 
					piece.getColumn() + piece.getRow() + "-" + this.column + 
					this.row);
				piece.moveTo(this.row, this.column);
				acted = true;
			}
			else if (piece.canAttackTo(this.row, this.column) && !acted) {
				this.uiBoard.getBoard().inform(piece.getName() + " from " + 
					Board.COLUMNS[piece.getColumn()] + (piece.getRow()+1) + 
					" attacked " + this.uiBoard.getBoard().
					getPiece(this.row, this.column).getName() + " in " + 
					Board.COLUMNS[this.column] + (this.row+1) + ".\n");
				this.uiBoard.getBoard().addMove(piece.toString() + 
					piece.getColumn() + piece.getRow() + "x" + this.column + 
					this.row);
				piece.attackTo(this.row, this.column);
				acted = true;
			}
			else if (piece.canUseSpecialTo(this.row, this.column) && !acted) {
				if (piece.getRow() != this.row || 
						piece.getColumn() != this.column) {
					if (piece.useSpecialTo(this.row, this.column)) {
						acted = true;
					}
				}
			}
			this.uiBoard.setActivePiece(null);
			if (acted) {
				this.uiBoard.getBoard().endTurn();
				return;
			}
		}
		/*If no action was taken and the piece in this square is not the active
		 *piece...*/
		if (piece != this.uiBoard.getBoard().getPiece(this.row, this.column)) {
			//...makes the piece in this square the active piece.
			this.uiBoard.setActivePiece(
				this.uiBoard.getBoard().getPiece(this.row, this.column));
		}
		//If not...
		else {
			//...makes active piece null.
			this.uiBoard.setActivePiece(null);
		}
	}

}
