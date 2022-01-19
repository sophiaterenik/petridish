/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a cell move diagonal object. It comes with a instance
variables, constructors, and methods.
**/
import java.util.List;
/**
This class is a subclass of Cell Move Up and both uses and overrides its methods.
instance variables:
orientedRight- represents the orientation of the cell
diagonalMoves- number of moves the cell has made
**/
public class CellMoveDiagonal extends CellMoveUp{
  //instance variables
  public boolean orientedRight;
  public int diagonalMoves;
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param currRow- current row of Cell
  @param currCol- current column of cell
  @param mass- mass of cell
  **/
  public CellMoveDiagonal(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    //initializes oriented right to true and diagonalMoves to 0
    orientedRight = true;
    diagonalMoves = 0;
  }
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param otherCellMoveDiagonal- cell move diagonal to be copied
  **/
  public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal) {
    super(otherCellMoveDiagonal);
    orientedRight = otherCellMoveDiagonal.orientedRight;
    diagonalMoves = otherCellMoveDiagonal.diagonalMoves;
  }
  /**
  this method returns a string representation of cell move diagonal
  @return string "/" if oriented right
  @return string "\" if oriented left
  **/
  public String toString(){
    if(orientedRight){
      return "/";
    }
    else {
      return "\\";
    }
  }
  /**
  this method overrides the super. determines if the cell should initiate
  apoptosis or not
  @param neighbors - list of cell's neighbors
  @return true if cell should initiate apoptosis
  **/
  public boolean checkApoptosis(List<Cell>neighbors){
    //check conditions
    if (neighbors.size()>3){
      return true;
    }
    else {
      return false;
    }
  }
  /**
  creates a new CellMoveDiagonal deep copy

  @return new cell
  **/
  public Cell newCellCopy(){
    //call constructor
    Cell newCell = new CellMoveDiagonal(this);
    return newCell;
  }
  /**
  this method moves the cell to a new position

  @return int array of new position
  **/
  public int[] getMove() {
    //moves based on if oriented right
    int[] arr = new int[2];
    if(orientedRight) {
      arr[0] = getCurrRow()-1;
      arr[1] = getCurrCol()+1;
    }
    else{
      arr[0] = getCurrRow()-1;
      arr[1] = getCurrCol()-1;
    }
    //changes oriented right based on diagonal moves
    diagonalMoves++;
    if(diagonalMoves%4 == 0) {
      orientedRight = !orientedRight;
    }
    return arr;
  }
}
