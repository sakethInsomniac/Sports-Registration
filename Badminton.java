/**
 * Badminton class stores the details of badminton game.
 * Stores all the Lab Class Data get and set methods
 * @author LakshmiSaketh
 * @version 04262018
 */
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Badminton extends Sport
{

    private boolean isRacquetProvided = false;
    private static final int Slot_Time = 120;
    //private ArrayList<Court> bdList=new ArrayList<Court>();
    /**
     * Constructor for objects of class Badminton
     */
    public Badminton(String data) throws IOException, FileNotFoundException
    {
                 super(data,Slot_Time); // Daily Max time for slot time is 3Hrs
    }
    
     /**
     * this function checks the limit of the slot
     * @param time as input to check
     * @return bool datatype
     */
    public boolean timeCheck(int timecheck)
    {
        if(Slot_Time >= timecheck)
        {   
            return true;
        }    
        else
            return false;
    }
    
    /**
     * get method for the racquet required
     * @return boolean status
     */
     public boolean isRacquetProvided() {
        return isRacquetProvided;
    }

    /**
     * set method for racquet required
     * @param boolean type racquet required.
     */
    public void setRacquetProvided(boolean racquetProvided) {
        isRacquetProvided = racquetProvided;
    }

    public String toString()
    {
         return  "Badminton{" +
                "Slot Time Booking per Day =" + Slot_Time +
                '}';
    }

}
