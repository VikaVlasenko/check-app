package com.mycompany.app.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.sql.*;
//import java.sql.Connection;
//import java.sql.SQLException;

public class Product {


    Product(String filepath)
    {
        ReadProduct(filepath);

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

    //  Чтение карточки с продуктами CSV
    public static void ReadProduct(String filepath)
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
