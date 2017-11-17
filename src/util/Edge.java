package util;

public class Edge {
    private TreeNode src, dest;
    
    private int source_r, source_c;
    private int dest_r, dest_c;
    double weight;

  public Edge(int sr,int sc, int dr, int dc, double weight){
    this.source_r      = sr;
    this.source_c      = sc;
    this.dest_r = dr;
    this.dest_c = dc;
    this.weight   = weight;
  }
  
  public Edge(TreeNode src, TreeNode dest, double weight){
      this.src = src;
      this.dest = dest;
      this.weight = weight;
  }
  
  public TreeNode getDest(){
    return dest;
  }
  
  public void setDest(TreeNode dest){
    this.dest = dest;
  }
  
}
