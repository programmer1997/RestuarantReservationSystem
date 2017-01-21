package restaurant;

import java.io.IOException;
import java.util.*;
import java.text.*;


/*************************************************************************
   VERY IMPORTANT
  This is the main control class of the Application
  It calls all the functions from all the classes which is controlled by a while loop and a switch structure
    *************************************************************************/
public class RRPSS {

    private static Scanner sc;
    private static Scanner se;
    
    
    // these constants are the operating hours of the restaurant 
    protected static final int time1 = 11;
    protected static final int time2 = 15;
    protected static final int time3 = 18;
    protected static final int time4 = 22;
    
    // this objects are needed to call the public functions
    static Order test = new Order();
    static ReservationSystem object = new ReservationSystem();
    static SalesRevenue test_object=new SalesRevenue();
    public static void main(String[] args) throws IOException,ParseException {
        sc = new Scanner(System.in);
        
        TimeSlot object2=new TimeSlot();
        
        Calendar cal = Calendar.getInstance();
        ReservationSystem rs = new ReservationSystem();
        boolean cont = true;
        System.out.println("configuring settings");
        object.getReservations();
        if(cal.get(Calendar.DATE)==1||cal.get(Calendar.HOUR_OF_DAY)<time2) ; 
        else test_object.getSales();
        test.getMenu();
        
        while (cont == true) {

            System.out.println(
                    "=========================\n"
                    + "Welcome to RRPSS \n"
                    + "=========================\n"
                    + "-------------------------\n"
                    + "1:Update menu \n"
                    + "2:Create order \n"
                    + "3:Create reservation \n"
                    + "4:Avail Reservation\n"
                    + "5:Print invoice\n"
                    + "6:Print sales report\n"
                    + "7:Quit\n"
                    + "please enter your option: \n"
                    + "-------------------------"
            );
            int Choice = sc.nextInt();
            int choice2;
            System.out.println("-------------------------");
            switch (Choice) {
                case 1:
                    System.out.println("Press 1 for adding or 2 for deleting item from the menu");
                    label1: if((choice2=sc.nextInt())==1){
                        test.addItem();
                    }
                    else if(choice2==2){
                        test.deleteItem();
                    }
                    else break label1;
                    
                    
                    break;
                case 2:System.out.println("Enter customer no");
                    test.createOrder(sc.nextInt());
                    break;
                case 3:
                    System.out.println("Enter number of people(max 10)");
                    int pax = sc.nextInt();
                    System.out.println("Enter the time details in the order YYYY- MM - DD - HH - MIN_MIN (Press Enter after every entry)");
                    cal = new GregorianCalendar(sc.nextInt(), sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt());
                    object.checkSlotAvailability(pax, cal);
                    
                    break;
                case 4:System.out.println("Enter contact no");
                       object.checkBooking(sc.nextInt());
                    break;
                    

                    
                case 5: test.printInvoice(sc.nextInt());
                    
                    break;
                case 6:System.out.println("Press 1 for Day Revenue and 2 for monthly revenue");
                int choice;
                label:  if((choice=sc.nextInt())==1){
                           test_object.displayDayRevenue();
                       }
                       else if(choice==2){
                           test_object.displayMonthRevenue();
                       }
                       else {
                           System.out.println("Invalid choice. enter again");
                           break label;
                       }
                    
                
                case 7:
                    
                    object.saveReservations();
                    test.saveMenu();
                    test_object.saveSales();
                    System.out.println("Shutting down\n"
                            + "-------------------------");
                    System.exit(0);

                    break;
            }
        }
    }

   
    }


