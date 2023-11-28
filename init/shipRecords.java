
/**
 * record of the floating ships, theyir position, and state
 */
import java.util.*;
public class shipRecords
{
    private int ship2=0;
    private int ship3=0;
    private int ship4=0;
    private int ship5=0;

    private int amountShip2;
    private int amountShip3;
    private int amountShip4;
    private int amountShip5;
    private ArrayList <ArrayList<ArrayList<Integer>>> Ships= new ArrayList(); //ships contains ships of all size, which contains all the amount of the that ship size, which cintains al its locations

    public shipRecords() //constructor
    {

        ArrayList<ArrayList<Integer>> Ship2=new ArrayList();
        ArrayList<ArrayList<Integer>> Ship3=new ArrayList();
        ArrayList<ArrayList<Integer>> Ship4=new ArrayList();
        ArrayList<ArrayList<Integer>> Ship5=new ArrayList();

        Ships.add(Ship2);
        Ships.add(Ship3);
        Ships.add(Ship4);
        Ships.add(Ship5);

    }

    void setShips(ArrayList <ArrayList<ArrayList<Integer>>> ShipsL) //sets the ship
    {Ships=ShipsL;}

    void resetAmount() //resets the amount of ships of each type
    {
        amountShip2=0;
        amountShip3=0;
        amountShip4=0;
        amountShip5=0;
    }

    void addShipList(int size) //add ships to the list
    {
        ArrayList<Integer> Ship21=new ArrayList();
        ArrayList<Integer> Ship22=new ArrayList();
        ArrayList<Integer> Ship23=new ArrayList();
        ArrayList<Integer> Ship24=new ArrayList();
        ArrayList<Integer> Ship31=new ArrayList();
        ArrayList<Integer> Ship32=new ArrayList();
        ArrayList<Integer> Ship33=new ArrayList();
        ArrayList<Integer> Ship41=new ArrayList();
        ArrayList<Integer> Ship42=new ArrayList();
        ArrayList<Integer> Ship51=new ArrayList();

        if(size==2)

        {
            if(amountShip2==0)
                Ships.get(0).add(Ship21);
            else if(amountShip2==1)
                Ships.get(0).add(Ship22);
            else if(amountShip2==2)
                Ships.get(0).add(Ship23);
            else if(amountShip2==3)
                Ships.get(0).add(Ship24);
        }
        else if(size==3)
        {
            if(amountShip3==0)
                Ships.get(1).add(Ship31);
            else if(amountShip3==1)
                Ships.get(1).add(Ship32);
            else if(amountShip3==2)
                Ships.get(1).add(Ship33);
        }
        else if(size==4)
        {
            if(amountShip4==0)
                Ships.get(2).add(Ship41);
            else if(amountShip4==1)
                Ships.get(2).add(Ship42);
        }
        else if(size==5)
            if(amountShip5==0)
                Ships.get(3).add(Ship51);
    }

    void addShip(int spot, int size) //adds spot to the ships
    {
        if(size==2)
        {
            if(amountShip2==4)
                System.out.println("Something wrong with ship 2");
            Ships.get(0).get(amountShip2).add(spot);
        }
        else if(size==3)
        {
            if(amountShip3==3)
                System.out.println("Something wrong with ship 3");
            Ships.get(1).get(amountShip3).add(spot);
        }
        else if(size==4)
        {
            if(amountShip4==2)
                System.out.println("Something wrong with ship 4");
            Ships.get(2).get(amountShip4).add(spot);
        }
        else if(size==5)
        {
            if(amountShip5==1)
                System.out.println("Something wrong with ship 5");
            Ships.get(3).get(amountShip5).add(spot);
        }
        else
            System.out.println("SR  addShip   EROR!");
    }

    void addToAmount(int size) //adds the anount of ships
    {
        if(size==2)
            amountShip2++;
        else if(size==3)
            amountShip3++;
        else if(size==4)
            amountShip4++;
        else if(size==5)
            amountShip5++;
    }

    void removeToAmount(int size) //removes the amount of ships
    {
        if(size==2)
            amountShip2--;
        else if(size==3)
            amountShip3--;
        else if(size==4)
            amountShip4--;
        else if(size==5)
            amountShip5--;
    }

    void removeShip(int head, int sizeS)  //remove a ship of thelist
    {
        int location=0;
        int size=sizeS-2; 
        int amountShips=0;
        if(sizeS==2)
            amountShips=amountShip2;
        else if(sizeS==3)
            amountShips=amountShip3;
        else if(sizeS==4)
            amountShips=amountShip4;
        else if(sizeS==5)
            amountShips=amountShip5;
    }

    boolean removeSpot(int spot, int content) //removes the spot of th eship
    {
        int location=0;
        int size=content/10-2; 
        int amountShips=0;
        
        if(size==0)
            amountShips=amountShip2;
        else if(size==1)
            amountShips=amountShip3;
        else if(size==2)
            amountShips=amountShip4;
        else if(size==3)
            amountShips=amountShip5;

        for(int x=0;x<Ships.get(size).size(); x++)
        {
            for(int y=0; y<Ships.get(size).get(x).size(); y++)
            {
                if(Ships.get(size).get(x).get(y)==spot)
                {
                    Ships.get(size).get(x).remove(y);
                    if(Ships.get(size).get(x).size()==0)
                    {
                        Ships.get(size).remove(x);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //getter
    int getAmountShip2()
    {
        int amount=0;
        int z=0;
        for(int y=0; y<Ships.get(z).size(); y++)
            if(Ships.get(z).get(y).size()>0)
                amount++;
        return amount;
    }

    int getAmountShip3()
    {
        int amount=0;
        int z=1;
        for(int y=0; y<Ships.get(z).size(); y++)
            if(Ships.get(z).get(y).size()>0)
                amount++;
        return amount;
    }

    int getAmountShip4()
    {
        int amount=0;
        int z=2;
        for(int y=0; y<Ships.get(z).size(); y++)
            if(Ships.get(z).get(y).size()>0)
                amount++;
        return amount;
    }

    int getAmountShip5()
    {
        int amount=0;
        int z=3;
        for(int y=0; y<Ships.get(z).size(); y++)
            if(Ships.get(z).get(y).size()>0)
                amount++;
        return amount;
    }

    int getSmallestShip()
    {
        int smallest=0;
        if(getAmountShip2()>0)
            smallest=2;
        else if(getAmountShip3()>0)
            smallest=3;
        else if(getAmountShip4()>0)
            smallest=4;
        else if(getAmountShip5()>0)
            smallest=5;
        return smallest;
    }

    int getBiggestShip()  
    {
        int highest=0;
        if(getAmountShip5()>0)
            highest=5;
        else if(getAmountShip4()>0)
            highest=4;
        else if(getAmountShip3()>0)
            highest=3;
        else if(getAmountShip2()>0)
            highest=2;
        return highest;
    }

    int getAmountShipsTotal()
    {
        return getAmountShip2()+getAmountShip3()+getAmountShip4()+getAmountShip5();
    }

    ArrayList <ArrayList<ArrayList<Integer>>> getShips()
    {
        return Ships;
    }
}
