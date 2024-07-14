package com.mycompany.app.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;

public class Product {

    static database DB;

    Product()
    {
        //ReadProduct(filepath);

    }
     
    public static class CARDp{
    int     index;
    String  description;
    float  price;
    int     quantity;              // Количество
    boolean isWholesale;           // оптовый
   
    CARDp(int index,String description,float  price,int quantity,boolean isWholesale)
    {
        this.index=index;
        this.description=description;
        this.price=price;
        this.quantity=quantity;
        this.isWholesale=isWholesale;
    }
   };

   public static List<CARDp> listProduct=new ArrayList<CARDp>();

   void setDB(database db) {
    Product.DB = db;
  }  

   public  void ReadData(String filepath,Integer signDatabase) throws SQLException
   {
     if(signDatabase==0)
       ReadProductCSV(filepath);
     else
       ReadProductDB();

   }

   //  Чтение карточки с продуктами DataBase
   public void ReadProductDB() throws SQLException
   {
     ResultSet result = null;


      result=DB.getSQL("SELECT description,price,quantity,isWholesale from public.products");

      while (result.next()) {

      int     index=result.getRow();
      String  description=result.getString("description");
      float   price=result.getFloat("price");
      int     quantity=result.getInt("quantity");
      boolean isWholesale=result.getBoolean("isWholesale");
      
      CARDp newCard=new CARDp(index,description,price,quantity,isWholesale);       
      listProduct.add(newCard);
}                
 
      DB.closeConnet();
   }

    //  Чтение карточки с продуктами CSV
    public  void ReadProductCSV(String filepath)
    {
      boolean isWholesale;

         try  (BufferedReader br= new BufferedReader(new FileReader(filepath)))
         {
          String line;
          while ((line = br.readLine()) != null) {
              String[] values = line.split(";");
              
              for(int i=0;i<values.length;i++)
              values[i]=values[i].trim();
              
              if(values[4]=="+") isWholesale=true;
              else isWholesale=false;

              CARDp newCard=new CARDp(
              Integer.parseInt(values[0]),
              values[1],
              Float.parseFloat(values[2]),
              Integer.parseInt(values[3]),
              isWholesale);

              listProduct.add(newCard);
          }
         }
         catch (IOException e) {
          throw new RuntimeException("Не удалось прочитать файл 'discountCards.csv'", e);
      }

      
      return ;
     }
    

}
