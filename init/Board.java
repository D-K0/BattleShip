
/**
 * super class for the boards of the game
 */
import java.util.*;
public abstract class Board
{
    //board data
    private int boardSize=10;
    private int[][] shipBoard=new int[boardSize][boardSize];
    private int[][]  hitBoard=new int[boardSize][boardSize];

    void resetboards()
    {
        for(int y=0; y<boardSize; y++)
            for(int x=0; x<boardSize; x++)
            {
                shipBoard[y][x]=0;
                hitBoard [y][x]=0;
            }
    }

    void resetboards(int[][] shipBoardL, int[][] hitBoardL)
    {
        shipBoard=shipBoardL;
        hitBoard=hitBoardL;
    }

    void printHitBoard()
    {
        Display display =new Display();
        for(int y=-1; y<boardSize; y++)
        {
            for(int x=-1; x<boardSize; x++)
            {
                if(x==-1&&y==-1)
                    System.out.print("   ");
                else if(y==-1)
                    if(x>=0&&x<=9)
                        System.out.print(display.convertNumbersToLetters(x)+" ");
                    else
                        System.out.println("board    printShipBoard   EROR");
                else if(x==-1){
                    System.out.print((y+1)+" ");
                    if(y<9)
                        System.out.print(" ");}
                else if(x>=0&&y>=0){
                    if(hitBoard[y][x]==0)
                        System.out.print("O "); //clean spot
                    else if(hitBoard[y][x]%10==1)
                        System.out.print("X "); //empty hit, or around ruins
                    else if(hitBoard[y][x]%10==2) 
                        System.out.print(" ۞  "); //good Hit
                    else if(hitBoard[y][x]%10==3)
                        System.out.print("Ø ");} //destrid
                else
                    System.out.print("board    printHitBoard   ?!?!?!?! ");
            }
            System.out.println();
        }
    }

    void printShipBoard()
    {
        Display display=new Display();
        System.out.println();
        for(int y=-1; y<boardSize; y++)
        {
            for(int x=-1; x<boardSize; x++)
            {
                if(x==-1&&y==-1)
                    System.out.print("   ");
                else if(y==-1)
                    if(x>=0&&x<=9)
                        System.out.print(display.convertNumbersToLetters(x)+" ");
                    else
                        System.out.println("board    printShipBoard   EROR");
                else if(x==-1){
                    System.out.print((y+1)+" ");
                    if(y<9)
                        System.out.print(" ");}

                else if(x>=0&&y>=0)
                    if(shipBoard[y][x]/10==0&&shipBoard[y][x]%10==0)
                        System.out.print("O "); //clean spot
                    else if(shipBoard[y][x]/10==0&&shipBoard[y][x]%10==1)
                        System.out.print("X "); //empty hit
                    else if(shipBoard[y][x]/10==2&&(shipBoard[y][x]%10==8||shipBoard[y][x]%10==9)) 
                        System.out.print("2 "); //ship
                    else if(shipBoard[y][x]/10==3&&(shipBoard[y][x]%10==8||shipBoard[y][x]%10==9)) 
                        System.out.print("3 "); //ship
                    else if(shipBoard[y][x]/10==4&&(shipBoard[y][x]%10==8||shipBoard[y][x]%10==9)) 
                        System.out.print("4 "); //ship
                    else if(shipBoard[y][x]/10==5&&(shipBoard[y][x]%10==8||shipBoard[y][x]%10==9)) 
                        System.out.print("5 "); //ship
                    else if(shipBoard[y][x]/10==2&&(shipBoard[y][x]%10==2||shipBoard[y][x]%10==3)) 
                        System.out.print("❷ "); //good Hit
                    else if(shipBoard[y][x]/10==3&&(shipBoard[y][x]%10==2||shipBoard[y][x]%10==3)) 
                        System.out.print("❸ "); //good Hit
                    else if(shipBoard[y][x]/10==4&&(shipBoard[y][x]%10==2||shipBoard[y][x]%10==3)) 
                        System.out.print("❹ "); //good Hit
                    else if(shipBoard[y][x]/10==5&&(shipBoard[y][x]%10==2||shipBoard[y][x]%10==3)) 
                        System.out.print("❺ "); //good Hit
                    else if(shipBoard[y][x]/10==2&&shipBoard[y][x]%10==4) 
                        System.out.print("② "); //destroid
                    else if(shipBoard[y][x]/10==3&&shipBoard[y][x]%10==4) 
                        System.out.print("③ "); //destroid
                    else if(shipBoard[y][x]/10==4&&shipBoard[y][x]%10==4) 
                        System.out.print("④ "); //destroid
                    else if(shipBoard[y][x]/10==5&&shipBoard[y][x]%10==4) 
                        System.out.print("⑤ "); //destroid
                    else 
                        System.out.print("board    printShipBoard   ERWHAAA?! ");
                else
                    System.out.print("board    printShipBoard   ?!?!?!?! ");
            }
            System.out.println();
        }
        System.out.println();
    }

