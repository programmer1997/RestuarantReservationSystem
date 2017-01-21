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
 * VERY IMPORTANT This function represents each timeSlot (AM or PM) Each
 * timeSlot contains 30 tables *****So this class contains arrays of tables of
 * different sizes(30 in total) (Object Composition)********* When we reach the
 * object of this class, we re already inside a specific slot
 *
 ************************************************************************
 */
public class TimeSlot {

    private Scanner sc = new Scanner(System.in);

    // arrays of table objects for each slot 
    private Table[] table10 = new Table[5];
    private Table[] table8 = new Table[5];
    private Table[] table4 = new Table[10];
    private Table[] table2 = new Table[10];
    protected static PrintWriter pw;

    protected static Scanner rd;

// Initialize the tables with number of seats
    public TimeSlot() {

        for (int i = 0; i < 5; i++) {
            table10[i] = new Table(i + 1, 10);

            table8[i] = new Table(6 + i, 8);
        }
        for (int i = 0; i < 10; i++) {
            table4[i] = new Table(11 + i, 4);
            table2[i] = new Table(21 + i, 2);
        }
    }

    // CHecking availability if a table of the required seat is available
    //******* Note how the program allocates the proper table even if number of people is not exactly to the size 
    public void checkTableAvailability(int pax, Calendar time) throws IOException {

        int i;
        switch (pax) {
            case 10:
            case 9:
                for (i = 0; i < 5; i++) {
                    if (table10[i].isReserved() == false) {
                        System.out.println("Enter Name and contact no");
                        table10[i].book(sc.nextLine(), sc.nextInt(), time);
                        return;
                    }
                }

                System.out.println("Table not available");
                return;

            case 8:
            case 7:
            case 6:
            case 5:

                for (i = 0; i < 5; i++) {
                    if (table8[i].isReserved() == false) {
                        System.out.println("Enter Name and contact no");
                        table8[i].book(sc.nextLine(), sc.nextInt(), time);
                        return;
                    }
                }

                System.out.println("Table not available");
                return;

            case 4:
            case 3:
                for (i = 0; i < 10; i++) {
                    if (table4[i].isReserved() == false) {
                        System.out.println("Enter Name and contact no");
                        table4[i].book(sc.nextLine(), sc.nextInt(), time);
                        return;
                    }
                }

                System.out.println("Table not available");
                return;
            case 2:
            case 1:
                for (i = 0; i < 10; i++) {
                    if (table2[i].isReserved() == false) {
                        System.out.println("Enter Name and contact no");
                        table2[i].book(sc.nextLine(), sc.nextInt(), time);
                        return;
                    }
                }

                System.out.println("Table not available");
                return;
            default:
                System.out.println("Number of pax shold be between 1 and 10");
        }
    }

    // This function checks for the reservation of a particular table
    public void checkTable(int seats, int identifier) {

        switch (seats) {

            case 2:
                table2[identifier - 1].check();
                break;

            case 4:
                table4[identifier - 1].check();
                break;

            case 8:
                table8[identifier - 1].check();
                break;
            case 0:
                table10[identifier - 1].check();
                break;
        }
    }

    /*
    As said earlier, when the program ends. all the reservations will be lost and hence we must store them to the files
    The saveBookings and GetBookings functions are used to store the bookings and get them back from files
     */
    public void getBookings() throws IOException, ParseException {

        File file = new File("bookings.txt");
        if (file.length() == 0) {
            return;
        }

        int i;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);

