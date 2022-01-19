/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a cell stationary object. It comes with
constructors and methods.
**/

import java.util.List;

/**
This class is a subclass of Cell and both uses and overrides its methods.
**/
public class CellStationary extends Cell{
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param currRow- current row of Cell
  @param currCol- current column of cell
  @param mass- mass of cell
  **/
  public CellStationary(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
  }
  /**
  the constructor calls Cell's constructor to initialize instance variables
  @param otherCellStationary- cell stationary to be copied
  **/
  public CellStationary(CellStationary otherCellStationary) {
    super(otherCellStationary);
  }
  /**
  this method returns a string representation of cell stationary
  @return string "."
  **/
  public String toString(){
    return ".";
  }
  /**
  this method overrides the super. determines if the cell should initiate
  apoptosis or not
  @param neighbors - list of cell's neighbors
  @return true if cell should initiate apoptosis
  **/
  public boolean checkApoptosis(List<Cell>neighbors){
    //checks conditions
    if(neighbors.size()<=7 && neighbors.size()>=3){
      return true;
    }
    else{
      return false;
    }
  }
  /**
  creates a new CellStationary deep copy

  @return new cell
  **/
  public Cell newCellCopy(){
    //call constructor
    Cell newCell = new CellStationary(this);
    return newCell;
  }
}
