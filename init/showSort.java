
/**
 * demonstrayes that the merge sort is functioning, and also the recursion
 */
import java.util.*;
public class showSort
{
    static public void main() throws Exception
    {
        SortSearch ss=new SortSearch(); //object
        String data[][]=new String[5][2]; //list
        //sets the list
        data[0][0]="Hol";
        data[0][1]="2010.01.18-09:35:00";
        data[1][0]="hola";
        data[1][1]="2002.02.14-03:15:59";
        data[2][0]="Diana";
        data[2][1]="1978.08.08-01:05:37";
        data[3][0]="diana";
        data[3][1]="1975.06.13-11:95:01";
        data[4][0]="ABC";
        data[4][1]="2020.07.04-04:05:37";

        System.out.println("original list");
        for (int x=0; x<data.length; x++)
            System.out.println(data[x][0]+" - "+data[x][1]);
        System.out.println();

        System.out.println("sorted by dates:");
        data=ss.sortListDate(data);
        for (int x=0; x<data.length; x++)
            System.out.println(data[x][0]+" - "+data[x][1]);
        System.out.println();

        System.out.println("sorted by names:");
        data=ss.sortListName(data);
        for (int x=0; x<data.length; x++)
            System.out.println(data[x][0]+" - "+data[x][1]);

    }
}
