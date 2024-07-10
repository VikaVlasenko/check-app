package com.mycompany.app.model;
//package model;

import java.util.HashMap;
import java.util.Map;


public class Result {

  public static Integer discountCard,balanceDebitCard;
  public static Map<Integer, Integer> listIn = new HashMap<>();   

  public Result(int nom,String [] spispar,String filepath)
  {

    ReadPar(spispar);

    Discount mDiscount =new Discount(filepath+"discountCards.csv");
    Product  mProduct  =new Product(filepath+"products.csv");

    //int cnt=Product.listProduct.size();

    Check check=new Check();
    Check.CheckFormer(mDiscount,mProduct,listIn,discountCard);
    

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


   


// public  static void  CheckFormerM()
// {
   
//    Check ch=new Check();

//   for(int k: listIn.keySet()) 
//   {
      
//     for(CARDp i: listProduct )
//     {
//       if(i.index==k)
//       {
//         ch.addItem(i.description, i.price, i.quantity, i.isWholesale);
        
//       }
//     }

//   }
 
// return ;
// }

//  public static void  printer(String filepath)
//  { 

//  try {
//                   FileWriter writer = new FileWriter(filepath);
//                   writer.writer();
//                   writer.close();
//           } catch (IOException e) {
//                   System.out.println("Ошибка при записи в файл");
//                   e.printStackTrace();
//               }


    // for(Item i: Check.list)
    // {
    //   String str=i.description+";"+i.qty+";"+i.price+";";
    //   System.out.println(str);
    // }

//  }


}







// for(int key: map.keySet()){
//   if(key==index)
//   bbb=0;    
// }
