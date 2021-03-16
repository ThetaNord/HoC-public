package game;

/**
 * A class depicting the Air Elemental piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class AirElemental extends Bishop {

	/**
	 * Creates a new AirElemental-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */

	public AirElemental(int hp, boolean team,
			Board board, int row, int column, boolean hasMoved) {
		super(4, 2, hp, 5, team, board, row, column, hasMoved);
		this.faction = Faction.ARCANE;
		this.hasSpecial = true;
	}

	@Override
	public String getName() {
		return "Air Elemental";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		//Checks whether the target square is empty
		if (this.getBoard().getPiece(row, column) == null) {
			//Checks whether the square is on the correct diagonals
			if (Math.abs(row - this.getRow()) == 
					Math.abs(column - this.getColumn())) {
				int r = row;
				int c = column;
				//Sets up the correct delta values
				int dr = (row < this.getRow() ? +1 : -1);
				int dc = (column < this.getColumn() ? +1: -1);
				//Runs through the applicable squares towards the piece itself
				while (r != this.getRow() && c != this.getColumn()) {
					//If a piece is one the way...
					if (this.getBoard().getPiece(r, c) != null) {
						/*...returns the boolean value of whether that piece
						 *is assailable.*/
						return this.canAttackTo(r, c);
					}
					r += dr;
					c += dc;
				}
			}
		}
		//By default, false.
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		int r = row;
		int c = column;
		//Sets the correct delta values
		int dr = (row < this.getRow() ? +1 : -1);
		int dc = (column < this.getColumn() ? +1: -1);
		//Searches for the piece to serve as the ability's target
		while (r != this.getRow() && c != this.getColumn()) {
			Piece piece = this.getBoard().getPiece(r, c);
			//If a piece is found...
			if (piece != null) {
				//...and it belongs to a different team...
				if (piece.getTeam() != this.getTeam()) {
					//The superclass method is called
					super.useSpecialTo(row, column);
					//The found piece is attacked
					this.attack(piece);
					//And this piece is moved to the target square
					this.moveTo(row, column);
					return true;
				}
				else return false;
			}
			r += dr;
			c += dc;
		}
		return false;

	}

	@Override
	public String getSpecialAbility() {
		return "Tempest Strike";
	}

}
