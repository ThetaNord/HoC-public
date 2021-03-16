package game;

/**
 * A class that depicts The King piece. (The King-extender of the
 * Valor Faction)
 * 
 * @author Tuure Saloheimo
 *
 */

public class TheKing extends King {

	/**
	 * Creates a new TheKing-object accordingöy to the given parametres.
	 * 
	 * @param team the team The King belongs to
	 * @param board the Board The King is located on
	 */
	
	public TheKing(boolean team, Board board) {
		super(4, 3, 5, 5, team, board, (team ? 0 : 7), 4, false);
		this.faction = Faction.VALOR;
	}
	
	@Override
	public String getName() {
		return "The King";
	}

}
