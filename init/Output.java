
/**
 * outputs data of the saved game
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.*;
import java.io.*;
public class Output
{
    //obkects 
    private ComputerBoard CB;
    private UserBoard UB;
    private String timeG; //
    private String name; //
    private String adress; //SavedGames\\name

    private int boardSize=10;
    private int [][] shipBoardCG;
    private int [][] shipBoardUG;
    private int [][] guessBoardCG;
    private int [][] guessBoardUG;
    private int level=0;

    private int DistractionCG;
    private int originDistant;
    private boolean direction;
    private ArrayList <Integer> possibleHitsCG=new ArrayList();
    private ArrayList <Integer> directionCG=new ArrayList();

    void setSetters(ComputerBoard cBoard, UserBoard uBoard, Hits hit, int levelL) //setts the setters
    {
        CB=cBoard;
        UB=uBoard;

        setName();
        setAdress();
        setDate();

        setTable(CB.getShipBoard(), UB.getShipBoard(), CB.getHitBoard(), UB.getHitBoard());
        setHitList(hit.getPossibleHitsL(), hit.getDirectionL(), hit.distractionH(), hit.getOriginDistantH(), hit.getDirectionH());
        
        level=levelL;
    }

    void setName() ///can not handle names which don't fit, or repeteive yet  //sets the name of the game
    {
        name="Error";
        Scanner box =new Scanner(System.in); 
        System.out.println("Name the game, that you would like to save. \n(Do Not use the follwing charachters: \\ / : * ? \" < > | ) \n The name:  ");
        name=box.nextLine();
    }

    void setAdress() //setssthe adress of the game
    {
        adress="SavedGames\\"+name+".txt";
    }

    void setDate() //sets the date of the game
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");  //find how to compare
        Date date = new Date();
        timeG=dateFormat.format(date);
    }

    void setTable(int [][] shipBoardC, int[][] shipBoardU, int [][] guessBoardC, int [][]guessBoardU) //sets the diffrent boards
    {
        shipBoardCG=shipBoardC;
        shipBoardUG=shipBoardU;
        guessBoardCG=guessBoardC;
        guessBoardUG=guessBoardU;
    }

    void setHitList(ArrayList <Integer> possibleHitsCL, ArrayList <Integer> directionCL, int DistractionCL, int originDistantL, boolean directionL) //setthe hit list
    {
        DistractionCG=DistractionCL;
        originDistant=originDistantL;
        direction=directionL;
        ArrayList <Integer> directionCG=directionCL;
        ArrayList <Integer> possibleHitsCG=possibleHitsCL;
    }

    void outputData() throws Exception //calls the add methods
    {
        addName();
        addData();
        addShipRecord("SavedGames\\"+name+"CompShipRecord.txt", CB.getSB().getShips()); //comp ShipRecord
        addShipRecord("SavedGames\\"+name+"UserShipRecord.txt", UB.getSB().getShips()); //User ShipRecord
    }

    void addName() throws IOException //addds the name to the name list file
    {
        FileWriter fw = new FileWriter("aaList.txt", true);
        PrintWriter output = new PrintWriter(fw);
        System.out.println("name: "+name);
        output.println(name);
        output.println(timeG);
        output.close( ); //closes PrintWriter
        fw.close( ); //closes FileWriter
    }

    void addData() throws IOException //outouts the data 
    {
        FileWriter fw = new FileWriter(adress);
        PrintWriter output = new PrintWriter(fw);

        output.printf("%d ", level);
        output.printf("%n");

        for(int y=0; y<boardSize; y++)
        {
            for(int x=0; x<boardSize; x++)
                output.printf("%02d ", shipBoardCG[y][x]);
            output.printf("%n");
        }
        for(int y=0; y<boardSize; y++)
        {
            for(int x=0; x<boardSize; x++)
                output.printf("%02d ", shipBoardUG[y][x]);
            output.printf("%n");
        }
        for(int y=0; y<boardSize; y++)
        {
            for(int x=0; x<boardSize; x++)
                output.printf("%02d ", guessBoardCG[y][x]);
            output.printf("%n");
        }
        for(int y=0; y<boardSize; y++)
        {
            for(int x=0; x<boardSize; x++)
                output.printf("%02d ", guessBoardUG[y][x]);
            output.printf("%n");
        }
        output.printf("%d ", DistractionCG);
        output.printf("%n");

        output.printf("%d ", originDistant);
        output.printf("%n");

        if(direction==false)
            output.printf("%d ", 0);
        else if(direction==true)
            output.printf("%d ", 1); 
        else
            System.out.println("OP    addData   ERRORRRRRRRRR");
        output.printf("%n");

        int z;
        for(z=0; z<directionCG.size(); z++)
        {
            output.printf("%d ", directionCG);
            output.printf("%n");
        }

        for(int x=z; x<4; x++)
        {
            output.printf("%d ", -6);
            output.printf("%n");
        }

        for(int y=0; y<possibleHitsCG.size(); y++)
        {
            output.printf("%d ", possibleHitsCG);
            output.printf("%n");
        }

        output.close( ); //closes PrintWriter
        fw.close( ); //closes FileWriter
    }

    void addShipRecord(String adressSR, ArrayList <ArrayList<ArrayList<Integer>>> Ships)throws Exception //adds the ship records
    {
        FileWriter fw = new FileWriter(adressSR);
        PrintWriter output = new PrintWriter(fw);
        for(int x=0; x<Ships.size();x++)
        {
            for(int y=0; y<Ships.get(x).size();y++)
            {
                for(int z=0; z<Ships.get(x).get(y).size();z++)
                    output.println(Ships.get(x).get(y).get(z));
                output.println(-55);
            }
            output.println(-56);
        }
        output.close( ); //closes PrintWriter
        fw.close( ); //closes FileWriter
    }
}
