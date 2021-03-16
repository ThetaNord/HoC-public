package game;

/**
 * A class depicting the Lich-piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Lich extends Bishop {

	/**
	 * Creates a new Lich-object accordingly to the given parametres.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Lich(int hp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(3, 4, hp, 4, team, board, row, column, hasMoved);
		this.faction = Faction.UNDEAD;
	}
	
	@Override
	public String getName() {
		return "Lich";
	}

}
