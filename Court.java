import java.time.*;
import java.util.*;

/**
 * Provides date utility methods for converting dates from string to 
 * Java 8 date formats, and vice versa.
 * 
 * @Lakshmi Saketh
 * @04262018
 */
public class Court
{
    private ArrayList<Booking> courtBookings;
    private int courtId;
  
    /**
     * parameterized constructor 
     * @param int of CourtID
     */
    public Court(int courtId)
    {
        this.courtId=courtId;
        courtBookings=new ArrayList<Booking>();
        
    }
    
    /**
     * get method used for getting court ID
     * @return courtID ofint
    */
    public int getCourtId()
    {
        return courtId;
    }
    /**
     * this function is used to add a booking
     * @param BOOKING OBJ
     * 
     */
    public void addBooking(Booking b)
    {
        courtBookings.add(b);
        

    }
    
    /**
     * this gives us array list of courtbookings
     * @return the court bookings
    */
    public ArrayList<Booking> getCourtBookings()
    {
        return courtBookings;
    }
    /**
     * this function is used for setting court Bookings
     * @param Array List of court Bookings
     * 
     */
    public void setCourtBookings(ArrayList<Booking> courtBookings)
    {
        this.courtBookings=courtBookings;
        

    }
    
    
    
        /**
         * this function is used to check the availability status of the court
         * @param date date,time,duration
         * @return boolean
         */
        public boolean availabilityStatus(LocalDate date, LocalTime time, int duration)
        {
    
            for(Booking b : courtBookings)   
            {
                if(b.getBookingDate().isEqual(date))
                {                               
                    
                    if(!b.overTime(time, duration))
                    {
                        
                        return true;
                    }       
                    return false;
                }
                else    
                    return true;
            }
    
            return true;
        }

    

}
