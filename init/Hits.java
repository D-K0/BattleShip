
/**
 * HIt list of the computer
 */
import java.util.*;
public class Hits
{
    //objects
    private ComputerBoard CB;
    private Input input;

    private int boardSize;

    private int[][] hitBoardCG;
    private int[][] mapFrequency;
    private ArrayList <Integer> possibleHitsCG=new ArrayList();//general possible hit list
    private ArrayList <Integer> directionCG=new ArrayList();//hit list which finds the direction 
    private int DistractionCG;//has a target
    private int originDistant;//gives the ability to rtern around and do the other if hte ship
    private boolean direction; //direction of the ship search

    private int choiceLevel=0;

    private ArrayList <Integer> allPosibleHits=new ArrayList();//for level 3

    /**
     * Constructor for objects of class Hits
     */
    public Hits(ComputerBoard cBoard)
    {
        CB=cBoard;
        hitBoardCG=CB.getHitBoard();
        boardSize=CB.getDimensions();
    }

    void setPossibleHits(ArrayList <Integer> possibleHitsCL) //general possible hit list
    {possibleHitsCG=possibleHitsCL;}

    void setPossibleDirection(ArrayList <Integer> directionCL) //hit list which finds the direction 
    {directionCG=directionCL;}

    void setDistractionData(int DistractionCL, int originDistantL, boolean directionL) //has a target
    {
        DistractionCG=DistractionCL;
        originDistant=originDistantL;
        direction=directionL;
    }
    //setters and resseters
    void setPossibleHit(int choicel, Input IP) throws Exception //sets a hit list
    {
        choiceLevel=choicel;
        resetPossibleHits();
        if(choiceLevel==1)
            setEasyPossibleHits(); //only random hits
        else if(choiceLevel==2){
            setMediumPossiblityHits(); }//every second spot hit
        else if(choiceLevel==3)
        {
            input=IP;
            setConcentrationList();//using a data table every second hit
        }
        else if(choiceLevel==0)
            System.out.println("EXIT"); //exit
        else 
            System.out.println("hit setPossibleHit ERORRRR"); //error
    }

    void setBoards(int mapHittedC[][])
    {
        hitBoardCG=mapHittedC;
    }

    void resetPossibleHits()
    {
        resetArryList(possibleHitsCG);
    }

    void resetPossibleDirection()
    {
        resetArryList(directionCG);
    }

    void resetDistractionData()
    {
        DistractionCG=-5;
        originDistant=2;
        direction=true;
    }

    boolean isDistractionNotEmpty()
    {
        if(DistractionCG==-5)
            return false;
        return true;
    }

    boolean isDiractionNotEmpty()
    {
        if(directionCG.size()==0)
            return false;
        return true;
    }

    void resetArryList(ArrayList list)
    {
        for(int x=0; x<list.size(); x=x+0)
            list.remove(0);
    }

    void setEasyPossibleHits()
    {
        for(int y=0; y<(hitBoardCG.length*10); y=y+10)
            for(int x=0; x<hitBoardCG[y/10].length; x++)
                if(hitBoardCG[y/10][x]==0)
                    possibleHitsCG.add(y+x);
    }

    void setMediumPossiblityHits()
    {
        if(CB.getSmallestShip()==2||CB.getSmallestShip()==3)
            for(int x=0; x<hitBoardCG[0].length; x++)
                for(int y=(x%2)*10; y<(hitBoardCG.length*10); y=y+20) //every second spot
                    if(hitBoardCG[y/10][x]==0)
                        possibleHitsCG.add(y+x);
        if(CB.getSmallestShip()==4||CB.getSmallestShip()==5)
            for(int x=0; x<hitBoardCG[0].length; x++)
                for(int y=(x%4)*10; y<(hitBoardCG.length*10); y=y+40) //every fourth spot
                    if(hitBoardCG[y/10][x]==0)
                        possibleHitsCG.add(y+x);
    }

