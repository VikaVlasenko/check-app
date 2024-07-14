package com.mycompany.app.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Discount {
    
    static database DB;
    
    public static class CARD{
      int index;
      int number;
      int percent;

      CARD(int index,int number,int percent)
      {
          this.index=index;
          this.number=number;
          this.percent=percent;
      }
    };


  public static ArrayList<CARD>  listDiscont=new ArrayList<CARD>();    
  //----------------------------------------------------------------
  Discount()
  {  

  }
 //---------------------------------------------------------------------- 
  void setDB(database db) {
    Discount.DB = db;
  }  
 //---------------------------------------------------------------------- 
  public static void ReadData(String filepath,Integer signDatabase) throws SQLException
  {
    if(signDatabase==0)
      ReadDiscountCSV(filepath);
    else
      ReadDiscountDB();

  }

  //  Читает из базы данных дисконтные карты  
  public static void ReadDiscountDB() throws SQLException
  {
    ResultSet result = null;

    result=database.getSQL("SELECT id,name FROM ctcviewad.users ");

    while (result.next()) {
      System.out.println("np #" + result.getRow()
              + "\t Номер  #" + result.getInt("id")
              + "\t Имя " + result.getString("name"));
             
    }                

    database.closeConnet();

  }

  //  Читает из файла дисконтные карты
  public static  void ReadDiscountCSV(String filepath)
  {

    Integer index=0;
    Integer number=0;
    Integer percent=0;

     try  (BufferedReader br= new BufferedReader(new FileReader(filepath)))
       {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            
            for(int i=0;i<values.length;i++)
            values[i]=values[i].trim();

            index=Integer.parseInt(values[0]);
            number=Integer.parseInt(values[1]);
            percent=Integer.parseInt(values[2]);


            CARD newCard=new CARD(index,number,percent);
            listDiscont.add(newCard);
        }
       }
       catch (IOException e) {
        throw new RuntimeException("Не удалось прочитать файл 'discountCards.csv'", e);
    }
    return ;
  }

  //   Возвращает скидку по дисконту
  public static int GedDiscont(int  discountCard) 
  {

    for(CARD item: listDiscont)
    {
      if(item.number==discountCard)
        return item.percent;
    }
    return 0;
  }


}
