package game;

/**
 * An exception thrown when saving the game proves unsuccesful.
 * 
 * @author Tuure Saloheimo
 *
 */

public class SaveException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new SaveException with the default message.
	 */
	public SaveException() {
		super("An error occurred while loading the game.");
	}
	
}
