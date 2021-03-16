package game;

/**
 * An interface that contains the methods required for special abilities.
 * 
 * @author Tuure Saloheimo
 *
 */

public interface SpecialAbility {

	/**
	 * Returns the boolean value of whether the object that implements the 
	 * interface can use it's special ability to the coordinates defined
	 * by the given integer values.
	 * 
	 * @param row The row of the target square
	 * @param column The column of the target square
	 * @return whether the target is valid for the special ability or not
	 */
	public abstract boolean canUseSpecialTo(int row, int column);
	
	/**
	 * Uses the special ability on the coordinates defined by the parameter
	 * values. This method usually doesn't check whether the use is possible,
	 * at least not as thoroughly as the canUse-method. However, sometimes it
	 * does, or asks for user input, and the special ability may not be used
	 * succesfully. On these occasions the method will return the value false.
	 * 
	 * @param row The row of the target square
	 * @param column The column of the target square
	 * @return Whether the special ability was succesfully used.
	 */
	public abstract boolean useSpecialTo(int row, int column);
	
	/**
	 * This nethod returns the String-representation e.g. the name of
	 * the special ability.
	 * 
	 * @return the name of the special ability
	 */
	
	public abstract String getSpecialAbility();
	
}
