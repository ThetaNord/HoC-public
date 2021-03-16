package game;

/**
 * A class depicting the Earth Elemental piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class EarthElemental extends Rook {

	/**
	 * Creates a new EarthElemental-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public EarthElemental(int hp, boolean team,
			Board board, int row, int column, boolean hasMoved) {
		super(2, 6, hp, 5, team, board, row, column, hasMoved);
		this.faction = Faction.ARCANE;
	}
	
	@Override
	public String getName() {
		return "Earth Elemental";
	}

}
