
/**
 * Write a description of class Start here.
 *
 * @author Lakshmi Saketh
 * @version 27042018
 */
import java.io.IOException;
import java.io.FileNotFoundException;
public class Start
{


    public static void main(String[] args)
    {
        Club sportsClub = new Club("Sports Club");
        UserInterface consoleApp = new UserInterface(sportsClub);
        try
        {
          sportsClub.fileReadSports();//read the Sports.txt
          sportsClub.fileReadMembers(sportsClub);//read members.txt
          sportsClub.readBookings(sportsClub);//read bookings.txt
        }
        catch(IOException e)
        {
          e.printStackTrace();
        }
        consoleApp.run();
        try 
        {
            FileUtility.writeToFile(sportsClub.fileWriteBookings(),"Bookings.txt");//write the bookings.txt
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
    }


}
