
/**
 * The boards of the computer
 */
import java.util.*;
public class ComputerBoard extends Board
{
    //objects
    private shipRecords compSR=new shipRecords();
    private UserBoard UB;

    //setters
    void setSR(ArrayList <ArrayList<ArrayList<Integer>>> ShipsL)
    {
        compSR.setShips(ShipsL);
    }

    void setUserObject(UserBoard uBoard)
    {
        UB=uBoard;
    }

    void setShipMap()
    {

        Random rnd=new Random();
        int dimensions=super.getDimensions();
        int direction=0; //|=0; -=1
        int xPosition=-1;
        int yPosition=-1;
        int loopStuck=0;
        int shipAmount=0;
        boolean check=false; //while thee is no propership
        int[][] shipBoard=super.getShipBoard();

        compSR.resetAmount();

        for(int size=5; size>1; size--)
        {
            shipAmount++;
            for(int t=0; t<shipAmount; t++)
            {
                check=false;
                loopStuck=0;
                while(check==false)
                {
                    check=true;
                    direction=rnd.nextInt(2);
                    if(direction==0)
                    {
                        xPosition=rnd.nextInt(dimensions);
                        yPosition=rnd.nextInt(dimensions-size+1);
                    }
                    else if(direction==1)
                    {
                        xPosition=rnd.nextInt(dimensions-size+1);
                        yPosition=rnd.nextInt(dimensions);
                    }
                    check=super.spaceCheck(direction, xPosition, yPosition, size);
                    loopStuck++;
                    if(loopStuck==100)
                    {
                        super.resetboards();
                        setShipMap();
                        return;
                    }
                }
                //fill the spts
                super.fillTheShip(direction, yPosition, xPosition, size, compSR);
            }
        }
    }

    void setHitMap(int spotC, Hits hit) //level 1 //finds the level by the abscent //pickspot randomly
    {
        int[][] UshipBoard=UB.getShipBoard();
        int[][] hitBoard=super.getHitBoard();

        int direction=spotC/100;
        int spot=spotC-spotC/100; 
        boolean destroid; 

        int sizeShip=UshipBoard[spot/10][spot%10]/10;
        int directionShip=UshipBoard[spot/10][spot%10]%10;

        //remove from the genral list
        if(sizeShip==0) //if empty hit
        {
            hitBoard[spot/10][spot%10]=1;
            UshipBoard[spot/10][spot%10]=1;
        }
        else if(sizeShip!=0)
        {
            destroid=compSR.removeSpot(spot, UshipBoard[spot/10][spot%10]); //erase from the shiperecord + // recors if destroid
            if(destroid==true)
                shipWreckShipBoard(super.shipWreckHitBoard(spot, sizeShip, directionShip), directionShip, sizeShip, UshipBoard); //ship wreck convert a wreck into wreck
            else if(destroid==false)
            {
                hitBoard[spot/10][spot%10]=2;
                if(directionShip==8)
                    UshipBoard[spot/10][spot%10]=sizeShip*10+2;
                else if(directionShip==9)
                    UshipBoard[spot/10][spot%10]=sizeShip*10+3;
                else
                    System.out.println(" CB setHIt1   WHAAT?1     ");
            }    
            else
                System.out.println("CB setHIt1 EROerR");
        }
        else
            System.out.println("CB setHIt1 EROR");
        hit.removeSpotGeneralList(spot); //remove from the genral list
    }    

