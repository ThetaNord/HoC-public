
package game;

/**
 * An abstract class providing with the default functions of all game pieces.
 * 
 * @author Tuure Saloheimo
 * 
 */

public abstract class Piece implements SpecialAbility {

	/**
	 * The attack power of the Piece
	 */
	private int attack;
	/**
	 * The original attack power of the piece, should it somehow be altered
	 */
	private int origAttack;

	/**
	 * The defense power of the Piece
	 */
	private int defense;
	/**
	 * The original defense power of the Piece, should it somehow be altered
	 */
	private int origDefense;

	/**
	 * The current hit points of the Piece
	 */
	private int hitPoints;
	/**
	 * The maximum hit points of the Piece
	 */
	private int fullHitPoints;

	/**
	 * Tells whether the Piece has moved or not
	 */
	private boolean hasMoved;
	/**
	 * Tells whether the Piece can move or not
	 */
	private boolean canMove;

	/**
	 * The row of the Piece's location
	 */
	private int row;
	/**
	 * The column of the Piece's location
	 */
	private int column;

	/**
	 * The Board the Piece is located on 
	 */
	private Board board;
	/**
	 * The team the Piece belongs to
	 */
	private boolean team;
	/**
	 * The Faction the Piece is affiliated with
	 */
	protected Faction faction;

	/**
	 * Whether the Piece has a special ability or not
	 */
	protected boolean hasSpecial;

	/**
	 * Sets the values of the Piece's attributes accordingly to the given 
	 * parametres.
	 * 
	 * @param atk the attack power of the Piece
	 * @param def the defense value of the Piece
	 * @param hp the current hit points of the Piece
	 * @param fhp the maximum hit points of the Piece
	 * @param team the team the Piece belongs to
	 * @param board the Board the Piece is located on 
	 * @param row the row of the Piece's location
	 * @param column the column of the Piece's location
	 * @param hasMoved whether the Piece has moved or not
	 */
	
	public Piece(int atk, int def, int hp, int fhp, boolean team, Board board, 
			int row, int column, boolean hasMoved) {
		this.attack = atk;
		this.origAttack = atk;
		this.defense = def;
		this.origDefense = def;
		//Makes sure that the maximum hit points of the Piece are at least 1 
		if (fhp < 1) fhp = 1;
		this.fullHitPoints = fhp;
		/*Makes sure that the current hit points aren't larger than the
		 *maximum hit points and are over 0.*/
		if (hp > fhp) hp = fhp;
		else if (hp < 1) hp = 1;
		this.hitPoints = hp;
		this.team = team;
		this.faction = Faction.NONE;
		this.board = board;
		this.row = row;
		this.column = column;
		this.hasMoved = hasMoved;
		this.canMove = true;
		this.hasSpecial = false;
	}

	/**
	 * A method that returns the attack power of the Piece
	 * @return the attack power of the Piece
	 */
	public int getAttack() {
		return this.attack;
	}

	/**
	 * A method that returns the original attack of the Piece.
	 * @return the original attack value of the Piece
	 */
	public final int getOrigAttack() {
		return this.origAttack;
	}

	/**
	 * A method that returns the defense value of the Piece
	 * @return the defense value of the Piece
	 */
	public int getDefense() {
		return this.defense;
	}

	/**
	 * A method that returns the original defense value of the Piece
	 * @return the original defense value of the Piece
	 */
	public final int getOrigDefense() {
		return this.origDefense;
	}

	/**
	 * A method that returns the current hit points of the Piece
	 * @return the current hit points of the Piece
	 */
	public int getHitPoints() {
		return this.hitPoints;
	}

	/**
	 * A method that returns the maximum hit points of the Piece
	 * @return the maximum hit points of the Piece
	 */
	public final int getFullHitPoints() {
		return this.fullHitPoints;
	}

	/**
	 * A method that returns the row of the Piece's current location.
	 * @return the row of the Piece's current location
	 */
	public final int getRow() {
		return this.row;
	}

