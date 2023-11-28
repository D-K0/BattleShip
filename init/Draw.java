
/**
 * draw which decides who starts
 */
import java.util.*;
public class Draw
{
    boolean draw()
    {
        //objects
        Random rnd=new Random();
        Menu menu=new Menu();
        int choice=-1;
        int resoult=-1;
        
        choice=menu.draw(); //menu

        resoult=rnd.nextInt(2); //randomly chooses between 0, amd 1
        
        if(choice==resoult)
            System.out.println("Resoult of the draw: "+resoult+"\nCongratulation! you start.");
        else
            System.out.println("Resoult of the draw: "+resoult+"\nUnfortunatly, you lost.");
            
        return(choice==resoult);
    }
}
