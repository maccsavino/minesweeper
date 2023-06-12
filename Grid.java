//https://www.youtube.com/watch?v=3tLbKCXyVII
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
class Grid extends JFrame {
	private boolean [][]bombGrid;
	private int [][]countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;  
	private JButton buttons[][];
	private int safeCount;
	public Grid() {
		
		numColumns = 10;
		numRows = 10;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	    safeCount = (getNumRows() * getNumColumns()) - getNumBombs();
		buttons = new JButton[getNumRows()][getNumColumns()];
	    setLayout(new GridLayout(getNumRows(), getNumColumns()));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        for (int i = 0; i < getNumRows(); i++) {
            for (int u = 0; u < getNumColumns(); u++) {
                  buttons[i][u] = new JButton();
                  buttons[i][u].addActionListener((ActionListener) new ButtonClickListener(i, u));
            add(buttons[i][u]);
            }
      }
	}  
	public Grid(int rows, int columns) {
	
		numColumns = columns;
		numRows = rows;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	     safeCount = (getNumRows() * getNumColumns()) - getNumBombs();
		buttons = new JButton[getNumRows()][getNumColumns()];
	    setLayout(new GridLayout(getNumRows(), getNumColumns()));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        for (int i = 0; i < getNumRows(); i++) {
            for (int u = 0; u < getNumColumns(); u++) {
                  buttons[i][u] = new JButton();
                  buttons[i][u].addActionListener((ActionListener) new ButtonClickListener(i, u));
            add(buttons[i][u]);
            }
      }
	} 
	public Grid(int rows, int columns, int numBombs) {
		
		numRows = rows;
		numColumns = columns;
		this.numBombs = numBombs;
		createBombGrid();
		createCountGrid();
	     safeCount = (getNumRows() * getNumColumns()) - getNumBombs();
		buttons = new JButton[getNumRows()][getNumColumns()];
	    setLayout(new GridLayout(getNumRows(), getNumColumns()));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        for (int i = 0; i < getNumRows(); i++) {
            for (int u = 0; u < getNumColumns(); u++) {
                  buttons[i][u] = new JButton();
                  buttons[i][u].addActionListener((ActionListener) new ButtonClickListener(i, u));
            add(buttons[i][u]);
            }
      }
	}  
	public int getNumRows() {
	return numRows;
	}  
	public int getNumColumns() {
	return numColumns;
	} 
	public int getNumBombs() {
	return numBombs;
	} 
	public boolean[][] getBombGrid() {
	boolean[][] result = new boolean[this.numRows][this.numColumns];
	for (int i = 0; i < this.numRows; i++) {
	System.arraycopy(this.bombGrid[i], 0, result[i], 0, this.bombGrid[i].length);
	}
	return result;
	}
	public int[][] getCountGrid() {
	int[][] result = new int[this.numRows][this.numColumns];
	for (int i = 0; i < this.numRows; i++) {
	System.arraycopy(this.countGrid[i], 0, result[i], 0, this.countGrid[i].length);
	}
	return result;
	}
	  
	public boolean isBombAtLocation(int row, int column) {
	return (bombGrid[row][column]);
	}
	  
	public int getCountAtLocation(int row, int column) {
	int count = 0;
	if (isBombAtLocation(row, column)) {
	count++;
	}
	  
	if (row + 1 < numRows) {
	if (isBombAtLocation(row + 1, column))
	count++;
	if (column + 1 < numColumns) {
	if (isBombAtLocation(row + 1, column + 1))
	count++;
	}
	  
	if (column - 1 >= 0) {
	if (isBombAtLocation(row + 1, column - 1))
	count++;
	}
	}
	  
	if (row - 1 >= 0) {
	if (isBombAtLocation(row - 1, column))
	count++;
	if (column - 1 >= 0) {
	if (isBombAtLocation(row - 1, column - 1))
	count++;
	}
	  
	if (column + 1 < numColumns) {
	if (isBombAtLocation(row - 1, column + 1))
	count++;
	}
	  
	}
	  
	if (column + 1 < numColumns) {
	if (isBombAtLocation(row, column + 1))
	count++;
	}
	  
	if (column - 1 >= 0) {
	if (isBombAtLocation(row, column - 1))
	count++;
	}
	return count;
	}
	  
