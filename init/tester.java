
/**
 * SeaBattle
 *
 * @author (DK)
 * @version (Dec. 2018, Jan 2019)
 */
import java.util.*;
public class tester
{
    static public void main(String[] args) throws Exception
    {
        //objects
        Display display=new Display();
        Menu menu=new Menu();

        ComputerBoard cBoard=new ComputerBoard();
        UserBoard uBoard=new UserBoard();
        Hits hit=new Hits(cBoard); //incharge over the hit list of the computer
        Output OP=new Output();
        Input IP=new Input();
        SortSearch ss=new SortSearch();
        Battle battle=new Battle(display, menu, cBoard, uBoard, hit);

        //sets the objects of the oponents
        uBoard.setCompObject(cBoard);
        cBoard.setUserObject(uBoard);

        int choice1=0; //choice whether to exit, start a new gme, or continue an old one

        int levelChoice=-4; //chooses level
        boolean contnue=true; //controls the loop of the whole game
        int pause=-3; //the ersoult of the menu in the game(pause menu)
        boolean drawV=true; //controls the draw which decides who starts

        int boardSize=cBoard.getDimensions(); //the size of the boards of the game

        while(contnue==true) //while the usert decides not to exit
        {
            display.welcome(); //tells welcome to the user
            choice1=menu.start(); //presents the starting menu
            if(choice1==1||choice1==2) //if the user desided not to exit
            {

                if(choice1==2) //start an old game
                {

                    IP.inputList(); //inputs thhe list with the names of the previous games
                    if(IP.getLengthNameList()==0) //if there are no saved games
                        choice1=menu.nameListEmpty(); //the user has an option to exit, or start a new game
                    else if(IP.getLengthNameList()>0) //if there are saved games
                    {
                        if(menu.findAdress(ss, IP.getListName(), IP)=="newGame") //if the user decides to start new game
                            choice1=1;
                        else if(menu.findAdress(ss, IP.getListName(), IP)=="exit") //if the user decieds to exit
                        {
                            levelChoice=0;
                            contnue=false;
                            System.out.println("Exit."+choice1);
                        }
                        else
                        {
                            IP.inputData(menu.adressChoice(IP.getListName(), IP.getLengthNameList())); //inputts data

                            cBoard.resetboards(IP.getShipBoardC(), IP.getHitBoardC()); //resetts the board of the computer
                            uBoard.resetboards(IP.getShipBoardU(), IP.getHitBoardU()); //resets the boeards of the user
                            hit.setPossibleHits(IP.getPossibleHitsList()); //sets the hit list
                            hit.setPossibleDirection(IP.getDirectionList()); //sets the hit list with direction
                            hit.setDistractionData(IP.getDistraction(), IP.getOriginDistant(), IP.getDirection()); //sets the hit list with a certain target

                            cBoard.setSR(IP.getCompShips()); //setts the computer Ship record
                            uBoard.setSR(IP.getUserShips()); //setts the user ship record
                        }
                    }
                    else  //eror message
                        System.out.println("ERor tester");

                }
                if(choice1==1) //starts new game
                {
                    //sets new game
                    levelChoice=menu.levelChoice(); //level
                    if(levelChoice!=0) //if the user did'nt decide to exit
                    {
                        cBoard.resetboards(); //reset the computer's boards
                        cBoard.setShipMap(); //set the computer's boards

                        uBoard.resetboards(); //reset the user's boards
                        uBoard.setShipMap(); //reset the user's boards

                        IP.inputConc(); //inputs the concentration table(has calculated amount of possible ships in a certain spot)

                        hit.resetPossibleDirection(); //erase direction,  
                        hit.resetDistractionData(); //reset distraction

                        hit.resetPossibleHits(); //reset hitList
                        hit.setPossibleHit(levelChoice, IP); //set hitList

                        Draw draw=new Draw(); //makes a draw of who starts
                        drawV=draw.draw();
                    } 
                }
                if(levelChoice!=0) //if the user did'nt decide to exit
                {
                    pause=battle.game(drawV, levelChoice); //the actual game, sends the resoult of the game
                    if(pause==1)
                    {
                        if(battle.getCompResoult()&&!battle.getUserResoult()) //lost
                            display.defeat();
                        else if(battle.getUserResoult()&&!battle.getCompResoult()) //won
                            display.victory();
                    }
                    else if(pause==2)//if the user decided to save the game
                    { 
                        OP.setSetters(cBoard, uBoard, hit, levelChoice); //sets the data in th output class
                        OP.outputData(); //outputs the data of the game
                    }
                    if(pause==1||pause==2) //suggests to start over
                    {
                        contnue=menu.contnue();
                    }
                }
                if(levelChoice==0) //if the user decide to exit)
                {
                    contnue=false;
                    System.out.println("Exit.");
                }
            }

            else if(choice1==0) //exit program
            {
                contnue=false;
                System.out.println("Exit.");
            }
            if(levelChoice==0) //if the user did'nt decide to exit)
            {
                contnue=false;
                System.out.println("Exit.");
            }
        }
    }
}
