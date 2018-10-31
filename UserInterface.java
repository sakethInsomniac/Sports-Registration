import java.time.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Provides date utility methods for converting dates from string to 
 * Java 8 date formats, and vice versa.
 * 
 * @Lakshmi Saketh
 * @04262018
 */
public class UserInterface
{

    private Club sportsClub;//variable: sports Club
    private int duration;// variable int duration
    private Scanner sc = new Scanner(System.in);// scanner method
    
    /**
     * Parameterised constructor
     */
    public UserInterface(Club sportsClub)
    {
        this.sportsClub = sportsClub;
    }

    public void run()
    {
        while(true)
            switch (menu() )
            {
                    case 1:
                        showAvailableCourts();
                        break;
                    case 2:
                        makeBooking();
                        break;
                    case 3:
                        showMemberBookings();
                        break;
                     case 4:
                        showCourtBookings();
                        break;
                      case 5:
                        deleteBooking();
                        break;
                     case 6:
                        return;
                     default:
                        System.out.println ( "Invalid option" );
                        break;
            }
    }

    private int menu()
    {
             System.out.println("|-------------------------------------------------|");
             System.out.println("| 1 - Show Available Courts                       |");
             System.out.println("| 2 - Make Booking for Member                     |");
             System.out.println("| 3 - Show Member Bookings                        |");
             System.out.println("| 4 - Show Court Bookings                         |");
             System.out.println("| 5 - Delete Booking                              |");
             System.out.println("| 6 - Exit                                        |" );
             System.out.println("|-------------------------------------------------|");
             System.out.println("Select your option (enter a selection number): ");
             int option = sc.nextInt();
             sc.nextLine();
             return  option;
     }

/**
 * This function used to show the avilable courts in the system
 * @return void
 */
    private void showAvailableCourts()
    {
        Scanner in=new Scanner(System.in);
        try
        {
         
      
            Scanner sc= new Scanner(System.in); 
            System.out.println("Enter the Sport Name u want to Play :");
            System.out.println("Basketball\nBadminton" );
            String sportName = sc.nextLine();
            Sport s = sportsClub.findSport(sportName);  
            if(s == null)
            {
                System.out.println("No Sport Found");
            }
            else
            {
                System.out.println("=========Available List==========");
            }
            for(Court co : s.getCourtList())
            {
                System.out.println("Court number :" + co.getCourtId());
            } 
             System.out.println("=====================================");
        }
    
    catch(Exception e)
    {
        System.out.println("Exception"+e);
    }
}
        
    /** 
     * This function helps to make booking for the court by taking the time user id as input
     * 
     */
    private void makeBooking()
    {
        Scanner sc= new Scanner(System.in);
        Date dt = new Date();
        System.out.println("Enter Registered member ID: ");
         int memberID = sc.nextInt();
        if(sportsClub.memberIdentity(memberID))//check id exist or not
        {
            Member mem = sportsClub.searchMember(memberID); // get the obj of that member based on ID
            if(mem != null)
            {
            if(mem.getFinancial())
            {
                
                
                System.out.println("Enter the Sport Name u want to Play :");
                System.out.println("Basketball\nBadminton" );
                Scanner sc1= new Scanner(System.in);
                String userSportName=sc1.nextLine();

                if(mem.statusSport(userSportName))
                {
                    Scanner sc2= new Scanner(System.in);
                    System.out.println("Enter the date u want to play in this format dd-MM-yyyy : ");   
                    String stringData=sc2.nextLine();
                    LocalDate date = DateUtility.convertDate(stringData);
                    System.out.println("Please enter the Start Time "); 
                    stringData=sc2.nextLine();
                    LocalTime startTime = DateUtility.convertTime(stringData);
                     System.out.println("Please enter the End Time"); 
                     stringData=sc2.nextLine();
                    LocalTime endTime= DateUtility.convertTime(stringData);
                    
                    DateUtility du=new DateUtility();
                    duration=(int)(du.timeBetweenDateTimes(startTime,endTime));//calculats to get the  duration              
                    Sport s = sportsClub.findSport(userSportName);

                    if(s.timeCheck(mem.getTotalDuration(userSportName, date)+ duration))//total duration checking (without exceeding limits
                    {   

                        if(sportsClub.validateTime(date, startTime, duration)==1)//check the pass condition for the given statement
                        {   

                            System.out.println("=========Available List==========");
                            for(Court courtObj : s.getAvailableCourts(date, startTime, duration))
                            {
                                System.out.println("\tCourt number " + courtObj.getCourtId() + " is available");
                            } 
                             System.out.println("=====================================");
                             Scanner in= new Scanner(System.in);
                             System.out.println("Which Court would you like to play in?");
                            int courtNumber = in.nextInt();

                            Court courtObj = null;
                            for(Court c : s.getCourtList())
                            {
                                if(c.getCourtId() == courtNumber)
                                {
                                    courtObj = c;
                                }
                            }

                            Booking book = new Booking(dt.getTime(),date,startTime,endTime,mem,courtObj);
                            mem.addBooking(book);
                            courtObj.addBooking(book);
                            System.out.println("Successfully booked the court!");

                        }
                        else if(sportsClub.validateTime(date, startTime, duration)==5)
                            System.out.println("Booking done between 8-11pm");
                        else if(sportsClub.validateTime(date, startTime, duration)==4)
                            System.out.println("only 7 days Advance is available");
                        else if(sportsClub.validateTime(date, startTime, duration )==2)
                            System.out.println("Booking cannot be done");
                        else if(sportsClub.validateTime(date, startTime, duration)==1)
                            System.out.println("Bookings cant be advanved by years");
                        else if(sportsClub.validateTime(date, startTime, duration) < 0)
                            System.out.println("Bookings cant be done for previously!");
                    }
                    else
                        System.out.println("Sorry  no slots available for sort"+userSportName);
                }
                else
                {
                    System.out.println("Sorry! sport is not is not available.");

                }

            }
            else
                System.out.println("Finances are poor!");
        }
        }
        else
            System.out.println("Sorry you are not in any club!");

    }//endclass

