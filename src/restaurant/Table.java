package restaurant;

import java.util.*;
import java.io.*;

/**
 *
 * @author Ajinkya Marathe
 */


 /*****************************************************************************************************
   /*****************************************************************************************************
  This class represents a single table in the restaurant.
  This table has a tableId, seats and other attributes. 
  It also keeps the information of who sits at the table and his name, contact no
    ******************************************************************************************************
******************************************************************************************************/

public class Table {

    private int tableId;
    private int seats;
    private String customerName;
    private int customerNo;
    private boolean occupied;
    private boolean reserved;
    private Calendar time = Calendar.getInstance();
    private Scanner sc = new Scanner(System.in);

    // constructor
    public Table(int tableId, int seats) {
        this.seats = seats;
        this.tableId = tableId;
        customerName = "";
        customerNo = 0;
        occupied = false;
        reserved = false;
        Date test=time.getTime();
        time.setTime(test);

    }
/*
    the occupied and reserved boolean variables are used to indicate the conditoin of the table
    */
    public boolean isOccupied() {
        return occupied;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
/*
    This is the time for which the table has been reserved
    This time is later incremented by half an hour to check whether the reservation has expired
    */
    public Calendar gettime()
    {
        return time;
    }
    public void settime(Calendar time)
    {
        this.time=time;
    }
    public int getTableId() {
        return tableId;
    }

    
    /*************************************************************************
    This function books the specific table for a specific slot on a specific day
    Sets the table as reserved
    For quick reference saves the contact number of the customer with the reservation details like the date,timeSlot and table no
    *************************************************************************/
    public void book(String customerName, int customerNo, Calendar time) throws IOException {
        this.customerName = customerName;
        this.customerNo = customerNo;
        this.time=time;
        
        reserved = true;
        System.out.println("table no." + tableId + " booked");
        int identifier;
        
        if(tableId<=10){
            if((tableId%5)==0) identifier=5;
            else identifier=tableId%5;
        }
        else{
            if((tableId%10)==0) identifier=10;
            else identifier=tableId%10;
        }
        int slot = time.get(Calendar.HOUR_OF_DAY) < RRPSS.time2 ? 1 : 2;
        

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.txt", true)));
        pw.println(customerNo);
        pw.println(slot);
        pw.println(time.get(Calendar.DATE));
        pw.println(seats);
        pw.println(identifier);
        pw.close();
    }

    
    
     /*************************************************************************
   This function checks whether the reservation is still valid
   The reservation may be terminated because the people came late 
   * reservation is terminated after half an hour 
    *************************************************************************/
    
    
    public void check() {
        
        System.out.println("Press '1' to avail booking and '2' to cancel and 3 to checkout");
        int choice = sc.nextInt();
        switch (choice) {

            case 1:
                Calendar now = Calendar.getInstance();
                Date date= now.getTime();
                now.setTime(date);
                Calendar expiryTime=time;
                 
                 expiryTime.add(Calendar.MINUTE,30);
                if (now.before(time)) {
                    System.out.println("Cannot Proceed. Reservation scheduled for"+date);
                } 
                else if(now.after(expiryTime)) {
                    reserved = false;
                    System.out.println("Your Reservation has expired");

                }
                else{
                    occupied = true;
                    System.out.println("Please proceed to table no " + tableId);

                }
             break;
            case 2:
            case 3:releaseTable();
                            
                
                
        }

    }
    
    
    
     /*************************************************************************
   This function releases the table because the reservation was canceled or the people have finished dining
   Reserved and occupied are both set to false
    *************************************************************************/
    public void releaseTable()
    {
        occupied = false;
                    reserved = false;
                    customerName="";
                    customerNo=0;
                    System.out.println("Table Released");
        
    }

   
}
