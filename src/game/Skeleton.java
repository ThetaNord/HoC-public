package game;

/**
 * A class depicting the Skeleton piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class Skeleton extends Pawn {

	/**
	 * Creates a new Skeleton-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Skeleton(int hp, boolean team,
			Board board, int row, int column, boolean hasMoved) {
		super(2, 2, hp, 2, team, board, row, column, hasMoved);
		this.faction = Faction.UNDEAD;
	}
	
	@Override
	public String getName() {
		return "Skeleton";
	}

	@Override
	public int getDamage(int attack) {
		int damage = Piece.getInitialDamage(attack, this.getDefense());
		if (damage > 1) {
			/*Checks whether there is an allied Lich in the surrounding
			 *eight squares.*/
			lichCheck:
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						Piece piece = this.getBoard().getPiece(this.getRow()+i, this.getColumn()+j);
						if (piece instanceof Lich && piece.getTeam() == this.getTeam()) {
							//If yes, halves the inflicted damage (rounded up).
							damage = (damage+1)/2;
							break lichCheck;
						}
					}
				}
		}
		if (damage > this.getHitPoints()) {
			return this.getHitPoints();
		}
		return damage;
	}

}
