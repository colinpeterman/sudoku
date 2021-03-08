import java.util.ArrayList;
import java.util.Scanner;

public class LetsPlaySudoku {
	static Scanner reader = new Scanner(System.in);

	public static void main(String args[]) {

	String[] puzString = { " 6 354 9 ", "9 58  6 3", " 4     8 ", "6  4   1 ", "872 9  6 ", "    368 9",
			"3 17 2   ", "  6   93 ", "4  9 31 6" };
		ArrayList<String> prevMoves = new ArrayList<String>();
		SudokuPuzzle suk = new SudokuPuzzle(puzString);
		int choice = 0;
		while (choice != 7) {

			suk.displayGrid();
			System.out.println("1. write number \n2. erase \n3. undo \n4. show grid"
					+ "\n5. show orginal puzzle \n6. erase all moves (cannot be undone) \n7. quit");

			System.out.print("Enter choice:");
			choice = reader.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter row column value:");
				int row = reader.nextInt();
				int column = reader.nextInt();
				int value = reader.nextInt();
				if(suk.makeMove(row, column, value) == true) {
					String row2 = Integer.toString(row);
					String column2 = Integer.toString(column);
					String move = row2 + " " + column2;
					prevMoves.add(move);
					suk.helpUndo(prevMoves);
					if(suk.isSolved() == true) {
						System.out.println("*** You have solved the puzzle! ***");
						suk.displayGrid();
						System.out.println("*** Goodbye!");
						choice = 7;
						break;
					}
					if(suk.isValid() == false) {
						System.out.println("*** There is an erorr in the puzzle!");
					}
					else {
						System.out.println("*** Move Successul!");
					}
				}
				else {
					System.out.println("No Change Made");
				}
				break; 
			case 2:
				System.out.print("Enter row column:");
				int row1 = reader.nextInt();
				int column1 = reader.nextInt();
				if(suk.erase(row1, column1) == true) {
					System.out.println("*** Sucess!");
				}
				else {
					System.out.println("No Change Made");
				}
				break;
			case 3:
				if(suk.undo()) {
					System.out.println("*** Success!");
				}
				else {
					System.out.println("No Moves to Undo");
				}
				break;
			case 4:
				suk.displayGrid();
				break;
			case 5:
				suk.displayOriginal();
				break;
			case 6:
				suk.eraseAllMoves();
				break;
			case 7:
				System.out.println("*** Goodbye");
			}
		}

	}
}
