package mansfield.edu.jphillip.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * MVC View: displays a panel that draws the maze and player pieces. 
 * Called from AView.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by team 4
 */
public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 4417547627470770942L;
	private MazeBoard board;
	private MazeShape human;
	private MazeRobot robot;
	private int mazeSquareSize;
	

	/**
	 * The constructor sets the size and color of the panel.
	 */
	public DrawingPanel() {
		setPreferredSize(new Dimension(MazeApp.MAZEWIDTH,
				MazeApp.MAZEHEIGHT));
		setBackground(Color.BLACK);
	}

	public int getMazeSquareSize() { return mazeSquareSize; }
	/**
	 * Called to sync the main objects should the controller change them.
	 * 
	 * @param board
	 * @param human
	 * @param robot
	 */
	public void setup(MazeBoard board, MazeShape human,
			MazeRobot robot) {
		this.board = board;
		this.human = human;
		this.robot = robot;
		calcMazeSquareSize();
	}

	/**
	 * Repaints the drawing panel.
	 */
	public void refresh() {
		repaint();
	}

	/**
	 * Draws the maze board and the players pieces.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if ( !AController.getGameOver() == true) {
			drawBoard(g);
			drawPlayer(robot, g);
			drawPlayer(human, g);
		}
		
	}

	/**
	 * Calculates the size to draw each maze square given the display size 
	 * and the maze size.
	 */
	private void calcMazeSquareSize() {
		int nomWidth = MazeApp.MAZEWIDTH / (board.getEndCol() - 1);
		int nomHeight = MazeApp.MAZEHEIGHT / (board.getEndRow() - 1);
		mazeSquareSize = nomWidth < nomHeight ? nomWidth : nomHeight;
	}
	/**
	 * Calculates the size to draw each maze square given the display size 
	 * and the maze size.
	 */
	

	/**
	 * Determines how to color a given maze square.
	 */
	private Color getMazeColor(char c) {
		if (c == '-' || c == 's')
			return (new Color(200, 200, 255));
		// end position color
		else if (c == 'e')
			return (new Color(0, 255, 0));
		// castle wall position color
		else if (c == 'c')
			return (new Color(238,223,204));
		// grass position color
		else if (c == 'g')
			return (new Color(0,139,0));
		// green grass for patrol 1
		else if (c == '1')
			return (new Color(0,139,0));
		// water position color
		else if (c == 'w')
			return (new Color(28,134,238));
		// bridge position color
		else if (c == 'b')
			return (new Color(156,102,31));
		// wine cellar floor color for patrol 2
		else if (c == '2')
			return (new Color(255,130,71));
		// wine cellar floor color
		else if (c == 'f')
			return (new Color(255,130,71));
		// red hallway floor
		else if (c == 'r')
			return (new Color(238,44,44));
		// red hallway floor for patrol
		else if (c == '3')
			return (new Color(238,44,44));
		// wine cellar box
		else if (c == 'v')
			return (new Color(156,102,31));
		// hallway wall grayish black / dining room "seat"
		else if (c == 'q')
			return (new Color(81,81,81));
		// dining room floor
		else if (c == 'u')
			return (new Color(148,0,211));
		// dining room floor "patrol"
		else if (c == '4')
			return (new Color(148,0,211));
		// dining room table
		else if (c == 't')
			return (new Color(138,54,15));
		// hallway level 5 floor "patrol"
		else if (c == '5')
			return (new Color(238,44,44));
		// sidewalk level 6 floor
		else if (c == 'd')
			return (new Color(205,197,191));
		// sidewalk level 6 floor "patrol"
		else if (c == '6')
			return (new Color(205,197,191));
		// bushes level 6
		else if (c == 'a')
			return (new Color(48,128,20));
		// level 9 patrol, will be on red carpet
		else if (c == '9')
			return (new Color(238,44,44));
		// golden carpet for throne
		else if (c == 'h')
			return (new Color(255,193,37));
		else if (c == 'x')
			return (new Color(20, 20, 200));
		else if (c == '7')
			return ( Color.yellow);
		return new Color(0, 0, 0);
	}

	/**
	 * Draws the maze board on the screen.
	 * 
	 * @param g
	 */
	public void drawBoard(Graphics g) {
		for (int row = 1; row < board.getEndRow(); row++) {
			for (int col = 1; col < board.getEndCol(); col++) {
				g.setColor(getMazeColor(board.getChar(row, col)));
				int x1 = (col - 1) * mazeSquareSize;
				int x2 = (row - 1) * mazeSquareSize;
				g.fillRect(x1, x2, mazeSquareSize, mazeSquareSize);
			}
		}
	}

	/**
	 * Draws a message when there is a winner.
	 * 
	 * @param g
	 */
	public void drawWinner() {
		Graphics g = this.getGraphics();
		Font font = new Font("Arial", Font.BOLD, 100);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("You Survived!", 150, 300);
		g.drawString("Score: "+ControlPanel.counterOutputTF.getText(), 270, 450);
	}

	/**
	 * Draws a player's icon on the screen.
	 * 
	 * @param ms
	 * @param g
	 */
	public void drawPlayer(MazeShape ms, Graphics g) {
		String name = ms.getName();
		g.setColor(ms.getColor());
		int startX = (ms.getCol() - 1) * mazeSquareSize;
		int startY = (ms.getRow() - 1) * mazeSquareSize;
		int size = mazeSquareSize;

		if (name.equals("circle")) {
			g.fillOval(startX, startY, size, size);
		} else if (name.equals("triangle")) {
			int x2 = startX + mazeSquareSize / 2;
			int x3 = startX + mazeSquareSize;
			int y2 = startY + mazeSquareSize;
			int[] xPoints = { x2, x3, startX, x2 };
			int[] yPoints = { startY, y2, y2, startY };
			int nPoints = 4;
			g.fillPolygon(xPoints, yPoints, nPoints);
		} else if (name.equals("puppy")) {
			MazePuppy mp = (MazePuppy) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("link")) {
			MazeLink mp = (MazeLink) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}	
		else if (name.equals("mudkip")) {
			MazeMudkip mp = (MazeMudkip) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} else if (name.equals("batman")) {
			MazeBatman mp = (MazeBatman) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} else if (name.equals("megaman")) {
			MazeMegaman mp = (MazeMegaman) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} else if (name.equals("mario")) {
			MazeMario mp = (MazeMario) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} else if (name.equals("viking")) {
			MazeViking mp = (MazeViking) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} else if (name.equals("kitten")) {
			MazeKitten mp = (MazeKitten) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} else if (name.equals("robot")) {
			MazeRobot mr = (MazeRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
	}
}
