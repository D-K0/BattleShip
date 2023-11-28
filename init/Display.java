
/**
 * method which contains diffrent kinds of message in the game
 */
public class Display
{
    void welcome()
    {
        System.out.println("Welcome to the great Sea Battle!");
    }

    void victory()
    {
        System.out.print('\u000C');
        System.out.println("Congratulations!!!!!!!!!!!!!!!!!!!!!!! \nYou have sank your enemy's fleet and WON!");  
    }

    void defeat()
    {
        System.out.print('\u000C');
        System.out.println("We are Sorry, \nYou have lost your fleet."); 
    }

    void fillUserShip(shipRecords userSR)
    {
        System.out.println("The amount of ships left: ");
        System.out.println("2 units ships: "+(4-userSR.getAmountShip2())+"; 3 units ships: "+(3-userSR.getAmountShip3())+"; 4 units ships: "+(2-userSR.getAmountShip4())+"; 5 units ships: "+(1-userSR.getAmountShip5()));
    }

    void fillUserShip(int size, int direction, int xPosition, int yPosition)
    {
        System.out.println("The size of the ship which you've chose: "+size);
        if(direction!=-1)
        {
            System.out.print("The direction of the ship which you've chose is ");
            if(direction==0)
                System.out.println("vertical (=> | )");
            else if(direction==1)
                System.out.println("vertical (=> - )");
            else 
                System.out.println("display   fillUserShip   EROORRR");
        }
        if(xPosition!=-1)
            System.out.println("The xPosition of the ship is= "+convertNumbersToLetters(xPosition));
    }

    void fillUserShip(int xPosition)
    {
        if(xPosition!=-1)
            System.out.println("The xPosition of the ship is= "+convertNumbersToLetters(xPosition));
    }

    int convertLettersToNumbers(char let)
    {
        int spot=-2;

        if(let=='a'||let=='A')
            spot=0;
        else if(let=='b'||let=='B')
            spot=1;
        else if(let=='c'||let=='C')
            spot=2;
        else if(let=='d'||let=='D')
            spot=3;
        else if(let=='e'||let=='E')
            spot=4;
        else if(let=='f'||let=='F')
            spot=5;
        else if(let=='g'||let=='G')
            spot=6;
        else if(let=='h'||let=='H')
            spot=7;
        else if(let=='i'||let=='I')
            spot=8;
        else if(let=='j'||let=='J')
            spot=9;
        else
            System.out.println("display convertLettersToNumbers eror    Out Of Range");
        return spot;
    }

    char convertNumbersToLetters(int num)
    {
        char letter=' ';
        if(num==0)
            letter='A';
        else if(num==1)
            letter='B';
        else if(num==2)
            letter='C';
        else if(num==3)
            letter='D';
        else if(num==4)
            letter='E';
        else if(num==5)
            letter='F';
        else if(num==6)
            letter='G';
        else if(num==7)
            letter='H';
        else if(num==8)
            letter='I';
        else if(num==9)
            letter='J';
        return letter;
    }

    void nameList(String [][] names, Input IP, SortSearch ss) throws Exception
    {
        int dataAdress;
        boolean contine=true;
        String [][] nameList=names;

        Menu menu=new Menu();
        while(contine==true)
        {
            dataAdress=menu.dateName();
            System.out.println("\f");
            System.out.printf(" #  %-15s %-10s \n", "Name:", "Date: ");
            if(dataAdress==0)
                nameList=ss.sortListDate(IP.getListName());
            else if(dataAdress==1)
                nameList=ss.sortListName(IP.getListName());
            else
                System.out.println("display   nameList    Erroorree XD");
            for(int y=0; y<names.length; y++)
                System.out.printf("%2d  %-15s  %-10s\n", (y+1), names[y][0], names[y][1]);
            if(menu.rearengeList()==0)
                contine=false;
        }
    }
}
