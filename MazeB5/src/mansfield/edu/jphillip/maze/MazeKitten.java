package mansfield.edu.jphillip.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a kitten.
 * 
 * Kitten image is located on a public open source domain as a clip art image.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by Justin Kruger
 */

public class MazeKitten extends MazeShape {
	BufferedImage image = null;
	static String name = "kitten";
	static final int size = 88;

	public MazeKitten(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazePuppy.class
					.getResource("/image/kitten.png");
			image = ImageIO.read(imageURL);
		} catch (IOException ioe) {
		}
	}

	public int getSize() {
		return size;
	}

	public BufferedImage getImage() {
		return image;
	}
}
