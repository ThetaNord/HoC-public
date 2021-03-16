package game;

/**
 * A class that depicts the chess piece Rook.
 * 
 * @author Tuure Saloheimo
 *
 */
public class Rook extends Piece {

	/**
	 * Creates a new Rook accordingly with the given parametres.
	 * 
	 * @param atk the attack value of the Rook
	 * @param def the defense value of the Rook
	 * @param hp the current hit points of the Rook
	 * @param fhp the maximum hit points of the Rook 
	 * @param team the team the Rook belongs to
	 * @param board the Board the Rook is on
	 * @param row the row of the Rook's location
	 * @param column the column of the Rook's location
	 * @param hasMoved whether the Rook has moved or not
	 */

	public Rook(int atk, int def, int hp, int fhp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(atk, def, hp, fhp, team, board, row, column, hasMoved);
	}

	@Override
	public String getName() {
		return "Rook";
	}

	@Override
	public boolean canMoveTo(int row, int column) {
		if (this.canMove() && this.getBoard() != null) {
			//Checks that the target square is empty
			if (this.getBoard().getPiece(row, column) == null) {
				/*Checks that the target square is on the same row and 
				 *different column.*/
				if (row == this.getRow() && column != this.getColumn()) {
					int i = column;
					//Checks that the path to the target square is clear.
					while (i != this.getColumn()) {
						if (this.getBoard().getPiece(row, i) != null) {
							return false;
						}
						if (i < this.getColumn()) i++;
						else i--;
					}
					return true;
				}
				//Or on the same column and different row.
				else if (row != this.getRow() && column == this.getColumn()) {
					int i = row;
					//Checks that the path to the target square is clear.
					while (i != this.getRow()) {
						if (this.getBoard().getPiece(i, column) != null) {
							return false;
						}
						if (i < this.getRow()) i++;
						else i--;
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canAttackTo(int row, int column) {
		if (this.canMove() && this.getBoard() != null) {
			/*Checks that there is a Piece of the opposing team in the
			 *target square*/
			Piece piece = this.getBoard().getPiece(row, column); 
			if (piece != null && piece.getTeam() != this.getTeam()) {
				//Checks that the target square is on the same row or column
				if (row == this.getRow() || column == this.getColumn()) {

					int dr = 0;
					int dc = 0;
					//Sets the correct delta values
					if (row < this.getRow()) dr = +1;
					else if (row > this.getRow()) dr = -1;
					if (column < this.getColumn()) dc = +1;
					else if (column > this.getColumn()) dc = -1;
					//Checks that the path to the target square is clear.
					if (column+dc == this.getColumn() && row+dr == this.getRow()) {
						return true;
					}
					else return this.canMoveTo(row+dr, column+dc);
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "R";
	}

}
