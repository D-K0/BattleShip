
/**
 * contains sorting and search algorithms
 */
import java.util.*;
import java.text.SimpleDateFormat;
public class SortSearch
{
    private String [][] nameListG;
    int bsearch(String name, String [][] nameList) //binary search
    {
        return bsearchRecursion(name,  nameList, 0, nameList.length-1); //?-1
    }

    int bsearchRecursion(String name, String [][] nameList, int first, int last){ //binary search in recuesion
        int mid=(first+last)/2; //find the mid integer
        if(name.toLowerCase().equals(nameList[mid][0].toLowerCase())) //if the location is found stop the loop 
            return mid; //and return the loation
        else if(name.toLowerCase().compareTo(nameList[mid][0].toLowerCase())>0&&first!=last) //if first is the last integer and the search id is bigger then the middle one
            return bsearchRecursion(name, nameList, (mid+1), last); //go to the right side of the list
        else if(name.toLowerCase().compareTo(nameList[mid][0].toLowerCase())<0&&first!=last) //if first is the last integer and the search id is smaller then the middle one
            return bsearchRecursion(name, nameList, first, mid);    //go to the left side of the list
        else //if nothing was found return -1
            return -1;
    }

    String[][] sortListDate(String [][] nameListL)  throws Exception //sort the name list by date
    {
        nameListG=nameListL;
        mergeSortDate(nameListG, 0, (nameListG.length-1));
        return nameListG;
    }

    void mergeDate(String [][] listName, int first, int mid, int last) throws Exception
    {
        Date date1;
        Date date2;
        for(int x1=first; x1<=mid; x1++)
            for(int x2=last; x2>mid; x2--)
            {
                date1=new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").parse(listName[x1][1]);
                date2=new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").parse(listName[x2][1]);
                if(date1.compareTo(date2)>0)
                    swap(listName, x1, x2);
            }
    }

    void mergeSortDate(String[][] listName, int first, int last) throws Exception
    {
        if(first>=last) return;
        int mid=(first+last)/2;
        mergeSortDate(listName, first, mid);
        mergeSortDate(listName, (mid+1), last);
        mergeDate(listName, first, mid, last);
    }

    String[][] sortListName(String [][] nameListL) //sort the name list by name
    {
        nameListG=nameListL;
        mergeSortName(nameListG, 0, (nameListG.length-1));
        return nameListG;
    }

    void mergeName(String [][] listName, int first, int mid, int last)
    {
        for(int x1=first; x1<=mid; x1++)
            for(int x2=last; x2>mid; x2--)
                if(listName[x1][0].toLowerCase().compareTo(listName[x2][0].toLowerCase())>0)
                    swap(listName, x1, x2);

    }

    void mergeSortName(String[][] listName, int first, int last){
        if(first>=last) return;
        int mid=(first+last)/2;
        mergeSortName(listName, first, mid);
        mergeSortName(listName, (mid+1), last);
        mergeName(listName, first, mid, last);
    }

    void swap(String [][] listName, int a, int b) //swap method
    {
        String tempName=listName[a][0];
        String tempDate=listName[a][1];

        listName[a][0]=listName[b][0];
        listName[a][1]=listName[b][1];

        listName[b][0]=tempName;
        listName[b][1]=tempDate;
    }

}
