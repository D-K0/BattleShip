/**
 * fake tester in which the user ship board is filled automaticly
 */
import java.util.*;
public class fakeTester
{
    static public void main(String[] args) throws Exception
    {
        //objects
        Display display=new Display();
        Menu menu=new Menu();
        ComputerBoard cBoard=new ComputerBoard();
        UserBoard uBoard=new UserBoard();
        Hits hit=new Hits(cBoard); 
        Output OP=new Output();
        Input IP=new Input();
        SortSearch ss=new SortSearch();
        Battle battle=new Battle(display, menu, cBoard, uBoard, hit);
        
        //sets the obbosits objects in each other
        uBoard.setCompObject(cBoard);
        cBoard.setUserObject(uBoard);

        int choice1=0;
        int levelChoice=-4;
        boolean contnue=true;
        int pause=-3;
        boolean drawV=true;

        int boardSize=cBoard.getDimensions();

        while(contnue==true)//goes while the game is active
        {
            display.welcome();
            choice1=menu.start(); //starting menu
            if(choice1==1||choice1==2) //doen't exit
            {
                if(choice1==2) //start an old game
                {
                    IP.inputList();//inputs thhe list with the names of the previous games
                    if(IP.getLengthNameList()==0) //if there are no saved games
                        choice1=menu.nameListEmpty(); //the user has an option to exit, or start a new game
                    else if(IP.getLengthNameList()>0) //if there are saved games
                    {
                        if(menu.findAdress(ss, IP.getListName(), IP)=="newGame") //if the user decides to start new game
                            choice1=1;
                        else if(menu.findAdress(ss, IP.getListName(), IP)=="exit")//if the user decieds to exit
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
                    else //eror message
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
                        uBoard.fakeSetShipMap(); //reset the user's boards

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
                    if(pause==1||pause==2)
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

            if(levelChoice==0) //if the user decide to exit)
            {
                contnue=false;
                System.out.println("Exit.");
            }
         }
    }
}
