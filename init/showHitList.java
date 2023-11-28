
/**
 * demonstrates how the array lists works in the program.(or at least that it works)
 */
public class showHitList
{
    static public void main(String args[]) throws Exception
    {
        //objects
        ComputerBoard cBoard=new ComputerBoard();
        UserBoard uBoard=new UserBoard();
        cBoard.setUserObject(uBoard);
        Input IP=new Input(); //input
        Hits hit=new Hits(cBoard);
        Menu menu=new Menu();

        cBoard.resetboards(); //reset the computer's boards
        cBoard.setShipMap(); //set the computer's boards
        uBoard.resetboards(); //reset the user's boards
        uBoard.fakeSetShipMap(); //reset the user's boards

        IP.inputConc(); //sets the concentration board

        hit.resetPossibleHits(); //reset hitList
        hit.setPossibleHit(menu.levelChoice(), IP); //set hitList


        for(int x=0; x<hit.getPossibleHitsL().size(); x++) //print the hit list
            System.out.println(hit.getPossibleHitsL().get(x));
    }
}