        Calendar cal = Calendar.getInstance();
        for (i = 0; i < 10; i++) {
            table2[i].setReserved(Boolean.parseBoolean(TimeSlot.rd.nextLine()));

            table2[i].gettime().set(Calendar.YEAR, Integer.parseInt(rd.nextLine()));
            table2[i].gettime().set(Calendar.MONTH, Integer.parseInt(rd.nextLine()));
            table2[i].gettime().set(Calendar.DATE, Integer.parseInt(rd.nextLine()));
            table2[i].gettime().set(Calendar.HOUR_OF_DAY, Integer.parseInt(rd.nextLine()));
            table2[i].gettime().set(Calendar.MINUTE, Integer.parseInt(rd.nextLine()));

            table4[i].setReserved(Boolean.parseBoolean(TimeSlot.rd.nextLine()));

            table4[i].gettime().set(Calendar.YEAR, Integer.parseInt(rd.nextLine()));
            table4[i].gettime().set(Calendar.MONTH, Integer.parseInt(rd.nextLine()));
            table4[i].gettime().set(Calendar.DATE, Integer.parseInt(rd.nextLine()));
            table4[i].gettime().set(Calendar.HOUR_OF_DAY, Integer.parseInt(rd.nextLine()));
            table4[i].gettime().set(Calendar.MINUTE, Integer.parseInt(rd.nextLine()));

        }
        for (i = 0; i < 5; i++) {
            table8[i].setReserved(Boolean.parseBoolean(TimeSlot.rd.nextLine()));

            table8[i].gettime().set(Calendar.YEAR, Integer.parseInt(rd.nextLine()));
            table8[i].gettime().set(Calendar.MONTH, Integer.parseInt(rd.nextLine()));
            table8[i].gettime().set(Calendar.DATE, Integer.parseInt(rd.nextLine()));
            table8[i].gettime().set(Calendar.HOUR_OF_DAY, Integer.parseInt(rd.nextLine()));
            table8[i].gettime().set(Calendar.MINUTE, Integer.parseInt(rd.nextLine()));

            table10[i].setReserved(Boolean.parseBoolean(TimeSlot.rd.nextLine()));

            table10[i].gettime().set(Calendar.YEAR, Integer.parseInt(rd.nextLine()));
            table10[i].gettime().set(Calendar.MONTH, Integer.parseInt(rd.nextLine()));
            table10[i].gettime().set(Calendar.DATE, Integer.parseInt(rd.nextLine()));
            table10[i].gettime().set(Calendar.HOUR_OF_DAY, Integer.parseInt(rd.nextLine()));
            table10[i].gettime().set(Calendar.MINUTE, Integer.parseInt(rd.nextLine()));
        }

    }

    public void saveBookings() throws IOException {

        int i;
        Calendar now = Calendar.getInstance();
        Date temp = now.getTime();
        now.setTime(temp);
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);
        int year, month, date, hour, minutes;

        for (i = 0; i < 10; i++) {
            if (table2[i].gettime().after(now) && table2[i].isReserved() == true) {
                TimeSlot.pw.println(table2[i].isReserved());
            } else {
                TimeSlot.pw.println(false);
            }

            year = table2[i].gettime().get(Calendar.YEAR);
            month = table2[i].gettime().get(Calendar.MONTH);
            date = table2[i].gettime().get(Calendar.DATE);
            hour = table2[i].gettime().get(Calendar.HOUR_OF_DAY);
            minutes = table2[i].gettime().get(Calendar.MINUTE);

            TimeSlot.pw.println(year);
            TimeSlot.pw.println(month);
            TimeSlot.pw.println(date);
            TimeSlot.pw.println(hour);
            TimeSlot.pw.println(minutes);

            if (table4[i].gettime().after(now) && table4[i].isReserved() == true) {
                TimeSlot.pw.println(table4[i].isReserved());
            } else {
                TimeSlot.pw.println(false);
            }

            year = table4[i].gettime().get(Calendar.YEAR);
            month = table4[i].gettime().get(Calendar.MONTH);
            date = table4[i].gettime().get(Calendar.DATE);
            hour = table4[i].gettime().get(Calendar.HOUR_OF_DAY);
            minutes = table4[i].gettime().get(Calendar.MINUTE);

            TimeSlot.pw.println(year);
            TimeSlot.pw.println(month);
            TimeSlot.pw.println(date);
            TimeSlot.pw.println(hour);
            TimeSlot.pw.println(minutes);

        }
        for (i = 0; i < 5; i++) {
            if (table8[i].gettime().after(now) && table8[i].isReserved() == true) {
                TimeSlot.pw.println(table8[i].isReserved());
            } else {
                TimeSlot.pw.println(false);
            }

            year = table8[i].gettime().get(Calendar.YEAR);
            month = table8[i].gettime().get(Calendar.MONTH);
            date = table8[i].gettime().get(Calendar.DATE);
            hour = table8[i].gettime().get(Calendar.HOUR_OF_DAY);
            minutes = table8[i].gettime().get(Calendar.MINUTE);

            TimeSlot.pw.println(year);
            TimeSlot.pw.println(month);
            TimeSlot.pw.println(date);
            TimeSlot.pw.println(hour);
            TimeSlot.pw.println(minutes);

            if (table10[i].gettime().after(now) && table10[i].isReserved() == true) {
                TimeSlot.pw.println(table10[i].isReserved());
            } else {
                TimeSlot.pw.println(false);
            }

            year = table10[i].gettime().get(Calendar.YEAR);
            month = table10[i].gettime().get(Calendar.MONTH);
            date = table10[i].gettime().get(Calendar.DATE);
            hour = table10[i].gettime().get(Calendar.HOUR_OF_DAY);
            minutes = table10[i].gettime().get(Calendar.MINUTE);

            TimeSlot.pw.println(year);
            TimeSlot.pw.println(month);
            TimeSlot.pw.println(date);
            TimeSlot.pw.println(hour);
            TimeSlot.pw.println(minutes);

        }

    }
}
