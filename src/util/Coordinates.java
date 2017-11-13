package util;

public class Coordinates{
  private int x;
  private int y;
  public Coordinates(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int get_x_coordinate(){
    return x;
  }

  public int get_y_coordinate(){
    return y;
  }

  public void set_x_coordinate(int x){
    this.x = x;
  }

  public void set_y_coordinate(int y){
    this.y = y;
  }
}
