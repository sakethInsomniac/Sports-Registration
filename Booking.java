import java.time.*;
import java.util.*;

public class Booking implements Comparable<Booking>
{
    LocalDate bookingDate;
    Member bookedBy;
    Court bookedFor;
    LocalTime startTime;
    LocalTime endTime ;
    long bookingId;
    

    public Booking(long bookingId, 
                   LocalDate bookingDate, 
                   LocalTime startTime,
                   LocalTime endTime,
                   Member bookedBy,
                   Court bookedFor)
    {
            this.bookingDate = bookingDate;
            this.startTime = startTime;
            this.endTime = endTime;
            this.bookedFor = bookedFor;  
            this.bookedBy = bookedBy;
            this.bookingId=bookingId;
    }
    
    /**
     * parameterised constructor  
     * @param bookingData of string and club objj
     * 
     */
    public Booking(String bookingData,Club club) 
    {
        String list[] = bookingData.split(",");
        this.bookingDate = LocalDate.parse(list[0].trim());
        this.startTime = LocalTime.parse(list[1].trim());
        this.endTime = LocalTime.parse(list[2].trim());
        this.bookedFor = (club.searchCourt(Integer.parseInt(list[3].trim())));
        this.bookedBy = (club.searchMember(Integer.parseInt(list[4])));

     }  
  
     
    /**
     * this function used for getting booking Time
     * @return start Time of data typetime 
     * 
     */
    public LocalTime getBookingTime()
    {
        return startTime;
    }
    
    /**
     * this function used for getting the court object
     * @return court object
     * 
     */
    public Court getCourt()
    {
        return bookedFor;
    }   
    
    /**
     * this function used for getting booking ID
     * @return booking ID
     * 
     */
    public long getbookingID()
    {
        return bookingId;
    }   
    
    /**
     * Set method for booking ID
     * 
     */
    public void setBookingID()
    {
        this.bookingId=bookingId;
    }
    
    /**
    * this function is for getting the booking Date
    * @return returns the booking Date of type Date
    * 
    */   
    public LocalDate getBookingDate()
    {
        return bookingDate;
    }
     /*
     * this function is used for getting booking End time
     * @return end time of datatype time
     * 
     */
    public LocalTime getBookingEndTime()
    {
        return endTime;
    }
    
    
    /**
     * This function OverTime is used for checking the lape of time
     * @param local time, duration
     * @ return boolean
     */
    public boolean overTime(LocalTime time, int duration)
    {
        DateUtility du=new DateUtility();
        duration=(int)(du.timeBetweenDateTimes(startTime,endTime));
        if((time.isAfter(startTime)&&(time.isAfter(startTime.plusMinutes(duration))||time.equals(startTime.plusMinutes(duration))))||(time.isBefore(startTime)&&(time.plusMinutes(duration).isBefore(startTime)||time.plusMinutes(duration).equals(startTime))))
            {               
                return false;
            }       
            return true;
        
    }

    
    
    /*
     * this function is used to save the bookings made
     * @return String returns the values as a string
     * 
     */
    public String storeDetails()
    {
        String bookingDetails="";
        try
        {
            bookingDetails = bookingDate.toString()+","+startTime.toString()+","+bookedFor.getCourtId()+","+bookedBy.getMemberID()+","+endTime.toString();  
            return bookingDetails;         
        }
        catch(Exception e)
        {
           System.out.println("Error :" + e);
        }
        return bookingDetails; 
        
    }
    
    /*
     * this function helps to get the member details
     * @return Member of member Datatype
     */
    public Member getMember()
    {
        return bookedBy;
    }
    
 
        
    /*
     * the to string function is used to display the variables of the class.
     * @return String this function returns the variables as a string
     */
    public String toString()
    {
        return "\tCourt Identity Number : "+bookedFor.getCourtId()+"\tBooking Date : "+bookingDate+ "\t Booking Time : "+startTime.toString() +"\tSlot End Time : "+ endTime.toString() +"mins.";
    }

    /*
     * this helps to cpmpare the
     * @return int returns 1 or 0 based on the values being compared to.
     * 
     */
    public int compareTo(Booking b)
    {
        LocalDate loc = b.getBookingDate();
        
        return this.bookingDate.compareTo(loc);
    }
    
    /**
     * returns member information from id
     *
     * @param number requested member id
     * @param club club
     * @return member information from user Id
     */
    public static Member getuserDateMember(int number, Club clubObj) {
        ArrayList<Member> memberListObj = clubObj.getMemberList();
        Member member = new Member();
        for (int i = 0; i < memberListObj.size(); i++) {
            Member memberOne = memberListObj.get(i);
            if (memberOne.getMemberID()==number) {
                member = memberOne;
            }
        }
        return member;
    }
    
}
