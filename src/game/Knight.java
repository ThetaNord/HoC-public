package game;

/**
 * A class depicting the chess piece Knight.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Knight extends Piece {

	/**
	 * Creates a new Knight-object according to the given parametres.
	 * 
	 * @param atk the attack value of the Knight
	 * @param def the defense value of the Knight
	 * @param hp the current hit points of the Knight
	 * @param fhp the maximum hitpoints of the Knight
	 * @param team the team the Knight belongs to
	 * @param board the Board the king is placed on
	 * @param row the row of the Knight's location
	 * @param column the column of the Knight's location
	 * @param hasMoved whether the Knight has moved already or not
	 */
	
	public Knight(int atk, int def, int hp, int fhp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(atk, def, hp, fhp, team, board, row, column, hasMoved);
	}
	
	@Override
	public String getName() {
		return "Knight";
	}

	/**
	 * A method that check's whether the target square is a possible move
	 * or attack square for the Knight, and that the Knight could move to it.
	 * 
	 * @param row the row of the target square
	 * @param column the column of the target square
	 * @return true if the square is a valid target, false if it isn't
	 */
	public boolean canActTo(int row, int column) {
		if (this.canMove() && this.getBoard() != null) {
			if (row != this.getRow() && column != this.getColumn()) {
				if (Math.abs(row-this.getRow()) + 
						Math.abs(column-this.getColumn()) == 3) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canMoveTo(int row, int column) {
		return (canActTo(row, column) && 
			this.getBoard().getPiece(row, column) == null);
	}

	@Override
	public boolean canAttackTo(int row, int column) {
		return (canActTo(row, column) && 
			this.getBoard().getPiece(row, column) != null && this.getBoard().getPiece(row, column).getTeam() != this.getTeam());
	}
	
	@Override
	public void attackTo(int row, int column) {
		if (this.canAttackTo(row, column)) {
			this.attack(this.getBoard().getPiece(row, column));
			this.wasMoved();
			if (this.getBoard().getPiece(row, column) == null) {
				this.moveTo(row, column);
			}
		}
	}

	@Override
	public String toString() {
		return "N";
	}

}
