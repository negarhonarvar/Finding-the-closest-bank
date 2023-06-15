import HashMap.*;
import java.util.Scanner;

public class MainClass
{
    int bankCtr=0;
    int branchCtr=0;
    Node root=null;
    int depth=0;
    KD_Tree Branches=new KD_Tree();
    Node b_root=null;
    int b_depth=0;
    KD_Tree Banks=new KD_Tree();
    private static HashMap<String, NeighbourHood> neighbourHoodHashMap = new HashMap<String,NeighbourHood>(); // neighbourhood name -> NeighbourHood
    private static HashMap<String,MainBank> ListOfMainBanks= new HashMap<String, MainBank>();

    Scanner in = new Scanner(System.in);
    public void MainManu()
    {
        System.out.println("Welcome , please choose your desired service.");
        System.out.println("enter : -addN if you want to add a neighbourhood.");
        System.out.println("enter : -addB if you want to add a main bank.");
        System.out.println("enter : -addBr if you want to add a main bank's branch.");
        System.out.println("enter : -delBr if you want to delete an specific branch.");
        System.out.println("enter : -listB if you want a list of an specific neighbourhood banks list.");
        System.out.println("enter : -listBrs if you want a list of all branches of an specific bank.");
        System.out.println("enter : -nearB if you want to find the closest main bank to you.");
        System.out.println("enter : -nearBr if you want to find the closest branch to you.");
        System.out.println("enter : -availB if you want to find main banks and branches in an specific radius around you.");
        System.out.println("enter : -end if you want to end the program.");
        System.out.print("please enter your choice : ");
        String command=in.nextLine();
        switch (command)
        {
            case("addN"): {
                addN();
                //MainManu();
                break;
            }
            case("addB"):
            {
                addB();
                //MainManu();
                break;
            }
            case ("addBr"):
            {
                addBr();
                //MainManu();
                break;
            }
            case("delBr"):
            {
                delBr();
                //MainManu();
                break;
            }
            case("listB"):
            {
                listB();
                //MainManu();
                break;
            }
            case("listBrs"):
            {
                listBrs();
                //MainManu();
                break;
            }
            case("nearB"):
            {
                nearB();
                //MainManu();
                break;
            }
            case ("nearBr"):
            {
                nearBr();
                //MainManu();
                break;
            }
            case("availB"):
            {
                availB();
                //MainManu();
                break;
            }
            case("end"):
            {
                return;
            }
            default:
            {
               MainManu();
            }
        }
        MainManu();
    }
    public void addN()
    {
        System.out.println("Please enter Neighbourhood's name:");
        String name=in.nextLine();
        System.out.println("Now,Please enter the coordinates in this format:");
        System.out.println("1)coordinates of right-up point of neighbourhood border");
        System.out.println("1)coordinates of left-down point of neighbourhood border");
        System.out.print("x:");
        double x1=in.nextDouble();
        System.out.print("y:");
        double y1=in.nextDouble();
        System.out.print("x:");
        double x2=in.nextDouble();
        System.out.print("y:");
        double y2=in.nextDouble();
        NeighbourHood N=new NeighbourHood(name,x1,x2,y1,y2);
        neighbourHoodHashMap.put(N.getName(),N);
    }
    public void addB()
    {
        bankCtr++;
        System.out.println("Please enter Bank's name:");
        String name=in.nextLine();
        System.out.println("Now,Please enter the coordinates :");
        System.out.print("x:");
        double x=in.nextDouble();
       // System.out.println();
        System.out.print("y:");
        double y=in.nextDouble();
        //System.out.println();
        Point p=new Point(x,y);
        System.out.println("please enter the number of branches you wish to add now:");
        int flag=in.nextInt();
        MainBank b=new MainBank(name,p);
        ListOfMainBanks.put(name,b);
        for (int i = 0; i < flag ; i++)
        {
            addBr();
        }
        ListOfMainBanks.put(b.getName(),b);
        Node node=new Node(x,y,b.getName());
        Banks.Insertion(b_root,node,b_depth);
        if(bankCtr==1)
           b_root=node;
        b_depth++;
    }
    public void addBr()
    {
        branchCtr++;
        System.out.print("please enter the main bank's name:");
        String mainBankName=in.nextLine();
        //System.out.println();
        System.out.print("please enter the branch's name:");
        String branchName=in.nextLine();
        //System.out.println();
        System.out.println("please enter the coordinates:");
        System.out.print("x:");
        double x=in.nextDouble();
        //System.out.println();
        System.out.print("y:");
        double y=in.nextDouble();
        //System.out.println();
        Node node=new Node(x,y,branchName);
        node.setMainBankName(mainBankName);
        while(Branches.Search(root,node,0))
        {
            System.out.println("a branch with the same coordinates exist,please enter coordinates again");
            System.out.println("re-enter the coordinates:");
            System.out.print("x:");
            x=in.nextDouble();
            //System.out.println();
            System.out.print("y:");
            y=in.nextDouble();
            //System.out.println();
            node=new Node(x,y,branchName);
        }
        //Point p=new Point(x,y);
        Branches.Insertion(root,node,depth);
        ListOfMainBanks.get(mainBankName).Branches.Insertion(ListOfMainBanks.get(mainBankName).root,node,ListOfMainBanks.get(mainBankName).depth);
        if(ListOfMainBanks.get(mainBankName).depth==0)
            ListOfMainBanks.get(mainBankName).root=node;
        ListOfMainBanks.get(mainBankName).depth++;
        depth++;
        if(branchCtr==1)
           root=node;
        //Branch branch=new Branch(branchName,mainBankName,p);
    }

