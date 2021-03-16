package game;

/**
 * A class depicting the chess piece Queen.
 * 
 * @author Tuure Saloheimo
 *
 */
public class Queen extends Piece {

	/**
	 * The special abilities of the Queen'extending pieces are powerful
	 * one-shot abilities and as such need an attribute to mark if they
	 * have been used or not.
	 */
	protected boolean specialUsed;

	/**
	 * Creates a new Queen accordingly with the given parametres.
	 * 
	 * @param atk the attack value of the Queen
	 * @param def the defense value of the Queen
	 * @param hp the current hit points of the Queen
	 * @param fhp the maximum hit points of the Queen 
	 * @param team the team the Queen belongs to
	 * @param board the Board the Queen is on
	 * @param row the row of the Queen's location
	 * @param column the column of the Queen's location
	 * @param hasMoved whether the Queen has moved or not
	 */

	public Queen(int atk, int def, int hp, int fhp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(atk, def, hp, fhp, team, board, row, column, hasMoved);
		this.specialUsed = true;
	}

	@Override
	public String getName() {
		return "Queen";
	}

	@Override
	public boolean canMoveTo(int row, int column) {
		if (this.canMove() && this.getBoard() != null) {
			if (this.getBoard().getPiece(row, column) == null) {
				int r = row;
				int c = column;
				int dr = 0;
				int dc = 0;
				//Sets the correct delta values.
				if (row < this.getRow()) dr = +1;
				else if (row > this.getRow()) dr = -1;
				if (column < this.getColumn()) dc = +1;
				else if (column > this.getColumn()) dc = -1;
				/*Checks if the target square is on the same row or column
				 *as the Queen*/
				if (row == this.getRow() || column == this.getColumn()) {
					//Checks that the path to the square is clear
					while (c != this.getColumn() || r != this.getRow()) {
						if (this.getBoard().getPiece(r, c) != null) {
							return false;
						}
						r += dr;
						c += dc;
					}
					return true;
				}
				/*Checks if the target square is on the same diagonals as the
				 *Queen*/
				else if (Math.abs(row - this.getRow()) == 
						Math.abs(column - this.getColumn())) {
					//Checks that the path to the square is clear
					while (r != this.getRow() && c != this.getColumn()) {
						if (this.getBoard().getPiece(r, c) != null) {
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
		if (this.canMove() && this.getBoard() != null) {
			Piece piece = this.getBoard().getPiece(row, column);
			/*Checks that there is a Piece of the opposing team in the
			 *target square*/
			if (piece != null && piece.getTeam() != this.getTeam()) {
				/*Checks that the target square is on the same row, column or
				 *daigonal sa the Queen*/
				if (row == this.getRow() || column == this.getColumn() || 
						Math.abs(row - this.getRow()) == 
						Math.abs(column - this.getColumn())) {

					int dr = 0;
					int dc = 0;
					//Sets the correct delta values
					if (row < this.getRow()) dr = +1;
					else if (row > this.getRow()) dr = -1;
					if (column < this.getColumn()) dc = +1;
					else if (column > this.getColumn()) dc = -1;
					//Checks that the path to the target square is clear
					if (column+dc == this.getColumn() && 
							row+dr == this.getRow()) {
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
		return "Q";
	}

	/**
	 * A method that can be used to 'exhaust' the Queen's special ability,
	 * i.e. change the value of specialUsed to true, without using the 
	 * special ability itself.
	 */

	void specialExhausted() {
		this.specialUsed = true;
	}

}
