/**
 * The boards of the user
 */
import java.util.*;
public class UserBoard extends Board implements usersTool
{
    //objects 
    private Menu menu=new Menu();
    private shipRecords userSR=new shipRecords();
    private ComputerBoard CB;

    void setSR(ArrayList <ArrayList<ArrayList<Integer>>> ShipsL) //sets a ships recors for the user's ships
    {
        userSR.setShips(ShipsL);
    }

    public void setCompObject(ComputerBoard cBoard) //sets the compuetr object in the class
    {
        CB=cBoard;
    }

    public void setShipMap() //sets the ship map
    {

        Display display=new Display();

        int size=0;
        int direction=0; //|=0; -=1
        int xPosition=-1;
        int yPosition=-1;
        int dimensions=super.getDimensions();
        int[][] shipBoard=super.getShipBoard();
        //
        boolean check;
        boolean change=true;
        boolean loopCheck=true;
        int setChoice=0;

        boolean stop;

        while(change==true){
            System.out.print('\u000C');
            if(userSR.getAmountShipsTotal()>0&&userSR.getAmountShipsTotal()<=10)
            {
                super.printShipBoard();
                setChoice=menu.RemAddShip(userSR.getAmountShipsTotal()); //choose to remive,add a ship or start the game
            }
            if(userSR.getAmountShipsTotal()==0||(setChoice==1&&userSR.getAmountShipsTotal()<10)) //add a ship
            {
                check=false;
                while(check==false)
                {
                    check=true;
                    size=-1;
                    direction=-1;
                    xPosition=-1;
                    yPosition=-1;

                    System.out.print('\u000C');
                    System.out.println("your ship board :\n");
                    super.printShipBoard();

                    display.fillUserShip(userSR);
                    size=menu.getSize(userSR); //recievs the size of the ship

                    System.out.print('\u000C');
                    System.out.println("your ship board :\n");
                    super.printShipBoard();

                    direction=menu.getDirection(); //recieves the direction of the ship

                    System.out.print('\u000C');
                    System.out.println("your ship board :\n");
                    super.printShipBoard();

                    display.fillUserShip(size, direction, xPosition, yPosition);
                    xPosition=menu.getXPosition(size, dimensions, direction, 1); //recieves the x coordinate

                    System.out.print('\u000C');
                    System.out.println("your ship board :\n");
                    super.printShipBoard();

                    display.fillUserShip(size, direction, xPosition, yPosition);
                    yPosition=menu.getYPosition(size, dimensions, direction, 1); //recieves the y coordinate

                    check=super.spaceCheck(direction, xPosition, yPosition, size);//checks that are no neigboring or overlaping ships
                    System.out.print('\u000C'); //cleans the screen
                    if(check==false) //checks that are no neigboring or overlaping ships
                    {
                        char Xspot=display.convertNumbersToLetters(xPosition);
                        System.out.println("\ncoordinate ( "+Xspot+", "+(yPosition+1)+") \nunfirtinatly this ship can not be placed here");
                    }
                }
                super.fillTheShip(direction, yPosition, xPosition, size, userSR); //fills the ship
            }
            else if(setChoice==2&&userSR.getAmountShipsTotal()>0&&userSR.getAmountShipsTotal()<=10) //remove ship
            {
                loopCheck=true;
                stop=false;
                while(loopCheck==true)
                {
                    loopCheck=false;
                    xPosition=-1;
                    yPosition=-1;

                    System.out.print('\u000C');
                    System.out.println("your ship board :\n");
                    super.printShipBoard();

                    xPosition=menu.getXPosition(size, dimensions, direction, 2);

                    System.out.print('\u000C');
                    System.out.println("your ship board :\n");
                    super.printShipBoard();

                    display.fillUserShip(xPosition);
                    yPosition=menu.getYPosition(size, dimensions, direction, 2);
                    if(shipBoard[yPosition][xPosition]==0)
                    {
                        int choiceRem=menu.qRemoveShip();
                        if(choiceRem==1)
                            loopCheck=true;
                        else if(choiceRem==0)
                            stop=true;
                    }
                }
                if(stop==false)
                    super.emptyTheShip(yPosition, xPosition, userSR); //empties the ship
            }
            else if(setChoice==0)
                change=false;
            else
                System.out.println("Unfortnatly it is not possible to perform this operation");
        }
    }

    void fakeSetShipMap() //sets the user ship board by using th eautomotic computer methos
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

