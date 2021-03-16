package game;

/**
 * A class depicting the Arcane Elemental piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class ArcaneElemental extends Queen {

	/**
	 * Creates a new ArcaneElemental-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public ArcaneElemental(int hp, boolean team,
			Board board, int row, int column, boolean hasMoved) {
		super(5, 3, hp, 7, team, board, row, column, hasMoved);
		this.faction = Faction.ARCANE;
		this.hasSpecial = true;
		this.specialUsed = false;
	}
	
	@Override
	public String getName() {
		return "Arcane Elemental";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		if (this.specialUsed == false) {
			if (row == this.getRow() && column == this.getColumn()) {
				int emptySquares = 0;
				Board board = this.getBoard();
				if (board.getPiece(row+1, column) == null && Board.isOnBoard(row+1, column)) emptySquares += 1;
				if (board.getPiece(row-1, column) == null && Board.isOnBoard(row-1, column)) emptySquares += 1;
				if (board.getPiece(row, column+1) == null && Board.isOnBoard(row, column+1)) emptySquares += 1;
				if (board.getPiece(row, column-1) == null && Board.isOnBoard(row, column-1)) emptySquares += 1;
				if (emptySquares >= 2) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		super.useSpecialTo(row, column);
		Board board = this.getBoard();
		int nodesSpawned = 0;
		if (board.getPiece(row, column+1) == null && Board.isOnBoard(row, column+1)) {
			board.setPiece(new ManaNode(3, this.getTeam(), board, row, column+1, true), row, column+1);
			board.inform("Mana Node spawned in " + Board.COLUMNS[column+1] + (row+1) + ".\n");
			nodesSpawned += 1;
		}
		if (board.getPiece(row, column-1) == null && Board.isOnBoard(row, column-1)) {
			board.setPiece(new ManaNode(3, this.getTeam(), board, row, column-1, true), row, column-1);
			board.inform("Mana Node spawned in " + Board.COLUMNS[column-1] + (row+1) + ".\n");
			nodesSpawned += 1;
		}
		if (nodesSpawned < 2) {
			int dr = (this.getTeam() ? +1 : -1);
			for (int i = 0; i < 2; i++) {
				if (nodesSpawned < 2) {
					if (board.getPiece(row+dr, column) == null && Board.isOnBoard(row+dr, column)) {
						board.setPiece(new ManaNode(3, this.getTeam(), board, row+dr, column, true), row+dr, column);
						board.inform("Mana Node spawned in " + Board.COLUMNS[column] + (row+dr+1) + ".\n");
						nodesSpawned += 1;
					}
				}
				dr = -dr;
			}
		}
		this.specialUsed = true;
		return true;
	}
	
	@Override
	public String getSpecialAbility() {
		return "Veins of Mana";
	}
	
}
