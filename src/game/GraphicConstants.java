package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A class that loads all the Images used for the Pieces and provides methods
 * for staticly accessing the right Image.
 * 
 * @author Tuure Saloheimo
 *
 */

public abstract class GraphicConstants {

	/**
	 * The Image to be used if no other is found.
	 */
	private static Image defaultImage;

	/**
	 * A List of Images used for regular white chess pieces.
	 */
	private static List<Image> white;
	
	/**
	 * A List of Images used for regular black chess pieces.
	 */
	private static List<Image> black;
	
	/**
	 * A List of Images used for the pieces of the Undead Faction
	 */
	private static List<Image> undead;
	
	/**
	 * A List of Images used for the pieces of the Valor Faction
	 */
	private static List<Image> valor;
	
	/**
	 * A List of Images used for the pieces of the Arcane Faction
	 */
	private static List<Image> arcane;

	static {

		white = new ArrayList<Image>();
		black = new ArrayList<Image>();
		undead = new ArrayList<Image>();
		valor = new ArrayList<Image>();
		arcane = new ArrayList<Image>();

		try {
			defaultImage = ImageIO.read(new File("graphics/default/pawn.png"));
			//defaultImage = ImageIO.read(GraphicConstants.class.getResourceAsStream("graphics/default/pawn.png"));
			//Load White Images
			white.add(ImageIO.read(new File("graphics/default/pawn.png")));
			white.add(ImageIO.read(new File("graphics/default/rook.png")));
			white.add(ImageIO.read(new File("graphics/default/knight.png")));
			white.add(ImageIO.read(new File("graphics/default/bishop.png")));
			white.add(ImageIO.read(new File("graphics/default/queen.png")));
			white.add(ImageIO.read(new File("graphics/default/king.png")));
			//Load Black Images
			black.add(ImageIO.read(new File("graphics/default/blackpawn.png")));
			black.add(ImageIO.read(new File("graphics/default/blackrook.png")));
			black.add(ImageIO.read(new File("graphics/default/blackknight.png")));
			black.add(ImageIO.read(new File("graphics/default/blackbishop.png")));
			black.add(ImageIO.read(new File("graphics/default/blackqueen.png")));
			black.add(ImageIO.read(new File("graphics/default/blackking.png")));
			//Load Undead Images
			undead.add(ImageIO.read(new File("graphics/undead/skeleton.png")));
			undead.add(ImageIO.read(new File("graphics/undead/crypt.png")));
			undead.add(ImageIO.read(new File("graphics/undead/vampire.png")));
			undead.add(ImageIO.read(new File("graphics/undead/lich.png")));
			undead.add(ImageIO.read(new File("graphics/undead/wraith.png")));
			undead.add(ImageIO.read(new File("graphics/undead/necromancer.png")));
			//Load Valor Images
			valor.add(ImageIO.read(new File("graphics/valor/footman.png")));
			valor.add(ImageIO.read(new File("graphics/valor/tower.png")));
			valor.add(ImageIO.read(new File("graphics/valor/cavalier.png")));
			valor.add(ImageIO.read(new File("graphics/valor/priest.png")));
			valor.add(ImageIO.read(new File("graphics/valor/angel.png")));
			valor.add(ImageIO.read(new File("graphics/valor/theking.png")));
			//Load Arcane Images
			arcane.add(ImageIO.read(new File("graphics/arcane/mananode.png")));
			arcane.add(ImageIO.read(new File("graphics/arcane/earthelemental.png")));
			arcane.add(ImageIO.read(new File("graphics/arcane/sorcerer.png")));
			arcane.add(ImageIO.read(new File("graphics/arcane/airelemental.png")));
			arcane.add(ImageIO.read(new File("graphics/arcane/arcaneelemental.png")));
			arcane.add(ImageIO.read(new File("graphics/arcane/archmage.png")));
		}
		catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, 
					"An error occurred while loading graphics", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * A static method that returns the Image associated with the Piece given
	 * as parametre.
	 * 
	 * @param piece the Piece the Image of which is required
	 * @return the Image associated with the Piece, or the default Image if
	 * none is found.
	 */
	public static Image getImage(Piece piece) {
		if (piece.getFaction() == Faction.UNDEAD) {
			if (piece instanceof Skeleton) {
				return undead.get(0);
			}
			if (piece instanceof Crypt) {
				return undead.get(1);
			}
			if (piece instanceof Vampire) {
				return undead.get(2);
			}
			if (piece instanceof Lich) {
				return undead.get(3);
			}
			if (piece instanceof Wraith) {
				return undead.get(4);
			}
			if (piece instanceof Necromancer) {
				return undead.get(5);
			}
		}
		else if (piece.getFaction() == Faction.VALOR) {
			if (piece instanceof Footman) {
				return valor.get(0);
			}
			if (piece instanceof Tower) {
				return valor.get(1);
			}
			if (piece instanceof Cavalier) {
				return valor.get(2);
			}
			if (piece instanceof Priest) {
				return valor.get(3);
			}
			if (piece instanceof Archangel) {
				return valor.get(4);
			}
			if (piece instanceof TheKing) {
				return valor.get(5);
			}
		}
		else if (piece.getFaction() == Faction.ARCANE) {
			if (piece instanceof ManaNode) {
				return arcane.get(0);
			}
			if (piece instanceof EarthElemental) {
				return arcane.get(1);
			}
			if (piece instanceof Sorcerer) {
				return arcane.get(2);
			}
			if (piece instanceof AirElemental) {
				return arcane.get(3);
			}
			if (piece instanceof ArcaneElemental) {
				return arcane.get(4);
			}
			if (piece instanceof Archmage) {
				return arcane.get(5);
			}
		}
		else if (piece.getFaction() == Faction.NONE) {
			if (piece instanceof Pawn) {
				if (piece.getTeam()) {
					return white.get(0);
				}
				else {
					return black.get(0);
				}
			}
			if (piece instanceof Rook) {
				if (piece.getTeam()) {
					return white.get(1);
				}
				else {
					return black.get(1);
				}
			}
			if (piece instanceof Knight) {
				if (piece.getTeam()) {
					return white.get(2);
				}
				else {
					return black.get(2);
				}
			}
			if (piece instanceof Bishop) {
				if (piece.getTeam()) {
					return white.get(3);
				}
				else {
					return black.get(3);
				}
			}
			if (piece instanceof Queen) {
				if (piece.getTeam()) {
					return white.get(4);
				}
				else {
					return black.get(4);
				}
			}
			if (piece instanceof King) {
				if (piece.getTeam()) {
					return white.get(5);
				}
				else {
					return black.get(5);
				}
			}
		}
		return defaultImage;
	}

}
