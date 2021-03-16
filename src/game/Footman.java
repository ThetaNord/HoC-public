package game;

/**
 * A class depicting the Footman piece.
 * 
 * @author Tuure Saloheimo
 *
 */

public class Footman extends Pawn {

	/**
	 * Creates a new Footman-object accordingly to the given parameters.
	 * 
	 * @param hp The hitpoints the object has upon creation
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 * @param row The row where the piece is located
	 * @param column The column where the piece is located
	 * @param hasMoved The boolean value of whether the piece has been 
	 * previously moved or not
	 */
	
	public Footman(int hp, boolean team, Board board, int row, int column, 
			boolean hasMoved) {
		super(2, 2, hp, 3, team, board, row, column, hasMoved);
		this.faction = Faction.VALOR;
	}
	
	@Override
	public String getName() {
		return "Footman";
	}

	@Override
	public int getDefense() {
		int def = super.getDefense();
		/*Checks whether The King is in one of the 8 surrounding squares.
		 *If yes, defense + 1*/
		kingCheck:
		{
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					Piece piece = this.getBoard().getPiece(this.getRow()+i, 
							this.getColumn()+j);
					if (piece instanceof TheKing && piece.getTeam() == 
							this.getTeam()) {
						def += 1;
						break kingCheck;
					}
				}
			}
		}
		return def;
	}

	@Override
	public int getAttack() {
		int atk = super.getAttack();
		int dr = -1;
		int dc = 0;
		/*Checks whether there is an allied Tower in one of the 4 adjacent 
		 *squares. If yes, attack + 1*/
		for (int i = 0; i < 4; i++) {
			Piece piece = this.getBoard().getPiece(this.getRow()+dr, this.getColumn()+dc);
			if (piece != null && piece.getTeam() == this.getTeam()) {
				if (piece instanceof Tower) {
					atk += 1;
				}
			}
			switch (i) {
			case 0: dr = +1;
			case 1: dr = 0; dc = -1;
			case 2: dc = +1;
			}
		}
		return atk;
	}

}
