package util;

public class Edge {
  int source_r;
  int source_c;
  int dest_r;
  int dest_c;
  double weight;

  public Edge(int sr,int sc, int dr, int dc, double weight){
    this.source_r      = sr;
    this.source_c      = sc;
    this.dest_r = dr;
    this.dest_c = dc;
    this.weight   = weight;
  }

  public int[] getDestination(){
      int[] d = {this.dest_r,this.dest_c};
    return d;
  }
}
