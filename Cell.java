/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

This file is used to create a cell object. It comes with 2 constructors,
instance variables, and methods.
**/

import java.util.List;

/**
This class is a superclass for many subclasses. Its constructors are used
in the subclasses.
instance variables:
currRow- current row of the Cell
currCol- current column of the cell
mass- mass of the cell
**/

public abstract class Cell implements Comparable<Cell>{
  //instance variables
  public int currRow;
  public int currCol;
  public int mass;

/**
the constructor initializes the instance variables

@param currRow- current row of Cell
@param currCol- current column of cell
@param mass- mass of cell
**/
  public Cell(int currRow, int currCol, int mass) {
    this.currRow = currRow;
    this.currCol = currCol;
    this.mass = mass;

    if(currRow<0){
      this.currRow = 0;
    }
    if(currCol<0) {
      this.currCol = 0;
    }
    if(mass<0) {
      this.mass = 0;
    }

  }
  /**
  the constructor initializes the instance variables

  @param otherCell- cell to be copied
  **/
  public Cell(Cell otherCell){
    //initializes variables to parameter's instance variables
    this.currRow = otherCell.getCurrRow();
    this.currCol = otherCell.getCurrCol();
    this.mass = otherCell.getMass();
  }
  /**
  this method is called when apoptosis happens. it sets all instance variables
  to -1
  **/
  public void apoptosis(){
    currRow = -1;
    currCol= -1;
    mass = -1;
  }
  /**
  this method gets the current row of the Cell

  @return the current row
  **/
  public int getCurrRow(){
    return currRow;
  }
  /**
  this method gets the current column of the cell

  @return the current column
  **/
  public int getCurrCol(){
    return currCol;
  }
  /**
  this method gets the mass of the Cell

  @return mass of cell
  **/
  public int getMass(){
    return mass;
  }
  /**
  this method needs to be overridden. it determines if the cell should
  iniate apoptosis or not

  @return true if cell should initiate apoptosis
  **/
  public abstract boolean checkApoptosis(List<Cell> neighbors);

  /**
  this method compares two cell's masses

  @param otherCell- cell to compare to
  @return integer of the difference of the cell's masses
  **/
  public int compareTo(Cell otherCell){
    return mass - otherCell.getMass();
  }

  /**
  this method is to be implemented by subclasses to create a deep copy of the
  cell

  @return new deep copy of cell
  **/
  public abstract Cell newCellCopy();

  /**
  this helper method sets the current row of a cell

  @param newRow- cell's new row
  @return boolean if method successfully sets the new row
  **/
  public boolean setCurrRow(int newRow) {
    //set new row
    this.currRow = newRow;
    // check validity
    if(newRow<0){
      currRow = 0;
    }
    return true;
  }

  /**
  this helper method sets the current column of a cell

  @param newCol- cell's new col
  @return boolean if method successfully sets the new column
  **/
  public boolean setCurrCol(int newCol){
    //set new column
    this.currCol = newCol;
    //check validity
    if(newCol<0){
      currCol = 0;
    }
    return true;
  }
  /**
  this helper method sets the current mass of a cell

  @param newMass- cell's new mass
  @return boolean if method successfully sets the new mass
  **/
  public boolean setMass(int newMass){
    //sets new mass
    this.mass = newMass;
    //check validity
    if(newMass<0){
      mass = 0;
    }
    return true;
  }
}
