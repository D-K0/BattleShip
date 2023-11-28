
/**
 * the actual battle of the game
 */
import java.util.*;
public class Battle 
{
    private Display display; 
    private Menu menu; 
    private ComputerBoard cBoard; 
    private UserBoard uBoard; 
    private Hits hit; 

    private boolean compWon=false;
    private boolean userWon=false;

    public Battle(Display displayL, Menu menuL, ComputerBoard cBoardL, UserBoard uBoardL, Hits hitL) //constructer, and a setter
    {
        display=displayL; 
        menu=menuL; 
        cBoard=cBoardL; 
        uBoard=uBoardL; 
        hit=hitL;
    }

    int game(boolean strt, int level) throws Exception//strts, decides how starts 
    {

        int type=0; //from which hit list the hit would be chosen
        int pause=1;//menu pause reoult
        int  previousSmallestShip=cBoard.getSmallestShip();

        while(cBoard.getTotalShipOnBoard()>0&&uBoard.getTotalShipOnBoard()>0&&pause==1)//while there are ships on the boards for bith sides or the user didn't decide to exit
        {
            //Computer move
            if(!strt)  //if the user lost the draw
            {
                if(level!=1) //if it isn't the first level
                {
                    if(level==3) //if it is level 3
                    {
                        previousSmallestShip=cBoard.getSmallestShip(); //save the smallest ship value
                    }
                    if(hit.isDistractionNotEmpty()) //if there is a certain target to hit
                    {
                        type=1; //look for end
                    }
                    else if(hit.isDiractionNotEmpty()) //if there is a direction to hit 
                    {
                        type=2; //look for direction
                    }
                    else //if there is no idea where to hit
                    {
                        type=3; //look for ship
                    }
                    cBoard.setHitMap(hit.getSpot(type), type, hit); //picks the spot and prepares the next one

                    if(previousSmallestShip+1==cBoard.getSmallestShip()) //if level 3 or 2, and the smallest ship size changed. chnge the possible hit list
                        if(level==2)
                            hit.setMediumPossiblityHits();
                        else if(level==3)
                            hit.setConcentrationList();
                }
                else if(level==1) //if it is just level 1, picks a random spot to shoot
                    cBoard.setHitMap(hit.getSpot(3), hit);
            }

            //User's move
            if(cBoard.getTotalShipOnBoard()>0&&uBoard.getTotalShipOnBoard()>0) //users hit
                pause=uBoard.setHitMap();
            strt=false;
        }

        //resoult analysis
        if(uBoard.getTotalShipOnBoard()==0)
            compWon=true;
        else if(cBoard.getTotalShipOnBoard()==0)
            userWon=true;
        else if(cBoard.getTotalShipOnBoard()<0||uBoard.getTotalShipOnBoard()<0||cBoard.getTotalShipOnBoard()>10||uBoard.getTotalShipOnBoard()>10)
            System.out.println("EERROORR");
        else if(pause==1)
            System.out.println("EE!!!RROORR");
        return pause;
    }

    boolean getCompResoult()
    {
        return compWon;
    }

    boolean getUserResoult()
    {
        return userWon;
    }

}
