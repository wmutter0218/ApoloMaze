import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;



public class Solve{
	private ArrayList < Cell > board = new ArrayList<>();
	private int rowSize, colSize;
	
	public Solve(String fileName){
		FileReader txtReader = null;

		//fix exception for file reading errors
		try {
			txtReader=new FileReader(fileName);
		}catch(Exception e) {
			System.out.println("Error");
		}

		Scanner scan = new Scanner(txtReader);
		rowSize = scan.nextInt();
		colSize = scan.nextInt();
		while(scan.hasNextLine()){

			int row = scan.nextInt();
			int col = scan.nextInt();
			String color = scan.next();
			String circle = scan.next();
			String dir = scan.next();
			Boolean isBlack, isCircle;
			if(color.equals("B")) {
				isBlack = true;
			} else {
				isBlack = false;
			}
			if ( circle.equals("C")) {
				isCircle = true;
			} else {
				isCircle = false;
			}
			board.add(new Cell(dir, isCircle, isBlack, row, col));
		}	

	}
	public void createList() {
		for (int i = 0; i < board.size(); i++) {

			Cell currCell = board.get(i);
			if(currCell.getDir().equals("X")) {
				continue;
			}
			String dir = currCell.getDir();
			int temp = i;
			if(dir.equals("W")) {
				temp -= 1;
				while(temp % colSize != colSize-1 && temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp -= 1;
				}
				temp = i + 1;
				while(temp % colSize != 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp += 1;
				}
			} else if (dir.equals("N")) {
				temp -= colSize;
				while(temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp -= colSize;
				}
				temp = i + colSize;
				while(temp < rowSize*colSize) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp += colSize;
				}
			} else if (dir.equals("E")) {
				temp += 1;
				while(temp % colSize != 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp += 1;
				}

				temp = i - 1;
				while(temp % colSize != colSize-1 && temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp -= 1;
				}
			} else if (dir.equals("S")) {
				temp += colSize;
				while(temp < rowSize*colSize) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp += colSize;
				}
				temp = i - colSize;
				while(temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp -= colSize;
				}
			} else if (dir.equals("NW")) {
				temp -= (colSize + 1);
				while(temp % colSize != colSize-1 && temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp -= (colSize + 1);
				}
				temp = i + (colSize + 1);
				while(temp % colSize != 0 && temp < rowSize*colSize) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp += (colSize + 1);
				}
			} else if (dir.equals("NE")) {
				temp -= (colSize - 1);
				while(temp % colSize != 0 && temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp -= (colSize - 1);
				}
				temp = i + (colSize - 1);
				while(temp % colSize != colSize - 1  && temp < rowSize*colSize) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp += (colSize - 1);
				}
			} else if (dir.equals("SW")) {
				temp += (colSize - 1);
				while(temp % colSize != colSize - 1  && temp < rowSize*colSize) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp += (colSize - 1);
				}
				temp = i - (colSize - 1);
				while(temp % colSize != 0 && temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp -= (colSize - 1);
				}
			} else if (dir.equals("SE")) {
				temp += (colSize + 1);
				while(temp % colSize != 0 && temp < rowSize*colSize) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdj(nextCell);
					}
					temp += (colSize + 1);
				}
				temp = i - (colSize + 1);
				while(temp % colSize != colSize-1 && temp >= 0) {
					Cell nextCell = board.get(temp);
					if (nextCell.getIsBlack() != currCell.getIsBlack()) {
						currCell.addAdjTail(nextCell);
					}
					temp -= (colSize + 1);
				}

			}
		}
	}
	public void doMaze() {
		dfs(board.get(0), true);
	}
	public void dfs(Cell currCell, Boolean isHead) {
		if(currCell.getIsCircle()) {
			isHead = !isHead;
		}
		ArrayList<Cell>	tempAdj;		
		if(isHead) {
			tempAdj = currCell.getAdj();
			currCell.setStatusFor(Status.GREY);
		} else {
			tempAdj = currCell.getAdjTail();
			currCell.setStatusBack(Status.GREY);
		}
		for(Cell adjCell : tempAdj) {
			Boolean tempHead = isHead;
			if(adjCell.getIsCircle()) {
				tempHead = !isHead;
			}
			
			if((tempHead && adjCell.getStatusFor() == Status.WHITE)) {
				if(isHead) {
					adjCell.setParentFor(currCell);
				}
				else if(!isHead) {
					adjCell.setParentBack(currCell);
				}
				dfs(adjCell,isHead);
			}
			else if(!tempHead && adjCell.getStatusBack() == Status.WHITE){
				if(isHead) {
					adjCell.setParentFor(currCell);
				}
				else if(!isHead) {
					adjCell.setParentBack(currCell);
				}
				dfs(adjCell,isHead);
			}
		}
		if(isHead) {
			currCell.setStatusFor(Status.BLACK);
		} else {
			currCell.setStatusBack(Status.BLACK);
		}
	}
	public void printCells() {
		for(Cell cell : board) {
			cell.printAdj();
		}
	}
	public void printAns() {
		Cell currCell = board.get(colSize*rowSize - 1);
		Stack<Cell> stack = new Stack<Cell>();
		Boolean isHead = null;
		if(currCell.getParentFor() != null) {
			isHead = true;
			
		}
		else if(currCell.getParentBack() != null) {
			isHead = false;
		}
		do {
			stack.push(currCell);
			if(currCell.getIsCircle()) {
				isHead = !isHead;
			}
			if(isHead) {
				currCell = currCell.getParentFor();
			} else {
				currCell = currCell.getParentBack();
			}
		} while (currCell != board.get(0));
		System.out.print(board.get(0));
		while(!stack.empty()) {
			System.out.print(stack.pop());
		}
	}
	public static void main(String[] args) {
		Solve solve = new Solve("input.txt");
		
		solve.createList();
		solve.doMaze();
		solve.printAns();
	}

}