    public void delBr()
    {
        System.out.println("please enter the coordinates of the branch you want to delete:");
        System.out.print("x:");
        double x=in.nextDouble();
        //System.out.println();
        System.out.print("y:");
        double y=in.nextDouble();
        //System.out.println();
        Node node=new Node(x,y,null);
        while(Banks.Search(b_root,node,0) || Branches.Search(root,node,0))
        {
            System.out.println("the coordinates either belong to a main bank or those not exist;please re-enter the coordinates:");
            System.out.print("x:");
            x=in.nextDouble();
            //System.out.println();
            System.out.print("y:");
            y=in.nextDouble();
            //System.out.println();
        }
        root=Branches.Deletation(root,x,y,0);
        b_root=Banks.Deletation(b_root,x,y,0);
        b_depth--;
        depth--;
    }
    public void listB()
    {
        System.out.print("please enter the neighbourhood's name:");
        String neighbourhoodName=in.nextLine();
        //System.out.println();
        NeighbourHood n= neighbourHoodHashMap.get(neighbourhoodName);
        double x_max=n.getRight_up_x();
        double x_min=n.getLeft_down_x();
        double y_max=n.getRight_up_y();
        double y_min=n.getLeft_down_y();
        Banks.InNeighbourHood(x_max,x_min,y_max,y_min,b_root,b_depth);
        Branches.InNeighbourHood(x_max,x_min,y_max,y_min,root,depth);
    }
    public void listBrs()
    {
        System.out.print("please enter the main bank's name:");
        String name=in.nextLine();
        //System.out.println();
        MainBank bank=ListOfMainBanks.get(name);
        bank.root=ListOfMainBanks.get(name).root;
        bank.Branches=ListOfMainBanks.get(name).Branches;
        bank.Branches.Iteration(bank.root);

    }
    public void nearB()
    {
        System.out.println("please enter your coordinates:");
        System.out.print("x:");
        double x=in.nextDouble();
        //System.out.println();
        System.out.print("y:");
        double y=in.nextDouble();
        //System.out.println();
        double distance=Math.sqrt((b_root.x-x)*(b_root.x-x)+(b_root.y-y)*(b_root.y-y));
        Node n1= Banks.findNearest(b_root.left,x,y,distance);
        Node n2= Banks.findNearest(b_root.right,x,y,distance);
        double distance1=Math.sqrt((n1.x-x)*(n1.x-x)+(n1.y-y)*(n1.y-y));
        double distance2=Math.sqrt((n2.x-x)*(n2.x-x)+(n2.y-y)*(n2.y-y));
        if(distance1>=distance2)
            Banks.MainBankPrint(n2);
        else
            Banks.MainBankPrint(n1);
    }
    public void nearBr()
    {
        System.out.println("please enter your coordinates:");
        System.out.print("x:");
        double x=in.nextDouble();
        //System.out.println();
        System.out.print("y:");
        double y=in.nextDouble();
        //System.out.println();
        System.out.print("please enter the main banks name:");
        String name=in.nextLine();
        //System.out.println();
        MainBank bank=ListOfMainBanks.get(name);
        bank.root=ListOfMainBanks.get(name).root;
        double distance=Math.sqrt((bank.root.x-x)*(bank.root.x-x)+(bank.root.y-y)*(bank.root.y-y));
        Node n1= Banks.findNearest(bank.root.left,x,y,distance);
        Node n2= Banks.findNearest(bank.root.right,x,y,distance);
        double distance1=Math.sqrt((n1.x-x)*(n1.x-x)+(n1.y-y)*(n1.y-y));
        double distance2=Math.sqrt((n2.x-x)*(n2.x-x)+(n2.y-y)*(n2.y-y));
        if(distance1>=distance2)
            Branches.BranchPrint(n2);
        else
            Branches.BranchPrint(n1);
    }
    public void availB()
    {
        System.out.println("please enter your coordinates:");
        System.out.print("x:");
        double x=in.nextDouble();
        //System.out.println();
        System.out.print("y:");
        double y=in.nextDouble();
        //System.out.println();
        System.out.print("please enter the radius:");
        double radius=in.nextDouble();
        //System.out.println();
        Banks.findAround(b_root,x,y,radius,0);
        Branches.findAround(root,x,y,radius,0);

    }
}

