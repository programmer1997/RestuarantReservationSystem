package restaurant;

import java.util.*;
import java.io.*;
import java.text.*;

/**
 *
 * @author Ajinkya
 */
/**
 * ***********************************************************************
 * VERY IMPORTANT This function is used to contain timeSlots (AM and PM) of a
 * month Reservations can be made at most a moth prior to the actual
 * reservations Therefore this class stores 62 objects of the TimeSlot Class It
 * contains 31 each for each shift
 *
 *
 ************ Circular rotation of days******************* Only 31 days are
 * needed as we shift the earlier days to the next month
 *
 * For example if today is 4th Jan, then the fist three days 1,2,3 can be used
 * for the first three days of Feb
 *
 *
 * As days pass, the earlier days are allocated to the next month 
    ************************************************************************
 */
public class ReservationSystem {

    /*
    Each array contains 31 instances of the class Reservation
    for AM and PM slots as bookings can only be done atmost one month in advance.
    As a month advances, the earlier dates turn into the dates of the next month.
     */
    TimeSlot[] AM = new TimeSlot[31];
    TimeSlot[] PM = new TimeSlot[31];

    // constructor 
    public ReservationSystem() {
        for (int i = 0; i < 31; i++) {
            AM[i] = new TimeSlot();
            PM[i] = new TimeSlot();
        }
    }

    /*
    Checks availability for a particular slot on a particular day in an year
     */

    public void checkSlotAvailability(int pax, Calendar time) throws IOException {
        Calendar cal = Calendar.getInstance();
        Date now;
        now = cal.getTime();
        cal.setTime(now);
        cal.add(Calendar.MONTH, 1);
        int limit = time.get(Calendar.HOUR_OF_DAY);

        // The condition checks whether the reservation is within a month and during the operating hours of the restuarant.
        if  (time.after(cal)||(limit <= RRPSS.time1) || ((limit > RRPSS.time2) && (limit < RRPSS.time3)) || (limit >= RRPSS.time4)){
            System.out.println("Booking can only be made a month prior to the date and has to be in the operating hours ");

        } else /*
           It sends the request to the respective timeslot array (AM or PM ) depending on the time.
           time1, time2, time3, time4 are static final variables which define the boundaries of the operating hours of the Restaurant.
         */ if (time.get(Calendar.HOUR_OF_DAY) < RRPSS.time2) {
            AM[time.get(Calendar.DAY_OF_MONTH) - 1].checkTableAvailability(pax, time);
        } else {
            PM[time.get(Calendar.DAY_OF_MONTH) - 1].checkTableAvailability(pax, time);
        }
    }

    // Check the already booked reservation or cancel it 
    public void checkBooking(int customerNo) throws IOException {
        Calendar cal = Calendar.getInstance();
        Date date1 = cal.getTime();
        cal.setTime(date1);
        int slot = 0;
        int date = 0;
        int seats = 0;
        int identifier = 0;
        Scanner rd = new Scanner(new FileReader("contacts.txt"));
        while (rd.hasNext()) {
            if (rd.nextLine().equals(Integer.toString(customerNo))) {
                slot = rd.nextInt();
                date = rd.nextInt();
                seats = rd.nextInt();
                identifier = rd.nextInt();

                break;
            } else {
                rd.nextLine();
                rd.nextLine();
                rd.nextLine();
                rd.nextLine();

            }
        }
        rd.close();

        if (slot == 1) {
            AM[date].checkTable(seats, identifier);
        } else if (slot == 2) {
            PM[date].checkTable(seats, identifier);
        } else {
            System.out.println("no such reservation");
            return;
        }

    }

    /**
     * ***********************************************************************
     * These two files are used for saving the reservations of each table to the
     * files and get them back next time 
    ************************************************************************
     */
    public void getReservations() throws IOException, ParseException {
        int i;
        TimeSlot.rd = new Scanner(new BufferedReader(new FileReader("bookings.txt")));

        for (i = 0; i < 31; i++) {
            AM[i].getBookings();
            PM[i].getBookings();
        }
        TimeSlot.rd.close();

    }

    public void saveReservations() throws IOException {
        int i;
        TimeSlot.pw = new PrintWriter("bookings.txt");

        for (i = 0; i < 31; i++) {
            AM[i].saveBookings();
            PM[i].saveBookings();
        }
        TimeSlot.pw.close();

    }

}
