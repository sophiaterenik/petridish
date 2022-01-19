/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: none

this file is used to create a divisible interface. It has one abstract method
**/

/**
This class allows classes that implement divisble to also implement its
method getDivision. it also allows to keep track of divisble objects
**/

public interface Divisible {
  /**
  this method gets the position of a divided cells
  @return int array of the new position
  **/
  public abstract int[] getDivision();
}
