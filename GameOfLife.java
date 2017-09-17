/**
 * <h1>Conways game of life!</h1>
 * The GameOfLife program implements Conway's game of
 * life. The rules of conways game of life are:
 * <ol>
 * <li>Any live cell with fewer than two live neighbours dies,
 *  as if caused by underpopulation.
 *  </li>
 *  <li>
 *   Any live cell with two or three live neighbours lives on to the next generation.
 *  </li>
 *  <li>
 *   Any live cell with more than three live neighbours dies, as if by overpopulation
 *  </li>
 *  <li>
 *  Any dead cell with exactly three live neighbours becomes a live cell,
 *  as if by reproduction.
 *  </li>
 * </ol>
 *
 * The board is a 2d grid with a horizontal and vertical axis. Each co-ordinate
 * in the grid is a cell which is either alive or dead.
 * The board for the game is represented by a 2d HashMap which takes a cell's 
 * co-ordinates and returns whether the cell is alive.
 *
 */
import java.util.*;

public class GameOfLife {

    public class Cell{

        int x;
        int y;

        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }


    }

    // Default number of rows in the board
    public static final int DEFAULT_ROW_LEN = 100;
    
    // Default number of columns in the board
    public static final int DEFAULT_COL_LEN = 100;
	
    private int rowLen;
    private int columnLen;
    private HashMap<Integer, HashMap<Integer,Boolean>> board;
	
    public GameOfLife(){
        this(GameOfLife.DEFAULT_ROW_LEN, GameOfLife.DEFAULT_COL_LEN);
    }
			
    public GameOfLife(int rowLen, int columnLen){
	if(rowLen <= 0 || columnLen <= 0)
	    throw new IllegalArgumentException("Row and Column size must be a " +
										   "positive whole number greater " +
										   "than 0");
        this.rowLen = rowLen;
        this.columnLen = columnLen; 
        board = new HashMap<Integer, HashMap<Integer, Boolean>>();
    }
    
    public int getRowLen(){
        return this.rowLen;
    }
    
    public int getColumnLen(){
	    return this.columnLen;
    }
    public void setCellValue(int x, int y, boolean value){
	/* Sets cell in board to alive or dead.
	 */
		if(x >= 0 && y >= 0 && x < this.getRowLen() & y < this.getColumnLen()){
			HashMap<Integer, Boolean> cell;
            if(board.containsKey(x)){
                cell = board.get(x);
            }
            else{
                cell = new HashMap<Integer, Boolean>();
            }
            cell.put(y, value);
			board.put(x, cell);
			
		}
		
    }


    public void setCellValue(int x, int y, boolean value,
                             HashMap<Integer, HashMap<Integer, Boolean>> board){
	/* Sets cell in board to alive or dead.
	 */
		if(x >= 0 && y >= 0 && x < this.getRowLen() & y < this.getColumnLen()){
			HashMap<Integer, Boolean> cell;
            if(board.containsKey(x)){
                cell = board.get(x);
            }
            else{
                cell = new HashMap<Integer, Boolean>();
            }
            cell.put(y, value);
			board.put(x, cell);
			
		}
		
    }


    public boolean getCellValue(int x, int y){
        Cell cell = new Cell(x, y);
        if(board.containsKey(x) && board.get(x).containsKey(y))
			return board.get(x).get(y);
		else
			return false;
	}

    public int getAliveNeighbours(int x, int y){
        int count = 0;
        for(int i = x - 1; i <= x + 1;i++){
            for(int j = y - 1; j <= y + 1; j++){
                
                if( i != x || j != y)
                    count += (this.getCellValue(i, j) ? 1 : 0 );
            }
        }
        return count;
    }

    public boolean willLive(int x, int y){
        return (this.getAliveNeighbours(x, y) == 2 &&
                this.getCellValue(x, y)) ||(this.getAliveNeighbours(x, y) == 3);
    }

    public void updateCell(int x, int y, HashMap<Integer, HashMap<Integer, Boolean>> board){
        if(x >= 0 && y >= 0 && x < this.getRowLen() && y < this.getColumnLen())
            this.setCellValue(x, y, this.willLive(x, y), board);
   }

    public Cell[] cellsToUpdate(){
        Set<Cell> cells = new HashSet<Cell>(this.board.size() * 2);
        for(int i : this.board.keySet()){
            for(int j : this.board.get(i).keySet()){    
                for(int x = i - 1; x <= i + 1; x++ ){
                    for(int y =  j - 1 ; y <= j + 1; y++ ){
                        if(x >= 0 && y >= 0 && x < this.getRowLen() &&
                           y < this.getColumnLen())
                            cells.add(new Cell(x, y));
                    }
                }
            }            
        }
        return cells.toArray(new Cell[1]);
        
    }

    public void updateAll(){
		/* Updates all cells in the board.
		 */
        HashMap<Integer, HashMap<Integer, Boolean>> newBoard;
        newBoard = new HashMap<Integer, HashMap<Integer, Boolean>>();
        Cell[] toUpdate = cellsToUpdate(); 
        for(Cell cell : toUpdate){
            this.updateCell(cell.x, cell.y, newBoard);
        }
		board = newBoard;
    }

    
    public static void main(String[] args){
        GameOfLife test = new GameOfLife(1000, 1000);
        test.setCellValue(0, 0, true);
        test.setCellValue(0, 1, true);
        test.setCellValue(1, 0, true); 
        System.out.println(test.getCellValue(1, 1));
        test.updateAll();
        System.out.println(test.getCellValue(1, 1));
    }

}
