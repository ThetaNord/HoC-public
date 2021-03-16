
package game;

/**
 * An enumeration listing the different available Factions, as well as 
 * providing methods for e.g. receiving different kinds of Pieces according
 * to Faction and parsing Factions from Strings.  
 * 
 * @author Tuure Saloheimo
 *
 */

public enum Faction {

	VALOR, UNDEAD, ARCANE, NONE;
	
	/**
	 * Returns a Pawn-object or a Pawn-extending-object affiliated with the 
	 * Faction calling the method. The returned Piece will have attributes set 
	 * according to the given parametres. 
	 * 
	 * @param row the row of the location where the Pawn is to be placed
	 * @param column the column of the location where the Pawn is to be placed
	 * @param team the team the Pawn will belong to
	 * @param board the board on which the Pawn will be placed
	 * @return a Pawn-extending-object associated with the calling Faction,
	 * or a Pawn if the Faction is NONE.
	 */
	
	public Pawn getPawn(int row, int column, boolean team, Board board) {
		switch (this) {
		case UNDEAD: return new Skeleton(2, team, board, row, column, false);
		case VALOR: return new Footman(3, team, board, row, column, false);
		case ARCANE: return new ManaNode(3, team, board, row, column, false);
		default: return new Pawn(6, 1, 1, 1, team, board, row, column, false);
		}
	}
	
	/**
	 * Returns a Rook-object or a Rook-extending-object affiliated with the 
	 * Faction calling the method. The returned Piece will have attributes set 
	 * according to the given parametres. 
	 * 
	 * @param row the row of the location where the Rook is to be placed
	 * @param column the column of the location where the Rook is to be placed
	 * @param team the team the Rook will belong to
	 * @param board the board on which the Rook will be placed
	 * @return a Rook-extending-object associated with the calling Faction,
	 * or a Rook if the Faction is NONE.
	 */
	
	public Rook getRook(int row, int column, boolean team, Board board) {
		switch (this) {
		case UNDEAD: return new Crypt(5, team, board, row, column, false);
		case VALOR: return new Tower(4, team, board, row, column, false);
		case ARCANE: return new EarthElemental(5, team, board, row, column, false);
		default: return new Rook(6, 1, 1, 1, team, board, row, column, false);
		}
	}
	
	/**
	 * Returns a Knight-object or a Knight-extending-object affiliated with the 
	 * Faction calling the method. The returned Piece will have attributes set 
	 * according to the given parametres. 
	 * 
	 * @param row the row of the location where the Knight is to be placed
	 * @param column the column of the location where the Knight is to be placed
	 * @param team the team the Knight will belong to
	 * @param board the board on which the Knight will be placed
	 * @return a Knight-extending-object associated with the calling Faction,
	 * or a Knight if the Faction is NONE.
	 */
	
	public Knight getKnight(int row, int column, boolean team, Board board) {
		switch (this) {
		case UNDEAD: return new Vampire(4, team, board, row, column, false);
		case VALOR: return new Cavalier(4, team, board, row, column, false);
		case ARCANE: return new Sorcerer(4, team, board, row, column, false);
		default: return new Knight(6, 1, 1, 1, team, board, row, column, false);
		}
	}
	
	/**
	 * Returns a Bishop-object or a Bishop-extending-object affiliated with the 
	 * Faction calling the method. The returned Piece will have attributes set 
	 * according to the given parametres. 
	 * 
	 * @param row the row of the location where the Bishop is to be placed
	 * @param column the column of the location where the Bishop is to be placed
	 * @param team the team the Bishop will belong to
	 * @param board the board on which the Bishop will be placed
	 * @return a Bishop-extending-object associated with the calling Faction,
	 * or a Bishop if the Faction is NONE.
	 */
	
	public Bishop getBishop(int row, int column, boolean team, Board board) {
		switch (this) {
		case UNDEAD: return new Lich(4, team, board, row, column, false);
		case VALOR: return new Priest(5, team, board, row, column, false);
		case ARCANE: return new AirElemental(5, team, board, row, column, false);
		default: return new Bishop(6, 1, 1, 1, team, board, row, column, false);
		}
	}
	
	/**
	 * Returns a Queen-object or a Queen-extending-object affiliated with the 
	 * Faction calling the method. The returned Piece will have attributes set 
	 * according to the given parametres. 
	 * 
	 * @param row the row of the location where the Queen is to be placed
	 * @param column the column of the location where the Queen is to be placed
	 * @param team the team the Queen will belong to
	 * @param board the board on which the Queen will be placed
	 * @return a Queen-extending-object affiliated with the calling Faction,
	 * or a Queen if the Faction is NONE.
	 */
	
