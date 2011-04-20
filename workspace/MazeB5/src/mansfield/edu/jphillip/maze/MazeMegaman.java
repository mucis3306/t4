package mansfield.edu.jphillip.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as Megaman.
 * 
 * Mario image originally found on public domain
 * http://www.comicvine.com/forums/battles/7/megaman-vs-batman/30049/
 * I have edited the image personally, and the derived product is what is displayed in the program
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by Justin Kruger
 */
public class MazeMegaman extends MazeShape {
	BufferedImage image = null;
	static String name = "megaman";
	static final int size = 90;

	public MazeMegaman(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeLink.class
					.getResource("/image/megaman_png.png");
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
