/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a cell divide object. It comes with an instance
variable, constructors, and methods.
**/
import java.util.List;
/**
This class is a subclass of Cell and both uses and overrides its methods.
instance variables:
direction - the direction of cell divide
**/
public class CellDivide extends Cell implements Divisible{
  //instanvce variables
  public int direction;
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param currRow- current row of Cell
  @param currCol- current column of cell
  @param mass- mass of cell
  **/
  public CellDivide(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    //initializes direction to 1
    direction = 1;
  }
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param otherCellDivide- cell divide to be copied
  **/
  public CellDivide(CellDivide otherCellDivide) {
    super(otherCellDivide);
    direction = otherCellDivide.direction;
  }
  /**
  this method returns a string representation of cell divide
  @return string "+"
  **/
  public String toString(){
    return "+";
  }
  /**
  this method overrides the super. determines if the cell should initiate
  apoptosis or not
  @param neighbors - list of cell's neighbors
  @return true if cell should initiate apoptosis
  **/
  public boolean checkApoptosis(List<Cell>neighbors){
    //checks conditions
    if(neighbors.size() == 3){
      return true;
    }
    else{
      return false;
    }
  }
  /**
  creates a new CellDivide deep copy

  @return new cell
  **/
  public Cell newCellCopy(){
    //calls constructor
    Cell newCell = new CellDivide(this);
    return newCell;
  }
  /**
  divides cell into another space in dish

  @return int array of new position
  **/
  public int[] getDivision() {
    //sets position based on direction
    //direction increases by 1 each time method is called
    int[] arr = new int[2];
    if(direction == 0) {
      arr[0] = getCurrRow()+1;
      arr[1] = getCurrCol();
      direction = 1;
    }
    else if(direction == 1) {
      arr[0] = getCurrRow()-1;
      arr[1] = getCurrCol();
      direction = 2;
    }
    else if(direction == 2) {
      arr[0] = getCurrRow();
      arr[1] = getCurrCol()-1;
      direction = 3;
    }
    else if(direction == 3) {
      arr[0] = getCurrRow();
      arr[1] = getCurrCol()+1;
      direction = 0;
    }
    return arr;
  }
}
