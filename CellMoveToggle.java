/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a cell move toggle object. It comes with an instance
variable, constructors, and methods.
**/
import java.util.List;
/**
This class is a subclass of Cell Move Up and both uses and overrides its methods.
instance variables:
toggled- represents if cell is currently toggled
**/
public class CellMoveToggle extends CellMoveUp{
  //instance variable
  public boolean toggled;
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param currRow- current row of Cell
  @param currCol- current column of cell
  @param mass- mass of cell
  **/
  public CellMoveToggle(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    //initializes toggled to true
    toggled = true;
  }
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param otherCellMoveToggle- cell move toggle to be copied
  **/
  public CellMoveToggle(CellMoveToggle otherCellMoveToggle) {
    super(otherCellMoveToggle);
  }
  /**
  this method returns a string representation of cell move toggle
  @return string "T" if toggled is true
  @return string "t" if toggled is false
  **/
  public String toString(){
    if (toggled){
      return "T";
    }
    else{
      return "t";
    }
  }
  /**
  this method overrides the super. determines if the cell should initiate
  apoptosis or not
  @param neighbors - list of cell's neighbors
  @return true if cell should initiate apoptosis
  **/
  public boolean checkApoptosis(List<Cell>neighbors){
    //checls conditions
    if(neighbors.size()<2||neighbors.size()>5) {
      return true;
    }
    else{
      return false;
    }
  }
  /**
  creates a new CellMoveToggle deep copy

  @return new cell
  **/
  public Cell newCellCopy(){
    //call constructor
    Cell newCell = new CellMoveToggle(this);
    return newCell;
  }
  /**
  moves the cell to a new position

  @return int array of new position
  **/
  public int[] getMove() {
    //set new position based on if toggled
    int[] arr = new int[2];
    if (toggled) {
      arr[0] = getCurrRow()-1;
      arr[1] = getCurrCol();
      toggled = false;
    }
    else {
      toggled = true;
    }
    return arr;
  }
}
