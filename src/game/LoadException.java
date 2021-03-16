package game;

/**
 * An exception thrown when loading a saved game proves unsuccesful.
 * 
 * @author Tuure Saloheimo
 *
 */

public class LoadException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new LoadException with the default message.
	 */
	public LoadException() {
		super("An error occurred while loading a saved game.");
	}

	/**
	 * Creates a new LoadException with the default message followed by the
	 * String given as parametre.
	 * 
	 * @param message the String to be added to the end of the message.
	 */
	
	public LoadException(String message) {
		super("An error occurred while loading a saved game:\n\n" + message);
	}

}
