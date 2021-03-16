package game;

/**
 * A class depicting the Sorcerer piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class Sorcerer extends Knight {

	/**
	 * Creates a new Sorcerer-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Sorcerer(int hp, boolean team,
			Board board, int row, int column, boolean hasMoved) {
		super(5, 2, hp, 4, team, board, row, column, hasMoved);
		this.faction = Faction.ARCANE;
		this.hasSpecial = true;
	}
	
	@Override
	public String getName() {
		return "Sorcerer";
	}
	
	@Override
	public boolean canUseSpecialTo(int row, int column) {
		return this.canAttackTo(row, column);
	}
	
	@Override
	public boolean useSpecialTo(int row, int column) {
		super.useSpecialTo(row, column);
		this.attack(this.getBoard().getPiece(row, column));
		return true;
	}
	
	@Override
	public String getSpecialAbility() {
		return "Arcane Attack";
	}

}
