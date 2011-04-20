package mansfield.edu.jphillip.maze;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * MVC Model: generates a maze board as a 2D array of chars.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips Edited by Justin Kruger
 */
public class MazeBoard implements MazeBoardInterface {
	private char[][] mArray;
	private ArrayList<Dimension> startList, pstartList;
	private int startRow = 1;
	private int startCol = 1;
	private int pstartRow = 1;
	private int pstartCol = 1;
	private int endRow, endCol;
	private String version;
	private static Random gen = new Random();

	public MazeBoard(String fileName) {
		createBoard(fileName);
		endCol = mArray[0].length;
		endRow = mArray.length;
	}

	@Override
	/**
	 * Gathers the characters from the mazefile and generates the board
	 */
	public void createBoard(String fileName) {
		Scanner sc = new Scanner(
				MazeBoard.class.getResourceAsStream("/maze/" + fileName));
		version = sc.nextLine();
		endRow = sc.nextInt();
		sc.nextLine();
		endCol = sc.nextInt();
		sc.nextLine();
		System.out.println("r=" + endRow + "c" + endCol);
		mArray = new char[endRow][endCol];
		for (int r = 0; r < endRow; r++)
			mArray[r] = sc.nextLine().toCharArray();

		makeStartList();
		chooseRandomStart();
		
		makePatrol1StartList();
		choosePatrolRandomStart();
	}

	/**
	 * Generates the starting point 0f the user for the maze
	 */
	private void makeStartList() {
		startList = new ArrayList<Dimension>();
		for (int row = 1; row < endRow - 1; row++) {
			for (int col = 1; col < endCol - 1; col++) {
				if (mArray[row][col] == 's')
					startList.add(new Dimension(row, col));
			}
		}
		startList.trimToSize();
	}
	
	/**
	 * Generates the starting point 0f the A.I. for the maze
	 */
	private void makePatrol1StartList() {
		pstartList = new ArrayList<Dimension>();
		for (int row = 1; row < endRow - 1; row++) {
			for (int col = 1; col < endCol - 1; col++) {
				if (mArray[row][col] == '1' || mArray[row][col] == '2'|| mArray[row][col] == '3'
					|| mArray[row][col] == '4' || mArray[row][col] == '5'|| mArray[row][col] == '6'
					|| mArray[row][col] == '7' || mArray[row][col] == '8'|| mArray[row][col] == '9')
					pstartList.add(new Dimension(row, col));
			}
		}
		pstartList.trimToSize();
	}

	private void choosePatrolRandomStart() {
		Dimension dm = pstartList.get(gen.nextInt(pstartList.size()));
		pstartRow = (int) dm.getWidth();
		pstartCol = (int) dm.getHeight();
	}

	private void chooseRandomStart() {
		Dimension dm = startList.get(gen.nextInt(startList.size()));
		startRow = (int) dm.getWidth();
		startCol = (int) dm.getHeight();
	}

	@Override
	/**
	 * Checks to see if the user has reached the finish point
	 */
	public boolean isFinish(int row, int col) {
		if (mArray[row][col] == 'e') {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Checks to see if the direction in which the player is moving is a valid move
	 */
	public boolean isValidMove(int row, int col) {
		char c = mArray[row][col];
		if (c == '-' || c == 's' || c == 'e' || c == 'g' || c == 'b' || c == '1' 
			|| c == '2' || c == 'f'|| c =='3' || c == 'r' || c == 'u' || c == '4' || c == '5' 
				|| c == 'd' || c == '6'  || c == '9' || c == 'h') {
			return true;
		}
		return false;
	}
	/**
	 * Checks to see if the direction derived from the rand() movement function
	 * for the A.I. is valid or not
	 */
	public boolean isValidPatrolMove(int row, int col) {
		char c = mArray[row][col];
		// The patrol paths, if you will, are designated with the character of a number
		// representing the level on which the path is placed
		// This is so the guard or robot knows what spots are valid spots to move on,
		// but also retain the color of the maze so the GUI doesn't show a differentiation.
		if (c == '1' || c == '2' || c =='3' || c == '4' || c == '5' || c == '6'  || c == '9') {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "startRow" + getStartRow() + " startCol" + getStartCol() +
		"pstartRow" + getPStartRow() + " pstartCol" + getPStartCol();
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	/**
	 * Gathers the starting row value for the user
	 */
	public int getStartRow() {
		return startRow;
	}

	@Override
	/**
	 * Gathers the starting column value for the user
	 */
	public int getStartCol() {
		return startCol;
	}
	/**
	 * Gathers the starting row value for the A.I.
	 */
	public int getPStartRow() {
		return pstartRow;
	}
	/**
	 * Gathers the starting column value for the A.I.
	 */
	public int getPStartCol() {
		return pstartCol;
	}
	
	@Override
	/**
	 * Gathers the character in the maze .txt file located at the current row and column value
	 */
	public char getChar(int row, int col) {
		return mArray[row][col];
	}

	@Override
	public int getEndRow() {
		return endRow - 1;
	}

	@Override
	public int getEndCol() {
		return endCol - 1;
	}
}
