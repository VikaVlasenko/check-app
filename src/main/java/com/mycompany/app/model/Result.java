package com.mycompany.app.model;
//package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Result {

  public static Integer discountCard,balanceDebitCard;
  public static Map<Integer, Integer> listIn = new HashMap<>();   

  public Result(int nom,String [] spispar,String filepath) throws IOException
  {

    ReadPar(spispar);

    Discount mDiscount =new Discount(filepath+"discountCards.csv");
    Product  mProduct  =new Product(filepath+"products.csv");

    //int cnt=Product.listProduct.size();

    Check check=new Check();
    Check.CheckFormer(mDiscount,mProduct,listIn,discountCard);
    Check.saveCSV(filepath);
    

  }

  //  Список входных данных    id  - quantity    -   discountCard   - balanceDebitCard
  // Список товара и его количества пришедшее в коммандной строке

   private static void ReadPar(String [] spispar)
   {
  
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

    }
    return ;
   }

}
  