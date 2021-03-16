package game;

public class Archmage extends King {

	/**
	 * Creates a new Archmage-object accordingly to the given parameters. 
	 * 
	 * @param team The team the object belongs to
	 * @param board The board the piece is, or will be positioned on
	 */
	
	public Archmage(boolean team, Board board) {
		super(3, 4, 5, 5, team, board, (team ? 0 : 7), 4, false);
		this.faction = Faction.ARCANE;
		this.hasSpecial = true;
	}
	
	@Override
	public String getName() {
		return "The Archmage";
	}

	@Override
	public boolean canUseSpecialTo(int row, int column) {
		//Checks whether the piece in the target square is a Mana Node
		Piece piece = this.getBoard().getPiece(row, column);
		if (piece instanceof ManaNode) {
			int dr = 0;
			int dc = 0;
			if (row < this.getRow()) dr = +1;
			else if (row > this.getRow()) dr = -1;
			if (column < this.getColumn()) dc = +1;
			else if (column > this.getColumn()) dc = -1;
			int r = row;
			int c = column;
			//If it is checks whether the piece is on the same row or column...
			if (row == this.getRow() || column == this.getColumn()) {
				r += dr;
				c += dc;
				/*If it is, checks that there are no other pieces between the 
				 *Mana Node and the Archmage.*/
				while (c != this.getColumn() || r != this.getRow()) {
					if (this.getBoard().getPiece(r, c) != null) {
						return false;
					}
					r += dr;
					c += dc;
				}
				return true;
			}
			//...or on the correct diagonals.
			else if (Math.abs(row - this.getRow()) == 
					Math.abs(column - this.getColumn())) {
				r += dr;
				c += dc;
				/*If it is, checks that there are no other pieces between the 
				 *Mana Node and the Archmage.*/
				while (r != this.getRow() && c != this.getColumn()) {
					if (this.getBoard().getPiece(r, c) != null) {
						return false;
					}
					r += dr;
					c += dc;
				}
				return true;
			}
			
		}
		return false;
	}
	
	@Override
	public boolean useSpecialTo(int row, int column) {
		////Checks whether the piece in the target square is a Mana Node
		Piece piece = this.getBoard().getPiece(row, column);
		if (piece instanceof ManaNode) {
			//If it is, tells the Mana Node to explode().
			ManaNode node = (ManaNode)piece;
			super.useSpecialTo(row, column);
			node.explode();
			return true;
		}
		return false;
	}
	
	@Override
	public String getSpecialAbility() {
		return "Fireball Spell";
	}
	
}
