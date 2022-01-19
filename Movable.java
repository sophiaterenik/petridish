/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a movable interface. It has one abstract method
**/

/**
This class allows classes that implement movable to also implement its
method getmove. it also allows to keep track of movable objects
**/
public interface Movable{
  /**
  this method gets the position of cell's move
  @return int array of the new position
  **/
  public abstract int[] getMove();
}
