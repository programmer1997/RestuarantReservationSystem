package restaurant;

import java.util.*;
import java.io.*;

/**
 *
 * @author Ajinkya Marathe
 */
/**
 * ***************************************************************************************************
 * /*****************************************************************************************************
 * the following are the functions of this class create new orders add items to
 * the menu delete items from the menu Save menu in a file so that the data is
 * not lost Get menu from the file when the application is started print the
 * invoice
 * *****************************************************************************************************
*****************************************************************************************************
 */
public class Order {

    // Constants for calculating service tax and GST
    private static final double SERVICE_TAX = 0.1;
    private static final double GST = 0.07;

    // Order No takes the contact number of the customer
    private int orderNo;

    /*
    Each of these Arraylists store Fooditem class objects (Object compostion)
    There is a different array for each of the categories.
    The use of ArrayList instead of array makes dynamic allocation possible.
    
     */
    protected static ArrayList<FoodItem> mainCourse = new ArrayList<FoodItem>();
    protected static ArrayList<FoodItem> drinks = new ArrayList<FoodItem>();
    protected static ArrayList<FoodItem> dessert = new ArrayList<FoodItem>();

    /*
       These are Hashtables for storing the respective category orders.
       The keys are the foodId and the elements are the quantity of each fooditem.
    
     */
    Hashtable<Integer, Integer> mainOrder = new Hashtable<Integer, Integer>();
    Hashtable<Integer, Integer> drinkOrder = new Hashtable<Integer, Integer>();
    Hashtable<Integer, Integer> dessertOrder = new Hashtable<Integer, Integer>();

    // Constructor
    public Order() {
        orderNo = 0;

    }

    /**
     * **********************************************************************************************************
     * This function is used to create an order by the staff and store it to a
     * file so that it can be printed later;
     ************************************************************************************************************
     */
    public void createOrder(int customerNo) throws FileNotFoundException {

        this.orderNo = customerNo;

        // All the hashtables are cleared each time an order is taken
        mainOrder.clear();
        drinkOrder.clear();
        dessertOrder.clear();
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);

        Scanner read;
        System.out.println("Press 1 for Main menu,2 for Drinks and 3 for Desserts");
        int choice;
        int quantity;
        // The while loop and the switch structure used to insert orders in the mainOrder,drinks and desert array.
        while ((choice = sc.nextInt()) != 0) {
            switch (choice) {
                case 1:
                    // Display menu from the array list
                    for (int i = 0; i < mainCourse.size(); i++) {
                        System.out.println((i + 1) + " " + mainCourse.get(i).getFoodName());
                    }

                    // Takes mainMenu order
                    while ((choice = sc1.nextInt()) != 0) {
                        System.out.println("enter the quantity");
                        quantity = sc1.nextInt();
                        mainOrder.put(choice, quantity);
                        System.out.println("Press the number of your choice(press 0 to exit)");
                    }
                    break;
                case 2:
                    // Display menu from the array list
                    for (int i = 0; i < drinks.size(); i++) {
                        System.out.println((i + 1) + " " + drinks.get(i).getFoodName());
                    }
                    // Takes Drinks order
                    System.out.println("Press the number of your choice(press 0 to exit)");
                    while ((choice = sc2.nextInt()) != 0) {
                        System.out.println("enter the quantity");
                        quantity = sc2.nextInt();
                        drinkOrder.put(choice, quantity);
                        System.out.println("Press the number of your choice(press 0 to exit)");
                    }

                    break;
                case 3:
                    // Display menu from the array list
                    for (int i = 0; i < dessert.size(); i++) {
                        System.out.println((i + 1) + " " + dessert.get(i).getFoodName());
                    }
                    // Takes Dessert order
                    while ((choice = sc3.nextInt()) != 0) {
                        System.out.println("enter the quantity");
                        quantity = sc3.nextInt();
                        drinkOrder.put(choice, quantity);
                        System.out.println("Press the number of your choice(press 0 to go back)");

                    }
                    break;

            }
            System.out.println("Press 1 for Main menu,2 for Drinks and 3 for Desserts(Press 0 to Exit)");

        }
        /*
        Calls the viewOrder function to print the order created.
         */
        viewOrder();