	public Queen getQueen(int row, int column, boolean team, Board board) {
		switch (this) {
		case UNDEAD: return new Wraith(6, team, board, row, column, false);
		case VALOR: return new Archangel(6, team, board, row, column, false);
		case ARCANE: return new ArcaneElemental(7, team, board, row, column, false);
		default: return new Queen(6, 1, 1, 1, team, board, row, column, false);
		}
	}
	
	/**
	 * Returns a King-object or a King-extending-object affiliated with the 
	 * Faction calling the method. The returned Piece will have attributes set 
	 * according to the given parametres.
	 * 
	 * @param row the row of the location where the King is to be placed
	 * @param column the column of the location where the King is to be placed
	 * @param team the team the King will belong to
	 * @param board the board on which the King will be placed
	 * @return a King-extending-object affiliated with the calling Faction,
	 * or a King if the Faction is NONE.
	 */
	
	public King getKing(int row, int column, boolean team, Board board) {
		switch (this) {
		case UNDEAD: return new Necromancer(team, board);
		case VALOR: return new TheKing(team, board);
		case ARCANE: return new Archmage(team, board);
		default: return new King(6, 1, 1, 1, team, board, row, column, false);
		}
	}
	
	/**
	 * Returns an array containing an instance of all the officer (i.e. not a 
	 * Pawn or a King) pieces of the calling faction. Used when promoting Pawns
	 * 
	 * @param row the row where the Pieces' possible location
	 * @param column the column of the Pieces' possible location
	 * @param team the team the Pieces will belong to
	 * @param board the Board on which the Pieces might be placed
	 * @return an array containing the officer pieces of the calling Faction.
	 */
	
	public Piece[] getOfficers(int row, int column, boolean team, Board board) {
		Piece[] pieces = {this.getQueen(row, column, team, board), 
				this.getRook(row, column, team, board), 
				this.getKnight(row, column, team, board), 
				this.getBishop(row, column, team, board)};
		return pieces;
	}
	
	/**
	 * A method that gives an equivalent of a piece given as parametre, but
	 * which affiliates with the calling Faction.
	 * The returned Piece will have it's attributes set accordingly to the
	 * piece given as parametre, except for team which will be set according
	 * to the given parametre.
	 * 
	 * @param piece the Piece the equivalent of which will be returned
	 * @param team the team of the Piece to be returned
	 * @return a Piece equivalent to the given Piece-parametre, but affiliated
	 * with the calling Faction, or null if the equivalent couldn't ba made 
	 * (most likely because the Piece-parametre was null).
	 */
	
	public Piece getEquivalent(Piece piece, boolean team) {
		if (piece instanceof King) {
			return this.getKing(piece.getRow(), piece.getColumn(), team, piece.getBoard());
		}
		if (piece instanceof Queen) {
			return this.getQueen(piece.getRow(), piece.getColumn(), team, piece.getBoard());
		}
		if (piece instanceof Rook) {
			return this.getRook(piece.getRow(), piece.getColumn(), team, piece.getBoard());
		}
		if (piece instanceof Knight) {
			return this.getKnight(piece.getRow(), piece.getColumn(), team, piece.getBoard());
		}
		if (piece instanceof Bishop) {
			return this.getBishop(piece.getRow(), piece.getColumn(), team, piece.getBoard());
		}
		if (piece instanceof Pawn) {
			return this.getPawn(piece.getRow(), piece.getColumn(), team, piece.getBoard());
		}
		return null;
	}
	
	/**
	 * A method that parses the String-values associated with each Faction
	 * back to the according Faction.
	 * 
	 * @param string the String to be parsed into Faction
	 * @return the parsed Faction, or null if the parsing wasn't succesful.
	 */
	
	public static Faction parseFaction(String string) {
		if (string.equals("Undead")) {
			return UNDEAD;
		}
		if (string.equals("Valor")) {
			return VALOR;
		}
		if (string.equals("Arcane")) {
			return ARCANE;
		}
		if (string.equals("<None>")) {
			return NONE;
		}
		return null;
	}
	
	@Override
	public String toString() {
		switch(this) {
		case UNDEAD: return "Undead";
		case VALOR: return "Valor";
		case ARCANE: return "Arcane";
		case NONE: return "<None>";
		default: return "none";
		}
	}
	
}
