package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TreeNode{
 private int r;
 private int c;
 private final List<TreeNode> children = new ArrayList<>();

 public TreeNode(int r, int c) {
  this.r = r;
  this.c = c;
 }

 public int[] getVertex() {
     int[]v = {this.r, this.c};
     return v;
 }

 public void addChild(TreeNode child){
   children.add(child);
 }

 public List<TreeNode> getChildren() {
  return children;
 }

 public static int findDistance(TreeNode root, int[] goalVertex){
  if(root.getChildren() == null)
    return -1;

  int distance = -1;

  int[] rootVert = root.getVertex();
  List<TreeNode> childrenList = root.getChildren();
  int numOfChildren = childrenList.size();

  if(Arrays.equals(rootVert,goalVertex))
  {
    return distance + 1;
  }

  TreeNode currNode;
  for(int i = 0; i < numOfChildren; i++){
    currNode = childrenList.get(i);
    if( (distance = findDistance(currNode, goalVertex)) >= 0)
      return distance + 1;
  }
  return distance;
 }

 //Reference: http://www.geeksforgeeks.org/find-distance-root-given-node-binary-tree/
 // modified for 2d list
}