    void setHitMap(int spotC, int distinct, Hits hit) //level 2 and higher //knows the level by the existent of distinct //more compelx then if it was level1
    {
        int[][] UshipBoard=UB.getShipBoard();
        int[][] hitBoard=super.getHitBoard();

        int spotAmoount=0;

        int direction=spotC/100;
        int spot=spotC%100; 

        boolean destroid; 

        int sizeShip=UshipBoard[spot/10][spot%10]/10;
        int directionShip=UshipBoard[spot/10][spot%10]%10;

        int nSpot=0;

        //remove from the genral list
        if((distinct==1)&&!(spot/10>=0 && spot/10<super.getDimensions() && spot%10>=0 && spot%10<super.getDimensions())) //if the sequencial ship search goes out of boundries, it will starts search fron the other side
        {
            hit.distractionChangeDiretion();               
            if(direction==1)
                nSpot=-10;
            else if(direction==2)
                nSpot=1;
            else if(direction==3)
                nSpot=10;
            else if(direction==4)
                nSpot=-1;
            hit.setDistraction(direction, (spot+hit.getOriginDistantH()*nSpot));
            setHitMap(hit.getSpot(distinct), distinct, hit);
        }
        else if(sizeShip==0) //if empty hit
        {
            hitBoard[spot/10][spot%10]=1;
            UshipBoard[spot/10][spot%10]=1;
            if(distinct==1) //if it was looking for a target and fell out bounds without destring it start looking on the other side 
            {
                hit.distractionChangeDiretion();               
                if(direction==1)
                    nSpot=10;
                else if(direction==2)
                    nSpot=-1;
                else if(direction==3)
                    nSpot=-10;
                else if(direction==4)
                    nSpot=+1;
                hit.setDistraction(direction, (spot+hit.getOriginDistantH()*nSpot));
                setHitMap(hit.getSpot(distinct), distinct, hit);
            }
        }
        else if(sizeShip!=0) //if not empty
        {
            destroid=compSR.removeSpot(spot, UshipBoard[spot/10][spot%10]); //erase from the shiperecord + // recors if destroid
            if(destroid==true) //destrois th eship //wouldn't work for some reason
            {
                //rewrite the hit board
                //rewrite the ship board
                shipWreckShipBoard(super.shipWreckHitBoard(spot, sizeShip, directionShip), directionShip, sizeShip, UshipBoard);

                //erase all lists
                hit.resetPossibleDirection(); //erase direction,  
                hit.resetDistractionData(); //reset distraction

            }
            else if(destroid==false) //if got hit bu t didn't destroy yet
            {
                hitBoard[spot/10][spot%10]=2;
                if(directionShip==8)
                    UshipBoard[spot/10][spot%10]=sizeShip*10+2;
                else if(directionShip==9)
                    UshipBoard[spot/10][spot%10]=sizeShip*10+3;
                else
                    System.out.println(" CB setHIt2   WHAAT?1     ");

                if(distinct==1) //there is a target
                    hit.setDistraction(direction, spot);//  
                else if(distinct==2) //there is direction
                {
                    hit.setDistraction(direction, spot); //set distraction
                    hit.resetPossibleDirection();
                }
                else if(distinct==3) ///there are no data
                    hit.setDirection(spot);
            }    
            else
                System.out.println(" CB setHIt2   EROR");
        }
        else
            System.out.println("  CB setHIt2  EROerR");
        hit.removeSpotGeneralList(spot); //remove from the genral list

    }    

    void shipWreckShipBoard(int head, int direction, int size, int[][] uShipBoard) //when a ships is destrid it adjust the computer shipBoard
    {
        int finish=0;
        if(direction==2||direction==8)//not suer yet
        {
            finish=(head/10+size);
            for(int y=head/10; y<finish; y++)//
            {
                if(!(y>=0&&y<super.getDimensions()))
                    System.out.print("eror... ");
                else if(uShipBoard[y][head%10]%10==2||uShipBoard[y][head%10]%10==8)
                    uShipBoard[y][head%10]=size*10+4;
                else
                    System.out.println(" CB SHipWreck EROOORR!!!!!!!!!!!!!");
            }

            for (int x=(head%10-1); x<=(head%10+1); x++)
                for(int y=(head/10-1); y<=(head/10+size); y++)
                    if((x>=0&&x<super.getDimensions()&&y>=0&&y<super.getDimensions()))
                        if(!(x==head%10&&y>=head/10&&y<(head/10+size)))
                            uShipBoard[y][x]=1;
        }
        else if(direction==3||direction==9)
        {
            finish=(head%10+size);
            for(int x=head%10; x<finish; x++)
            {
                if(!(x>=0&&x<super.getDimensions()))
                    System.out.print("eror... ");
                else if(uShipBoard[head/10][x]%10==3||uShipBoard[head/10][x]%10==9)
                    uShipBoard[head/10][x]=size*10+4;
                else
                System.out.println(" CB SHipWreck  EROOeORR!!!!!!!!!!!!!");
            }

            for (int x=(head%10-1); x<=(head%10+size); x++)
                for(int y=(head/10-1); y<=(head/10+1); y++)
                    if((x>=0&&x<super.getDimensions()&&y>=0&&y<super.getDimensions()))
                        if(!(y==head/10&&x>=head%10&&x<(head%10+size)))
                            uShipBoard[y][x]=1;
        }

    }
    //getter

    int getTotalShipOnBoard()
    {
        return compSR.getAmountShipsTotal();
    }

    int getBiggestShip()
    {
        return compSR.getBiggestShip();
    }

    int getSmallestShip()
    {
        return compSR.getSmallestShip();
    }

    shipRecords getSB()
    {
        return compSR;
    }

}