        userSR.resetAmount();

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
                super.fillTheShip(direction, yPosition, xPosition, size, userSR);
            }
        }
    }

    public int setHitMap()  //picks a location to hit and adjust the map
    {
        int[][] CshipBoard=CB.getShipBoard();
        int[][] hitBoard=super.getHitBoard();

        Display display=new Display();
        int xPosition=-1;
        int yPosition=-1;

        int menuChoice;

        boolean checker=true;

        while(checker==true)
        {
            checker=false;

            System.out.print('\u000C');
            System.out.println("your ship board :\n");
            super.printShipBoard();
            System.out.println("your hit board :\n");
            super.printHitBoard();

            xPosition=menu.getXPosition(super.getDimensions());
            if(xPosition>-1)
            {
                display.fillUserShip(xPosition);
                yPosition=menu.getYPosition(super.getDimensions());

                int sizeShip=CshipBoard[yPosition][xPosition]/10;
                int directionShip=CshipBoard[yPosition][xPosition]%10;
                boolean destroid; 
                if(sizeShip==0&&hitBoard[yPosition][xPosition]==0) //if empty hit
                {
                    hitBoard[yPosition][xPosition]=1;
                    CshipBoard[yPosition][xPosition]=1;
                }
                else if(sizeShip!=0&&hitBoard[yPosition][xPosition]==0)
                {
                    destroid=userSR.removeSpot((yPosition*10+xPosition), CshipBoard[yPosition][xPosition]); //erase from the shiperecord + // recors if destroid
                    System.out.println("destroid: "+destroid);
                    if(destroid==true)
                    //rewrite the hit board
                    //rewrite the ship board
                        shipWreckShipBoard(super.shipWreckHitBoard((yPosition*10+xPosition), sizeShip, directionShip), directionShip, sizeShip, CshipBoard);
                    else if(destroid==false)
                    {
                        hitBoard[yPosition][xPosition]=2;
                        if(directionShip==8)
                            CshipBoard[yPosition][xPosition]=sizeShip*10+2;
                        else if(directionShip==9)
                            CshipBoard[yPosition][xPosition]=sizeShip*10+3;
                        else
                            System.out.println(" UB   setHitMap     WHAAT?1     ");
                    }    
                    else
                        System.out.println(" UB   setHitMap     EROR");
                }
                else if(hitBoard[yPosition][xPosition]>0)
                {
                    System.out.println("you've already tried guessing at this spot.");
                    checker=true;
                }
                else
                    System.out.println(" UB   setHitMap     ERerOR");

            }
            else if(xPosition==-1) //pause menu
            {
                menuChoice=menu.pause();
                if(menuChoice==0||menuChoice==2)
                    return menuChoice;
                else if(menuChoice==1)
                    checker=true;
            }
        }
        return 1;
    }    

    void shipWreckShipBoard(int head, int direction, int size, int[][] cShipBoard) //when a ships is destrid it adjust the computer shipBoard
    {
        int finish=0;
        if(direction==2||direction==8)//not suer yet
        {
            finish=(head/10+size);
            for(int y=head/10; y<finish; y++)//
                if(!(y>=0&&y<super.getDimensions()))
                    System.out.print("Eror... ");
                else if(cShipBoard[y][head%10]%10==2||cShipBoard[y][head%10]%10==8)
                    cShipBoard[y][head%10]=size*10+4;
                else
                    System.out.println("  UB  shipWreckShipBoard    EROOORR!!!!!!!!!!!!!");

            for (int x=(head%10-
                    1); x<=(head%10+1); x++)
                for(int y=(head/10-1); y<=(head/10+size); y++)
                    if((x>=0&&x<super.getDimensions()&&y>=0&&y<super.getDimensions()))
                        if(!(x==head%10&&y>=head/10&&y<(head/10+size)))
                            cShipBoard[y][x]=1;
        }
        else if(direction==3||direction==9)
        {
            finish=(head%10+size);

            for(int x=head%10; x<finish; x++)
                if(!(x>=0&&x<super.getDimensions()))
                    System.out.print("Eror... ");
                else if(cShipBoard[head/10][x]%10==3||cShipBoard[head/10][x]%10==9)
                    cShipBoard[head/10][x]=size*10+4;
                else
                    System.out.println("  UB  shipWreckShipBoard    EROerOORR!!!!!!!!!!!!!");

            for (int x=(head%10-1); x<=(head%10+size); x++)
                for(int y=(head/10-1); y<=(head/10+1); y++)
                    if((x>=0&&x<super.getDimensions()&&y>=0&&y<super.getDimensions()))
                        if(!(y==head/10&&x>=head%10&&x<(head%10+size)))
                            cShipBoard[y][x]=1;
        }
    }
    //getter
    int getTotalShipOnBoard()
    {
        return userSR.getAmountShipsTotal();
    }

    shipRecords getSB()
    {
        return userSR;
    }
}
