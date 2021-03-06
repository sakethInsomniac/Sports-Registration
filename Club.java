/**
 * Club class stores the details of the club .
 * Stores all the Lab Class Data get and set methods
 * @author LakshmiSaketh
 * @version 04262018
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.*;
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Club
{
    // Variables declared
    public static ArrayList<Member> memberList;
    public static ArrayList<Sport> sportList;
    String clubName;
    final static LocalTime courtOpenTime = LocalTime.of(8, 00, 00, 00);
    final static LocalTime courtEndTime = LocalTime.of(23, 00,00,00);
    /**
     * parameterized constructor with the string name
     */
    public Club(String name)
    {
        clubName = name;
        memberList = new ArrayList<Member>();
        sportList = new ArrayList<Sport>();
        
    }
    
    
        /**
         * accessor for generating court list
         */
        public ArrayList<Sport> getSportList()
        {
            return sportList;
        }
    
        /**
         * mutator to access courtList list
         *
         * @param courtList user provided data for courtList list
         */
        public void setSportList(ArrayList<Sport> SportList)
        {
            this.sportList=sportList;
        }
        /**
         * accessor for generating court list
         */
        public ArrayList<Member> getMemberList()
        {
            return memberList;
        }
    
        /**
         * mutator to access courtList list
         *
         * @param courtList user provided data for courtList list
         */
        public void setMemberList(ArrayList<Member> memberList)
        {
            this.memberList=memberList;
        }
    /**
     * This helps to identify the member with their ID
     * @param member ID of int
     * @return Member object.
     * 
     */
    public Member searchMember(int id)
    {
        Member var = null;
        try
        {
        for(Member m : memberList)
        {
            if(m.getMemberID() == id)
            {
                var = m;
            }       
        }
        if(var==null)
        {   
            String str = "Could not find the member with the ID";
            
        }
        }
        catch(Exception e)
        {
            System.out.println("Exception:" + e);
        }
        return var;        
    }
    
    /**
     * this function helps to find the identity of member
     * @param id of int type it is member id
     * @return boolean whether the memeber with that id exist or not
     * 
     */
    public boolean memberIdentity(int id)
    {
        boolean bool = false;
        for(Member mem : memberList)
        {
            if(mem.getMemberID() == id)
                bool = true;
        }

        return bool;

    }
    
     /**
     * this function helps to identify the court based on ID
     * @param ID of int data type
     * @return object of Court
     * 
     */
    public Court searchCourt(int courtID)
    {
        for(Sport sportObj : sportList)
        {
            ArrayList<Court> CourtList = sportObj.getCourtList();
            for(Court courtObj : CourtList)
            {
                if(courtObj.getCourtId()==courtID)
                {
                    return courtObj;
                }
            }
        }
        return null;
    }
    
    /**
     * this function used for validation of the time of office
     * @param date,time,duration
     * @return int returns the availability as an Integer
     */
    public int validateTime(LocalDate checkDate, LocalTime checkTime, int checkDuration)
    {
        Period difference = LocalDate.now().until(checkDate);
        int checkYears = difference.getYears();
        int checkMonths = difference.getMonths();
        int checkDays = difference.getDays();
        if(checkYears < 0 || checkMonths < 0 || checkDays < 0)
            return -1;
        else if(checkYears == 0)
        {
            if(checkMonths == 0)
            {
                if(checkDays <=7 && checkDays !=0)
                {
                    if((checkTime.isAfter(courtOpenTime)||checkTime.equals(courtOpenTime))&& checkTime.plusMinutes(checkDuration).isBefore(courtEndTime))
                    {
                        return 1;
                    }    
                    else
                        return 2;
                }
                else if(checkDays==0)
                {
                    if(checkTime.isAfter(LocalTime.now()))
                        return 1;
                    else
                        return -1;
                }
                else
                    return 3;
            }
            else
                return 5;
        }
        else
            return 1;   
    }
    
    
    /**
     * this function used for getting the sportObj based on the string sportname
     * @param sport Name fromuser
     * @return sport object
     * 
     */
    public Sport findSport(String sport)
    {
        Sport sportObj = null;
        //System.out.println(sportList.toString());
        for(Sport s : sportList)
        {
            if(s.getSportName().equals(sport))
            {
                System.out.println(s.getSportName());
                sportObj= s;
            }
        }
        return sportObj;
    }
    
        /**
     * This functions used to read file of Sports
     * 
     */

    public void fileReadSports() throws IOException ,FileNotFoundException
    {
        Sport sp=null;
        try
        {
        ArrayList<String> sportData = FileUtility.readFromFile("sports.txt");
        for(String sportString : sportData)
        {
            String[] data = sportString.split(",");
            if(data[0].equalsIgnoreCase("Basketball"))
            {
                sp = new Basketball(sportString);
                   
            }           
            else if(data[0].equalsIgnoreCase("Badminton"))
            {
                sp = new Badminton(sportString);
                
            }
           
            else
            {
                System.out.println("Please send proper file Sports.txt");
            }
            sportList.add(sp); 
            
        }
        }
        catch(Exception e)
        {
            System.out.println("Exception:"+e);
        }
                
    }
    
     /**
     * this function used to read the file members from members.txt
     * @param club object
     * 
     */
    public void fileReadMembers(Club clubObj) throws IOException
    {
        Member b;
        
        ArrayList<String> memberData = FileUtility.readFromFile("members.txt");
        for(String memObj : memberData)
        {
            b= new Member(memObj, clubObj);
            memberList.add(b);
        }   
    } 
    
    
    /**
     * this function is used to write the bookings in bookings.txt
     * @return ArrayList of string data type.
     * 
     */
    public ArrayList<String> fileWriteBookings()
    {
        ArrayList <String> bookingList = new ArrayList<String>();
        try
        {       
            for(Member mem : memberList)
            {
                
                ArrayList<Booking> bookingsList = mem.getBookings();
                if(bookingsList!=null)
                {
                for(Booking b : bookingsList)
                {
                    if(b!=null)
                    {
                        String Data = b.storeDetails();
                        bookingList.add(Data);
                    }
                    else
                        System.out.println("IT should hav some data to write");
                }               
            }
            
           }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
        return bookingList;
    }
    
    /**
     *  This helps to read bookings from bookings.txt
     *  @param ClubOBJ of club datatype.
     */
     public void readBookings(Club clubObj) throws IOException
    {
        ArrayList<String> bookingsList = FileUtility.readFromFile("Bookings.txt");
        for(String data : bookingsList)
        {
            try
            {
            Booking booking = new Booking(data,this);
            if(booking.getBookingDate()==null)
              System.out.println("Object is Null");
            else
            {
            Member mem = booking.getMember();
            Court court = booking.getCourt();
            court.addBooking(booking);
            mem.addBooking(booking); 
        }
            }
            catch(Exception e)
            {
                String str = "Issue :"+e;  
            }
        }
    }
    
}
    
    
    