	/**
	 * A method that returns the column of the Piece's current location.
	 * @return the column of the Piece's current location
	 */
	public final int getColumn() {
		return this.column;
	}

	/**
	 * A method that returns the Board the Piece is located on.
	 * @return the Board the Piece is located on
	 */
	public final Board getBoard() {
		return this.board;
	}

	/**
	 * A method that returns the team the Piece belongs to
	 * @return the team the Piece belongs to
	 */
	public final boolean getTeam() {
		return this.team;
	}

	/**
	 * A method that returns the Faction the Piece is associated with.
	 * @return the Faction the piece is associated with
	 */
	public final Faction getFaction() {
		return this.faction;
	}

	/**
	 * A method that returns the name of the Piece.
	 * @return the name of the Piece
	 */
	public String getName() {
		return "Piece";
	}

	/**
	 * A method that tells whether the Piece has moved or not
	 * @return true if the piece has moved, false if it has not
	 */
	public final boolean hasMoved() {
		return this.hasMoved;
	}

	/**
	 * A method used to change the value of the hasMoved-attribute froma false
	 * to true.
	 */
	public void wasMoved() {
		if (!this.hasMoved) {
			this.hasMoved = true;
		}
	}

	/**
	 * A method that tells whether the Piece can move or not.
	 * @return false if canMove is false or hit points are 0 (or lower), 
	 * true otherwise
	 */
	public boolean canMove() {
		if (!this.canMove || this.hitPoints < 1 ) {
			return false;
		}
		return true;
	}

	/**
	 * A method that tells whether the Piece has a special ability or not
	 * @return true if the Piece has a special ability, false otherwise
	 */
	public final boolean hasSpecial() {
		return this.hasSpecial;
	}

	/**
	 * A method used to damage the Piece accordingly to the attack power of
	 * the Piece given as parametre.
	 * 
	 * @param attacker the Piece attacking this Piece
	 * @return the amount of damage inflicted
	 */
	int damage(Piece attacker) {
		return this.damage(attacker.getAttack());
	}
	
	/**
	 * A method used to damage the Piece accordingly to the attack power given
	 * as parametre.
	 * 
	 * @param attack the attack power used to damage the Piece
	 * @return the amount of damage inflicted
	 */
	int damage(int attack) {
		int damage = this.getDamage(attack);
		/*Makes sure that the amount of damage inflicted is not larger
		 *than the Piece's current hit points*/
		if (damage > this.hitPoints) {
			damage = this.hitPoints;
		}
		this.hitPoints -= damage;
		//Informs the Board of the loss of hit points
		if (damage == 1) {
			this.board.inform(this.getName() + " lost 1 hit point.\n");
		}
		else {
			this.board.inform(this.getName() + " lost " + damage + " hit points.\n");
		}
		/*If the Piece is defeated, removes it from the Board and informs the
		 *Board of the event.*/
		if (this.getHitPoints() <= 0) {
			this.getBoard().movePiece(this, -1, -1);
			this.board.addDeadPiece(this);
			this.row = -1;
			this.column = -1;
			this.board.inform(this.getName() + " was defeated.\n");
		}
		return damage;
	}

	/**
	 * Restores hit points to the Piece according to the amount given as 
	 * parametre. The current hit points cannot rise over the maximum hit
	 * points using this method.
	 * 
	 * @param amount the amount of hit points to be healed (the maximum value).
	 */
	void heal(int amount) {
		/*Checks that the amount is larger than zero and that the current 
		 *hit points of the Piece are lower than the maximum hit points*/
		if (amount > 0 && this.hitPoints < this.fullHitPoints) {
			//Makes sure the amount won't rise the current hp over max hp.
			if (amount > (this.fullHitPoints - this.hitPoints)) {
				amount = this.fullHitPoints - this.hitPoints;
			}
			this.hitPoints += amount;
			//Informs the Board of the event
			if (amount == 1) {
				this.board.inform(this.getName()+ " restored 1 hit point.\n");
			}
			else this.board.inform(this.getName()+ " restored " + amount + 
					" hit points.\n");
		}
	}

