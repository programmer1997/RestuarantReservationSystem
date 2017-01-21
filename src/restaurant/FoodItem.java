/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

/**
 *
 * @author Ajinkya
 */

 /*****************************************************************************************************
   /*****************************************************************************************************
 This class represents a single foodItem including a main menu item, a drinks item and a dessert item.
 So it has all the attributes of a foodItem such as name , foodId and price....

* *********** Very Important*************
Every foodItem object stores the daily and monthly items sold in the store.
Every time a customer buy an object, the daily and monthly quantity variables are incremented
Later those values are saved to the file while closing the application.
This is how the application helps to generate the monthly and daily revenue report.
    ******************************************************************************************************
******************************************************************************************************/
public class FoodItem {
    private int foodId;
    private String foodName;
    private int price;
    private int dayQuantity;
    private int monthQuantity;
    
    
    public FoodItem(String foodName,int foodId, int price){
        this.foodName=foodName;
        this.foodId=foodId;
        this.price=price;
        dayQuantity=0;
        monthQuantity=0;
    }
    
    public String getFoodName()
    {
        return foodName;
    }
    public int getPrice()
    {
        return price;
    }
    public int getFoodId()
    {
        return foodId;
    }
        
    
        
    
    public void incrDayQuantity(int quantity)
    {
        
            
        
        dayQuantity+=quantity;
        
    }
    
    public void incrMonthQuantity(int quantity)
    {
        
            
        
        monthQuantity+=quantity;
       
    }
   
   public void setDayQuantity(int quantity){
       dayQuantity=quantity;
   }
    
   public int getDayQuantity(){
       return dayQuantity;
   }
   public void setMonthQuantity(int quant){
    monthQuantity=quant;
}
   public int getMonthQuantity(){
       return monthQuantity;
   }
}
