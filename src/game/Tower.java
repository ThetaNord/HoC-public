package game;

/**
 * A class depicting the Tower piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class Tower extends Rook {

	/**
	 * Creates a new Tower-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Tower(int hp, boolean team, Board board,
			int row, int column, boolean hasMoved) {
		super(4, 5, hp, 4, team, board, row, column, hasMoved);
		this.faction = Faction.VALOR;
	}
	
	@Override
	public String getName() {
		return "Tower";
	}

}
