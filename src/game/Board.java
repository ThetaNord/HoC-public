
package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import ui.GameLog;

/**
 * A class that defines the properties and functionalities of the game board.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Board {

	/**
	 * An array of Piece-objects depicting the board and the pieces on it
	 */
	private Piece[][] board;

	/**
	 * A list of pieces that have been defeated during the course of the game
	 */
	private List<Piece> deadPieces;
	/**
	 * The King of Player 1
	 */
	private King kingOne;
	/**
	 * The King of Player 2
	 */
	private King kingTwo;

	/**
	 * A boolean value, that defines, which palyer's turn it is to move.
	 */
	private boolean whoseTurn;
	/**
	 * A boolean value that tells whether the board is currently playable.
	 */
	private boolean playable;

	/**
	 * A GameLog-object that is used to record the moves and other events
	 * taking place on the board.
	 */
	private GameLog gameLog;
	/**
	 * A String containing the information necessary to save the game.
	 * Namely, the Factions of the game and the moves made.
	 */
	private String currentGame;

	/**
	 * A static array used to turn column numbers into letters accordingly
	 * to a standard chess board
	 */
	public static final String[] COLUMNS = 
		{"A", "B", "C", "D", "E", "F", "G", "H"};

	/**
	 * Creates a new Board-object
	 */
	public Board() {
		this.board = new Piece[8][8];
		this.whoseTurn = true;
		this.deadPieces = new ArrayList<Piece>();
		this.playable = true;
		this.whoseTurn = true;
		this.gameLog = new GameLog();
		this.currentGame = null;
	}

	/**
	 * A method that initializes the board and readies it for a new game.
	 * The given parametres define the Factions taking part in the game.
	 * 
	 * @param faction1 The Faction of Player 1
	 * @param faction2 The Faction of Player 2
	 */

	public void newGame(Faction faction1, Faction faction2) {
		this.board = new Piece[8][8];
		this.deadPieces = new ArrayList<Piece>();
		this.playable = true;
		this.whoseTurn = true;
		this.gameLog.clear();
		if (faction1 != Faction.NONE) {
			this.gameLog.addText(faction1 + " moves first.\n");
		}
		else this.gameLog.addText("White moves first.\n");
		//Adds the Faction names to the beginning of the currentGame String.
		this.currentGame = faction1.toString() + "\n" + faction2.toString() + 
				"\n";
		for (int i = 0; i < 8; i++) {
			this.setPiece(faction1.getPawn(1, i, true, this), 1, i);
			this.setPiece(faction2.getPawn(6, i, false, this), 6, i);
		}
		//Initializes Player 1's team
		this.kingOne = faction1.getKing(0, 4, true, this); 
		this.setPiece(this.kingOne, 0, 4);
		this.setPiece(faction1.getQueen(0, 3, true, this), 0, 3);
		this.setPiece(faction1.getRook(0, 0, true, this), 0, 0);
		this.setPiece(faction1.getRook(0, 7, true, this), 0, 7);
		this.setPiece(faction1.getKnight(0, 1, true, this), 0, 1);
		this.setPiece(faction1.getKnight(0, 6, true, this), 0, 6);
		this.setPiece(faction1.getBishop(0, 2, true, this), 0, 2);
		this.setPiece(faction1.getBishop(0, 5, true, this), 0, 5);
		//Initializes Player 2's team
		this.kingTwo = faction2.getKing(7, 4, false, this); 
		this.setPiece(this.kingTwo, 7, 4);
		this.setPiece(faction2.getQueen(7, 3, false, this), 7, 3);
		this.setPiece(faction2.getRook(7, 0, false, this), 7, 0);
		this.setPiece(faction2.getRook(7, 7, false, this), 7, 7);
		this.setPiece(faction2.getKnight(7, 1, false, this), 7, 1);
		this.setPiece(faction2.getKnight(7, 6, false, this), 7, 6);
		this.setPiece(faction2.getBishop(7, 2, false, this), 7, 2);
		this.setPiece(faction2.getBishop(7, 5, false, this), 7, 5);
	}

	/**
	 * Returns the GameLog-object assosiated with the Board object
	 * 
	 * @return the GameLog-object assosiated with the Board-object
	 */

	public GameLog getGameLog() {
		return this.gameLog;
	}

	/**
	 * A method that ends the current turn.
	 * If the game is over, ends the game and makes the board not playable.
	 */

	public void endTurn() {
		if (!this.gameOver()) {
			this.whoseTurn = !this.whoseTurn;
		}
		else {
			this.playable = false;
			if (!this.getWinner().equals("<None>")) {
				this.inform(this.getWinner() + " is victorious!");
			}
			else {
				this.inform((this.whoseTurn ? "White" : "Black") + 
					" is victorious!");
			}
		}
	}

	/**
	 * A method that checks whether the game is over.
	 * The game is over when the King of either player is defeated. 
	 * 
	 * @return true if the game is over, false if it isn't.
	 */

	public boolean gameOver() {
		if (kingOne.getHitPoints() < 1 || kingTwo.getHitPoints() < 1) {
			return true;
		}
		return false;
	}

	/**
	 * A method that returns the name of the victorious faction.
	 * 
	 * @return the name of the victorious faction, or null if the game is not 
	 * over yet.
	 */

	public String getWinner() {
		if (this.gameOver()) {
			if (kingOne.getHitPoints() > 0) {
				return this.kingOne.getFaction().toString();
			}
			else {
				return this.kingTwo.getFaction().toString();
			}
		}
		return null;
	}

	/**
	 * A method that tells whether the board is playable or not.
	 * 
	 * @return true if the board is playable, false if it isn't
	 */
	public boolean isPlayable() {
		return this.playable;
	}

	/**
	 * A method that tells whose turn it is.
	 * 
	 * @return true if it's Player 1's turn, false if Player 2's.
	 */
	public boolean whoseTurn() {
		return this.whoseTurn;
	}

	/**
	 * Returns the piece located in the square defined by the coordinates
	 * given as parametres.
	 * 
	 * @param row The row of the square
	 * @param column The column of the square
	 * @return the Piece-object in the defined square, or null if the Piece or 
	 * the square doesn't exist.
	 */
	public Piece getPiece(int row, int column) {
		if (row >= 0 && column >= 0 && row < 8 && column < 8) {
			return this.board[row][column];
		}
		else return null;
	}

	/**
	 * Puts the given Piece-object into the square defined by the given 
	 * coordinates if the Piece is associated with this Board. If there was a 
	 * Piece in the square before, it will be overwritten.
	 * 
	 * @param piece the Piece-object to be placed on the Board
	 * @param row the row of the target square
	 * @param column the column of the target square
	 */
	void setPiece(Piece piece, int row, int column) {
		if (row >= 0 && row < 8 && column >= 0 && column < 8) {
			if (piece != null) {
				if (piece.getBoard() == this) {
					this.board[row][column] = piece;
				}
			}
			else {
				this.board[row][column] = null;
			}
		}
	}

	/**
	 * A method that moves the given Piece-object to the defined square in the
	 * board, if the PIece is associated with the Board. If the new coordinates
	 * fall outside of the board, only removes the piece from it's current
	 * location.
	 * 
	 * @param piece the Piece-object to be moved into the new location
	 * @param row the row of the target square
	 * @param column the column of the target square
	 */

	void movePiece(Piece piece, int row, int column) {
		if (piece != null && piece.getBoard() == this) {
			this.setPiece(null, piece.getRow(), piece.getColumn());
			if (row >= 0 && row < 8 && column >= 0 && column < 8) {
				this.board[row][column] = piece;
			}
		}
	}

	/**
	 * Returns an Iterator over the deadPieces-list of the Board.
	 * 
	 * @return an Iterator over the deadPieces-list
	 */

	public Iterator<Piece> getDeadPieceIterator() {
		return this.deadPieces.iterator();
	}

	/**
	 * Adds the given Piece-object to the deadPieces-list.
	 * 
	 * @param piece the Piece-object to be added to the deadPieces-list.
	 */

	void addDeadPiece(Piece piece) {
		if (!this.deadPieces.contains(piece)) {
			this.deadPieces.add(piece);
		}
	}

	/**
	 * Removes the given Piece-object from the deadPieces-list if the given 
	 * Piece is on the list.
	 * 
	 * @param piece the Piece-object to be removed from the list
	 * @return true, if the piece was removed, false if it wasn't.
	 */
	boolean removeDeadPiece(Piece piece) {
		if (this.deadPieces.contains(piece)) {
			return this.deadPieces.remove(piece);
		}
		return false;
	}

	/**
	 * A method that checks whether the given Piece-object is on the 
	 * deadPieces-list or not.
	 * 
	 * @param piece the Piece-object the presence of which to be checked
	 * @return true if the Piece is present in the list, false if it isn't 
	 */
	boolean containsDeadPiece(Piece piece) {
		return this.deadPieces.contains(piece);
	}

	/**
	 * A method that checks whether there are Piece-objects of the given
	 * team present on the deadPieces-list.
	 * 
	 * @param team the team the pieces of which to be searched for
	 * @return true if a piece belonging to the given team is found,
	 * false if none is found.
	 */

	boolean containsDeadPiece(boolean team) {
		Iterator<Piece> dpIter = this.getDeadPieceIterator();
		while (dpIter.hasNext()) {
			if (dpIter.next().getTeam() == team) {
				return true;
			}
		}
		return false;
	}

	/**
	 * A method that checks whether the deadPieces-list contains officer 
	 * (i.e. non-pawn) pieces of the given team.
	 * 
	 * @param team the team, the officers of which are to be searched
	 * @return true, if an officer was found, false if none was.
	 */

	boolean containsDeadOfficer(boolean team) {
		Iterator<Piece> dpIter = this.getDeadPieceIterator();
		Piece piece = null;
		while (dpIter.hasNext()) {
			piece = dpIter.next();
			if (piece.getTeam() == team && !(piece instanceof Pawn)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * A method that compiles a list of pieces that can be resurrected by
	 * the given team and then sends forward a request for the player to choose
	 * one from the list.
	 * 
	 * @param team the team performing the resurrection
	 * @return the Piece.object to be resurrected, or null if none was chosen.
	 */

	Piece resurrectPiece(boolean team) {
		List<Piece> resurrectable = new ArrayList<Piece>();
		Iterator<Piece> resurrectIterator = this.deadPieces.iterator();
		Piece piece = null;
		while(resurrectIterator.hasNext()) {
			piece = resurrectIterator.next();
			if (piece.getTeam() == team) {
				resurrectable.add(piece);
			}
		}
		return this.askPiece(resurrectable, 
				"Choose a piece to be resurrected.");
	}

	/**
	 * A method that compiles a list of Pieces that can be reanimated by the
	 * given team and then sends forward a request for the player to choose
	 * s piece to be reanimated.
	 * 
	 * @param team the team performing the reanimation
	 * @param faction the faction of the Piece performing the reanimation.
	 * @return the Piece chosen to be used, or null if none was chosen.
	 */

	Piece reanimatePiece(boolean team, Faction faction) {
		List<Piece> reanimateable = new ArrayList<Piece>();
		Iterator<Piece> reanimateIterator = this.deadPieces.iterator();
		Piece piece = null;
		while(reanimateIterator.hasNext()) {
			piece = reanimateIterator.next();
			if (piece.getTeam() != team) {
				reanimateable.add(piece);
			}
		}
		return this.askPiece(reanimateable, "Choose a piece to reanimate as " +
				faction.getPawn(0, 0, team, this).getName() + ".");
	}

	/**
	 * A method that compiles a list of Pieces that can be used to enhance
	 * a Pawn located in the given coordinates and then sends forward a request
	 * for the player to choose one amongst them. It then replaces the Pawn
	 * with it's Faction's equivalent of the chosen piece. Then it returns
	 * the name of the replacing piece.
	 * 
	 * @param row the row of the Pawn's location
	 * @param column the column of the Pawn's location
	 * @return the name of the Piece that replaced the Pawn, or null if the 
	 * replacement was not succesfull.
	 */

	String enhancePawn(int row, int column) {
		Piece pawn = this.getPiece(row, column);
		if (pawn instanceof Pawn) {
			Piece piece = null;
			List<Piece> pieces = new ArrayList<Piece>();
			Iterator<Piece> dpIter = this.getDeadPieceIterator();
			while (dpIter.hasNext()) {
				Piece next = dpIter.next();
				if (next.getTeam() != pawn.getTeam() && 
						!(next instanceof Pawn)) {
					pieces.add(next);
				}
			}
			piece = this.askPiece(pieces, 
					"Choose a piece to be consumed in the process.");
			//If a Piece was chosen...
			if (piece != null) {
				//..removes it from the deadPieces-list...
				this.deadPieces.remove(piece);
				/*...and replaces the Pawn with an equivalent Piece of the
				 *correct Faction.*/
				Piece promoted = pawn.getFaction().getEquivalent(piece, 
						pawn.getTeam());
				this.setPiece(promoted, row, column);
				promoted.moveTo(row, column);
				return piece.getName();
			}
		}
		return null;
	}

	/**
	 * A method used for promoting Pawns that reach the end of the Board.
	 * Gets a list of the possible promotions and then sends forward a request
	 * for the player to choose a promotion among them.
	 * 
	 * @param faction the Faction the Pawn belongs to
	 * @param row the row of the Pawn's location
	 * @param column the column of the Pawn's location
	 * @param team the team the Pawn belongs to
	 */

	void promotePawn(Faction faction, int row, int column, boolean team) {
		if (this.getPiece(row, column) instanceof Pawn) {
			Piece piece = null;
			while (piece == null) {
				piece = this.askPiece(Arrays.asList(faction.getOfficers(row, 
						column, team, this)), "Choose a promotion.");
			}
			this.setPiece(piece, row, column);
		}
	}

	/**
	 * A method that prompts an InputDialog asking for the Player to choose
	 * a piece from the list. It then returns the chosen Piece.
	 * 
	 * @param pieces the List of Pieces to be chosen amongst
	 * @param message the message to be displayed with the choice
	 * @return the Piece-object the player chose, or null if none was chosen.
	 */

	private Piece askPiece(List<Piece> pieces, String message) {
		if (pieces != null && pieces.size() > 0) {
			String[] pieceNames = new String[pieces.size()];
			for (int i = 0; i < pieces.size(); i++) {
				pieceNames[i] = pieces.get(i).getName();
			}
			String pieceName = (String)JOptionPane.showInputDialog(null, 
					message, "Piece Chooser", JOptionPane.PLAIN_MESSAGE, null, 
					pieceNames, pieceNames[0]);
			if (pieceName != null) {
				for (int i = 0; i < pieces.size(); i++) {
					if (pieceName.equals(pieceNames[i])) {
						return pieces.get(i);
					}
				}
			}
		}
		return null;
	}

	/**
	 * A method used to inform the Board of an event occurring durin the game.
	 * @param string the event that occurred
	 */
	
	public void inform(String string) {
		this.gameLog.addText(string);
	}

	/**
	 * A method used to add moves to the Board' memory in order to make it
	 * possible to save the game
	 * @param string the move made
	 */
	
	public void addMove(String string) {
		this.currentGame += string + "\n";
	}

	/**
	 * A method used to load the game from the file "save.txt"
	 * 
	 * @throws LoadException if for some reason the loading proves unsuccesful
	 */
	
	public void loadGame() throws LoadException {
		File saveFile = new File("save.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(saveFile);
		}
		catch(FileNotFoundException fnfe) {
			throw new LoadException("Save file not found.");
		}
		//Reads the first two lines and attempts to parse Factions of them
		String line = scanner.nextLine();
		this.currentGame = line + "\n";
		Faction faction1 = Faction.parseFaction(line);
		line = scanner.nextLine();
		this.currentGame += line + "\n";
		Faction faction2 = Faction.parseFaction(line);
		/*If the Faction weren't parsed succesfully, or they weren't legal
		 *throws a LoadException*/
		if (faction1 == null || faction2 == null ) {
			if ((faction1 == Faction.NONE && faction2 != Faction.NONE) || 
					(faction1 != Faction.NONE && faction1 == faction2)) {
				throw new LoadException("Illegal factions.");
			}
		}
		//Starts a new game with the Factions
		this.newGame(faction1, faction2);
		//Starts reading the moves made from the save file
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			char[] lineChars = line.toCharArray();
			int[] location = new int[2];
			int[] destination = new int[2];
			try {
				location[0] = Integer.parseInt(Character.toString(lineChars[1]));
				location[1] = Integer.parseInt(Character.toString(lineChars[2]));
				destination[0] = Integer.parseInt(Character.toString(lineChars[4]));
				destination[1] = Integer.parseInt(Character.toString(lineChars[5]));
			}
			catch (NumberFormatException nfe) {
				throw new LoadException("Illegal coordinates on line:\n" + line);
			}
			//Checks that the coordinates are legal
			if (!Board.isOnBoard(location[1], location[0]) || 
					!Board.isOnBoard(destination[1], destination[0])) {
				throw new LoadException("Illegal coordinates on line:\n" + line);
			}
			boolean lineLoaded = false;
			Piece piece = this.getPiece(location[1], location[0]);
			/*Checks that the type of piece in the square matches the one 
			 * implied by the file*/
			if (piece != null && 
					piece.toString().equals(Character.toString(lineChars[0]))) {
				if (piece.getTeam() == this.whoseTurn()) {
					//Checks whether the move implied was a regular move...
					if (lineChars[3] == '-') {
						if (piece.canMoveTo(destination[1], destination[0])) {
							this.inform(piece.getName() + " moved from " + 
								Board.COLUMNS[piece.getColumn()] + 
								(piece.getRow()+1) + " to " + 
								Board.COLUMNS[destination[0]] + 
								(destination[1]+1) + ".\n");
							piece.moveTo(destination[1], destination[0]);
							lineLoaded = true;
							this.addMove(line);
						}
					}
					//..an attack...
					else if (lineChars[3] == 'x') {
						if (piece.canAttackTo(destination[1], destination[0])) {
							this.inform(piece.getName() + " from " + 
								Board.COLUMNS[piece.getColumn()] + 
								(piece.getRow()+1) + " attacked " + 
								this.getPiece(destination[1], 
								destination[0]).getName() + " in " + 
								Board.COLUMNS[destination[0]] + 
								(destination[1]+1) + ".\n");
							piece.attackTo(destination[1], destination[0]);
							lineLoaded = true;
							this.addMove(line);
						}
					}
					//...or a special ability.
					else if (lineChars[3] == 's') {
						if (piece.hasSpecial()) {
							if (piece.canUseSpecialTo(destination[1], 
									destination[0])) {
								/*Some pieces have special abilities that have
								 *to be handled differently while loading*/
								if (!(piece instanceof Archangel || 
										piece instanceof Necromancer || 
										piece instanceof Crypt)) {
									piece.useSpecialTo(destination[1], 
											destination[0]);
									lineLoaded = true;
								}
								else {
									String endOfLine = 
										Character.toString(lineChars[6]);
									for (int i = 7; i < lineChars.length; i++) {
										endOfLine += 
											Character.toString(lineChars[i]);
									}
									if (piece instanceof Crypt) {
										Iterator<Piece> dpIter = 
											this.getDeadPieceIterator();
										Piece nextPiece = null;
										while (dpIter.hasNext()) {
											nextPiece = dpIter.next(); 
											if (nextPiece.getName().
													equals(endOfLine)) {
												this.setPiece(new Skeleton(2, 
													piece.getTeam(), this, 
													destination[1], 
													destination[0], true), 
													destination[1], 
													destination[0]);
												lineLoaded = true;
											}
										}
										if (lineLoaded) {
											this.removeDeadPiece(nextPiece);
										}
									}
									else if (piece instanceof Archangel) {
										Iterator<Piece> dpIter = 
											this.getDeadPieceIterator();
										Piece nextPiece = null;
										while (dpIter.hasNext()) {
											nextPiece = dpIter.next(); 
											if (nextPiece.getName().
													equals(endOfLine)) {
												nextPiece.heal(nextPiece.
													getFullHitPoints());
												nextPiece.moveTo(destination[1], 
													destination[0]);
												((Archangel)piece).
													specialExhausted();
												lineLoaded = true;
											}
										}
										if (lineLoaded) {
											this.removeDeadPiece(nextPiece);
										}
									}
									else if (piece instanceof Necromancer) {
										Iterator<Piece> dpIter = 
											this.getDeadPieceIterator();
										Piece nextPiece = null;
										while (dpIter.hasNext()) {
											nextPiece = dpIter.next(); 
											if (nextPiece.getName()
													.equals(endOfLine)) {
												if (nextPiece instanceof Rook) {
													this.setPiece(
														new Crypt(10, 
														piece.getTeam(), this, 
														destination[1], 
														destination[0], true), 
														destination[1], 
														destination[0]);
													lineLoaded = true;
												}
												if (nextPiece instanceof Knight) {
													this.setPiece(
														new Vampire(10, 
														piece.getTeam(), this, 
														destination[1], 
														destination[0], true), 
														destination[1], 
														destination[0]);
													lineLoaded = true;
												}
												if (nextPiece instanceof Bishop) {
													this.setPiece(
														new Lich(10, 
														piece.getTeam(), this, 
														destination[1], 
														destination[0], true), 
														destination[1], 
														destination[0]);
													lineLoaded = true;
												}
												if (nextPiece instanceof Queen) {
													this.setPiece(
														new Wraith(10, 
														piece.getTeam(), this, 
														destination[1], 
														destination[0], true), 
														destination[1], 
														destination[0]);
													lineLoaded = true;
												}
											}
										}
										if (lineLoaded) {
											this.removeDeadPiece(nextPiece);
										}
									}
									if (lineLoaded) {
										this.inform(piece.getName() + " used " + 
											piece.getSpecialAbility() +".\n");
										this.addMove(line);
									}
								}
							}
						}
					}
				}
			}
			else {
				throw new LoadException("Piece mismatch on line:\n" + line); 
			}
			if (!lineLoaded) {
				throw new LoadException("Error on line:\n"  + line);
			}
			else {
				this.endTurn();
			}
		}
	}

	/**
	 * A method used to sace the game to the file "save.txt"
	 * 
	 * @throws SaveException if saving the game for some reason proves 
	 * unsuccesful
	 */
	
	public void saveGame() throws SaveException {
		BufferedWriter saveWriter;
		try {
			saveWriter = new BufferedWriter(new FileWriter(new File("save.txt")));
			saveWriter.write(this.currentGame);
			saveWriter.flush();
			saveWriter.close();
		} catch (IOException e) {
			throw new SaveException();
		}
	}

	/**
	 * A method that can be used to check that the location defined by
	 * certain coordinates fall's on the Board.
	 * 
	 * @param row the row of the location
	 * @param column the column of the location
	 * @return true, if the location is on a Board, false otherwise
	 */
	
	public static boolean isOnBoard(int row, int column) {
		if (row >= 0 && row < 8 && column >= 0 && column < 8) {
			return true;
		}
		return false;
	}

}
