/**
 * BasketBall class stores the details of the basketball Game.
 * Stores all the Lab Class Data get and set methods
 * @author LakshmiSaketh
 * @version 04262018
 */
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Basketball extends Sport
{
    private static final int Slot_Time = 180;
    private ArrayList<Court> bList=new ArrayList<Court>();
    private double netHeight;
    /**
     * Constructor for objects of class BasketBall
     */
    public Basketball(String data) throws IOException, FileNotFoundException
    {
        
         
        
        super(data,Slot_Time);// Daily Max time for slot time is 3Hrs
    
    
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
     * get method for net height
     * @return net height
     */
    public double getNetHeight() {
        return netHeight;
    }

    /**
     * set method for net height
     * @param netHeight
     */
    public void setNetHeight(double netHeight) {
        this.netHeight = netHeight;
    }
    
    public String toString()
    {
        return  "BasketBall{" +
                "Slot Time Booking per Day =" + Slot_Time +
                '}';

    }

}
