import java.util.ArrayList;
 
public class Cell {
	private String dir;
	private Boolean isCircle;
	private Boolean isBlack;
	private int row, col;
	private Status statusFor;
	private Status statusBack;
	private ArrayList<Cell> adj = new ArrayList<>();
	private ArrayList<Cell> adjTail = new ArrayList<>();
	private Cell parentFor;
	private Cell parentBack;
	public Cell getParentFor() {
		return parentFor;
	}
	public Cell(String dir, Boolean isCircle, Boolean isBlack, int row, int col) {
		this.dir = dir;
		this.isBlack = isBlack;
		this.isCircle = isCircle;
		this.col = col;
		this.row = row;
		statusFor = Status.WHITE;
		statusBack = Status.WHITE;
	}
	public void setParentFor(Cell parentFor) {
		this.parentFor = parentFor;
	}

	public Cell getParentBack() {
		return parentBack;
	}

	public void setParentBack(Cell parentBack) {
		this.parentBack = parentBack;
	}

	public Status getStatusFor() {
		return statusFor;
	}

	public void setStatusFor(Status statusFor) {
		this.statusFor = statusFor;
	}

	public Status getStatusBack() {
		return statusBack;
	}

	public void setStatusBack(Status statusBack) {
		this.statusBack = statusBack;
	}
	
	
	public ArrayList<Cell> getAdj() {
		return adj;
	}


	public ArrayList<Cell> getAdjTail() {
		return adjTail;
	}

	public void addAdj(Cell cell) {
		this.adj.add(cell);
	}
	public void addAdjTail(Cell cell) {
		this.adjTail.add(cell);
	}

	public String getDir() {
		return dir;
	}

	public Boolean getIsCircle() {
		return isCircle;
	}

	public Boolean getIsBlack() {
		return isBlack;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	public void printAdj() {
		for(Cell cell : adj) {
			System.out.print(cell.toString());
		}
		System.out.println();
	}
	@Override
	public String toString() {
		return "(" + row + "," + col + ") ";
	}
	
	
	
}
