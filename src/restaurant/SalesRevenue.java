/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.util.*;
import java.io.*;
import java.text.*;


/**
 *
 * @author Ajinkya
 */



 /*************************************************************************
   This function is used for 
   * Saving the monthly and daily consumption of a specific foodItem because all the values are lost when the application is closed
   * Displays the daily display
   * Displays the monthly display
    *************************************************************************/
public class SalesRevenue {
    
    Calendar cal=Calendar.getInstance();
    Date now;
    
    public void displayDayRevenue()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the day, month and year in that order. Press enter after every entry");
         Calendar cal=new GregorianCalendar(sc.nextInt(),sc.nextInt(),sc.nextInt());
         File file=new File("DaySales"+cal.get(Calendar.DATE)+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+".txt");
         int sum=0;
         System.out.println("Day Sales Report "+cal.get(Calendar.DATE)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR));
         System.out.println("Item\tquantity\tTotal");
                
         
         
             for(int i=0;i<Order.mainCourse.size();i++)
             {
                 int quant=Order.mainCourse.get(i).getDayQuantity();
                 int price=Order.mainCourse.get(i).getPrice();
                 System.out.println( Order.mainCourse.get(i).getFoodName()+"\t"+quant+"\t"+quant*price);
                 sum=sum+(price*quant);
             }
             for(int i=0;i<Order.drinks.size();i++)
             {
                 int quant=Order.drinks.get(i).getDayQuantity();
                 int price=Order.drinks.get(i).getPrice();
                 System.out.println( Order.drinks.get(i).getFoodName()+"\t"+quant+"\t"+quant*price);
                 sum=sum+(price*quant);
             }
             for(int i=0;i<Order.dessert.size();i++)
             {
                 int quant=Order.dessert.get(i).getDayQuantity();
                 int price=Order.dessert.get(i).getPrice();
                 System.out.println( Order.dessert.get(i).getFoodName()+"\t"+quant+"\t"+quant*price);
                 sum=sum+(price*quant);
             }
             System.out.println("Total\t\t"+sum);
             
         
         
        
         
        
        
        
        
    }
    
    public void displayMonthRevenue()
    {
         Scanner sc=new Scanner(System.in);
        System.out.println("Enter the  month and year in that order. Press enter after every entry");
         Calendar cal=new GregorianCalendar(sc.nextInt(),sc.nextInt(),0);
         File file=new File("MonthlySales"+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+".txt");
         int sum=0;
         System.out.println("Month Sales Report "+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR));
         System.out.println("Item\tquantity\tTotal");
                
        
             for(int i=0;i<Order.mainCourse.size();i++)
             {
                 int quant=Order.mainCourse.get(i).getMonthQuantity();
                 int price=Order.mainCourse.get(i).getPrice();
                 System.out.println( Order.mainCourse.get(i).getFoodName()+"\t"+quant+"\t"+quant*price);
                 sum=sum+(price*quant);
             }
             for(int i=0;i<Order.drinks.size();i++)
             {
                 int quant=Order.drinks.get(i).getMonthQuantity();
                 int price=Order.drinks.get(i).getPrice();
                 System.out.println( Order.drinks.get(i).getFoodName()+"\t"+quant+"\t"+quant*price);
                 sum=sum+(price*quant);
             }
             for(int i=0;i<Order.dessert.size();i++)
             {
                 int quant=Order.dessert.get(i).getMonthQuantity();
                 int price=Order.dessert.get(i).getPrice();
                 System.out.println( Order.dessert.get(i).getFoodName()+"\t"+quant+"\t"+quant*price);
                 sum=sum+(price*quant);
             }
             System.out.println("Total\t\t"+sum);
             
        
        
    }
    
    public  void saveSales() throws IOException{
      PrintWriter sales=new PrintWriter("DaySales"+cal.get(Calendar.DATE)+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+".txt") ; 
      PrintWriter sales2=new PrintWriter("MonthlySales"+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+".txt");
      for(int i=0;i<Order.mainCourse.size();i++)
      {
          sales.println(Order.mainCourse.get(i).getDayQuantity());
           sales.println(Order.mainCourse.get(i).getMonthQuantity());
         
      }
      sales.close();
      sales2.close();
     
      
      
    }
    
    
    public void getSales() throws FileNotFoundException{
        try{
        Scanner sc=new Scanner(new FileReader("DaySales"+cal.get(Calendar.DATE)+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+".txt"));
        for(int i=0;i<Order.mainCourse.size();i++)
      {
          Order.mainCourse.get(i).setDayQuantity(Integer.parseInt(sc.nextLine()));
         
      }
        sc.close();
        sc=new Scanner(new FileReader("MonthlySales"+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+".txt"));
         for(int i=0;i<Order.mainCourse.size();i++)
      {
          Order.mainCourse.get(i).setMonthQuantity(Integer.parseInt(sc.nextLine()));
         
      }
         sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("System intitliazation failed");
            
            
            
        }
        
    }
    
}
