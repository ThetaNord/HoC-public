package game;

/**
 * A class depicting the Mana Node piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class ManaNode extends Pawn {

	/**
	 * A method that creates a new Mana Node object according to the given
	 * parametres.
	 * 
	 * @param hp the current hit points of the Mana Node
	 * @param team the team the Mana Node belongs to
	 * @param board the Board the Mana Node is on
	 * @param row the row of the Mana Node's location
	 * @param column the column of the Mana Node's location
	 * @param hasMoved whether the Mana Node has moved or not
	 */
	
	public ManaNode(int hp, boolean team, Board board, int row, int column, 
			boolean hasMoved) {
		super(1, 3, hp, 3, team, board, row, column, hasMoved);
		this.faction = Faction.ARCANE;
	}

	@Override
	public String getName() {
		return "Mana Node";
	}
	
	@Override
	public int getDefense() {
		int def = super.getDefense();
		/*Checks whether there is an allied Earth Elemental in the 
		 *4 adjacent squares. If yes, defense+1.*/ 
		Board board = this.getBoard();
		int row = this.getRow();
		int column = this.getColumn();
		if (board.getPiece(row+1, column) instanceof EarthElemental && 
			board.getPiece(row+1, column).getTeam() == this.getTeam()|| 
			board.getPiece(row-1, column) instanceof EarthElemental &&
			board.getPiece(row-1, column).getTeam() == this.getTeam() ||
			board.getPiece(row, column+1) instanceof EarthElemental &&
			board.getPiece(row, column+1).getTeam() == this.getTeam() ||
			board.getPiece(row, column-1) instanceof EarthElemental &&
			board.getPiece(row, column-1).getTeam() == this.getTeam()) {
				return def+1;
		}
		return def;
	}
	
	/**
	 * A method used to "blow up" the Mana Node and damage all the Pieces in
	 * the surrounding 8 squares. Associated with the Archmage's special 
	 * ability.
	 */
	void explode() {
		//Informs the Board of the event.
		this.getBoard().inform(this.getName() + " explodes.\n");
		//Damages all the surrounding pieces.
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Piece piece = this.getBoard().getPiece(this.getRow()+i, 
						this.getColumn()+j);
				if (piece != null && piece != this) {
					piece.damage(piece.getDefense()+2);
				}
			}
		}
		//Removes the Mana Node from the board...
		this.getBoard().setPiece(null, this.getRow(), this.getColumn());
		//...and adds it to the deadPieces-list.
		this.getBoard().addDeadPiece(this);
	}
}
