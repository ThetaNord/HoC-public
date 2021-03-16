package game;

/**
 * A class depicting the properties and functionality of the chess piece bishop
 * 
 * @author Tuure Saloheimo
 *
 */

public class Bishop extends Piece {

	/**
	 * Creates a new Bishop-object accordingly to the given parameters.
	 * 
	 * @param atk The attack of the Bishop
	 * @param def The defense of the Bishop
	 * @param hp The current hitpoints of the Bishop
	 * @param fhp The maximum hitpoints of the Bishop
	 * @param team The team the Bishop belongs to.
	 * @param board The board the Bishop is on.
	 * @param row The row of the Bishop's location
	 * @param column The column of the Bishop's location
	 * @param hasMoved The boolean value of whether the Bishop has moved or not
	 */
	
	public Bishop(int atk, int def, int hp, int fhp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(atk, def, hp, fhp, team, board, row, column, hasMoved);
	}
	
	@Override
	public String getName() {
		return "Bishop";
	}

	@Override
	public boolean canMoveTo(int row, int column) {
		//Checks that the piece can move and that it is on a board
		if (this.canMove() && this.getBoard() != null) {
			//Checks that the target square is empty
			if (this.getBoard().getPiece(row, column) == null) {
				//Checks that the target square is on a correct diagonal
				if (Math.abs(row - this.getRow()) == 
						Math.abs(column - this.getColumn())) {
					int r = row;
					int c = column;
					int dr = (row < this.getRow() ? +1 : -1);
					int dc = (column < this.getColumn() ? +1: -1);
					//Checks that the path to the square is clear
					while (r != this.getRow() && c != this.getColumn()) {
						//If it's not...
						if (this.getBoard().getPiece(r, c) != null) {
							//..returns false.
							return false;
						}
						r += dr;
						c += dc;
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canAttackTo(int row, int column) {
		/*Check's that there is a piece in the target square and that it is
		 *a member of the opposing team.*/
		Piece piece = this.getBoard().getPiece(row, column); 
		if (piece != null && piece.getTeam() != this.getTeam()) {
			//Checks that the target square is on a correct diagonal
			if (Math.abs(row - this.getRow()) == 
					Math.abs(column - this.getColumn())) {
				//Sets the appropriate delta values
				int dr = (row < this.getRow() ? +1 : -1);
				int dc = (column < this.getColumn() ? +1: -1);
				/*If the target coordinates, with the delta values once added
				 *is the location of this piece...*/
				if (column+dc == this.getColumn() && row+dr == this.getRow()) {
					//...returns true.
					return true;
				}
				/*Otherwise, checks whether it is possible for the piece to 
				 *move into that square.*/
				else return this.canMoveTo(row+dr, column+dc);
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "B";
	}

}
