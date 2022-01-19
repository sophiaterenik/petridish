/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a cell move toggle child object. It comes with an instance
variable, constructors, and methods.
**/
import java.util.List;
/**
This class is a subclass of Cell Move Toggle and both uses and overrides its methods.
instance variables:
numAlive- number of instances of this cell type that are alive
**/
public class CellMoveToggleChild extends CellMoveToggle{
  //instance variable
  public static int numAlive = 0;
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param currRow- current row of Cell
  @param currCol- current column of cell
  @param mass- mass of cell
  **/
  public CellMoveToggleChild(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    // increases numalive everytime a new CellMoveToggleChild is created
    numAlive++;
  }
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param otherCellMoveToggleChild- cell move toggle child to be copied
  **/
  public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild) {
    super(otherCellMoveToggleChild);
    // increases numalive everytime a new CellMoveToggleChild is created
    numAlive++;
  }
  /**
  this method overrides the apoptosis method and calls the parent method but
  also decreases the number of alive cells by 1
  **/
  public void apoptosis(){
    super.apoptosis();
    numAlive--;
  }
  /**
  this method returns a string representation of cell move toggle child. It
  calls the parent method
  @return string "T" if toggled is true
  @return string "t" if toggled is false
  **/
  public String toString(){
    return super.toString();
  }
  /**
  this method overrides the super. determines if the cell should initiate
  apoptosis or not
  @param neighbors - list of cell's neighbors
  @return true if cell should initiate apoptosis
  **/
  public boolean checkApoptosis(List<Cell>neighbors){
    //checks conditions
    if(super.checkApoptosis(neighbors)&&numAlive<10) {
      return true;
    }
    else {
      return false;
    }
  }
  /**
  creates a new CellMoveToggleChild deep copy

  @return new cell
  **/
  public Cell newCellCopy(){
    //call constructor
    Cell newCell = new CellMoveToggleChild(this);
    return newCell;
  }
}
