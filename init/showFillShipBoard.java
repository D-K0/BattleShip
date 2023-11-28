
/**
 * demonstrates   that the 2D array works
 */
import java.util.*;
public class showFillShipBoard
{
    static public void main(String args[])
    {
        //objects
        ComputerBoard cBoard=new ComputerBoard();
        UserBoard uBoard=new UserBoard();
        uBoard.setCompObject(cBoard);
        cBoard.setUserObject(uBoard);

        System.out.println("Computer Ship board:");
        cBoard.resetboards(); //reset the computer's boards
        cBoard.setShipMap(); //set the computer's boards
        cBoard.printShipBoard();
        
        Scanner box=new Scanner(System.in); //stopper
        System.out.println("press a number to continue");
        int contnue=box.nextInt();
        
        System.out.println("User Ship board:");
        uBoard.resetboards(); //reset the user's boards
        uBoard.setShipMap(); //reset the user's boards
        uBoard.printShipBoard();
    }
}
