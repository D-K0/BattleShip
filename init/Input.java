
/**
 * inputs data of the saved game
 */
import java.util.*;
import java.io.*;
public class Input
{
    private String [][] nameList;  //nameList[AMOuNT][THEME]    name - date
    private int length;

    private int boardSize=10;
    private int [][][] board=new int[4][boardSize][boardSize]; //four 2d arrays...
    private int level=0;

    private int DistractionCG;
    private int originDistant;
    private int directionNum;
    private boolean direction;
    private ArrayList <Integer> directionCG=new ArrayList();
    private ArrayList <Integer> possibleHitsCG=new ArrayList();

    //0-shipBoardC
    //1-shipBoardU
    //2- hitBoardC
    //3- hitBoardU

    private int [][][] concetration=new int[4][boardSize][boardSize]; //4 tables for 4 ships

    private ArrayList <ArrayList<ArrayList<Integer>>> compShips= new ArrayList();
    private ArrayList <ArrayList<ArrayList<Integer>>> userShips= new ArrayList();
    void inputList() throws Exception //[adress][date] is sent
    {
        length=getLengthOfList();
        nameList=new String[length][2];
        length=0;
        Scanner sc=new Scanner(new File("aaList.txt"));
        while(sc.hasNext()) //input the list
        {
            nameList[length][0]=sc.next();
            nameList[length][1]=sc.next();
            length++;   
        }
    }

    void inputData(String adress) throws Exception  //yx 
    {
        String adress1="SavedGames\\"+adress+".txt";
        String adressC="SavedGames\\"+adress+"CompShipRecord.txt";
        String adressU="SavedGames\\"+adress+"UserShipRecord.txt";

        Scanner sc1=new Scanner(new File(adress1));

        level=sc1.nextInt();

        for(int z=0; z<4; z++)
            for(int y=0; y<boardSize; y++)
                for(int x=0; x<boardSize; x++)
                    board[z][y][x]=sc1.nextInt();

        //Distraction;
        DistractionCG=sc1.nextInt();
        //originDistant;
        originDistant=sc1.nextInt();
        //direction;
        directionNum=sc1.nextInt();
        if(directionNum==0)
            direction=false;
        else if(directionNum==1)
            direction=true; 
        else
            System.out.println("IP   inputData      ERRORRRRRRRRR");
        //possibleHits
        int temp=-1100;
        for(int l=0; l<4; l++)
        {
            temp=sc1.nextInt();
            if(temp>=0)
                directionCG.add(temp);
            else if(temp==-6)
                ;
            else
                System.out.println("IP   inputData   EroR");
        }
        //direction
        while(sc1.hasNext())
            possibleHitsCG.add(sc1.nextInt());
        sc1.close();

        compShips=inputShipRecord(adressC);
        userShips=inputShipRecord(adressU);
    }

    ArrayList <ArrayList<ArrayList<Integer>>> inputShipRecord(String adressSB) throws Exception 
    {
        //sets the arays lists
        ArrayList <ArrayList<ArrayList<Integer>>> Ships= new ArrayList();
        ArrayList<ArrayList<Integer>> Ship2=new ArrayList();
        Ships.add(Ship2);
        ArrayList<ArrayList<Integer>> Ship3=new ArrayList();
        Ships.add(Ship3);
        ArrayList<ArrayList<Integer>> Ship4=new ArrayList();
        Ships.add(Ship4);
        ArrayList<ArrayList<Integer>> Ship5=new ArrayList();
        Ships.add(Ship5);
        ArrayList<Integer> Ship21=new ArrayList();
        Ships.get(0).add(Ship21);
        ArrayList<Integer> Ship22=new ArrayList();
        Ships.get(0).add(Ship22);
        ArrayList<Integer> Ship23=new ArrayList();
        Ships.get(0).add(Ship23);
        ArrayList<Integer> Ship24=new ArrayList();
        Ships.get(0).add(Ship24);
        ArrayList<Integer> Ship31=new ArrayList();
        Ships.get(1).add(Ship31);
        ArrayList<Integer> Ship32=new ArrayList();
        Ships.get(1).add(Ship32);
        ArrayList<Integer> Ship33=new ArrayList();
        Ships.get(1).add(Ship33);
        ArrayList<Integer> Ship41=new ArrayList();
        Ships.get(2).add(Ship41);
        ArrayList<Integer> Ship42=new ArrayList();
        Ships.get(2).add(Ship42);
        ArrayList<Integer> Ship51=new ArrayList();
        Ships.get(3).add(Ship51);

        int temp=-11;
        int x=0;
        int y=0;
       
        Scanner sc0=new Scanner(new File(adressSB));

        while(sc0.hasNext())
        {
            temp=sc0.nextInt();
            if(temp==-55)
                y++;
            else if(temp==-56)
                x++;
            else
                Ships.get(x).get(y).add(temp);
        }

        sc0.close();
        return Ships;
    }

    void inputConc() throws Exception //inputs the calculated frequence of the ship location
    {
        Scanner ip=new Scanner(new File("ConcentrationShips.txt")); 
        for(int z=0; z<4; z++)
            for(int y=0; y<boardSize; y++)
                for(int x=0; x<boardSize; x++)
                    concetration[z][y][x]=ip.nextInt();
                    
    }

    //getter
    int getLengthOfList() throws Exception
    {
        int length=0;
        Scanner sc=new Scanner(new File("aaList.txt"));
        while(sc.hasNext()) //find the length
        {
            length++;   
            sc.nextLine();
        }
        return length/2;
    }

    int[][] getConcetrationTable(int biggestShip)
    {
        return concetration[biggestShip-2];
    }

    int getLengthNameList()
    {
        return length;
    }

    String[][] getListName()
    {
        return nameList;
    }

    int[][] getShipBoardC()
    {return board[0];}

    int[][] getShipBoardU()
    {return board[1];}

    int[][] getHitBoardC()
    {return board[2];}

    int[][] getHitBoardU()
    {return board[3];}

    int getDistraction()
    {return DistractionCG;}

    int getOriginDistant()
    {return originDistant;}

    boolean getDirection()
    {return direction;}

    ArrayList <Integer> getDirectionList()
    {return directionCG;}

    ArrayList <Integer> getPossibleHitsList()
    {return possibleHitsCG;}
    
    ArrayList <ArrayList<ArrayList<Integer>>> getCompShips()
    {return compShips;}
    
    ArrayList <ArrayList<ArrayList<Integer>>> getUserShips()
    {return userShips;}
}