        // Prints OrderNo and timestamp
        PrintWriter write = new PrintWriter("order" + orderNo + ".txt");
        Calendar cal = Calendar.getInstance();
        write.println("Order no" + orderNo);
        write.println(cal.getTime());

        // Prints information about staff
        Scanner staffReader = new Scanner(new FileReader("staff.txt"));
        if (cal.get(Calendar.HOUR_OF_DAY) < RRPSS.time2) {
            for (int i = 1; i <= 3; i++) {
                write.println(staffReader.nextLine());
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                staffReader.nextLine();
            }
            while (staffReader.hasNext()) {
                write.println(staffReader.nextLine());
            }

        }
        staffReader.close();

        /*
        This snippet writes the order to a file to store it till the customer finishes the meal. The stored file is later printed as the 
        invoice. Here order from all the hashtables is printed to the file. The total and tax calculations have also been done 
         */
        write.println();
        write.println();
        write.println("ITEM" + "\t\t QUANTITY" + "\tPRICE" + "\tTOTAL PRICE");
        Iterator it1 = mainOrder.keySet().iterator();
        Iterator it2 = drinkOrder.keySet().iterator();
        Iterator it3 = dessertOrder.keySet().iterator();
        double sum = 0;
        while (it1.hasNext()) {
            int param = (Integer) (it1.next())-1;
            write.print(mainCourse.get(param).getFoodName());
            quantity = mainOrder.get(param+1);
            mainCourse.get(param).incrDayQuantity(quantity);
            mainCourse.get(param).incrMonthQuantity(quantity);
            write.print("\t  " + quantity);
            int price = (mainCourse.get(param)).getPrice();
            write.print("\t\t$" + price);
            sum = sum + price * quantity;
            write.println("\t  $" + price * quantity);

        }
        while (it2.hasNext()) {
            int param = (Integer) (it2.next())-1;
            write.print(drinks.get(param).getFoodName());
            quantity = drinkOrder.get(param+1);
            drinks.get(param).incrDayQuantity(quantity);
            drinks.get(param).incrMonthQuantity(quantity);
            write.print("\t  " + quantity);
            int price = (drinks.get(param)).getPrice();
            write.print("\t\t$" + price);
            sum = sum + price * quantity;
            write.println("\t  $" + price * quantity);

        }
        while (it3.hasNext()) {
            int param = (Integer) (it3.next())-1;
            write.print(dessert.get(param).getFoodName());
            quantity = dessertOrder.get(param+1);
            dessert.get(param).incrDayQuantity(quantity);
            dessert.get(param).incrMonthQuantity(quantity);
            write.print("\t  " + quantity);
            int price = (dessert.get(param)).getPrice();
            write.print("\t\t$" + price);
            sum = sum + price * quantity;
            write.println("\t  $" + price * quantity);

        }
        write.println("Total" + "\t\t\t\t\t  $" + sum);

        write.println("Service Charge\t\t\t\t$" + sum * SERVICE_TAX);
        sum = sum * (1 + SERVICE_TAX);

        write.println("GST\t\t\t\t     $" + sum);
        sum = sum * (1 + GST);

