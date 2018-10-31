/**
 * Club class stores the details of the club .
 * Stores all the Lab Class Data get and set methods
 * @author LakshmiSaketh
 * @version 04262018
 */
import java.util.*;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Member
{
    private int memberID;
    private String memberName;
    private boolean financial;
    private ArrayList<String> sportList;
    private ArrayList<Booking> bookingList;
    /**
     * Constructor the class Member objects
     */
    public Member()
    {
    }
    /**
     * parameterized concructor 
     * @param name of string datatype
     * @thows IO exception, File found exception
     */
    public Member(String name) throws IOException, FileNotFoundException
    {
       bookingList = new ArrayList<Booking>();
        String[] list = name.split(",");
        this.memberID = Integer.parseInt(list[0].trim());
        this.memberName = list[1].trim();
        this.financial=Boolean.parseBoolean(list[2].trim()); 
        if(list[2].equalsIgnoreCase("true") && list[3]!=null && (list[3].equals("Badminton") || list[3].equals("Basketball")))
        {
            for(int i=3;i<list.length;i++)
            {
        sportList.add(list[i]);
                             
            }
        }
        
    }
        
    
    /**
     * this function is used to get the total duration of the booking
     * @param sport and date 
     * @return int returns the total duration
     */

    public int getTotalDuration(String sportName, LocalDate date)
    {
        int duration = 0;
        
        for(String s : sportList)
        {
            if(s.equalsIgnoreCase(sportName) )
            {               
                for(Booking b : bookingList)
                {
                    
                    if(b.getBookingDate().equals(date))
                    {   
                            DateUtility du=new DateUtility();
                        duration = duration + (int)(du.timeBetweenDateTimes(b.getBookingTime(),b.getBookingEndTime()));
                    }
                }

            }
        }
        return duration;

    }
    
    
    /**
     * This constructor takes in a string and initialises the variables of the member class
     * @param memberData and club Obj
     */
    public Member(String memberData, Club clubObj)
    {
        String[] splitData = memberData.split(",");
        
        try
        {
            
            memberID = Integer.parseInt(splitData[0]);
            memberName = splitData[1];
            sportList = new ArrayList<String>();
            if(splitData[2].equals("true")||splitData[2].equals("false"))
            {
                financial = Boolean.parseBoolean(splitData[2]);         
            }
            else
            {    
                financial=Boolean.parseBoolean(splitData[2]);    
                for(int i = 2;i<splitData.length;i++)
                {
                    for(Sport s : clubObj.sportList)
                    {
                        if(s.getSportName().equalsIgnoreCase(splitData[i]))
                            sportList.add(splitData[i]);
                    }
                    
                }
            }                   
            for(int i = 3;i<splitData.length;i++)
            {
                for(Sport s : clubObj.sportList)
                {
                    if(s.getSportName().equalsIgnoreCase(splitData[i]))
                        sportList.add(splitData[i]);
                    
                }
            }
            bookingList = new ArrayList<Booking>();         
        }
        catch(Exception e)
        {
            String str = "Error  :"+e;
        }
    }
    /**
     * Get method for the member ID
     * @return int of MemberID
     */
    public int getMemberID()
    {
        return memberID;
    }
    
     /**
     * Get method for the member ID
     * @return String of memberName
     */
    public String getMemberName()
    {
        return memberName;      
    }
    
     /**
     * Get method for bookings
     * @return list of bookingList
     */
    public ArrayList<Booking> getBookings()
    {
        return bookingList;
    }
    
    /**
     * Get method for booking OBj
     * @return list of bookingList
     */
    public void addBooking(Booking bookingObj)
    {
        bookingList.add(bookingObj);
    }
    
    /**
     * This function removes the booking object 
     * @param booking obj
     * 
     */

    public void removeBooking(Booking bookingObj)
    {
        bookingList.remove(bookingObj);
    }  
    
    /**
     * Get method for sports played
     * @return list of Sports List
     */
    public ArrayList<String> getSportsPlayed()
    {
        return sportList;
    }
    
    /**
     * get method for financial
     * @return boolean of financial
     */
    public boolean getFinancial()
    {
        return financial;
    }
    
    
    /**
     * this function checks if a member is playing a sport
     * @param sportName of string type
     * @return boolean returns true if the sport is played by a member
     */
    
    public boolean statusSport(String name)
    {
        boolean bool = false;
        for(String s : sportList)
        {
            if(s.equalsIgnoreCase(name))
            {
                bool = true;
            }
        }
        return bool;
    }
    
    
    
    
    
    
        
     

    public String toString()
    {
        return "Member Details{" +
                "Member ID='" + memberID + '\'' +
                ", Member Name='" + memberName + '\'' +
                ", Financial ='" + financial +
                ", Sports Played ='" + sportList.toString() +
                '}';
    }
    
}