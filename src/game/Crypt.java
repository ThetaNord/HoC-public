package game;

/**
 * A class depicting the Crypt piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Crypt extends Rook implements SpecialAbility {

	/**
	 * Creates a new Crypt-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */

	public Crypt(int hp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(4, 4, hp, 5, team, board, row, column, hasMoved);
		this.hasSpecial = true;
		this.faction = Faction.UNDEAD;
	}

	@Override
	public String getName() {
		return "Crypt";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		//Checks that the target square is one of the adjacent four squares.
		if (Math.abs(row - this.getRow()) + 
				Math.abs(column - this.getColumn()) == 1) {
			//Checks that the target square is empty.
			if (this.getBoard().getPiece(row, column) == null) {
				//Checks that there is a dead enemy piece to be reanimated. 
				if (this.getBoard().containsDeadPiece(!this.getTeam())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		//Asks for a piece to be "reanimated"
		Piece dPiece = this.getBoard().reanimatePiece(this.getTeam(), 
				this.getFaction());
		//If a non-null piece is received...
		if (dPiece != null) {
			//...removes the piece from the deadPieces-list...
			this.getBoard().removeDeadPiece(dPiece);
			//...informs the Board of a new move made...
			this.getBoard().addMove(this.toString() + this.getColumn() + 
				this.getRow() + "s" + column + row + dPiece.getName());
			//..."spawns" a skeleton in the target square...
			Piece piece = new Skeleton(2, this.getTeam(), this.getBoard(), 
				row, column, false);
			this.getBoard().setPiece(piece, row, column);
			//...and informs the Board of a new game event...
			this.getBoard().inform(this.getName() + " used " + 
				this.getSpecialAbility() + ".\n" + piece.getName() + 
				" spawned in " + Board.COLUMNS[column] + (row+1) + ".\n");	
			return true;
		}
		return false;
	}

	@Override
	public String getSpecialAbility() {
		return "Raise Corpse";
	}

}
