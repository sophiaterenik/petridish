/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a cell move up object. It comes with
constructors and methods.
**/
import java.util.List;

/**
This class is a subclass of Cell and both uses and overrides its methods.
**/

public class CellMoveUp extends Cell implements Movable{
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param currRow- current row of Cell
  @param currCol- current column of cell
  @param mass- mass of cell
  **/
  public CellMoveUp(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
  }
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param otherCellMoveUp- cell move up to be copied
  **/
  public CellMoveUp(CellMoveUp otherCellMoveUp) {
    super(otherCellMoveUp);
  }
  /**
  this method returns a string representation of cell move up
  @return string "^"
  **/
  public String toString(){
    return "^";
  }
  /**
  this method overrides the super. determines if the cell should initiate
  apoptosis or not
  @param neighbors - list of cell's neighbors
  @return true if cell should initiate apoptosis
  **/
  public boolean checkApoptosis(List<Cell>neighbors){
    //checks conditions
    if(neighbors.size()!=4) {
      return true;
    }
    else {
      return false;
    }
  }
  /**
  creates a new CellMoveUp deep copy

  @return new cell
  **/
  public Cell newCellCopy(){
    //calls constructor
    Cell newCell = new CellMoveUp(this);
    return newCell;
  }
  /**
  moves the cell

  @return int array of new position
  **/
  public int[] getMove() {
    //sets new position
    int[] arr = new int[2];
    arr[0] = getCurrRow() - 1;
    arr[1] = getCurrCol();
    return arr;
  }
}
