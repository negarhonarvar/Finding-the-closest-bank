import HashMap.HashMap;

public class KD_Tree
{
    static final int k=2;
    public Node Insertion(Node root,Node node,int depth)
    {
        if(root==null)
            return node;//the node itself will be assigned as root
        else
        {
            int a=depth%k;
            if(a==0)
            {
                if(node.x<root.x)
                    root.left=Insertion(root.left,node,depth+1);
                else
                    root.right=Insertion(root.right,node,depth+1);

            }
            else
            {
                if(node.y<root.y)
                    root.left=Insertion(root.left,node,depth+1);
                else
                    root.right=Insertion(root.right,node,depth+1);

            }
        }
        return root;
    }
    public boolean Search(Node root,Node node,int depth)//at the beginning we
            //should start the search with depth 0
    {
        if(root==null)
            return false;
        if(root==node)
            return true;
        int flag=depth%k;
        if(flag%2==0)
        {
            if(root.x<node.x)
                return Search(root.left,node,depth+1);
            else
                return Search(root.right,node,depth+1);
        }
        else
        {
            if(root.y<node.y)
                return Search(root.left,node,depth+1);
            else
                return Search(root.right,node,depth+1);
        }
    }
    public Node MinFinder(Node root,int a,int depth)
    {
       if(root == null)
           return null;
       int flag=depth%k;
       if(flag==a)
       {
           if(root.left==null)
               return root;
           else
               return MinFinder(root.left,a,depth+1);
       }
       return Min(root,MinFinder(root.left,a,depth+1),MinFinder(root.right,a,depth+1),depth);
    }
    public Node Min(Node a, Node b,Node c,int depth)
    {
        Node temp=a;
        if(depth%2==0)
        {
            if(b != null && b.x<temp.x)
                temp=b;
            if(c != null && c.x<temp.x)
                temp=c;
            return temp;
        }
        else
        {
            if(b != null && b.y<temp.y)
                temp=b;
            if(c != null && c.y<temp.y)
                temp=c;
            return temp;
        }
    }
    public Node Deletation(Node root,double x, double y,int depth)//at the beginning we should start from depth=0
    {
        if(root == null)
            return null;
        int flag=depth%k;

        if(root.x==x && root.y==y)
            {
                if(root.right != null)
                {
                    Node min=MinFinder(root.right,flag,0);
                    root.x=min.x;
                    root.y=min.y;
                    if(min.name != null)
                        root.name=min.name;
                    root.right=Deletation(root.right,min.x, min.y,depth+1);
                }
                else if (root.left != null)
                {
                    Node min=MinFinder(root.left,flag,0);
                    root.x=min.x;
                    root.y=min.y;
                    if(min.name != null)
                        root.name=min.name;
                    root.right=Deletation(root.left,min.x, min.y, depth+1);
                }
                else//if the node we need to delete is root itself
                    root=null;
                return root;
            }
        if(flag%2==0)
        {
            if(x<root.x)
                root.left=Deletation(root.left,x,y,depth+1);
            else
                root.right=Deletation(root.right,x,y,depth+1);
        }
        else
        {
            if(y<root.y)
                root.left=Deletation(root.left,x,y,depth+1);
            else
                root.right=Deletation(root.right,x,y,depth+1);
        }
        return root;
    }
    public void BranchPrint(Node node)
    {
        System.out.println("Main Banks name:"+node.mainBankName);
        System.out.println("Branch name:"+node.name);
        System.out.println("Coordinates:("+node.x+","+node.y+")");
    }
    public void MainBankPrint(Node node)
    {
        System.out.println("Main Banks name:"+node.mainBankName);
        System.out.println("Coordinates:("+node.x+","+node.y+")");
    }
    public boolean IsInside(double x_max,double x_min,double y_max,double y_min,Node root)
    {
         return (root.x<=x_max && root.x>=x_min && root.y>=y_min && root.y<=y_max);
    }
    public Node Range(double x_max,double x_min,double y_max,double y_min,Node root,int depth)
    {
        if(depth%2==0)
        {
            if(root.x>x_max && root.x>=x_min)
            {
                return root.left;
            }
            else if(root.x<=x_max && root.x<x_min)
            {
                return root.right;
            }
            else
                return root;
        }
       else
        {
            if (root.y<y_min && root.y<=y_max)
            {
                return root.right;
            }
            else if(root.y>=y_min && root.y>y_max)
            {
                return root.left;
            }
            else
                return root;
        }
    }
    public void InNeighbourHood(double x_max,double x_min,double y_max,double y_min,Node root,int depth)
    {
        if(root==null)
            return;
            if(IsInside(x_max,x_min,y_max,y_min,root))
            {
                if(root.mainBankName!=null)
                    BranchPrint(root);
                else
                    MainBankPrint(root);
                depth--;
                InNeighbourHood(x_max,x_min,y_max,y_min,root.right, depth);
                InNeighbourHood(x_max,x_min,y_max,y_min,root.left, depth);
            }
           else
            {
               Node temp=Range(x_max,x_min,y_max,y_min,root,depth);
                depth--;
               if(temp==root)
               {
                   InNeighbourHood(x_max,x_min,y_max,y_min,root.right, depth);
                   InNeighbourHood(x_max,x_min,y_max,y_min,root.left, depth);
               }
               else
               {
                   root=temp;
                   InNeighbourHood(x_max,x_min,y_max,y_min,root,depth);
               }
            }
    }
    public void coordinatePrint(Node node)
    {
        System.out.println("("+node.x+","+node.y+")");
    }
    public void Iteration(Node root)
    {
        if(root==null)
            return;
        else
        {
            coordinatePrint(root);
            Iteration(root.left);
            Iteration(root.right);
        }
    }
    public Node findNearest(Node root,double x, double y,double distance)
    {
        double newDistance=Math.sqrt((root.x-x)*(root.x-x)+(root.y-y)*(root.y-y));
        if(distance<=newDistance)
        {
           return root;
        }
        Node answer1;
        Node answer2;
        double axis_left=Math.sqrt((root.left.x-x)*(root.left.x-x)+(root.left.y-y)*(root.left.y-y));
        double axis_right=Math.sqrt((root.right.x-x)*(root.right.x-x)+(root.right.y-y)*(root.right.y-y));
        if(axis_left>=axis_right)
        {
            if(root.right!=null)
                answer1=findNearest(root.right,x,y,newDistance);
            else
                answer1= root;
            if(root.left!=null)
                answer2=findNearest(root.left,x,y,newDistance);
            else
                answer2= root;
        }
        else
        {
            if(root.left!=null)
               answer1=findNearest(root.left, x, y, newDistance);
            else
                answer1= root;
            if(root.right!=null)
               answer2=findNearest(root.right, x, y, newDistance);
            else
                answer2= root;
        }
        double distance1=Math.sqrt((answer1.x-x)*(answer1.x-x)+(answer1.y-y)*(answer1.y-y));
        double distance2=Math.sqrt((answer2.x-x)*(answer2.x-x)+(answer2.y-y)*(answer2.y-y));
        if(distance1>=distance2)
            return answer2;
        else
            return answer1;
    }
    public void findAround(Node root,double x, double y, double radius,int depth)// we should
            // begin at depth=0
    {
        if(root==null)
            return;
        double distance=Math.sqrt((root.x-x)*(root.x-x)+(root.y-y)*(root.y-y));
        if(distance<=radius)
        {
            if(root.mainBankName==null)
                MainBankPrint(root);
            else
                BranchPrint(root);
            depth++;
            findAround(root.right,x,y,radius,depth);
            findAround(root.left,x,y,radius,depth);
        }
        else
        {
            int flag=depth%k;
            depth++;
            if(flag%2==0)
            {
                if(x<root.x)
                    findAround(root.left,x,y,radius,depth);
                else
                    findAround(root.right,x,y,radius,depth);
            }
            else
            {
                if(y<root.y)
                    findAround(root.left,x,y,radius,depth);
                else
                    findAround(root.right,x,y,radius,depth);
            }
        }
    }
}