    void setConcentrationList() throws Exception //sets all possible hits
    {
        Input IP=input;
        mapFrequency=IP.getConcetrationTable(CB.getBiggestShip());

        if(CB.getSmallestShip()==2||CB.getSmallestShip()==3)
            for(int x=0; x<hitBoardCG[0].length; x++)
                for(int y=(x%2)*10; y<(hitBoardCG.length*10); y=y+20)
                {
                    if(hitBoardCG[y/10][x]==0)
                        allPosibleHits.add(y+x);
                }
        else  if(CB.getSmallestShip()==4||CB.getSmallestShip()==5)
            for(int x=0; x<hitBoardCG[0].length; x++)
                for(int y=(x%4)*10; y<(hitBoardCG.length*10); y=y+40)
                {
                    if(hitBoardCG[y/10][x]==0)
                        allPosibleHits.add(y+x);
                }
        else
            System.out.println("hits   setConcentrationList    WRONGG");

        setMediumTablePossibilityHits();
    }

    void setMediumTablePossibilityHits()  //picks out all the frequent spots
    {
        int mostLikely=0;
        for(int s=0; s<allPosibleHits.size(); s++)
            if(mapFrequency[allPosibleHits.get(s)/10][allPosibleHits.get(s)%10]>mostLikely)
                mostLikely=mapFrequency[allPosibleHits.get(s)/10][allPosibleHits.get(s)%10]; 

        for(int t=0; t<allPosibleHits.size(); t++)
            if(mapFrequency[allPosibleHits.get(t)/10][allPosibleHits.get(t)%10]==mostLikely)
                possibleHitsCG.add(allPosibleHits.remove(t)); 

    }

    void setDirection(int locationHit) //picks the direction of the search
    {
        if((locationHit/10-1)>=0&&hitBoardCG[(locationHit/10-1)][(locationHit%10)]==0) //north
            directionCG.add(locationHit-10+100);
        if((locationHit%10+1)<boardSize&&hitBoardCG[locationHit/10][(locationHit%10+1)]==0) //east
            directionCG.add(locationHit+1+200);
        if((locationHit/10+1)<boardSize&&hitBoardCG[(locationHit/10+1)][(locationHit%10)]==0) //south
            directionCG.add(locationHit+10+300);
        if((locationHit%10-1)>=0&&hitBoardCG[locationHit/10][(locationHit%10-1)]==0) //west
            directionCG.add(locationHit-1+400);
    }

    void distractionChangeDiretion() //chnges the direction of the search of ent out of bounds
    {
        direction=false;
    }

    void setDistraction(int directionNum, int spot) //has an approximate target
    {
        originDistant++;
        DistractionCG=-5;
        if (direction==false)
        {
            direction=true;
            if(directionNum==1||directionNum==200)
                directionNum=directionNum+200;
            else if(directionNum==300||directionNum==400)
                directionNum=directionNum-200;
        }
        directionNum=directionNum;
        if(directionNum==1)
            DistractionCG=spot-10+100;
        else if(directionNum==2)
            DistractionCG=spot+1+200;
        else if(directionNum==3)
            DistractionCG=spot+10+300;
        else if(directionNum==4)
            DistractionCG=spot-1+400;
        else
            System.out.println("HIts   setDistraction    EEEROR");
    }

    void removeSpotGeneralList(int spot) //removes the chosen spot from the hit list
    {
        for(int x=0; x<possibleHitsCG.size(); x++)
            if(possibleHitsCG.get(x)==spot)
                possibleHitsCG.remove(x);
    }

    int getSpot(int distinct) //guesses a hit spot
    {
        Random rnd=new Random();
        if(distinct==1)
            return DistractionCG; //directionCG.remove(rnd.nextInt(directionCG.size()));
        else if(distinct==2)
            return directionCG.remove(rnd.nextInt(directionCG.size()));
        else if(distinct==3)
        {
            int tempSpot=possibleHitsCG.remove(rnd.nextInt(possibleHitsCG.size()));
            if (choiceLevel==3&&possibleHitsCG.size()==0)
                setMediumTablePossibilityHits();
            return tempSpot;
        }
        else
            System.out.println(" HIS get spot   EROR    ");
        return -1;
    }
    
    //getters

    ArrayList getPossibleHitsL()
    {
        return possibleHitsCG;
    }

    ArrayList getDirectionL()
    {
        return directionCG;
    }

    int getOriginDistantH()
    {
        return originDistant;
    }

    int distractionH()
    {
        return DistractionCG;
    }

    boolean getDirectionH()
    {
        return direction;
    }
}