	/**
	 * Moves the Piece to the location defined by the values given as 
	 * parametres.
	 * 
	 * @param row the row of the target square
	 * @param column the column of the target square
	 */
	public void moveTo(int row, int column) {
		this.wasMoved();
		this.getBoard().movePiece(this, row, column);
		this.row = row;
		this.column = column;
	}

	/**
	 * A method that tells whether the Piece in question can move to the square
	 * defined by the values given as parametres.
	 * 
	 * @param row the row of the target square
	 * @param column the column of the target square
	 * @return true if moving is possible, false if it isn't
	 */
	public abstract boolean canMoveTo(int row, int column);
	
	/**
	 * A method that tells whether the Piece can attack the square defined
	 * by the values given as parametres.
	 * 
	 * @param row the row of the target square
	 * @param column the column of the target sqaure
	 * @return true if attacking is possible, false otherwise
	 */
	public abstract boolean canAttackTo(int row, int column);

	/**
	 * A method used to attack the square defined by the values given as
	 * parametres.
	 * 
	 * @param row the row of the target square
	 * @param column the column of the target square
	 */
	public void attackTo(int row, int column) {
		if (this.canAttackTo(row, column)) {
			this.attack(this.getBoard().getPiece(row, column));
			this.wasMoved();
			//If the target of the attack was defeated...
			if (this.getBoard().getPiece(row, column) == null) {
				//...moves to the target square.
				this.moveTo(row, column);
			}
			//If it wasn't...
			else {
				/*...moves to the square next to the target Piece in the
				 *line of attack.*/
				int dr = 0;
				int dc = 0;
				if (row < this.getRow()) dr = +1;
				else if (row > this.getRow()) dr = -1;
				if (column < this.getColumn()) dc = +1;
				else if (column > this.getColumn()) dc = -1;
				this.moveTo(row+dr, column+dc);
			}
		}
	}

	/**
	 * A method used to attack the Piece given as parametre.
	 * 
	 * @param piece
	 */
	public void attack(Piece piece) {
		if (piece != null) {
			piece.damage(this);
		}
	}

	/**
	 * A method that returns the attack to be inflicted to this Piece when it's
	 * attacked with the attack power given as parametre.
	 * This method does not alter the Piece's hit points. However, it does
	 * make sure the damage is at most the amount of the Piece's current
	 * hit points.
	 * 
	 * @param attack the attack used to damage this Piece
	 * @return the amount of damage to be inflicted
	 */
	public int getDamage(int attack) {
		int damage = Piece.getInitialDamage(attack, this.getDefense());
		if (damage > this.getHitPoints()) {
			return this.getHitPoints();
		}
		return damage;
	}

	/**
	 * A method used to receive the initial damage with the attack and
	 * defense values given as parametres. The value can be larger than the
	 * defender's current hit points.
	 * 
	 * @param attack the attack of the attacker
	 * @param defense the defense of the defender
	 * @return the amount of damage to be initially inflicted
	 */
	public static final int getInitialDamage(int attack, int defense) {
		/*Initially the damage is calculated by this formula
		 *The value is intentionally not double in any point.*/
		int d = (attack-defense+2)/2;
		if (d < 0) {
			d = 0;
		}
		else if (d < 1) {
			d = 1;
		}
		return d;
	}
	
	@Override
	public boolean canUseSpecialTo(int row, int column) {
		return false;
	}

	@Override
	public boolean useSpecialTo(int row, int column) {
		/*This version only informs the Board that the special ability has
		 *been used and also adds it as a move. It's useful for most of
		 *the special abilities that also do something else*/
		this.getBoard().inform(this.getName() + " used " + 
				this.getSpecialAbility() + ".\n");
		this.getBoard().addMove(this.toString() + this.getColumn() + 
				this.getRow() + "s" + column + row);
		return true;
	}
	
	@Override
	public String getSpecialAbility() {
		return "none";
	}

}
