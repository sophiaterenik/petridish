/**
Sophia Terenik
A16513450
sterenik@ucsd.edu
sources used: discussion problems

this file is used to create a petri dish object. this stores cells and keeps
track of them. It comes with an instance variable and a constructor
**/
import java.util.List;
import java.util.ArrayList;
/**
this class stores cell objects in a 2d array
instance variables:
dish - 2d array that stores cell objects
**/
public class PetriDish{
  //instance variable
  public Cell[][] dish;
  public List<Movable> movables;
  public List<Divisible> divisibles;
/**
the constructor initializes the dish and assigns each spot in the array
with a cell object
**/
  public PetriDish(String[][] board){
    movables = new ArrayList<>();
    divisibles = new ArrayList<>();
    dish = new Cell[board.length][board[0].length];
    //assign cells into board
    for (int i = 0; i<board.length; i++) {
      for (int j = 0; j<board[0].length; j++) {
        String input = board[i][j];

        String [] boardInfo = input.strip().split(" ");
        //cell type is first part of input
        String boardType = boardInfo[0];
        //cell mass is second string of input
        if (boardInfo.length>1){
          int mass = Integer.parseInt(boardInfo[1]);
          //assign what type of cell
          //add to movable or divisble if it is an instance of the interface
          if(boardType.equals("CellStationary")) {
            dish[i][j] = new CellStationary(i, j, mass);
          }
          else if (boardType.equals("CellMoveUp")) {
            dish[i][j] = new CellMoveUp(i, j, mass);
            movables.add((Movable)dish[i][j]);
          }
          else if (boardType.equals("CellDivide")) {
            dish[i][j] = new CellDivide(i, j, mass);
            divisibles.add((Divisible)dish[i][j]);
          }
          else if(boardType.equals("CellMoveToggle")) {
            dish[i][j] = new CellMoveToggle(i, j, mass);
            movables.add((Movable)dish[i][j]);
          }
          else if(boardType.equals("CellMoveDiagonal")) {
            dish[i][j] = new CellMoveDiagonal(i, j, mass);
            movables.add((Movable)dish[i][j]);
          }
          else if(boardType.equals("CellMoveToggleChild")) {
            dish[i][j] = new CellMoveToggleChild(i, j, mass);
            movables.add((Movable)dish[i][j]);
          }
        }
      }
    }
  }
  /**
  this helper method wraps the position of a cell so it is not out of bounds

  @param toWrap - int array of position to wrap
  **/
  private void wrap(int[] toWrap) {
    //row is at top of dish, move to bottom
    if (toWrap[0]<0) {
      toWrap[0] = dish.length - 1;
    }
    //row is at bottom of dish, move to top
    else if (toWrap[0]>dish.length-1) {
      toWrap[0] = 0;
    }
    //column is at left of dish, move to right
    if(toWrap[1]<0) {
      toWrap[1] = dish[0].length-1;
    }
    //colum is at right of dish, move to left
    else if(toWrap[1]>dish[0].length-1) {
      toWrap[1] = 0;
    }
  }
  /**
  this method gets the 8 neighbors of each cell, given dish is at least a 3x3

  @param row- row of cell to find neighbors of
  @param col- column of cell to find neighbors of
  @return list of cells that are neighbors, starting from the northwest corner
          and moving clockwise
  **/
  public List<Cell> getNeighborsOf(int row, int col) {
    //check validity
    if(row>=dish.length || row<0) {
      return null;
    }
    if(col>=dish[0].length || col<0){
      return null;
    }

    List<Cell> neighborCells = new ArrayList<Cell>();
    //loop through the 3x3 around cell
    for (int i = row-1; i>=row+1; i++){
      for (int j = col-1; j>=col+1; j++){
        int[] arr = new int[]{i, j};
        wrap(arr);
        //does not add the cell to that is trying to find neighbors of
        if(dish[arr[0]][arr[1]]!= null){
          if(!(row==i && col==j)){
            neighborCells.add(dish[arr[0]][arr[1]]);
          }
        }
      }
    }
    return neighborCells;
  }
  /**
  this method moves all movable cells to new positions. if a movable cell moves
  to a cell with a non-movable, the non-movable dies. if two movable cells go
  to the same position, the one with the smaller mass dies and if it is a tie,
  they both die
  **/
  public void move(){

    Cell[][] next = new Cell[dish.length][dish[0].length];
    boolean[][] ties = new boolean[dish.length][dish[0].length];
    List<Movable> movableToRemove = new ArrayList<>();
    List<Cell> cellToRemove = new ArrayList<>();
    //place all non movables on next
    for(int i = 0;i<dish.length;i++){
      for (int j= 0; j<dish[0].length;j++){
        if (!(movables.contains(dish[i][j]))) {
          next[i][j]=dish[i][j];
        }
      }
    }
    //loop through movables and move each one
    for(int i = 0; i<movables.size(); i++){
      Movable cell = movables.get(i);
      int[] pos = cell.getMove();
      wrap(pos);
      int newlong = pos[0];
      int lat = pos[1];
      //set next at new position to if there are no cells there
      if(next[newlong][lat] == null){
        next[newlong][lat] = (Cell)cell;
        ((Cell)cell).setCurrRow(newlong);
        ((Cell)cell).setCurrCol(lat);
      }
      //set next at new position to cell if cell there is not movable
      else if(!movables.contains(next[newlong][lat])) {

        cellToRemove.add(dish[newlong][lat]);
        next[newlong][lat] = (Cell)cell;
        ((Cell)cell).setCurrRow(newlong);
        ((Cell)cell).setCurrCol(lat);
        ties[newlong][lat]= false;
      }
      //set next at new position to cell if cell there is a smaller mass
      else if(((Cell)cell).compareTo(next[newlong][lat])>0){
        movableToRemove.add((Movable)next[newlong][lat]);
        next[newlong][lat] = (Cell)cell;
        ((Cell)cell).setCurrRow(newlong);
        ((Cell)cell).setCurrCol(lat);
        ties[newlong][lat] = false;
      }
      //check for tie
      else if(((Cell)cell).compareTo(next[newlong][lat])==0){
        ties[newlong][lat] = true;
        movableToRemove.add(cell);
        movableToRemove.add((Movable)next[newlong][lat]);
      }
      //cell will be removed if movable cell at new position has larger mass
      else{
        movableToRemove.add(cell);
      }
    }
    //loop through movableToRemove to kill all dead movable cells
    for(Movable cells : movableToRemove) {
      ((Cell)cells).apoptosis();
      movables.remove(cells);
    }
    //loop through cells to remove all overtaken non movable cells
    for(Cell cells : cellToRemove) {
      cells.apoptosis();
      if(divisibles.contains(cells)){
        divisibles.remove(cells);
      }
    }
    //loop through ties to kill all cells in a tie
    for (int i = 0; i<ties.length; i++){
      for (int j = 0; j<ties[0].length; j++){
        if(ties[i][j]){
          movables.remove((Movable)next[i][j]);
          next[i][j].apoptosis();
          next[i][j]=null;
        }
      }
    }
    //set dish to next
    dish = next;
  }
  /**
  divides all divisible cells to new positions if the new position is empty.
  if two cells divide to the same cell, the one with the larger mass stays and
  if there is tie, they both die
  **/
  public void divide(){

    Cell[][] next = new Cell[dish.length][dish[0].length];
    boolean[][] ties = new boolean[dish.length][dish[0].length];
    List<Divisible> divisibleToRemove = new ArrayList<>();
    List<Divisible> divisibleToAdd = new ArrayList<>();
    //transfers all non divisibles to next
    for(int i = 0;i<dish.length;i++){
      for (int j= 0; j<dish[0].length;j++){
        if (!(divisibles.contains(dish[i][j]))) {
          next[i][j]=dish[i][j];
        }
      }
    }
    //loops through divisibles to get division
    for(int i = 0; i<divisibles.size();i++){
      Divisible cell = divisibles.get(i);
      int currlong = ((Cell)cell).getCurrRow();
      int currlat = ((Cell)cell).getCurrCol();
      int[] newpos = ((CellDivide)cell).getDivision();
      wrap(newpos);
      int newlong = newpos[0];
      int newlat = newpos[1];
      //sets new position to divided cell if there was no cell there before
      if(next[newlong][newlat]==null){
        if(dish[newlong][newlat]==null){
          next[currlong][currlat] = (Cell)cell;
          Divisible copycell = (Divisible)((Cell)cell).newCellCopy();
          ((Cell)copycell).setCurrRow(newlong);
          ((Cell)copycell).setCurrCol(newlat);
          next[newlong][newlat] = (Cell)copycell;
          divisibleToAdd.add(copycell);
        }
        //places divisible cell to next but it does not divide since there is
        // a cell already there
        else{
          next[currlong][currlat]=(Cell)cell;
        }
        ties[newlong][newlat]=false;
      }
      //if empty cell before divisions occurred
      else if(dish[newlong][newlat]==null){
        //if the divided cell's mass is bigger than the current divided cell's
        //remove the current divided cell and replace with new divided cell
        if(((Cell)cell).compareTo(next[newlong][newlat])>0) {
          divisibleToRemove.add((Divisible)next[newlong][newlat]);
          next[currlong][currlat] = (Cell)cell;
          Divisible copycell = (Divisible)((Cell)cell).newCellCopy();
          ((Cell)copycell).setCurrRow(newlong);
          ((Cell)copycell).setCurrCol(newlat);
          next[newlong][newlat] = (Cell)copycell;
          ties[newlong][newlat]=false;
          divisibleToAdd.add(copycell);
        }
        //if both divided cells have the same mass, kill them both
        else if(((Cell)cell).compareTo(next[newlong][newlat])==0){
          ties[newlong][newlat]=true;
          divisibleToRemove.add((Divisible)next[newlong][newlat]);
        }
        //kill new cell if it has smaller mass
        else {
          divisibleToRemove.add(cell);
          ties[newlong][newlat]=false;
        }
      }
      //add cell to next in its current location
      else{
        next[currlong][currlat]=(Cell)cell;
      }
    }
    //add newly divided cells to divisibles
    for(Divisible cells : divisibleToAdd) {
      divisibles.add(cells);
    }
    //kill all cells that are in a tie
    for (int i = 0; i<ties.length; i++){
      for (int j = 0; j<ties[0].length; j++){
        if(ties[i][j]){
          divisibleToRemove.add((Divisible)next[i][j]);
          next[i][j]=null;
        }
      }
    }
    //kill all divisible cells that were removed
    for(Divisible cells : divisibleToRemove) {
      ((Cell)cells).apoptosis();
      divisibles.remove(cells);
    }

    dish = next;
  }
  /**
  this method updates the board and creates a new cell if it has 2 or 3
  neighbors
  **/
  public void update(){

    Cell[][] next = new Cell[dish.length][dish[0].length];
    List<Divisible> divisibles = new ArrayList<>();
    List<Movable> movables = new ArrayList<>();

//loops through dish to access each space
    for (int i =0; i<dish.length;i++){
      for (int j = 0; j<dish[0].length; j++){
        List<Cell> neighbors = getNeighborsOf(i, j);
//if dish is null and has 2 or 3 neighbors, create new cell to null position
        if(dish[i][j] == null ){
          if(neighbors.size()==2 || neighbors.size()==3){
            Cell hiNeighbor = neighbors.get(0);
            Cell newcell = hiNeighbor.newCellCopy();
            next[i][j] = newcell;
            newcell.setCurrRow(i);
            newcell.setCurrCol(j);
            if(newcell instanceof Movable) {
              movables.add((Movable)newcell);
            }
            if(newcell instanceof Divisible){
              divisibles.add((Divisible)newcell);
            }
          }
        }
        //if dish is ready for apoptosis, kill the cell

        else if(dish[i][j].checkApoptosis(neighbors)){
          dish[i][j].apoptosis();
          next[i][j]=null;
        }
        //set new position to old position if not null and not going in
        //apoptosis
        else{
          next[i][j]=dish[i][j];
        }
      }
    }


    dish = next;
  }
  /**
  this method calls move, divide, and update
  **/
  public void iterate(){
    this.move();
    this.divide();
    this.update();
  }
}
