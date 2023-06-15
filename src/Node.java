public class Node
{
    double x;
    double y;
    Node left;
    Node right;
    String name;
    String mainBankName;
      Node(double x, double y,String name)
      {
          this.x=x;
          this.y=y;
          this.left=this.right=null;
          this.name=name;
      }

    public void setMainBankName(String mainBankName) // this field is only used when the node refers to a branch
    {
        this.mainBankName = mainBankName;
    }
}
