package game;

/**
 * A class depicting the Necromancer piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Necromancer extends King implements SpecialAbility {

	/**
	 * Creates a new Necromancer object according to the given parametres.
	 * 
	 * @param team the team the Necromancer belongs to
	 * @param board the Board the Necromancer is on
	 */
	
	public Necromancer(boolean team, Board board) {
		super(3, 5, 4, 4, team, board, (team ? 0 : 7), 4, false);
		this.hasSpecial = true;
		this.faction = Faction.UNDEAD;
	}
	
	@Override
	public String getName() {
		return "The Necromancer";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		/*Checks that the piece is one of the 4 adjacent squares and
		 *that it contains an allied Skeleton.*/
		if (Math.abs(row - this.getRow()) + 
				Math.abs(column - this.getColumn()) == 1) {
			Piece piece = this.getBoard().getPiece(row, column);
			if (piece != null && piece instanceof Skeleton && 
					piece.getTeam() == this.getTeam()) {
				if (this.getBoard().containsDeadOfficer(!this.getTeam())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		String usedPiece = this.getBoard().enhancePawn(row, column);
		if (usedPiece != null) {
			this.getBoard().inform(this.getName() + " used " + 
				this.getSpecialAbility() + ".\nSkeleton in " + 
				Board.COLUMNS[column] + (row+1) + " was transformed into " +
				this.getBoard().getPiece(row, column).getName() + ".\n");
			this.getBoard().addMove(this.toString() + this.getColumn() + 
				this.getRow() + "s" + column + row + usedPiece);
			return true;
		}
		return false;
	}
	
	@Override
	public String getSpecialAbility() {
		return "Consume Soul";
	}

}
