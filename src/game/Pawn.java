package game;

/**
 * A class depicting the chess piece Pawn.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Pawn extends Piece {

	/**
	 * Creates a new Pawn accordingly with the given parametres.
	 * 
	 * @param atk the attack value of the Pawn
	 * @param def the defense value of the Pawn
	 * @param hp the current hit points of the Pawn
	 * @param fhp the maximum hit points of the Pawn 
	 * @param team the team the Pawn belongs to
	 * @param board the Board the Pawn is on
	 * @param row the row of the Pawn's location
	 * @param column the column of the Pawn's location
	 * @param hasMoved whether the Pawn has moved or not
	 */
	
	public Pawn(int atk, int def, int hp, int fhp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(atk, def, hp, fhp, team, board, row, column, hasMoved);
	}
	
	@Override
	public String getName() {
		return "Pawn";
	}

	@Override
	public boolean canMoveTo(int row, int column) {
		if (this.canMove() && this.getBoard() != null) {
			if (this.getBoard().getPiece(row, column) == null) {
				//Checks that the target square is on the same column
				if (column == this.getColumn()) {
					/*Checks that the target square is the one in front of
					 *the Pawn*/ 
					if (row == this.getRow()+(this.getTeam()? +1 : -1)) {
						return true;
					}
					//And if the Pawn has not yet moved...
					if (!this.hasMoved()) {
						//...the square two row's ahead is also valid...
						if (row == this.getRow()+(this.getTeam()? +2 : -2)) {
							//...providing that the square in between is empty
							if (this.getBoard().getPiece(this.getRow()+
								(this.getTeam()? +1 : -1), column) == null) {
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void moveTo(int row, int column) {
		//Moves the Pawn to the target square...
		super.moveTo(row, column);
		/*...and if the target square is on the final row, prompts the 
		 *promotion dialog.*/
		if ((this.getTeam() && row == 7) || (!this.getTeam() && row == 0)) {
			this.getBoard().promotePawn(this.getFaction(), this.getRow(), this.getColumn(), this.getTeam());
		}
	}

	@Override
	public boolean canAttackTo(int row, int column) {
		if (this.canMove() && this.getBoard() != null) {
			Piece piece = this.getBoard().getPiece(row, column);
			/*Checks that there is a piece in the target square and that it is
			 * of the opposing team.*/
			if (piece != null && piece.getTeam() != this.getTeam()) {
				//Checks that the piece is on the next row
				if (row == this.getRow()+(this.getTeam()? +1 : -1)) {
					//Checks that the piece is located diagonally to this one
					if (Math.abs(column - this.getColumn()) == 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "p";
	}

}