    /**
     * This function helps to show the bookigs done by the member
     */
    public void showMemberBookings()
    {
         try
        {
            System.out.println("Please enter the memeber ID");
            int memberId =sc.nextInt();
            Member mem = sportsClub.searchMember(memberId);   
            
            if(mem==null || mem.getBookings()==null)
            {
                 System.out.println("Sorry! Member is not found.");
                }
            else
            {
                for(Booking bookingObj : mem.getBookings())
                {
                     System.out.println("Booking made by "+mem.getMemberName() +" for " + bookingObj.getBookingDate() + " at " + bookingObj.getBookingTime() + " for " + bookingObj.getBookingEndTime() + " minutes on Court number " + bookingObj.getCourt().getCourtId());

                }
                if(mem.getBookings().size()==0)
                    System.out.println("Sorry! Currebtly no bookings done by the member ");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error"+e);
        }

    }//end class

 
    /**
     * It helps to see the bookings done by the court
     */
    private void showCourtBookings()
    {
        ArrayList<Court> courtList = new ArrayList<Court>();
        ArrayList<Booking> bookingList = new ArrayList<Booking>();
        for(Sport sObj : sportsClub.sportList)
        {
            System.out.println("Displaying Courts for : " + sObj.getSportName());
            courtList = sObj.getCourtList();
            for(Court cObj : courtList)
            {           
                if(cObj.getCourtBookings().size()==0)
                    System.out.println("Booking are not yet started for sport :" + sObj.getSportName() + " on Court : " + cObj.getCourtId());
                else
                {    

                    Collections.sort(cObj.getCourtBookings());
                    System.out.println(cObj.getCourtBookings().toString());

                }               
            }
        }       
    }//End class
    



      /**
     * this function is used to delete a booking
     * 
     * 
     */
    private void deleteBooking() 
    {
        Scanner memberID=new Scanner(System.in);
                         System.out.println("Please enter your member number: ");
                            int memberId  = memberID.nextInt();
    
        Member mem = sportsClub.searchMember(memberId);
        if(mem==null || mem.getBookings()==null)
        {
             System.out.println("Sorry! There are no members with the given ID. ");

           

        }
        else
        {
            for(Booking b : mem.getBookings())//Displays the member details n bookings
            {
                System.out.println("Booking Id : " + b.hashCode()+ " Booking made by "+mem.getMemberName() +" for " + b.getBookingDate() + " at " + b.getBookingTime() + " for " + b.getBookingEndTime() + " minutes on Court number " + b.getCourt().getCourtId());

            }
          
            if(mem.getBookings().size()!=0)
            {
                        Scanner inputID=new Scanner(System.in);
                         System.out.println("Enter Booking ID: ");
                        int input = inputID.nextInt();
                Iterator<Booking> itr = mem.getBookings().iterator();


                while(itr.hasNext())//delettion starts based on the ID
                {           
                    if(itr.next().hashCode() == input)
                    {
                        itr.remove();
                        for(String str : mem.getSportsPlayed())
                        {
                            Sport sportObj = sportsClub.findSport(str);

                            ArrayList<Court> itrCourt = sportObj.getCourtList();
                            for(Court c : itrCourt)//finds the court
                            {

                                Iterator<Booking> itrBooking = c.getCourtBookings().iterator();
                                while(itrBooking.hasNext())//helps to get the booking object
                                {
                                    if(itrBooking.next().hashCode() == input)
                                    {
                                        itrBooking.remove();//removes the booking object
                                        System.out.println("Deleted Successfully");
                                    }
                                }                       
                            }                   
                        }               
                    }
                }
            }
        }



    }    //endclass




} // end class
