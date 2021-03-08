import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class SudokuPuzzle {
	
	static Scanner reader = new Scanner(System.in); 
	ArrayList<String> prevMoves = new ArrayList<String>();
	public static String[][] original = new String[9][9];
	public static String[][] current = new String [9][9];
	

	/**
	 * Takes a puzzle represented as an array of 9 9-character strings and fills in the sudoku grid. Each
	 * character must be a digit or a space. Does not check if the puzzle has a solution or unique solution.
	 * It is up to client to provide legal puzzles.
	 * @param start an array of 9 9 character string, with each string as a row of digits and empty spaces.
	 */
	public SudokuPuzzle(String[] start) {
		for(int i = 0; i < 9; i++) {
			for(int x = 0; x < 9; x++) {
				String str = start[i].substring(x,x+1);
				original[i][x]= str;
				current[i][x] = str;
			}
		}
	}
	
	
	
	/**
	 * Displays the current grid in rows and columns, in the format specified.
	 */ 
	public void displayGrid() {
		System.out.println("    *** Current Grid ***    ");
		System.out.println("    0 1 2   3 4 5   6 7 8 ");
		System.out.println("   ========================");
		for(int i = 0; i < 9; i++) {
			System.out.print(i + " | " + current[i][0] + " " + current[i][1] + " " + current[i][2] + " | ");
			System.out.print(current[i][3] + " " + current[i][4] + " " + current[i][5] + " | ");
			System.out.println(current[i][6] + " " + current[i][7] + " " + current[i][8] + " | ");
			if(i == 2 || i == 5 || i == 8) {
				System.out.println("   =======|=======|=======");
			}
		}
	}
	
	 /**
	  * Displays the original grid in rows and columns, in the format specified.
	  */
	public void displayOriginal() {
		System.out.println("    *** Original Grid ***    ");
		System.out.println("    0 1 2   3 4 5   6 7 8 ");
		System.out.println("   ========================");
		for(int i = 0; i < 9; i++) {
			System.out.print(i + " | " + original[i][0] + " " + original[i][1] + " " + original[i][2] + " | ");
			System.out.print(original[i][3] + " " + original[i][4] + " " + original[i][5] + " | ");
			System.out.println(original[i][6] + " " + original[i][7] + " " + original[i][8] + " | ");
			if(i == 2 || i == 5 || i == 8) {
				System.out.println("   =======|=======|=======");
			}
		}
		
	}
	
	/**
	 * Erases the value in the specified row and column, provided the row and column are valid, that is the
	 * location it is meant to be in. It is not valid to erase a cell that is apart of original. It can be undone. 
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return true if a change was made to the grid.
	 */
	public boolean erase(int row,int col) {
		if(current[row][col] != original[row][col]) {
			current[row][col] = " ";
		}
		else {
			return false;
		}
		return true;
	}
	
	/**
	 * Erases all moves from the grid, restoring the grid to the original puzzle
	 */
	public void eraseAllMoves() {
		current = original;
	}
	
	 /**
	  * Gets the digit t the specified row and column, or 0 if location is empty
	  * or -1 if it is not valid.
	  * @param row the row of the desired cell
	  * @param col the column of the desired cell
	  * @return the digit at the specified row and column, or 0 if empty, or -1 if the
	  * row or column is not valid. 
	  */
	public int getDigitAt(int row, int col) {
		String str = current[row][col];
		int x = Integer.parseInt(str);
		return x;
	}
	
	/**
	 * Checks the grid to see if it is a solution to the puzzle. 
	 * @return true if the puzzle follows sudoku rules, and false otherwise.
	 */
	public boolean isSolved() {
		boolean idk = false;
		for(int i = 0; i < 9;i++) {
			for(int x = 0; x<9;x++) {
				if(current[i][x].equals(" ")) {
					idk = false;
					break;
				}
				else {
					idk = true;
				}
			}
		}
		if(isValid() == true && idk == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks the grid to see if it follows the rules of sudoku.
	 * @return true if the puzzle follows sudoku rules, and false otherwise.
	 */
	public boolean isValid() {
		boolean rowokay = false;
		for(int row = 0; row < 9; row++) {
			ArrayList<String> remainingValues = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));
			for(int column = 0; column < 9; column++) {
				if(current[row][column].equals(" ")) {
					rowokay = true;
				}
				else if(remainingValues.contains(current[row][column]) ) {
					remainingValues.remove(remainingValues.indexOf(current[row][column]));
					rowokay = true;
				}
				else {
					return false;
				}
				
			}
		}
		boolean columnokay = false;
		for(int column = 0; column < 9; column++) {
			ArrayList<String> remainingValues = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));
			for(int row = 0; row < 9; row++) {
				if(current[row][column].equals(" ")) {
					columnokay = true;
				}
				else if(remainingValues.contains(current[row][column]) ) {
					remainingValues.remove(remainingValues.indexOf(current[row][column]));
					columnokay = true;
				}
				else {
					return false;
				}
				
			}
		}
		boolean gridokay = false;
		if(columnokay && rowokay) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Writes the specified digit into the specified row and column provided the the digit is in the range
	 * as well as the row and column.
	 * @param row the row of the column, 0-8 inclusive
	 * @param col the column of the move, 0-8 inclusive
	 * @param digit the digit to be written, 1-9 inclusive
	 * @return
	 */
	public boolean makeMove(int row, int col, int digit) {
		if(row >= 0 && row < 9 && col >= 0 && col < 9 && digit > 0 && digit <= 9) {
			if(current[row][col].equals(" ") ||  current[row][col] != original[row][col]) {
				String str = Integer.toString(digit);
				current[row][col] = str;
				
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}
	
	/**
	 * Return a string representation of the original grid and the current grid, side by side,
	 * as specified 
	 */
	public String toString() {
		String idk = "original:        current:\n";
		for(int i = 0; i < 9; i++) {
			for(int x = 0; x<9;x++) {
				if(original[i][x].equals(" ")) {
					idk += "-";
				}
				else {
					idk += original[i][x];
				}
				if(x == 8) {
					idk += "        ";
					for(int l = 0; l < 9; l++) {
						if(current[i][l].equals(" ")) {
							idk += "-";
						}
						else {
							idk += current[i][l];
						}
					}
					idk += "\n";
				}
			}
		}
		return idk;
	}
	public void helpUndo(ArrayList<String> moves){
		prevMoves = moves;
	}
	
	/**
	 * Undoes the most recent move. After undoing, then the next most recent
	 * move becomes the most recent move. The is, it is possible to undo all moves
	 * until the original grid is restored.
	 * @return true if something was undone, and false otherwise.
	 */
	public boolean undo() {
		if(prevMoves.size() == 0) {
			return false;
		}
		else {
			String themove = prevMoves.get(prevMoves.size() - 1);
			prevMoves.remove(prevMoves.size() - 1);
			String row = themove.substring(0,1);
			String column = themove.substring(2,3);
			int row1 = Integer.parseInt(row);
			int column1 = Integer.parseInt(column);
			current[row1][column1] = original[row1][column1];
		}
		return true;
	}

}
