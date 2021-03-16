package game;

/**
 * A class depicting the Archangel piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class Archangel extends Queen implements SpecialAbility {

	/**
	 * Creates a new Archangel-object accordingly to the given parameters. 
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Archangel(int hp, boolean team, Board board, int row, int column, 
			boolean hasMoved) {
		super(5, 4, hp, 6, team, board, row, column, hasMoved);
		this.hasSpecial = true;
		this.faction = Faction.VALOR;
		this.specialUsed = false;
	}
	
	@Override
	public String getName() {
		return "Archangel";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		//Checks whether the special ability has already been used
		if (!this.specialUsed) {
			//Checks whether the target square is empty
			if (this.getBoard().getPiece(row, column) == null) {
				/*Checks whether the target square is in the four squares 
				 *adjacent to the piece*/
				if (Math.abs(row - this.getRow()) + 
						Math.abs(column - this.getColumn()) == 1) {
					/*Checks whether there is a dead piece of this piece's
					 *team to be resurrected.*/
					if (this.getBoard().containsDeadPiece(this.getTeam())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		//Asks for a piece to be resurrected.
		Piece dPiece = this.getBoard().resurrectPiece(this.getTeam());
		//If a non-null piece was received...
		if (dPiece != null) {
			//Removes the piece from the deadPieces-list
			this.getBoard().removeDeadPiece(dPiece);
			//Informs the board of the event
			this.getBoard().inform(this.getName() + " used " + 
					this.getSpecialAbility() + ".\n");
			//Adds a new performed move
			this.getBoard().addMove(this.toString() + this.getColumn() + 
					this.getRow() + "s" + column + row + dPiece.getName());
			//Heals the dead piece to full hitpoints
			dPiece.heal(dPiece.getFullHitPoints());
			//Moves the dead piece to the target square
			dPiece.moveTo(row, column);
			return true;
			
		}
		return false;
	}
	
	@Override
	public String getSpecialAbility() {
		return "Resurrection";
	}

}
