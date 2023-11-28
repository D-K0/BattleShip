
/**
 * demonstats that the binary search meyhod works
 */
import java.util.*;
public class showSearch
{
    static public void main() throws Exception
    {
        SortSearch ss=new SortSearch(); //object
        String data[][]=new String[5][2]; //data
        //sets the data
        data[0][0]="Hol";
        data[0][1]="2010.01.18-09:35:00";
        data[1][0]="hola";
        data[1][1]="2002.02.14-03:15:59";
        data[2][0]="Diana";
        data[2][1]="1978.08.08-01:05:37";
        data[3][0]="diana";
        data[3][1]="1975.06.13-11:95:01";
        data[4][0]="GN";
        data[4][1]="2020.07.04-04:05:37";
        
        for (int x=0; x<data.length; x++)
            System.out.println(data[x][0]);
            
        System.out.println("print the name that is being lookes for(Case is NOT ignored): ");
        Scanner box=new Scanner(System.in);
        String adress=box.nextLine();
        int res=ss.bsearch(adress, data); //recieve the name to search
        System.out.println("location of the adress(Starting from 0)= "+res);
        
    }
}
