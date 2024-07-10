package com.mycompany.app.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Discount {
    
    
    
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

  
  Discount(String filepath)
  {
    ReadDiscount(filepath);
  }


  public static ArrayList<CARD>  listDiscont=new ArrayList<CARD>();

  //  Читает из файла дисконтные карты
  public static  void ReadDiscount(String filepath)
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
