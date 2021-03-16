package game;

/**
 * A class depicting the Priest piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Priest extends Bishop implements SpecialAbility {

	/**
	 * Creates a new Priest-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Priest(int hp, boolean team, Board board, int row, int column, 
			boolean hasMoved) {
		super(2, 4, hp, 5, team, board, row, column, hasMoved);
		this.hasSpecial = true;
		this.faction = Faction.VALOR;
	}

	@Override
	public String getName() {
		return "Priest";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		//The only valid square is the Priest's own location
		if (row == this.getRow() && column == this.getColumn()) {
			int dr = -1;
			int dc = 0;
			/*The special ability can be used only if there is at least one
			 *Footman or Cavalier in the adjacent four squares, which has lost
			 *hit points.*/
			for (int i = 0; i < 4; i++) {
				Piece piece = this.getBoard().getPiece(this.getRow()+dr, 
					this.getColumn()+dc);
				if (piece != null && piece.getTeam() == this.getTeam()) {
					if (piece instanceof Footman || piece instanceof Cavalier) {
						if (piece.getHitPoints() < piece.getFullHitPoints()) {
							return true;
						}
					}
				}
				switch (i) {
				case 0: dr = +1; break;
				case 1: dr = 0; dc = -1; break;
				case 2: dc = +1; break;
				}
			}
		}
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		super.useSpecialTo(row, column);
		int dr = -1;
		int dc = 0;
		/*Restores one hit point to all Footmen and Cavaliers in the adjacent
		 *four squares.*/
		for (int i = 0; i < 4; i++) {
			Piece piece = this.getBoard().getPiece(this.getRow()+dr, 
					this.getColumn()+dc);
			if (piece != null && piece.getTeam() == this.getTeam()) {
				if (piece instanceof Footman || piece instanceof Cavalier) {
					piece.heal(1);
				}
			}
			switch (i) {
			case 0: dr = +1; break;
			case 1: dr = 0; dc = -1; break;
			case 2: dc = +1; break;
			}
		}
		return true;
	}
	
	@Override
	public String getSpecialAbility() {
		return "Healing Prayer";
	}

}
