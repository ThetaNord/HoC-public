package ui;

import game.GraphicConstants;
import game.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class the objects of which are used to display information on the
 * currently active piece
 * 
 * @author Tuure Saloheimo
 *
 */

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The label which displays the name of the active piece.
	 */
	private JLabel nameLabel;
	/**
	 * The JButton displaying the picture of the active piece
	 */
	private JButton piecePicture;
	/**
	 * The Image used for displaying health
	 */
	private Image healthPic;
	/**
	 * The JLabel used for displaying the current and full hit points of the
	 * active piece.
	 */
	private JLabel health;
	/**
	 * The Image used for displaying attack
	 */
	private Image attackPic;
	/**
	 * The JLabel used for displaying the attack power of the active piece.
	 */
	private JLabel attack;
	/**
	 * The Image used for displaying defense
	 */
	private Image defensePic;
	/**
	 * The JLabel used for displaying the defense value of the active piece.
	 */
	private JLabel defense;
	
	/**
	 * The JButton used for toggling the display of squares valid for
	 * the active piece's special ability's targets.
	 */
	private JButton special;

	/**
	 * The UIBoard object the InfoPanel is associated with
	 */
	private UIBoard uiBoard;

	/**
	 * Creates a new InfoPanel object
	 * 
	 * @param uiBoard the UIBoard object to be associated with the InfoPanel
	 */
	public InfoPanel(UIBoard uiBoard) {
		this.uiBoard = uiBoard;
		this.setMinimumSize(new Dimension(150, 125));
		this.setPreferredSize(this.getMinimumSize());
		try {
			this.loadImages();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error when loading pics.");
		}
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.nameLabel = new JLabel("No Piece", JLabel.LEFT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 8;
		gbc.ipady = 8;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 3;
		this.add(this.nameLabel, gbc);
		gbc.gridy = 4;
		gbc.gridwidth = 16;
		gbc.gridheight = 16;
		this.piecePicture = new JButton();
		this.piecePicture.setSize(new Dimension(64,64));
		this.piecePicture.setPreferredSize(this.piecePicture.getSize());
		this.piecePicture.setBackground(new Color(25,25,25,0));
		this.add(this.piecePicture, gbc);
		gbc.gridx = 17;
		gbc.gridy = 6;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		this.add(new JLabel(new ImageIcon(this.healthPic)), gbc);
		gbc.gridy = 13;
		this.add(new JLabel(new ImageIcon(this.attackPic)), gbc);
		gbc.gridy = 16;
		this.add(new JLabel(new ImageIcon(this.defensePic)), gbc);
		gbc.gridx = 22;
		gbc.gridy = 6;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.health = new JLabel("0/0");
		this.add(this.health, gbc);
		gbc.gridy = 13;
		this.attack = new JLabel("0");
		this.add(this.attack, gbc);
		gbc.gridy = 16;
		this.defense = new JLabel("0");
		this.add(this.defense, gbc);
		gbc.gridx = 5;
		gbc.gridy = 21;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 10;
		this.special = new JButton("Toggle Special");
		this.special.setActionCommand("special");
		this.special.setMnemonic(KeyEvent.VK_S);
		this.special.addActionListener(this.uiBoard);
		this.special.setEnabled(false);
		this.add(special, gbc);
	}

	/**
	 * A method that loads the Images required specifically for the InfoPanel
	 * @throws IOException if the Images aren't succesfully loaded
	 */
	private void loadImages() throws IOException {
		this.healthPic = ImageIO.read(new File("graphics/icons/heart.png"));
		this.attackPic = ImageIO.read(new File("graphics/icons/sword.png"));
		this.defensePic = ImageIO.read(new File("graphics/icons/shield.png"));
	}

	/**
	 * A method to be prompted when the information displayed needs to be 
	 * updated.
	 */
	public void updateInfo() {
		Piece piece = this.uiBoard.getActivePiece();
		if (piece != null) {
			this.nameLabel.setText(piece.getName());
			
			this.piecePicture.setIcon(
				new ImageIcon(GraphicConstants.getImage(piece)));
			
			this.health.setText(piece.getHitPoints() + "/" + 
				piece.getFullHitPoints());

			this.attack.setText(((Integer)piece.getAttack()).toString());
			if (piece.getAttack() > piece.getOrigAttack()) {
				this.attack.setForeground(Color.green);
			}
			else if (piece.getAttack() < piece.getOrigAttack()) {
				this.attack.setForeground(Color.red);
			}
			else {
				this.attack.setForeground(Color.black);
			}

			this.defense.setText(((Integer)piece.getDefense()).toString());
			if (piece.getDefense() > piece.getOrigDefense()) {
				this.defense.setForeground(Color.green);
			}
			else if (piece.getDefense() < piece.getOrigDefense()) {
				this.defense.setForeground(Color.red);
			}
			else {
				this.defense.setForeground(Color.black);
			}
			if (piece.hasSpecial()) {
				this.special.setEnabled(true);
			}
			else {
				this.special.setEnabled(false);
			}
		}
		else this.init();
		this.repaint();
	}
	
	/**
	 * A method used to initialize the InfoPanel's displayed information
	 * when the active piece is null.
	 */
	private void init() {
		this.nameLabel.setText("No Piece");
		this.piecePicture.setIcon(null);
		this.health.setText("0/0");
		this.attack.setText("0");
		this.attack.setForeground(Color.black);
		this.defense.setText("0");
		this.defense.setForeground(Color.black);
		this.special.setEnabled(false);
		this.repaint();
	}

}
