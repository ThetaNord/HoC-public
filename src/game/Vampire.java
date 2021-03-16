package game;

/**
 * A class depicting the Vampire piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class Vampire extends Knight {

	/**
	 * Creates a new Vampire-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Vampire(int hp, boolean team,
			Board board, int row, int column, boolean hasMoved) {
		super(4, 2, hp, 4, team, board, row, column, hasMoved);
		this.faction = Faction.UNDEAD;
	}
	
	@Override
	public String getName() {
		return "Vampire";
	}

	@Override
	public void attack(Piece piece) {
		if (piece != null) {
			int damage = piece.damage(this);
			//Restores hp equal to half of the inflicted damage (rounded up).
			this.heal(((damage+1)/2));
		}
	}
	
}
