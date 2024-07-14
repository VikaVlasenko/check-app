package com.mycompany.app.model;
//package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Result {

  public static Integer signDatabase;     //  если 1 - читать из Базы Данных
  public static Integer discountCard,balanceDebitCard;
  public static Map<Integer, Integer> listIn = new HashMap<>();   // Список входных параметров

  public static database DB;


  public Result(int nom,String [] spispar,String filepath) throws IOException, SQLException
  {

    ReadPar(spispar);

    DB=new database();

    Discount mDiscount =new Discount();
    mDiscount.setDB(DB);   
    Discount.ReadData(filepath+"discountCards.csv",signDatabase);


    Product  mProduct  =new Product();
    mProduct.setDB(DB);
    mProduct.ReadData(filepath+"products.csv",signDatabase);

    //int cnt=Product.listProduct.size();

    @SuppressWarnings("unused")
    Check check=new Check();
    check.setDB(DB);
    Check.CheckFormer(mDiscount,mProduct,listIn,discountCard);
    if(signDatabase==0)
      Check.saveCSV(filepath);
    else
      Check.saveDB();
    

  }

  //  Список входных данных    id  - quantity    -   discountCard   - balanceDebitCard
  // Список товара и его количества пришедшее в коммандной строке

   private static void ReadPar(String [] spispar)
   {
  
    signDatabase=0;
    //  "3-1 2-1 3-2"
    for (String arg : spispar)  {
      if(arg.contains("-"))
      {
        String [] spId=arg.split(" ");

        for(String sid: spId)  {
          String [] spT=sid.split("-");

          int index= Integer.parseInt(spT[0]);
          int count= Integer.parseInt(spT[1]);

          if(listIn.containsKey(index))  // Проверяем есть ли такой товар в перечне. Если есть суммируем с предыдущим
          {
            for (Map.Entry<Integer, Integer> entry : listIn.entrySet()) {
              if(entry.getKey()==index)
              {
                 int value=entry.getValue()+index;
                 entry.setValue(value);
              }             
            }
          }else{
            listIn.put(index, count);
          }
        }      
      }
        if(arg.contains("discountCard="))
        discountCard=Integer.parseInt(arg.substring("discountCard=".length()));

        if(arg.contains("balanceDebitCard="))
         balanceDebitCard=Integer.parseInt(arg.substring("balanceDebitCard=".length()));

        if(arg.contains("PostGree")) 
          signDatabase=1;
    }
    return ;
   }

}
  