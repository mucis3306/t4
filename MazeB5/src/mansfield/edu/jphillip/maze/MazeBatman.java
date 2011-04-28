package mansfield.edu.jphillip.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as an image of Batman in 8bit.
 * 
 * Batman image originally found on public domain
 * http://www.redbubble.com/products/configure/6141817-sticker
 * I have edited the image personally, and the derived product is what is displayed in the program
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by Justin Kruger
 */
public class MazeBatman extends MazeShape {
	BufferedImage image = null;
	static String name = "batman";
	static final int size = 90;

	public MazeBatman(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeLink.class
					.getResource("/image/batman_png.png");
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
