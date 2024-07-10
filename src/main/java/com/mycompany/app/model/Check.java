package com.mycompany.app.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mycompany.app.model.Product.CARDp;

public class Check {
   
    public static List<ItemCheck> list=new ArrayList<>();

    public static class ItemCheck
    {
        int qty=0;
        String description="";
        float price=0;
        boolean isWholesale=false;

        ItemCheck()
        {

        }
        ItemCheck(int a, String b, float pp, boolean s )
        {
            qty=a;
            description=b;
            price=pp;
            isWholesale=s;
        }
       
    }

    public Check(){
        
    }

    //  Добавление товара в чек
    public static void addItem(String descr, float price, int quantity, boolean isWholesale)
    {
        
        ItemCheck item= new ItemCheck(quantity, descr, price, isWholesale );
        list.add(item);
    }

    //  Формирование чека 
    public static void CheckFormer(Discount discount,Product product, Map<Integer,Integer> listIn,Integer discontIn)
    {
        float disc;

        disc=Discount.GedDiscont(discontIn);

        //  Проход по listIn
        for(int k: listIn.keySet()) 
            for(CARDp i: Product.listProduct )
                if(i.index==k)
                {
                  float nPrice=i.price;
                  if(disc==0) disc=2;

                  nPrice=i.price - i.price * (disc/100);
                  addItem(i.description, nPrice, i.quantity, i.isWholesale);
                  //System.out.println("name="+i.description+"\t price="+nPrice+"\t");
                }


    }

    

    public static void saveCSV(String filepath)  throws IOException 
    {

        try {
            FileWriter writer = new FileWriter(filepath+"result.csv");

            Date date = new Date();

            
            writer.write(date.toString()+"\r");
            for(ItemCheck i: list)
            {
                String str=i.description+";"+i.qty+";"+i.price+";\r";
                writer.write(str);

            }
            writer.close();

            } catch (IOException e) {
                    System.out.println("Ошибка при записи в файл");
                    e.printStackTrace();
            }
    }


}
