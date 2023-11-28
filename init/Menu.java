/**
 * class with most of the menus of the game
 */
import java.util.*;
public class Menu
{
    int start() //opening menu
    {
        int choice=0;
        boolean checker=false;
        Scanner box;
        Input ip=new Input();

        while(checker==false)
        {
            try
            {
                System.out.println("\fMenu: ");
                System.out.println("-press 1- To start a new game.");
                System.out.println("-press 2- Continue previous game.");
                System.out.println("-press 0- Exit game.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=2)
                {
                    if(!(choice==2&&ip.getLengthOfList()==0))
                        checker=true;
                    else //if the are no saved games
                    {
                        System.out.print('\u000C'); //cleans the screen
                        System.out.println("There are no games to continue.");
                    }
                }
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    boolean contnue() //asks the user they would like to continue the game
    {
        boolean checker=false;
        int choice=1;
        Scanner box;

        while(checker==false)
        {
            try
            {
                System.out.println("Would you like to restart? ");
                System.out.println("-press 1- For Yes.");
                System.out.println("-press 0- For No.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }
        return (choice==1);
    }

    int pause()  //if the user wants to pause in the miidle of a game
    {
        int choice=0;
        boolean checker=false;
        Scanner box;
        //Input ip=new Input();

        while(checker==false)
        {
            try
            {
                System.out.println("Menu(you paused): ");
                System.out.println("-press 1- To continue.");
                System.out.println("-press 2- stop, and save the game.");
                System.out.println("-press 0- stop, and exit the game without saving it.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=2)
                    checker=true; 
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int exit()  //if the user wants to exit
    {
        int choice=0;
        boolean checker=false;
        Scanner box;
        while(checker==false)
        {
            try
            {
                System.out.println("Are you sure you would like to exit? ");
                System.out.println("-press 0- For no.");
                System.out.println("-press 1- For Yes.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int levelChoice()  //the user chooses a level(controls the level of difficulty of the computer hit list)
    {
        int choice=0;
        boolean checker=false;
        Scanner box;
        
        while(checker==false)
        {
            try
            {
                System.out.println("choose level: ");
                System.out.println("-press 1- Beginner."); //random
                System.out.println("-press 2- Easy."); //chess
                System.out.println("-press 3- Normal."); //table
                System.out.println("-press 0- Exit."); //exit
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=3)
                    checker=true;
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int draw() //decides who starts the game
    {
        int choice=-1;
        boolean checker=false;
        Scanner box;
        while(checker==false)
        {
            try
            {
                System.out.println("Chooce '1' or '0', and whoever guesses the number right start. \n your guess: ");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&choice<=1)
                    checker=true;
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    String adressChoice(String[][] list, int length) //the user pickes the location o the saved game they would want to continue
    {
        Scanner box=new Scanner(System.in);
        int choice=0; 
        boolean checker=false;
        while(checker==false)
        {
            try
            {
                System.out.println("choose the number of the game which you would like to continue. ");
                System.out.printf(" #  %-15s %-10s \n", "Name:", "Date: ");
                for(int y=0; y<length; y++)
                {
                    if(list[y][0].length()<30)
                        System.out.printf("%2d  %-15s  %-10s\n", (y+1), list[y][0].substring(11, list[y][0].length()-4), list[y][1]);
                    else
                        System.out.printf("%2d  %-15s ...  %-10s\n", (y+1), list[y][0].substring(11, 41), list[y][1]);
                }
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=1||choice<length)
                {
                    checker=true;
                }
                else
                {
                    System.out.print("TRY AGAIN! ");
                    System.out.println("your previous choice: "+choice);
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return list[choice-1][0];
    }

    int dateName()//pick how to sort the list of the saved games
    {
        Scanner box=new Scanner(System.in);
        int choice=0; //|=0; -=1
        boolean checker=false;
        while(checker==false)
        {
            try
            {
                System.out.println("How would you like to sort the list? ");
                System.out.println("-press 0- By date.");
                System.out.println("-press 1- By Adress");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int RemAddShip(int ships) //pick whether add or remove the ship
    {
        int choice=0;
        boolean checker=false;
        Scanner box;

        while(checker==false)
        {
            try
            {
                if(ships<10) ///if there are no more ships to add
                    System.out.println("-press 1- To add a ship.");
                System.out.println("-press 2- To remove a Ship.");
                if(ships==10) //if all ships are on the board
                    System.out.println("-press 0- To finish.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=2)
                    checker=true;
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int getDirection() //pick the direction of the ship
    {
        Scanner box=new Scanner(System.in);
        int choice=0; //|=0; -=1
        boolean checker=false;
        
        while(checker==false)
        {
            try
            {
                System.out.println("Choose the direction in which the ship is turned. ");
                System.out.println("-press 0- For vertical.");
                System.out.println("-press 1- For horizonal");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }
   
    int getSize(shipRecords userSR) //picks the size of the ship
    {
        Scanner box=new Scanner(System.in);
        int choice=0;
        boolean checker=false;

        while(checker==false)
        {
            try
            {
                System.out.println("please choose the size of the ship.");
                if(userSR.getAmountShip2()<4) //if there 2 sized ships to add
                    System.out.println("-press 2- For 2 sized ship.");
                if(userSR.getAmountShip3()<3) //if there 3 sized ships to add
                    System.out.println("-press 3- For 3 sized ship.");
                if(userSR.getAmountShip4()<2) //if there 4 sized ships to add
                    System.out.println("-press 4- For 4 sized ship.");
                if(userSR.getAmountShip5()<1) //if there 5 sized ships to add
                    System.out.println("-press 5- For 5 sized ship.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if((choice==2&&userSR.getAmountShip2()<4)||(choice==3&&userSR.getAmountShip3()<3)||(choice==4&&userSR.getAmountShip4()<2)||(choice==5&&userSR.getAmountShip5()<1))
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int getXPosition(int size, int dimensions, int direction, int remAdd ) //get x coordination
    {
        Display display=new Display();
        Scanner box=new Scanner(System.in);
        char choice=' ';
        int spot=0;
        boolean checker=false;

        while(checker==false)
        {
            try
            {
                System.out.print("please choose  ");
                if(remAdd==1) //to add
                {
                    if(direction==0)
                        System.out.println("where to put the top left point of the ship from spot 'a' to spot j");
                    else if(direction==1)
                        System.out.println("where to put the top left point of the ship from spot 'a' to spot "+(char)('j'-size+1));
                }
                else if(remAdd==2) //to remove
                {
                    direction=3; //no direction 
                    System.out.println("which ship you would like to remove by picking the top left point of the ship from spot 'a' to spot j");

                }
                box=new Scanner(System.in);
                choice=box.next().charAt(0);
                spot=display.convertLettersToNumbers(choice); //convers letters to numbers
                if(spot>=0&&(((direction==3||direction==0)&&spot<dimensions)||(direction==1&&spot<=(dimensions-size)))) //inside bounds
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return spot;
    }

    int getYPosition(int size, int dimensions, int direction, int remAdd) //ship size // dimension board //direction o fthe ship// remove or add
    {
        Scanner box=new Scanner(System.in);
        int choice=0; //location chioce
        boolean checker=false;

        while(checker==false)
        {
            try
            {
                System.out.print("please choose  ");
                if(remAdd==1) //add
                {
                    if(direction==0)
                        System.out.println("where to put the top left point of the ship from spot 1 to spot "+(dimensions-size+1));
                    else if(direction==1)
                        System.out.println("where to put the top left point of the ship from spot 1 to spot "+(dimensions));
                }
                else if(remAdd==2) //remove
                {
                    direction=3;//no direction
                    System.out.println("which ship you would like to remove by picking the top left point of the ship from spot 1 to spot "+(dimensions));
                }

                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&((direction==0&&choice<=dimensions-size+1)||((direction==1||direction==3)&&choice<=dimensions)))
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return (choice-1);
    }

    int getXPosition(int dimension)
    {
        Scanner box=new Scanner(System.in);
        Display display=new Display();
        char choice=' ';
        int spot=0;
        boolean checker=false;

        while(checker==false)
        {
            try
            {
                System.out.println("choose a target to hit, by picking a point between 'a' and 'j', or press 'p' for the menu.");

                box=new Scanner(System.in);
                choice=box.next().charAt(0);
                if(choice!='p'&&choice!='P')
                    spot=display.convertLettersToNumbers(choice);
                else
                    spot=-1;
                if(spot>=-1&&spot<dimension)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return spot;
    }

    int getYPosition(int dimensions)
    {
        Scanner box=new Scanner(System.in);
        int choice=0;
        boolean checker=false;

        while(checker==false)
        {
            try
            {
                System.out.println("choose a target to hit, by picking a point between 1 and "+(dimensions));
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=dimensions)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return (choice-1);
    }

    int qRemoveShip()
    {
        int choice=0;
        boolean checker=false;
        Scanner box;
        while(checker==false)
        {
            try
            {
                System.out.println("would you like to pick a diffrent spot");
                System.out.println("-press 0- For no.");
                System.out.println("-press 1- For Yes.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }  

    int nameListEmpty()
    {
        Scanner box=new Scanner(System.in);
        int choice=0; //|=0; -=1
        boolean checker=false;
        while(checker==false)
        {
            try
            {
                System.out.println("Unfortunatly there are no saved games ");
                System.out.println("-press 0- Exit the game.");
                System.out.println("-press 1- Start a new game");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    int rearengeList()
    {
        Scanner box=new Scanner(System.in);
        int choice=0; //|=0; -=1
        boolean checker=false;
        while(checker==false)
        {
            try
            {
                System.out.println("would you like to rearenge the list?");
                System.out.println("-press 0- for No.");
                System.out.println("-press 1- for Yes.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }

    String findAdress(SortSearch ss, String [][] nameList, Input IP) throws Exception
    {
        String name=" ";
        int index=0;
        int keepGoing=1;
        Scanner box=new Scanner(System.in);
        Display display=new Display();

        Scanner num=new Scanner(System.in);
        int choice=0;
        while(keepGoing==1) //find a game
        {
            display.nameList(nameList, IP, ss);
            System.out.println();
            System.out.print("Enter the name of the game you are looking for \n(Do Not use the follwing charachters: \\ / : * ? \" < > | )  \n(for a new game print   *New   (with the star) ) \n(to exit type    *Exit     (with the star)) \n(To rearenge print   *rearenge   (with the star)) ---> ");
            name = box.nextLine();
            if  (name.toLowerCase().equals("*new")) 
                return "newGame";
            else if  (name.toLowerCase().equals("*exit")) 
                {System.out.println("check"); return "exit";}
            else if(name.toLowerCase().equals("*rearenge"))
                keepGoing=1;
            else if(name.equals("*")) 
                System.out.println("Another Potential bug");
            else
            {
                index=ss.bsearch(name, nameList);
                if(index==-1) //didn't find anything
                    System.out.println("No such game exists"); 
                else 
                    System.out.println("the game \" "+name+"\" is located on spot numer"+(index+1));
                keepGoing=findNewName();
            }
        } 

        while(choice<1||choice>nameList.length) //pick the game
        {
            System.out.println("Enter the location of the game, which you would like to reset. \n your choice: ");
            choice=num.nextInt();
            if(choice<1||choice>nameList.length)
            {
                System.out.println("TRY AGAIN");
                System.out.println("your previous choice: "+choice);
            }
        }
        return "SavedGames\\"+nameList[choice-1][0]+".txt"; //returns adress
    }

    int findNewName()
    {
        Scanner box=new Scanner(System.in);
        int choice=0;
        boolean checker=false;
        while(checker==false)
        {
            try
            {
                System.out.println("would you like to find a new name?");
                System.out.println("-press 0- for No.");
                System.out.println("-press 1- for Yes.");
                box=new Scanner(System.in);
                choice=box.nextInt();
                if(choice>=0&&choice<=1)
                    checker=true;
                else
                {
                    //System.out.print('\u000C'); //cleans the screen
                    System.out.println("Try again,"); //note to the user
                }
            }
            catch(Exception e)
            {   //System.out.print('\u000C'); //cleans the screen
                System.out.println("Try again,"); //note to the user
            }
        }

        return choice;
    }
}
