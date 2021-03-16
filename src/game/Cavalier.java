package game;

/**
 * A class depicting the Cavalier piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Cavalier extends Knight {

	/**
	 * Creates a new Cavalier-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */

	public Cavalier(int hp, boolean team, Board board, int row, int column, boolean hasMoved) {
		super(4, 3, hp, 4, team, board, row, column, hasMoved);
		this.faction = Faction.VALOR;
	}

	@Override
	public String getName() {
		return "Cavalier";
	}

	@Override
	public int getDefense() {
		int def = super.getDefense();
		/*Checks whether The King is located in one of the surrounding eight
		 *squares. If yes, adds one point to defense.*/
		kingCheck: 
		{
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					Piece piece = this.getBoard().getPiece(this.getRow()+i, this.getColumn()+j);
					if (piece instanceof TheKing && piece.getTeam() == this.getTeam()) {
						def += 1;
						break kingCheck;
					}
				}
			}
		}
		return def;
	}

}
