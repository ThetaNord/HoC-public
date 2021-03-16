package game;

/**
 * A class depicting the Wraith piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class Wraith extends Queen {

	/**
	 * Creates a new Wraith-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Wraith(int hp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(6, 3, hp, 6, team, board, row, column, hasMoved);
		this.faction = Faction.UNDEAD;
		this.specialUsed = false;
		this.hasSpecial = true;
	}

	@Override
	public String getName() {
		return "Wraith";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		if (this.specialUsed == false) {
			//Only valid square is the Wraith's location
			if (row == this.getRow() && column == this.getColumn()) {
				int emptySquares = 0;
				Board board = this.getBoard();
				/*And it must have at least two empty spaces in the adjacent
				 *four squares.*/
				if (board.getPiece(row+1, column) == null && 
						Board.isOnBoard(row+1, column)) emptySquares += 1;
				if (board.getPiece(row-1, column) == null && 
						Board.isOnBoard(row-1, column)) emptySquares += 1;
				if (board.getPiece(row, column+1) == null && 
						Board.isOnBoard(row, column+1)) emptySquares += 1;
				if (board.getPiece(row, column-1) == null && 
						Board.isOnBoard(row, column-1)) emptySquares += 1;
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
		int skeletonsSpawned = 0;
		/*Spawns two Skeletons in the adjacent four squares. The method
		 *is deterministic.*/
		if (board.getPiece(row, column+1) == null && 
				Board.isOnBoard(row, column+1)) {
			board.setPiece(new Skeleton(2, this.getTeam(), board, row, 
					column+1, true), row, column+1);
			board.inform("Skeleton spawned in " + Board.COLUMNS[column+1] + 
					(row+1) + ".\n");
			skeletonsSpawned += 1;
		}
		if (board.getPiece(row, column-1) == null && 
				Board.isOnBoard(row, column-1)) {
			board.setPiece(new Skeleton(2, this.getTeam(), board, row, 
					column-1, true), row, column-1);
			board.inform("Skeleton spawned in " + Board.COLUMNS[column-1] + 
					(row+1) + ".\n");
			skeletonsSpawned += 1;
		}
		if (skeletonsSpawned < 2) {
			int dr = (this.getTeam() ? +1 : -1);
			for (int i = 0; i < 2; i++) {
				if (skeletonsSpawned < 2) {
					if (board.getPiece(row+dr, column) == null && 
							Board.isOnBoard(row+dr, column)) {
						board.setPiece(new Skeleton(2, this.getTeam(), board, 
								row+dr, column, true), row+dr, column);
						board.inform("Skeleton spawned in " + 
								Board.COLUMNS[column] + (row+dr+1) + ".\n");
						skeletonsSpawned += 1;
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
		return "Spectral Call";
	}
}
