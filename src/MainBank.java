public class MainBank {
  private String name;
  private Point coordinate;
  int depth=0;
  Node root=null;
  KD_Tree Branches = new KD_Tree();

  MainBank(String name, Point point) {
    this.name = name;
    this.coordinate = point;
  }
  public String getName() {
    return name;
  }

  public Point getCoordinate() {
    return coordinate;
  }
}
