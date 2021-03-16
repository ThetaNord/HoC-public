package game;

/**
 * A class depicting the chess piece King.
 * 
 * @author Tuure Saloheimo
 *
 */

public class King extends Piece {

	/**
	 * Creates a new King-object according to the given parametres.
	 * 
	 * @param atk the attack value of the King
	 * @param def the defense value of the King
	 * @param hp the current hit points of the King
	 * @param fhp the maximum hitpoints of the King
	 * @param team the team the King belongs to
	 * @param board the Board the king is placed on
	 * @param row the row of the King's location
	 * @param column the column of the King's location
	 * @param hasMoved whether the King has moved already or not
	 */
	
	public King(int atk, int def, int hp, int fhp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(atk, def, hp, fhp, team, board, row, column, hasMoved);
	}
	
	@Override
	public String getName() {
		return "King";
	}

	@Override
	public boolean canMoveTo(int row, int column) {
		if (this.canMove()) {
			/*Checks that the King is on a Board and that the target square
			 *is empty.*/
			if (this.getBoard() != null && 
					this.getBoard().getPiece(row, column) == null) {
				//If the King hasn't moved, checks whether castling is possible
				if (!this.hasMoved()) {
					if (row == this.getRow()) {
						if (column == this.getColumn()+2) {
							if (this.getBoard().getPiece(row, column-1) 
									== null) {
								Piece piece = 
										this.getBoard().getPiece(row, column+1);
								if (piece instanceof Rook) {
									if (!piece.hasMoved()) {
										return true;
									}
								}
							}
						}
						else if (column == this.getColumn()-2) {
							if (this.getBoard().getPiece(row, column+1) == null 
									&& this.getBoard().getPiece(row, column-1) 
									== null) {
								Piece piece = 
										this.getBoard().getPiece(row, column-2);
								if (piece instanceof Rook) {
									if (!piece.hasMoved()) {
										return true;
									}
								}
							}
						}
					}
				}
			//Checks that the square is one of the surrounding 8 squares
				if (row != this.getRow() || column != this.getColumn()) {
					if (row >= this.getRow()-1 && row <= this.getRow()+1) {
						if (column >= this.getColumn()-1 && 
								column <= this.getColumn()+1) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void moveTo(int row, int column) {
		/*Checks if the King is being castled, 
		 *if yes also moves the appropriate Rook.*/
		if (!this.hasMoved()) {
			if (column == this.getColumn()-2) {
				this.getBoard().getPiece(row, column-2).moveTo(row, column+1);
			}
			if (column == this.getColumn()+2) {
				this.getBoard().getPiece(row, column+1).moveTo(row, column-1);
			}
		}
		super.moveTo(row, column);
	}

	@Override
	public boolean canAttackTo(int row, int column) {
		if (this.canMove()) {
			if (this.getBoard() != null) {
				/*Checks that the target square contains an enemy piece and
				 *that it is one of the 8 squares surrounding the King.*/
				if (row != this.getRow() || column != this.getColumn()) {
					if (row >= this.getRow()-1 && row <= this.getRow()+1) {
						if (column >= this.getColumn()-1 && 
								column <= this.getColumn()+1) {
							Piece piece = 
									this.getBoard().getPiece(row, column);
							if (piece != null && 
									piece.getTeam() != this.getTeam()) {
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
	public String toString() {
		return "K";
	}

}
