package mansfield.edu.jphillip.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as an 8bit viking character
 * 
 * Viking image originally found on public domain
 * http://media.photobucket.com/image/8+bit+knight+/Punisher013/8%2520bit%2520originals/Knight-1.jpg
 * I have edited the image personally, and the derived product is what is displayed in the program
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by team 4
 */
public class MazeViking extends MazeShape {
	BufferedImage image = null;
	static String name = "viking";
	static final int size = 90;

	public MazeViking(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeLink.class
					.getResource("/image/viking_png.png");
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
