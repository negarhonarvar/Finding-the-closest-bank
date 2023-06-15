public class NeighbourHood
{
   private String name;
   private double Right_up_x;
   private double Left_down_x;
   private double Right_up_y;
   private double Left_down_y;
      NeighbourHood(String name,double right_up_x, double left_down_x, double right_up_y, double left_down_y)
      {
          this.name=name;
          this.Right_up_x=right_up_x;
          this.Left_down_x=left_down_x;
          this.Right_up_y=right_up_y;
          this.Left_down_y=left_down_y;
      }

    public double getRight_up_x()
    {
        return Right_up_x;
    }

    public double getLeft_down_x()
    {
        return Left_down_x;
    }

    public double getRight_up_y()
    {
        return Right_up_y;
    }

    public double getLeft_down_y()
    {
        return Left_down_y;
    }

    public String getName(){return name;}

    public boolean IsInside(Point point)
    {
        if(point.getX()<=getRight_up_x() && point.getX()>=getLeft_down_x() && point.getY()>=getLeft_down_y()
        && point.getY()<=getRight_up_y())
        {
            return true;
        }
        else
            return false;
    }
}