        write.println("Grand Total\t\t\t\t$");
        write.printf("Grand Total\t\t\t\t$%.2f", sum);
        write.println();
        write.println("------------------------Thank you. visit again--------------------------------------");
        write.close();

    }

    /**
     * ***************************************************************************************************
     * This function prints the invoice already stored in the file. Later it
     * deletes the file after the invoice has been printed.
    *****************************************************************************************************
     */
    public void printInvoice(int orderNo) throws FileNotFoundException, IOException {
        Scanner read = new Scanner(new FileReader("order" + orderNo + ".txt"));
        for(int i=1;i<50;i++)
        {
            System.out.println("");
        }
        while (read.hasNext()) {
            System.out.println(read.nextLine());
        }
        read.close();
        File file = new File("order" + orderNo + ".txt");
        file.delete();
        RRPSS.object.checkBooking(orderNo);

    }

    /**
     * ***************************************************************************************************
     * This function is to add items into the menu. new objects are added into
     * the respective array lists.
    *****************************************************************************************************
     */
    public void addItem() throws IOException {
        int choice;
        String name;
        int price;
        PrintWriter menuWriter;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc0 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Please enter 1 for adding main order or promotional package item, 2 for drinks, 3 for dessert (0 to Exit)");
        choice = sc0.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the item");
                    name = sc1.nextLine();
                    System.out.println("Enter the price of the item");
                    price = Integer.parseInt(sc1.nextLine());
                    FoodItem newObj1 = new FoodItem(name, mainCourse.size() + 1, price);
                    mainCourse.add(newObj1);
                    break;
                case 2:
                    System.out.println("Enter the name of the item");
                    name = sc2.nextLine();
                    System.out.println("Enter the price of the item");
                    price = Integer.parseInt(sc2.nextLine());
                    FoodItem newObj2 = new FoodItem(name, drinks.size() + 1, price);
                    drinks.add(newObj2);
                    break;
                case 3:
                    System.out.println("Enter the name of the item");
                    name = sc3.nextLine();
                    System.out.println("Enter the price of the item");
                    price = Integer.parseInt(sc3.nextLine());
                    FoodItem newObj3 = new FoodItem(name, dessert.size() + 1, price);
                    dessert.add(newObj3);
                    break;

            }
            System.out.println("Please enter 1 for adding main menu item, 2 for drinks, 3 for dessert (0 to Exit)");
            choice = sc0.nextInt();
        }

    }

    /**
     * ***************************************************************************************************
     * This function is to delete items from the menu. new objects are deleted
     * from the respective array lists.
    *****************************************************************************************************
     */

    public void deleteItem() throws FileNotFoundException {

        System.out.println("Enter for 1 to delete from main menu, 2 from drinks menu, 3 from dessert menu");
        int choice;
        Scanner sc = new Scanner(System.in);

        label:
        if ((choice = sc.nextInt()) == 1) {

            System.out.println("Enter the no of item you want to delete");
            choice = sc.nextInt();
            mainCourse.remove(choice - 1);

        } else if (choice == 2) {

            System.out.println("Enter the no of item you want to delete");
            choice = sc.nextInt();
            drinks.remove(choice - 1);

        } else if (choice == 3) {

            System.out.println("Enter the no of item you want to delete");
            choice = sc.nextInt();
            dessert.remove(choice - 1);

        } else {
            System.out.println("Invalid choice. Enter again");
            break label;
        }
    }

    /**
     * ***************************************************************************************************
     * Every time the program stops execution, all the data from the program is
     * washed out. But we need to store the menu objects which have already been
     * created. So we write them to the file. So that they can be retrieved each
     * time the program is started. Files SaveMain, SaveDrinks and SaveDessert
     * are used to save the name, food Id and price one below the other for each
     * object
    *****************************************************************************************************
     */
    public void saveMenu() throws IOException {

        PrintWriter saveMenu;
        saveMenu = new PrintWriter("SaveMain.txt");
        for (int i = 0; i < mainCourse.size(); i++) {
            saveMenu.println(mainCourse.get(i).getFoodName());
            saveMenu.println(mainCourse.get(i).getFoodId());
            saveMenu.println(mainCourse.get(i).getPrice());
        }
        saveMenu.close();

        saveMenu = new PrintWriter("SaveDrinks.txt");
        for (int i = 0; i < drinks.size(); i++) {
            saveMenu.println(drinks.get(i).getFoodName());
            saveMenu.println(drinks.get(i).getFoodId());
            saveMenu.println(drinks.get(i).getPrice());
        }
        saveMenu.close();
        saveMenu = new PrintWriter("SaveDessert.txt");
        for (int i = 0; i < dessert.size(); i++) {
            saveMenu.println(dessert.get(i).getFoodName());
            saveMenu.println(dessert.get(i).getFoodId());
            saveMenu.println(dessert.get(i).getPrice());
        }
        saveMenu.close();

    }

    /**
     * ***************************************************************************************************
     * We retrieve the saved objects from the files and put them into the
     * respective ArrayLists which are the data structures used for storing
     * menus.
    *****************************************************************************************************
     */
    public void getMenu() throws FileNotFoundException {

        try {
            Scanner menuRead = new Scanner(new FileReader("SaveMain.txt"));
            while (menuRead.hasNext()) {
                mainCourse.add(new FoodItem(menuRead.nextLine(), Integer.parseInt(menuRead.nextLine()), Integer.parseInt(menuRead.nextLine())));
            }
            menuRead.close();

            Scanner menuRead2 = new Scanner(new FileReader("SaveDrinks.txt"));
            while (menuRead2.hasNext()) {
                mainCourse.add(new FoodItem(menuRead2.nextLine(), Integer.parseInt(menuRead2.nextLine()), Integer.parseInt(menuRead2.nextLine())));
            }
            menuRead.close();

            Scanner menuRead3 = new Scanner(new FileReader("SaveDessert.txt"));
            while (menuRead3.hasNext()) {
                mainCourse.add(new FoodItem(menuRead3.nextLine(), Integer.parseInt(menuRead3.nextLine()), Integer.parseInt(menuRead3.nextLine())));
            }
            menuRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("System Error");
            System.out.println("Shutting down");
            System.exit(0);
        }

    }

    /**
     * ***************************************************************************************************
     * This function is used to view the order created so that the user can
     * confirm. It also calls another method to delete some items from the
     * object This method is called by the CreateOrder function
    *****************************************************************************************************
     */
    public void viewOrder() {
        Scanner sc = new Scanner(System.in);
        Iterator viewOrder = mainOrder.keySet().iterator();
        Integer param;
        for(int i=1;i<50;i++)
        {
            System.out.println("");
        }
        System.out.println("Item\t\tQuantity");
        if (mainOrder.isEmpty() == false) {
            while (viewOrder.hasNext()) {
                param = (Integer) viewOrder.next() - 1;
                System.out.println(mainCourse.get(param).getFoodName() + "\t" + mainOrder.get(param+1));

            }
        }
        if (drinks.isEmpty() == false) {
            viewOrder = drinkOrder.keySet().iterator();

            while (viewOrder.hasNext()) {
                param = (Integer) viewOrder.next()-1;
                System.out.println(drinks.get(param).getFoodName() + "\t" + drinkOrder.get(param+1));
            }
        }

        if (dessert.isEmpty() == false) {

            viewOrder = dessertOrder.keySet().iterator();
            while (viewOrder.hasNext()) {
                param = (Integer) viewOrder.next()-1;
                System.out.println(dessert.get(param).getFoodName()+ "\t" + dessertOrder.get(param+1));
            }
        }
        label:
        System.out.println("Press 1 to confirm, press 2 to delete");
        int choice;
        label:
        if ((choice = sc.nextInt()) == 1) {
            return;
        } else if (choice == 2) {
            deleteOrder();

        } else {
            System.out.println("Invalid input.Enter again");
            break label;
        }

    }

    /**
     * ***************************************************************************************************
     * This function gives the user the chance to delete a specific order before
     * confirming it. This method is called by the viewOrder function
    *****************************************************************************************************
     */
    public void deleteOrder() {
        Scanner sc = new Scanner(System.in);
        int choice;
        int no;
        System.out.println("Press 1 to delete from main menu. 2 from drinks and 3 from dessert");
        label:
        if ((choice = sc.nextInt()) == 1) {
            System.out.println("Enter the item No to delete");
            System.out.println("Enter the item No to delete (Press 0 to exit)");
            while ((no = sc.nextInt()) != 0) {
                mainOrder.remove(no);

            }

        } else if (choice == 2) {

            System.out.println("Enter the item No to delete (Press 0 to exit)");
            while ((no = sc.nextInt()) != 0) {
                drinkOrder.remove(no);

            }

        } else if (choice == 3) {
            System.out.println("Enter the item No to delete");
            System.out.println("Enter the item No to delete (Press 0 to exit)");
            while ((no = sc.nextInt()) != 0) {
                dessertOrder.remove(no);

            }

        } else {
            System.out.println("Invalid input");
            break label;

        }
    }

}