	private void createBombGrid() {
	bombGrid = new boolean[numRows][numColumns];
	for (int i = 0; i < numRows; i++) {
	for (int u = 0; u < numColumns; u++) {
	bombGrid[i][u] = false;
	}
	}
	  
	ArrayList<Integer> list = new ArrayList<Integer>();
	for (int i = 1; i < numRows * numColumns; i++) {
	list.add(new Integer(i));
	}
	  
	Collections.shuffle(list);
	for (int i = 0; i < numBombs; i++) {
	int number = (list.get(i));
	int row = new Integer(number / numColumns);
	int column = new Integer(number % numColumns);
	  
	bombGrid[row][column] = true;
	}
	}
	  
	private void createCountGrid() {
	countGrid = new int[numRows][numColumns];
	for (int i = 0; i < numRows; i++) {
	for (int u = 0; u < numColumns; u++) {
	countGrid[i][u] = getCountAtLocation(i, u);
	}
	}
	}
	
private void reset() {
    createBombGrid();
    createCountGrid();
    safeCount = (getNumRows() * getNumColumns()) - getNumBombs();
    for (int i = 0; i < getNumRows(); i++) {
          for (int u = 0; u < getNumColumns(); u++) {
                buttons[i][u].setText("");
                buttons[i][u].setEnabled(true);
          }
    }
}
private class ButtonClickListener implements ActionListener {
    int row, column;
    public ButtonClickListener(int row, int column) {
          this.row = row;
          this.column = column;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
          if (isBombAtLocation(row, column)) {
                int[][] counts = getCountGrid();
                for (int i = 0; i < getNumRows(); i++) {
                      for (int u  = 0; u < getNumColumns(); u++) {
                            if (isBombAtLocation(i, u)) {
                                  buttons[i][u].setText("bomb");
                            } else {
                              buttons[i][u].setText(String.valueOf(counts[i][u]));
                            }                        
                            buttons[i][u].setEnabled(false);
                      }
                }
                int status = JOptionPane.showConfirmDialog(null,
                            "Nice try but you lost. Would you like to try again?", "Game over",
                            JOptionPane.YES_NO_OPTION);
                if (status == JOptionPane.YES_OPTION) {
                      reset();
                } else {
                	System.exit(0);
                }
          } else {
                buttons[row][column].setText(String.valueOf(getCountAtLocation(row, column)));
                buttons[row][column].setEnabled(false);
                safeCount--;
                if (safeCount == 0) {
                      int status = JOptionPane.showConfirmDialog(null,
                                  "Congraatulations YOU WON! Do you want to try again?", "Game over",
                                  JOptionPane.YES_NO_OPTION);
                      if (status == JOptionPane.YES_OPTION) {
                            reset();
                      } else {
                            System.exit(0);
                      }
                }
          }
    }
}
	public static void main(String args[]) {

	 Grid minesweep = new Grid();  
	 int rows = minesweep.getNumRows(); 
	 int columns = minesweep.getNumColumns();
	 boolean[][] bombgrid = minesweep.getBombGrid();  
	 for (int i = 0; i < rows; i++) {  
	 for (int u = 0; u < columns; u++) { 
	 System.out.print(bombgrid[i][u] ? "T" : "F"); 
	 } 
	 System.out.println("");  
	 }
	 System.out.println("");

	 int[][] countgrid = minesweep.getCountGrid();  
	 for (int i = 0; i < rows; i++) {  
	 for (int u = 0; u < columns; u++) { 
	 System.out.print(countgrid[i][u] + "");  
	 }  
	 System.out.println("");
	 }
}	
}