    boolean spaceCheck(int direction, int xPosition, int yPosition, int size)  //checks that are no neigboring or overlaping ships
    {
        if(direction==0)
        {
            for (int x=(xPosition-1); x<=(xPosition+1); x++)
                for(int y=(yPosition-1); y<=(yPosition+size); y++)
                    if((x>=0&&x<boardSize&&y>=0&&y<boardSize))
                        if(shipBoard[y][x]/10!=0)
                            return false; 
        }
        else if(direction==1)
        {
            for (int x=(xPosition-1); x<=(xPosition+size); x++)
                for(int y=(yPosition-1); y<=(yPosition+1); y++)
                    if((x>=0&&x<boardSize&&y>=0&&y<boardSize))
                        if(shipBoard[y][x]/10!=0)
                            return false; 
        }
        else
            System.out.println("Board   spaceCheck   Eror");
        return true;
    }

    void fillTheShip(int direction, int yPosition, int xPosition, int size, shipRecords SR) //fills the ship in the ship map
    {
        SR.addShipList(size);
        if(direction==0)
        {
            for(int y=yPosition; y<(yPosition+size); y++)
            {
                shipBoard[y][xPosition]=(size*10)+8;  
                SR.addShip(y*10+xPosition, size);
            }
        }
        else if(direction==1)
        {
            for(int x=xPosition; x<(xPosition+size); x++)
            {
                shipBoard[yPosition][x]=(size*10)+9;
                SR.addShip(yPosition*10+x, size);
            }
        }
        SR.addToAmount(size);
    }

    void emptyTheShip( int yPosition, int xPosition, shipRecords SR) //removes the ship from the ship map
    {
        int size=shipBoard[yPosition][xPosition]/10;
        int finish=0;
        int head=yPosition*10+xPosition;
        if(shipBoard[yPosition][xPosition]%10==8)
        {
            finish=(yPosition+size);
            for(int y=yPosition; y<finish; y++)
            {
                if(!(y>=0&&y<=boardSize))
                {
                    System.out.print("MDA... ");
                    return;
                }
                else  if(y==boardSize||shipBoard[y][xPosition]==0)
                {
                    finish=yPosition;
                    y=y-size-1;
                    head=(y+1)*10+xPosition;
                }
                else
                    shipBoard[y][xPosition]=0; 
            }
        }
        else if(shipBoard[yPosition][xPosition]%10==9)
        {
            finish=(xPosition+size);
            for(int x=xPosition; x<finish; x++)
            {
                if(!(x>=0&&x<=boardSize))
                {
                    System.out.print("MDA... ");
                    return;
                }
                else if(x==boardSize||shipBoard[yPosition][x]==0)
                {
                    finish=xPosition;
                    x=x-size-1;
                    head=yPosition*10+(x+1);
                }
                else

                    shipBoard[yPosition][x]=0;
            }
        }

        SR.removeShip(head, size);
        SR.removeToAmount(size);
    }

    int shipWreckHitBoard(int spot, int size, int direction) //when a ships is destrid it adjust the hit board
    {
        int finish=0;
        int head=spot;
        if(direction==2||direction==8)//not suer yet
        {
            finish=(spot/10+size);
            for(int y=spot/10; y<finish; y++)//
            {
                if(hitBoard[y][spot%10]==3)
                    System.out.println("EROOORR!!!!!!!!!!!!!");
                if(!(y>=0&&y<boardSize))
                {
                    System.out.print("MDA... ");
                    return -1;
                }
                if(hitBoard[y][spot%10]==0||y==boardSize)
                {
                    finish=spot/10;
                    y=y-size-1;
                    head=y*10+spot%10;
                }
                else
                    hitBoard[y][spot%10]=3;  
            }

            for (int x=(head%10-1); x<=(head%10+1); x++)
                for(int y=(head/10-1); y<=(head/10+size); y++)
                    if((x>=0&&x<boardSize&&y>=0&&y<boardSize))
                        if(!(x==head%10&&y>=head/10&&y<(head/10+size)))
                            hitBoard[y][x]=1;
        }
        else if(direction==3||direction==9)
        {
            finish=(spot%10+size);

            for(int x=spot%10; x<finish; x++)
            {
                if(hitBoard[spot/10][x]==3)
                    System.out.println("Board   SWhipWreck      EROOORR!!!!!!!!!!!!!");
                else if(!(x>=0&&x<boardSize))
                {
                    System.out.print("MDA... ");
                    return-1;
                }
                else if(hitBoard[spot/10][x]!=3||x==boardSize)
                {
                    finish=spot%10;
                    x=x-size-1;
                    head=x+spot/10*10;
                }
                else
                    hitBoard[spot/10][x]=3;
            }

            for (int x=(head%10-1); x<=(head%10+size); x++)
                for(int y=(head/10-1); y<=(head/10+1); y++)
                    if((x>=0&&x<boardSize&&y>=0&&y<boardSize))
                        if(!(y==head/10&&x>=head%10&&x<(head%10+size)))
                            hitBoard[y][x]=1;
        }
        return head;
    }
    //getter
    int[][] getShipBoard()
    {
        return  shipBoard;
    }

    int[][] getHitBoard()
    {
        return  hitBoard;
    }

    int getDimensions()
    {
        return boardSize;
    }
    //abstract methods
    abstract void setSR(ArrayList <ArrayList<ArrayList<Integer>>> ShipsL);
    
    abstract int getTotalShipOnBoard();
    
    abstract shipRecords getSB();
    
    
}
